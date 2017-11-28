package com.imall.iportal.core.shop.service.impl;


import com.imall.commons.base.interceptor.BusinessException;
import com.imall.commons.base.service.impl.AbstractBaseService;
import com.imall.commons.base.util.CommonUtil;
import com.imall.iportal.core.main.commons.ServiceManager;
import com.imall.iportal.core.shop.commons.ResGlobalExt;
import com.imall.iportal.core.shop.entity.DocInf;
import com.imall.iportal.core.shop.repository.DocInfRepository;
import com.imall.iportal.core.shop.service.DocInfService;
import com.imall.iportal.core.shop.vo.DocInfDetailVo;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * (服务层实现)
 * @author by imall core generator
 * @version 1.0.0
 */
@Service
@Transactional(readOnly = true)
public class DocInfServiceImpl extends AbstractBaseService<DocInf, Long> implements DocInfService {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	private String localPath;

	private String webUrl;

//	@Value("${app.gspInfo.doc.localPath}")
	public void setLocalPath(String localPath) {
		this.localPath = localPath;
	}

//	@Value("${app.gspInfo.doc.webUrl}")
	public void setWebUrl(String webUrl) {
		this.webUrl = webUrl;
	}

	@SuppressWarnings("unused")
    private DocInfRepository getDocInfRepository() {
		return (DocInfRepository) baseRepository;
	}

	@Override
	public List<DocInfDetailVo> searchByDocType(String docType) {
		List<DocInf> docInfList = this.getDocInfRepository().findByDocType(docType);
		List<DocInfDetailVo> docInfDetailVoList = new ArrayList<>();
		for(DocInf docInf : docInfList){
			DocInfDetailVo docInfDetailVo = new DocInfDetailVo();
			CommonUtil.copyProperties(docInf, docInfDetailVo);
			docInfDetailVoList.add(docInfDetailVo);
		}

		return docInfDetailVoList;
	}

	@Override
	public void downloadDoc(Long id, HttpServletResponse response) {
		if(StringUtils.isBlank(this.localPath) || StringUtils.isBlank(this.webUrl)){
			logger.error("请配置GSP资料绝对路径或远程GSP资料存放目录，localPath:" + localPath + ", webUrl:" + webUrl);
			String message = ServiceManager.sysExceptionCodeService.getMessageByCode(ResGlobalExt.COMMON_NOT_EMPTY);
			throw new BusinessException(ResGlobalExt.COMMON_NOT_EMPTY, message);
		}

		DocInf docInf = this.findOne(id);
		if(docInf==null){
			logger.error("文档对象不存在，id:" + id);
			String message = ServiceManager.sysExceptionCodeService.getMessageByCode(ResGlobalExt.COMMON_OBJECT_NO_FOUND);
			throw new BusinessException(ResGlobalExt.COMMON_OBJECT_NO_FOUND, String.format(message, new Object[]{"文件"}));
		}

		File docFile = new File(this.localPath + docInf.getDownloadAddr());
		if(!docFile.exists()){
			logger.error("文档不存在，tableNm:" + docInf.getTableNm());
			String message = ServiceManager.sysExceptionCodeService.getMessageByCode(ResGlobalExt.COMMON_OBJECT_NO_FOUND);
			throw new BusinessException(ResGlobalExt.COMMON_OBJECT_NO_FOUND, String.format(message, new Object[]{"文件"}));
		}

		FileInputStream fis = null;
		OutputStream os = null;
		try {
			fis = new FileInputStream(docFile);

			String exName = docInf.getDownloadAddr().substring(docInf.getDownloadAddr().lastIndexOf("."));
			String filename = new String((docInf.getTableNm() + exName).getBytes("GBK"), "ISO-8859-1");
			response.setContentType("application/octet-stream");
			response.addHeader("Content-Disposition", "attachment;filename=" + filename);
			response.addHeader("Content-Length", "" + docFile.length());
			os = response.getOutputStream();

			byte[] buffer = new byte[1024];
			int temp = 0;
			while ((temp = fis.read(buffer))!=-1){
				os.write(buffer, 0, temp);
			}
			os.flush();
		} catch (FileNotFoundException e) {
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