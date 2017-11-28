package com.imall.commons.base.idfs;

import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * 文件系统接口
 * 通过文件系统接口向文件系统保存或取出文件
 */
public interface IFileSystem {
    /**
     * 获得文件输入流
     * @param fileId 文件ID
     */
    InputStream open(String fileId) ;

    /**
     * 获得文件输出流
     * @param fileId 文件ID
     */
    OutputStream create(String fileId) ;

    /**
     * 删除文件
     * @param fileId 文件ID
     */
    boolean delete(String fileId);

    /**
     * 从文件系统中下载一份拷贝到本地目录
     * @param fileId
     * @param dir 本地目录
     */
    File checkout(String fileId, File dir) ;

    /**
     * 保存本地的文件到文件系统
     * @param file
     * @return 返回文件ID
     * @
     */
    String checkIn(File file, String fileId);

    /**
     * 获取本地文件
     * @param fileId
     * @return
     */
    File getLocalFile(String fileId);

    /**
     * 检查文件系统是否存在文件
     * @param fileId
     */
    boolean fileExists(String fileId);

    /**
     * 获取文件的外部网络地址
     * @param fileId 文件ID
     * @return
     */
    String getUrl(String fileId);

    /**
     * 获取规格图
     * @param fileId
     * @param spec
     * @return
     */
    String getUrl(String fileId, String spec);

    /**
     * 检查远程图片是否存在
     * @param fileId
     * @return
     */
    boolean remoteFileExists(String fileId);
}
