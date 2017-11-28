package com.imall.iportal.core.main.service.impl;


import com.imall.commons.base.global.ResGlobal;
import com.imall.commons.base.idfs.FileSystemEngine;
import com.imall.commons.base.interceptor.BusinessException;
import com.imall.commons.base.service.impl.AbstractBaseService;
import com.imall.commons.base.util.CommonUtil;
import com.imall.commons.base.util.DateTimeUtils;
import com.imall.commons.dicts.BoolCodeEnum;
import com.imall.commons.dicts.FileTypeCodeEnum;
import com.imall.iportal.core.main.commons.ServiceManager;
import com.imall.iportal.core.main.entity.SysFileLib;
import com.imall.iportal.core.main.repository.SysFileLibRepository;
import com.imall.iportal.core.main.service.SysFileLibService;
import com.imall.iportal.core.main.vo.SysFileLibSearchParamVo;
import com.imall.iportal.core.main.vo.SysFileLibVo;
import com.imall.iportal.core.main.vo.UploadFileVo;
import com.imall.iportal.core.shop.commons.ResGlobalExt;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * T_PT_SYS_FILE_LIB【系统 文件 库】(服务层实现)
 * @author by imall core generator
 * @version 1.0.0
 */
@Service
@Transactional(readOnly = true)
public class SysFileLibServiceImpl extends AbstractBaseService<SysFileLib, Long> implements SysFileLibService {

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	private String imgSpec;

	@Value("${app.img.default.spec:100X100}")
	public void setImgSpec(String imgSpec) {
		this.imgSpec = imgSpec;
	}

	@SuppressWarnings("unused")
	private SysFileLibRepository getSysFileLibRepository() {
		return (SysFileLibRepository) baseRepository;
	}

	@Override
	@Transactional
	public List<String> saveFiles(List<UploadFileVo> fileList, Long categoryId,Long orgId){
		String[] specs = imgSpec.split(",");
		List<String> fileLibIdList = new ArrayList<>();
		for(UploadFileVo fileVo : fileList){
			File file = fileVo.getFile();
			String fileName = fileVo.getFileNm();
			String fileType = fileName.substring(fileName.lastIndexOf('.'));
			String fileCode = fileType.toUpperCase(Locale.US);

			try {
				/*保存图片*/
				String fileId = FileSystemEngine.saveLocalFile(file,orgId);
				/*生成小图*/
				if(getfileTypeCode(fileCode).equals(FileTypeCodeEnum.IMAGE.toCode())){
					if(specs.length>0){
						for(String spec : specs){
							String smallFileId = FileSystemEngine.genPicsReturnFileId(fileId,spec,false,false);
							fileLibIdList.add(smallFileId);
						}
					}
				}
				/*保存文件记录*/
				insertSysFileLib(fileId, fileName.substring(0,fileName.lastIndexOf('.')), file.getTotalSpace(), categoryId, fileCode,orgId);
				fileLibIdList.add(fileId);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		/*同步文件到其他文件服务器*/
		//FTPClientTemplate.syncFile(fileLibIdList); TODO 同步代码先注释掉
		return fileLibIdList;
	}

	@Override
	public Page<SysFileLibVo> findFileLib(Pageable pageable, SysFileLibSearchParamVo paramVo, Long orgId) {
		Page<SysFileLib> result = getSysFileLibRepository().query(pageable,paramVo, orgId);
		List<SysFileLibVo> fileLibVoList = new ArrayList<>();
		for(SysFileLib sysFileLib : result.getContent()){
			SysFileLibVo fileLibVo = new SysFileLibVo();
			CommonUtil.copyProperties(sysFileLib, fileLibVo);
			fileLibVo.setId(sysFileLib.getId());
			fileLibVo.setFileDate(DateTimeUtils.convertDateToString(sysFileLib.getFileDate()));
			fileLibVo.setSysFileUrl(FileSystemEngine.getFileSystem().getUrl(sysFileLib.getSysFileId()));
			try {
				if(sysFileLib.getFileTypeCode().equals(FileTypeCodeEnum.IMAGE.toCode())){
					fileLibVo.setSmallFileUrl(FileSystemEngine.genPicsReturnImgUrl(sysFileLib.getSysFileId(),"160X160",false,false));
				}
			}catch (Exception e){
				fileLibVoList.add(fileLibVo);
				continue;
			}
			fileLibVoList.add(fileLibVo);
		}
		return new PageImpl<SysFileLibVo>(fileLibVoList,pageable,result.getTotalElements());
	}

	@Override
	@Transactional
	public void renameFileName(Long id, String fileNm, Long orgId) {
		SysFileLib sysFileLib  = findOne(id);
		if(sysFileLib == null || !sysFileLib.getOrgId().equals(orgId)) {
			String message = ServiceManager.sysExceptionCodeService.getMessageByCode(ResGlobal.COMMON_OBJECT_NO_FOUND);
			throw new BusinessException(ResGlobal.COMMON_OBJECT_NO_FOUND, String.format(message, new Object[]{"文件"}));

		}
		sysFileLib.setFileNm(fileNm);
		save(sysFileLib);
	}

	@Transactional
	@Override
	public void delete(List<Long> fileLibIds, Long orgId) {
		for(Long fileLibId : fileLibIds) {
			SysFileLib fileLib = findOne(fileLibId);
			if(fileLib == null || !fileLib.getOrgId().equals(orgId)) {
				continue;
			}
			delete(fileLib);
		}
	}

	@Override
	public String downloadFiles(List<Long> fileLibIds, Long orgId) {
		String uuid = UUID.randomUUID().toString();
		String sysFileId = uuid+".zip";

		File zipFile = FileSystemEngine.getFileSystem().getLocalFile(sysFileId);
		InputStream input = null;
		try {
			ZipOutputStream zipOut = new ZipOutputStream(new FileOutputStream(zipFile));
			for(Long fileLibId : fileLibIds){
				SysFileLib sysFileLib = findOne(fileLibId);
				if(sysFileLib == null || !sysFileLib.getOrgId().equals(orgId)) {
					continue;
				}
				File file = FileSystemEngine.getFileSystem().getLocalFile(sysFileLib.getSysFileId());
				input = new FileInputStream(file);
				zipOut.putNextEntry(new ZipEntry(file.getName()));

				int temp = 0;
				while ((temp = input.read()) != -1) {
					zipOut.write(temp);
				}
				input.close();
			}
			zipOut.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
		return FileSystemEngine.getFileSystem().getUrl(sysFileId);
	}

	@Transactional
	@Override
	public void changeFileCategory(List<Long> fileLibIds, Long fileCategoryId, Long orgId) {
		for(Long fileLibId : fileLibIds){
			SysFileLib sysFileLib = findOne(fileLibId);
			if(sysFileLib == null || !sysFileLib.getOrgId().equals(orgId)) {
				continue;
			}
			sysFileLib.setFileCategoryId(fileCategoryId);
			save(sysFileLib);
		}
	}

	private void insertSysFileLib(String sysFileId, String fileName, Long fileSize, Long categoryId, String fileCode, Long curOrgId) throws BusinessException {
		SysFileLib sysFileLib = new SysFileLib();
		sysFileLib.setOrgId(curOrgId);
		sysFileLib.setSysFileId(sysFileId);
		sysFileLib.setFileNm(fileName);
		sysFileLib.setFileCategoryId(categoryId);
		sysFileLib.setFileSize(fileSize);
		sysFileLib.setFileDate(new Date());
		sysFileLib.setFileTypeCode(getfileTypeCode(fileCode));
		sysFileLib.setIsDelete(BoolCodeEnum.NO.toCode());
		save(sysFileLib);
	}


	private String getfileTypeCode(String fileCode) {
		String result = FileTypeCodeEnum.OTHER.toCode();
		String[] codeImages = {".JPG", ".GIF", ".PNG", ".JPEG"};
		if (retunCode(fileCode, codeImages)) {
			result = FileTypeCodeEnum.IMAGE.toCode();
			return result;
		}
		String[] codeOffices = {".DOC", ".PPT", ".XLS", ".DOCX", ".XLSX", ".PPTX", ".XLSX"};
		if (retunCode(fileCode, codeOffices)) {
			result = FileTypeCodeEnum.OFFICE.toCode();
			return result;
		}
		String[] codeTexts = {".TXT", ".HTML"};
		if (retunCode(fileCode, codeTexts)) {
			result = FileTypeCodeEnum.TEXT.toCode();
			return result;
		}
		String[] codeSounds = {".MP3", ".WAV", ".WMA", ".MIDI"};
		if (retunCode(fileCode, codeSounds)) {
			result = FileTypeCodeEnum.SOUND.toCode();
			return result;
		}
		String[] codeSwfs = {".SWF"};
		if (retunCode(fileCode, codeSwfs)) {
			result = FileTypeCodeEnum.FLASH.toCode();
			return result;
		}
		String[] codeVideos = {".AVI", ".MPEG", ".MPG", ".FLV", ".MKV", ".3GP", ".ASF", ".MOV", ".MP4", ".RMVB"};
		if (retunCode(fileCode, codeVideos)) {
			result = FileTypeCodeEnum.VIDEO.toCode();
			return result;
		}
		return result;
	}

	private boolean retunCode(String fileCode, String[] codes) {
		for (String code : codes) {
			if (fileCode.equals(code)) {
				return true;
			}
		}
		return false;
	}

	@Override
	public void downloadFile(Long id, HttpServletResponse response) {
		SysFileLib sysFileLib = findOne(id);
		if(sysFileLib==null){
			logger.error("对象不存在，fileLibId:" + id);
			String message = ServiceManager.sysExceptionCodeService.getMessageByCode(ResGlobalExt.COMMON_OBJECT_NO_FOUND);
			throw new BusinessException(ResGlobalExt.COMMON_OBJECT_NO_FOUND, String.format(message, new Object[]{"文件"}));
		}

		File file = FileSystemEngine.getFileSystem().getLocalFile(sysFileLib.getSysFileId());
		if(!file.exists()){
			logger.error("文件不存在，fileNm:" + sysFileLib.getFileNm());
			String message = ServiceManager.sysExceptionCodeService.getMessageByCode(ResGlobalExt.COMMON_OBJECT_NO_FOUND);
			throw new BusinessException(ResGlobalExt.COMMON_OBJECT_NO_FOUND, String.format(message, new Object[]{"文件"}));
		}

		FileInputStream fis = null;
		OutputStream os = null;
		try {
			fis = new FileInputStream(file);
			String sysFileId = sysFileLib.getSysFileId();
			String exName = sysFileId.substring(sysFileId.lastIndexOf("."));
			String filename = new String((sysFileLib.getFileNm() + exName).getBytes("GBK"), "ISO-8859-1");
			response.setContentType("application/octet-stream");
			response.addHeader("Content-Disposition", "attachment;filename=" + filename);
			response.addHeader("Content-Length", "" + file.length());

			os = response.getOutputStream();
			byte[] buffer = new byte[1024];
			int temp = 0;
			while ((temp = fis.read(buffer))!=-1){
				os.write(buffer, 0, temp);
			}
			os.flush();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if(fis!=null){
				try {
					fis.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

			if(os!=null){
				try {
					os.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
}