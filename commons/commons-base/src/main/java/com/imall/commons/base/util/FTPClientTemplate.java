package com.imall.commons.base.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.List;

import com.imall.commons.base.idfs.FileSystemEngine;
import com.imall.commons.base.interceptor.BusinessException;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;
import org.apache.log4j.Logger;

/**
 * FTP客户端
 */
public class FTPClientTemplate {

    protected final Logger         log                  = Logger.getLogger(getClass());
    private ThreadLocal<FTPClient> ftpClientThreadLocal = new ThreadLocal<FTPClient>();

    private String                 host;
    private int                    port;
    private String                 username;
    private String                 password;

    private boolean                binaryTransfer       = true;
    private boolean                passiveMode          = true;
    private String                 encoding             = "UTF-8";
    private int                    clientTimeout        = 1000 * 30;

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isBinaryTransfer() {
        return binaryTransfer;
    }

    public void setBinaryTransfer(boolean binaryTransfer) {
        this.binaryTransfer = binaryTransfer;
    }

    public boolean isPassiveMode() {
        return passiveMode;
    }

    public void setPassiveMode(boolean passiveMode) {
        this.passiveMode = passiveMode;
    }

    public String getEncoding() {
        return encoding;
    }

    public void setEncoding(String encoding) {
        this.encoding = encoding;
    }

    public int getClientTimeout() {
        return clientTimeout;
    }

    public void setClientTimeout(int clientTimeout) {
        this.clientTimeout = clientTimeout;
    }

    /**
     * 返回一个FTPClient实例
     */
    private FTPClient getFTPClient(){
        if (ftpClientThreadLocal.get() != null && ftpClientThreadLocal.get().isConnected()) {
            return ftpClientThreadLocal.get();
        } else {
            FTPClient ftpClient = new FTPClient(); //构造一个FtpClient实例
            ftpClient.setControlEncoding(encoding); //设置字符集


            connect(ftpClient); //连接到ftp服务器

            //设置为passive模式
            if (passiveMode) {
                ftpClient.enterLocalPassiveMode();
            }
            setFileType(ftpClient); //设置文件传输类型

            try {
                ftpClient.setSoTimeout(clientTimeout);
            } catch (SocketException e) {
                throw new RuntimeException("Set timeout error.");
            }
            ftpClientThreadLocal.set(ftpClient);
            return ftpClient;
        }
    }

    /**
     * 设置文件传输类型
     * @throws IOException
     */
    private void setFileType(FTPClient ftpClient){
        try {
            if (binaryTransfer) {
                ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE);
            } else {
                ftpClient.setFileType(FTPClient.ASCII_FILE_TYPE);
            }
        } catch (IOException e) {
            throw new RuntimeException("Could not to set file type.");
        }
    }

    /**
     * 连接到ftp服务器
     *
     * @param ftpClient
     * @return 连接成功返回true，否则返回false
     */
    private boolean connect(FTPClient ftpClient){
        try {
            ftpClient.connect(host, port);

            // 连接后检测返回码来校验连接是否成功
            int reply = ftpClient.getReplyCode();

            if (FTPReply.isPositiveCompletion(reply)) {
                //登陆到ftp服务器
                if (ftpClient.login(username, password)) {
                    setFileType(ftpClient);
                    return true;
                }
            } else {
                ftpClient.disconnect();
                throw new RuntimeException("FTP server refused connection.");
            }
        } catch (IOException e) {
            if (ftpClient.isConnected()) {
                try {
                    ftpClient.disconnect(); //断开连接
                } catch (IOException e1) {
                    throw new RuntimeException("Could not disconnect from server.");
                }

            }
            throw new RuntimeException("Could not connect to server.");
        }
        return false;
    }
    
    /**
     * 断开ftp连接
     */
    public void disconnect() {
        try {
            FTPClient ftpClient = getFTPClient();
            ftpClient.logout();
            if (ftpClient.isConnected()) {
                ftpClient.disconnect();
                ftpClient = null;
            }
        } catch (IOException e) {
            throw new RuntimeException("Could not disconnect from server.");
        }
    }

    public boolean mkdir(String pathname){
        return mkdir(pathname, null);
    }

    /**
     * 在ftp服务器端创建目录（不支持一次创建多级目录）
     *
     * 该方法执行完后将自动关闭当前连接
     *
     * @param pathname
     */
    public boolean mkdir(String pathname, String workingDirectory){
        return mkdir(pathname, workingDirectory, true);
    }

    /**
     * 在ftp服务器端创建目录（不支持一次创建多级目录）
     *
     * @param pathname
     * @param autoClose 是否自动关闭当前连接
     */
    public boolean mkdir(String pathname, String workingDirectory, boolean autoClose) {
        try {
            getFTPClient().changeWorkingDirectory(workingDirectory);
            return getFTPClient().makeDirectory(pathname);
        } catch (IOException e) {
            throw new RuntimeException("Could not mkdir.");
        } finally {
            if (autoClose) {
                disconnect(); //断开连接
            }
        }
    }

    /**
     * 创建多级目录
     *
     * @param remotePath
     * @throws IOException
     */
    public boolean makeDirectory(String remotePath, boolean autoClose) throws IOException {
        String[] item = remotePath.split("/");
        String currentPath = "";
        for (int i = 0; i < item.length - 1; i++) {
            currentPath = currentPath + "/" + item[i];
            mkdir(currentPath,null,autoClose);
        }
        return mkdir(remotePath,null,autoClose);
    }

    /**
     * 上传一个本地文件到远程指定文件
     *
     * @param remoteAbsoluteFile 远程文件名(包括完整路径)
     * @param localAbsoluteFile 本地文件名(包括完整路径)
     * @return 成功时，返回true，失败返回false
     */
    public boolean put(String remoteAbsoluteFile, String localAbsoluteFile){
        return put(remoteAbsoluteFile, localAbsoluteFile, true);
    }

    /**
     * 上传一个本地文件到远程指定文件
     *
     * @param remoteAbsoluteFile 远程文件名(包括完整路径)
     * @param localAbsoluteFile 本地文件名(包括完整路径)
     * @param autoClose 是否自动关闭当前连接
     * @return 成功时，返回true，失败返回false
     */
    public boolean put(String remoteAbsoluteFile, String localAbsoluteFile, boolean autoClose)  {
        InputStream input = null;
        try {
            // 处理传输
            input = new FileInputStream(localAbsoluteFile);
            getFTPClient().storeFile(remoteAbsoluteFile, input);
            log.debug("put " + localAbsoluteFile);
            return true;
        } catch (FileNotFoundException e) {
            throw new RuntimeException("local file not found.");
        } catch (IOException e) {
            throw new RuntimeException("Could not put file to server.");
        } finally {
            try {
                if (input != null) {
                    input.close();
                }
            } catch (Exception e) {
                throw new RuntimeException("Couldn't close FileInputStream.");
            }
            if (autoClose) {
                disconnect(); //断开连接
            }
        }
    }

    /**
     * 下载一个远程文件到本地的指定文件
     *
     * @param remoteAbsoluteFile 远程文件名(包括完整路径)
     * @param localAbsoluteFile 本地文件名(包括完整路径)
     * @return 成功时，返回true，失败返回false
     * @
     */
    public boolean get(String remoteAbsoluteFile, String localAbsoluteFile)  {
        return get(remoteAbsoluteFile, localAbsoluteFile, true);
    }

    /**
     * 下载一个远程文件到本地的指定文件
     *
     * @param remoteAbsoluteFile 远程文件名(包括完整路径)
     * @param localAbsoluteFile 本地文件名(包括完整路径)
     * @param autoClose 是否自动关闭当前连接
     *
     * @return 成功时，返回true，失败返回false
     * @
     */
    public boolean get(String remoteAbsoluteFile, String localAbsoluteFile, boolean autoClose)  {
        OutputStream output = null;
        try {
            output = new FileOutputStream(localAbsoluteFile);
            return get(remoteAbsoluteFile, output, autoClose);
        } catch (FileNotFoundException e) {
            throw new RuntimeException("local file not found.");
        } finally {
            try {
                if (output != null) {
                    output.close();
                }
            } catch (IOException e) {
                throw new RuntimeException("Couldn't close FileOutputStream.");
            }
        }
    }

    /**
     * 下载一个远程文件到指定的流 处理完后记得关闭流
     *
     * @param remoteAbsoluteFile
     * @param output
     * @return
     * @
     */
    public boolean get(String remoteAbsoluteFile, OutputStream output)  {
        return get(remoteAbsoluteFile, output, true);
    }

    /**
     * 下载一个远程文件到指定的流 处理完后记得关闭流
     *
     * @param remoteAbsoluteFile
     * @param output
     * @return
     * @
     */
    public boolean get(String remoteAbsoluteFile, OutputStream output, boolean autoClose)  {
        try {
            FTPClient ftpClient = getFTPClient();
            // 处理传输
            return ftpClient.retrieveFile(remoteAbsoluteFile, output);
        } catch (IOException e) {
            throw new RuntimeException("Couldn't get file from server.");
        } finally {
            if (autoClose) {
                disconnect(); //关闭链接
            }
        }
    }

    /**
     * 从ftp服务器上删除一个文件
     * 该方法将自动关闭当前连接
     *
     * @param delFile
     * @return
     * @
     */
    public boolean delete(String delFile)  {
        return delete(delFile, true);
    }

    /**
     * 从ftp服务器上删除一个文件
     *
     * @param delFile
     * @param autoClose 是否自动关闭当前连接
     *
     * @return
     * @
     */
    public boolean delete(String delFile, boolean autoClose)  {
        try {
            getFTPClient().deleteFile(delFile);
            return true;
        } catch (IOException e) {
            throw new RuntimeException("Couldn't delete file from server.");
        } finally {
            if (autoClose) {
                disconnect(); //关闭链接
            }
        }
    }

    /**
     * 批量删除
     * 该方法将自动关闭当前连接
     *
     * @param delFiles
     * @return
     * @
     */
    public boolean delete(String[] delFiles)  {
        return delete(delFiles, true);
    }

    /**
     * 批量删除
     *
     * @param delFiles
     * @param autoClose 是否自动关闭当前连接
     *
     * @return
     * @
     */
    public boolean delete(String[] delFiles, boolean autoClose)  {
        try {
            FTPClient ftpClient = getFTPClient();
            for (String s : delFiles) {
                ftpClient.deleteFile(s);
            }
            return true;
        } catch (IOException e) {
            throw new RuntimeException("Couldn't delete file from server.");
        } finally {
            if (autoClose) {
                disconnect(); //关闭链接
            }
        }
    }

    /**
     * 列出远程默认目录下所有的文件
     *
     * @return 远程默认目录下所有文件名的列表，目录不存在或者目录下没有文件时返回0长度的数组
     * @
     */
    public String[] listNames()  {
        return listNames(null, true);
    }

    public String[] listNames(boolean autoClose)  {
        return listNames(null, autoClose);
    }

    /**
     * 列出远程目录下所有的文件
     *
     * @param remotePath 远程目录名
     * @param autoClose 是否自动关闭当前连接
     *
     * @return 远程目录下所有文件名的列表，目录不存在或者目录下没有文件时返回0长度的数组
     * @
     */
    public String[] listNames(String remotePath, boolean autoClose)  {
        try {
            String[] listNames = getFTPClient().listNames(remotePath);
            return listNames;
        } catch (IOException e) {
            throw new RuntimeException("列出远程目录下所有的文件时出现异常");
        } finally {
            if (autoClose) {
                disconnect(); //关闭链接
            }
        }
    }

    private static String hostList;
    private static String imgRemoteSyncDirectory;
    private static int ftpPort;
    private static String ftpUsername;
    private static String ftpPassword;

    public void setHostList(String hostList) {FTPClientTemplate.hostList = hostList;}
    public void setImgRemoteSyncDirectory(String imgRemoteSyncDirectory) {FTPClientTemplate.imgRemoteSyncDirectory = imgRemoteSyncDirectory;}
    public void setFtpPort(int ftpPort) {FTPClientTemplate.ftpPort = ftpPort;}
    public void setFtpUsername(String ftpUsername) {FTPClientTemplate.ftpUsername = ftpUsername;}
    public void setFtpPassword(String ftpPassword) {FTPClientTemplate.ftpPassword = ftpPassword;}

    /*同步文件到其他文件服务器*/
    public static void syncFile(List<String> sysFileIdList){
        List<FTPClientTemplate> templateList = new ArrayList<>();
        try {
            templateList = FTPClientTemplate.createFTPClientList(hostList,ftpPort,ftpUsername,ftpPassword);
            for(String fileId : sysFileIdList){
                for(FTPClientTemplate ftpClientTemplate : templateList ){
                    ftpClientTemplate.makeDirectory(imgRemoteSyncDirectory + fileId.substring(0, fileId.lastIndexOf("/")), false);
                    ftpClientTemplate.put(imgRemoteSyncDirectory + fileId, FileSystemEngine.getFileSystem().getLocalFile(fileId).getAbsolutePath(), false);
                }
            }
        }catch (IOException e){
            e.printStackTrace();
        }finally {
            FTPClientTemplate.disconnectFTPClient(templateList);
        }
    }

    public static FTPClientTemplate createFTPClient(String host, int port, String username, String password){
        FTPClientTemplate ftp = new FTPClientTemplate();
        ftp.setHost(host);
        ftp.setPort(port);
        ftp.setUsername(username);
        ftp.setPassword(password);
        ftp.setBinaryTransfer(true);
        ftp.setPassiveMode(true);
        ftp.setEncoding("utf-8");
        return ftp;
    }

    public static List<FTPClientTemplate> createFTPClientList(String hostList, int port, String username, String password) {
        List<FTPClientTemplate> templateList = new ArrayList<FTPClientTemplate>();
        if(StringUtils.isBlank(hostList)){
           return templateList;
        }
        String[] hosts = hostList.split(",");
        for(String host: hosts){
            templateList.add(createFTPClient(host,port, username, password));
        }
        return templateList;
    }

    public static void disconnectFTPClient(List<FTPClientTemplate> templateList) {
        for(FTPClientTemplate template : templateList){
            template.disconnect();
        }
    }

    public static void main(String[] args){
        FTPClientTemplate ftp = new FTPClientTemplate();
        ftp.setHost("localhost");
        ftp.setPort(2121);
        ftp.setUsername("admin");
        ftp.setPassword("admin");
        ftp.setBinaryTransfer(false);
        ftp.setPassiveMode(false);
        ftp.setEncoding("utf-8");

        //boolean ret = ftp.put("/group/tbdev/query/user-upload/12345678910.txt", "D:/099_temp/query/12345.txt");
        //throw new RuntimeException(ret);
        ftp.mkdir("asd", "user-upload");

        //ftp.disconnect();
        //ftp.mkdir("user-upload1");
        //ftp.disconnect();

        //String[] aa = {"/group/tbdev/query/user-upload/123.txt", "/group/tbdev/query/user-upload/SMTrace.txt"};
        //ftp.delete(aa);
    }

}