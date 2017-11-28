package com.imall.commons.base.idfs;

import com.imall.commons.base.interceptor.BusinessException;
import com.imall.commons.base.util.CommonUtil;
import com.imall.commons.base.util.ImageUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.Assert;

import java.io.File;
import java.io.IOException;


public class FileSystemEngine {
    private static IFileSystem fileSystem;
    private static IFileKeyGenerator fileKeyGenerator;
    private static String uploadPath;
    private static String fileWebUrl;
    public static final String IMAGE_SPEC_NUM_SPLIT_CHAR = "X";

    public static synchronized IFileSystem getFileSystem() {
        if (fileSystem == null) {
            fileSystem = new LocalFileSystem(uploadPath,fileWebUrl);
        }
        return fileSystem;
    }

    public static String saveLocalFile(File file,Long orgId) throws BusinessException, IOException {
        String fileId = getFileKeyGenerator().create(file,orgId);
        getFileSystem().checkIn(file, fileId);
        //TODO 生成图片后需要进行图片同步
        return fileId;
    }

    /**
     * 根据传入的规格，输出对应规格的图片文件ID
     * @param srcFileId 源文件
     * @param specs 规格
     * @param isReGenPicture 是否重新生成图片
     * @param force 是否强制生成规格图，可能会变形
     * @return  图片文件ID  1/2016/1/8/XXX-XX-XX-X.jpg
     * @throws BusinessException
     */
    public static String genPicsReturnFileId(String srcFileId, String specs, boolean isReGenPicture, boolean force) throws BusinessException {
        if(StringUtils.isBlank(srcFileId)){
            return "";
        }
        Assert.isTrue(specs.contains(IMAGE_SPEC_NUM_SPLIT_CHAR));
        String fileId = CommonUtil.insertFileNameSuffixToUrl(srcFileId, "_" + specs);
        if(FileSystemEngine.getFileSystem().fileExists(fileId)){
            if(!isReGenPicture){
                return fileId;
            }
        }
        String[] values = specs.split(IMAGE_SPEC_NUM_SPLIT_CHAR);
        int width = Integer.parseInt(values[0]);
        int height = Integer.parseInt(values[1]);
        try {
            ImageUtil.genPictures(new int[]{width,height},FileSystemEngine.getFileSystem().getLocalFile(srcFileId),null,force);
            return fileId;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * 根据传入的规格，输出对应规格的图片路径
     * @param srcFileId 源文件
     * @param specs 规格
     * @param isReGenPicture 是否重新生成图片
     * @param force 是否强制生成规格图，可能会变形
     * @return  图片路径  http://127.0.0.1:8080/iportal/upload/1/2016/1/8/XXX-XX-XX-X.jpg
     * @throws BusinessException
     */
    public static String genPicsReturnImgUrl(String srcFileId, String specs, boolean isReGenPicture, boolean force) throws BusinessException {
        if(StringUtils.isBlank(srcFileId)){
            return "";
        }
        //Assert.isTrue(specs.contains(IMAGE_SPEC_NUM_SPLIT_CHAR));
        if(StringUtils.isBlank(specs)){
            return FileSystemEngine.getFileSystem().getUrl(srcFileId);
        }
        //图片存在，且不重新生成，则直接返回图片路径
        String fileId = CommonUtil.insertFileNameSuffixToUrl(srcFileId, "_" + specs);
        if(FileSystemEngine.getFileSystem().fileExists(fileId)){
            if(!isReGenPicture){
              return FileSystemEngine.getFileSystem().getUrl(fileId);
            }
        }
        String[] values = specs.split(IMAGE_SPEC_NUM_SPLIT_CHAR);
        int width = Integer.parseInt(values[0]);
        int height = Integer.parseInt(values[1]);
        try {
            ImageUtil.genPictures(new int[]{width,height},FileSystemEngine.getFileSystem().getLocalFile(srcFileId),null,force);
            return FileSystemEngine.getFileSystem().getUrl(fileId);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static IFileKeyGenerator getFileKeyGenerator() {
        return fileKeyGenerator;
    }

    public void setFileKeyGenerator(IFileKeyGenerator fileKeyGenerator) {
        setFileKeyGen(fileKeyGenerator);
    }

    private static synchronized void setFileKeyGen(IFileKeyGenerator fileKeyGenerator) {
        FileSystemEngine.fileKeyGenerator = fileKeyGenerator;
    }

    public void setUploadPath(String uploadPath) {
        setStaticUploadPath(uploadPath);
    }

    public static void setStaticUploadPath(String uploadPath) {
        FileSystemEngine.uploadPath = uploadPath;
    }


    public void setFileWebUrl(String uploadPath) {
        setStaticFileWebUrl(uploadPath);
    }

    public static void setStaticFileWebUrl(String fileWebUrl) {
        FileSystemEngine.fileWebUrl = fileWebUrl;
    }
}
