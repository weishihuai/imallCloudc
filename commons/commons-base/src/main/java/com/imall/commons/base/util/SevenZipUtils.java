package com.imall.commons.base.util;

import org.apache.commons.compress.archivers.sevenz.SevenZArchiveEntry;
import org.apache.commons.compress.archivers.sevenz.SevenZFile;
import org.apache.commons.compress.archivers.sevenz.SevenZMethod;
import org.apache.commons.compress.archivers.sevenz.SevenZOutputFile;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * User: yang
 * Date: 14-1-15
 * Time: 上午11:55
 */
public class SevenZipUtils {

    public static final Logger LOGGER = LogManager.getLogger(SevenZipUtils.class);

    public interface ZipInterceptor{
        /**
         * @return 是否允许压缩
         */
        boolean beforeEachZip(File file);
        void afterEachZip(SevenZArchiveEntry zipEntry);
    }

    public interface UnzipInterceptor{
        /**
         * @return 是否允许解压缩
         */
        boolean beforeEachUnzip(SevenZArchiveEntry zipEntry);
        void afterEachUnzip(File unzipedFile);
    }

    private static void zipDir(SevenZOutputFile outArchive,final File fileDir,final String parentDir, ZipInterceptor interceptor) throws IOException {
        File[] files = fileDir.listFiles();
        if(files!=null && files.length > 0){
            for(File file : files){
                if(file.isDirectory()){
                    String path =  parentDir + file.getName() + "/";
                    //LOGGER.info("目录" + path);
                    zipDir(outArchive, file, path, interceptor);
                }
                else if(file.isFile()){
                    zipFile(outArchive, file, parentDir, interceptor);
                }
            }
        }
        else {
            if(interceptor!= null && !interceptor.beforeEachZip(fileDir)){
                return;
            }
            //创建空的目录
            //LOGGER.info("空的目录" + parentDir);
            SevenZArchiveEntry entry = outArchive.createArchiveEntry(fileDir, parentDir);
            Date accessDate = new Date();
            Calendar cal = Calendar.getInstance();
            cal.add(Calendar.HOUR, -1);
            Date creationDate = cal.getTime();
            entry.setCreationDate(creationDate);
            entry.setAccessDate(accessDate);
            outArchive.putArchiveEntry(entry);
            outArchive.closeArchiveEntry();
            if(interceptor!= null){
                interceptor.afterEachZip(entry);
            }
        }

    }

    private static void zipFile(SevenZOutputFile outArchive,final File file,final String parentDir, ZipInterceptor interceptor) throws IOException {
        if(interceptor!= null && !interceptor.beforeEachZip(file)){
            return;
        }
        String path =  parentDir + file.getName();
        //LOGGER.info("文件" + path);

        SevenZArchiveEntry entry = outArchive.createArchiveEntry(file, path);
        Date accessDate = new Date();
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.HOUR, -1);
        Date creationDate = cal.getTime();
        entry.setCreationDate(creationDate);
        entry.setAccessDate(accessDate);
        outArchive.putArchiveEntry(entry);
        copy(file, outArchive);
        outArchive.closeArchiveEntry();
        if(interceptor!= null){
            interceptor.afterEachZip(entry);
        }
    }

    public static Boolean zipFiles(String[] filePaths, String zipFilePath, ZipInterceptor interceptor){
        return zipFiles(SevenZMethod.LZMA2, filePaths, zipFilePath, interceptor);
    }

    public static Boolean zipFiles(SevenZMethod method, String[] filePaths, String zipFilePath, ZipInterceptor interceptor){
        if(filePaths==null || filePaths.length<=0){
            return false;
        }
        File zipFile = new File(zipFilePath);
        List<File> inputDirList = new ArrayList<File>();
        for(String filePath : filePaths){
            inputDirList.add(new File(filePath));
        }
        return zipFiles(method, new File(zipFile.getParent()), zipFile.getName(),inputDirList ,interceptor);
    }

    //递归压缩目录和文件
    public static Boolean zipFiles(SevenZMethod method,final File outputDir,final String outputZipFileName,final List<File> inputFileList, ZipInterceptor interceptor){
        if(inputFileList.isEmpty()){
            return false;
        }
        File output = new File(outputDir, outputZipFileName);
        SevenZOutputFile outArchive = null;
        try {
            outArchive = new SevenZOutputFile(output);
            outArchive.setContentCompression(method);
            for(File inputFile : inputFileList){
                if(!inputFile.exists()){
                    continue;
                }
                if(inputFile.isDirectory()){
                    zipDir(outArchive, inputFile, inputFile.getName() + "/", interceptor);
                }
                else if(inputFile.isFile()){
                    zipFile(outArchive, inputFile, "", interceptor);
                }
            }
            outArchive.finish();
        }catch (IOException e){
            LOGGER.error(e);
        } finally {
            try {
                if(outArchive!=null){
                    outArchive.close();
                }
            } catch (IOException e) {
                LOGGER.error(e);
            }
        }
        return true;
    }

    private static void copy(final File src, final SevenZOutputFile dst) throws IOException {
        FileInputStream fis = null;
        try {
            fis = new FileInputStream(src);
            final byte[] buffer = new byte[8*1024];
            int bytesRead;
            while ((bytesRead = fis.read(buffer)) >= 0) {
                dst.write(buffer, 0, bytesRead);
            }
        } finally {
            if (fis != null) {
                fis.close();
            }
        }
    }

    //解压文件
    public static List<File> unzipFile(String zipFile, String outFile,  UnzipInterceptor inteceptor){
        return unzipFile(new File(zipFile), new File(outFile), inteceptor);
    }
    //解压文件
    public static List<File> unzipFile(File zipFile, File outFile,  UnzipInterceptor inteceptor){
        List<File> unzipFileList = new ArrayList<File>();
        if(!zipFile.exists() || !zipFile.isFile()){
            return unzipFileList;
        }
        if(!outFile.exists()){
            outFile.mkdirs();
        }
        SevenZFile sevenZFile = null;
        try {
            sevenZFile = new SevenZFile(zipFile);
            SevenZArchiveEntry entry = sevenZFile.getNextEntry();
            while (entry!=null){
                if(inteceptor != null && !inteceptor.beforeEachUnzip(entry)){
                    entry = sevenZFile.getNextEntry();
                    continue;
                }
                File unzipFile = null;
                if(entry.isDirectory()){
                    unzipFile = new File(outFile.getPath(), entry.getName());
                    if(!unzipFile.exists()){
                        if(!unzipFile.getParentFile().exists()) {
                            //如果目标文件所在的目录不存在，则创建父目录
                            unzipFile.getParentFile().mkdirs();
                        }
                        unzipFile.mkdir();
                    }
                }
                else {
                    unzipFile = new File(outFile.getPath(), entry.getName());
                    if(!unzipFile.exists()){
                        if(!unzipFile.getParentFile().exists()) {
                            //如果目标文件所在的目录不存在，则创建父目录
                            unzipFile.getParentFile().mkdirs();
                        }
                        unzipFile.createNewFile();
                        byte[] contents = new byte[(int)entry.getSize()];
                        int off = 0;
                        while ((off < contents.length)) {
                            int bytesRead = sevenZFile.read(contents, off, contents.length - off);
                            off += bytesRead;
                        }
                        FileOutputStream fileOutput = null;
                        try {
                            fileOutput = new FileOutputStream(unzipFile);
                            fileOutput.write(contents, 0, contents.length);
                        } finally {
                            if (fileOutput != null) {
                                fileOutput.close();
                            }
                        }
                    }
                }
                if(inteceptor != null){
                    inteceptor.afterEachUnzip(unzipFile);
                }
                unzipFileList.add(unzipFile);
                entry = sevenZFile.getNextEntry();
            }
        }catch(IOException e){
            LOGGER.error(e);
        } finally {
            if(sevenZFile!=null){
                try {
                    sevenZFile.close();
                } catch (IOException e) {
                    LOGGER.error(e);
                }
            }
        }
        return unzipFileList;
    }
}
