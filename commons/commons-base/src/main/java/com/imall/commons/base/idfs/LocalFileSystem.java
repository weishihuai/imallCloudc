package com.imall.commons.base.idfs;

import com.imall.commons.base.global.ResGlobal;
import com.imall.commons.base.interceptor.BusinessException;
import com.imall.commons.base.util.CommonUtil;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class LocalFileSystem implements IFileSystem {
    private Logger logger = Logger.getLogger(this.getClass());
    private String uploadPath;
    private String fileWebUrl;

    public LocalFileSystem(String uploadPath,String fileWebUrl) {
        if(StringUtils.isNotBlank(uploadPath)){
            this.uploadPath = urlCheck(uploadPath);
        }
        if(StringUtils.isNotBlank(fileWebUrl)){
            this.fileWebUrl = urlCheck(fileWebUrl);
        }

    }

    public InputStream open(String fileId) throws BusinessException {
        try {
            return FileUtils.openInputStream(getLocalFile(fileId));
        } catch (IOException ex) {
            logger.error(ex.getMessage(), ex);
            throw new BusinessException(ResGlobal.ERRORS_IDSF_ALL, ex);
        }
    }

    public OutputStream create(String fileId) throws BusinessException {
        try {
            return FileUtils.openOutputStream(getLocalFile(fileId));
        } catch (IOException ex) {
            logger.error(ex.getMessage(), ex);
            throw new BusinessException(ResGlobal.ERRORS_IDSF_ALL, ex);
        }
    }

    public boolean delete(String fileId) {
        File file = getLocalFile(fileId);
        return file.delete();
    }

    /**
     * 检出文件到本地temp文件夹
     */
    @Override
    public File checkout(String fileId, File dir) throws BusinessException {
        File srcFile = getLocalFile(fileId);
        if(!srcFile.exists()){
        	return null;
        }
        File destFile = null;
        File temDir = null;
        if (dir != null && dir.mkdirs()) {
            temDir = dir;
        }
        if (dir == null) {
            String time = (new SimpleDateFormat("yyyyMMdd")).format(new Date());
            temDir = new File(uploadPath + "temp" + File.separator + time);
        }
        try {
            FileUtils.copyFileToDirectory(srcFile, temDir);
            destFile = new File(temDir, srcFile.getName());
        } catch (IOException ex) {
            logger.error(ex.getMessage(), ex);
            throw new BusinessException(ResGlobal.ERRORS_IDSF_ALL, ex);
        }
        return destFile;
    }

    @Override
    public String checkIn(File file, String fileId) throws BusinessException {
    	OutputStream out = null;
        FileInputStream in = null;
        try {
            out = create(fileId);
            in = FileUtils.openInputStream(file);
            IOUtils.copy(in, out);
            IOUtils.closeQuietly(in);
            IOUtils.closeQuietly(out);
        } catch (IOException ex) {
            logger.error(ex.getMessage(), ex);
            throw new BusinessException(ResGlobal.ERRORS_IDSF_ALL, ex);
        } finally {
            IOUtils.closeQuietly(in);
            IOUtils.closeQuietly(out);
        }
        return fileId;
    }

    public String getUrl(String fileId) {
        return fileWebUrl+ fileId;
    }

    @Override
    public String getUrl(String fileId, String spec) {
        return CommonUtil.insertFileNameSuffixToUrl(fileWebUrl + fileId, "_" + spec);
    }

    @Override
    public File getLocalFile(String fileId) {
        return new File(uploadPath + fileId);
    }

    private File getRemoteFile(String fileId) {
        return new File(fileWebUrl + fileId);
    }

    public String getUploadPath() {
        return uploadPath;
    }

    public void setUploadPath(String uploadPath) {
        this.uploadPath = urlCheck(uploadPath);
    }

    private String urlCheck(String path) {
        if (!path.endsWith("/")) {
            return path + "/";
        }
        return path;
    }

    @Override
	public boolean fileExists(String fileId) {
        File srcFile = getLocalFile(fileId);
        return srcFile.exists();
	}

    @Override
    public boolean remoteFileExists(String fileId) {
        File srcFile = getRemoteFile(fileId);
        return srcFile.exists();
    }


}
