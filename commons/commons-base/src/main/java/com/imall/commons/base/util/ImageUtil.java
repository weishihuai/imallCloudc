package com.imall.commons.base.util;

import org.springframework.util.Assert;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.List;

/**
 * Created by ZJC on 2016/1/5.
 */
public class ImageUtil {

    public static final String ERROR_MSG_SOURCE_IMAGE_FILE_NO_EXIST = "source Image File not exist!";

    public static File genPictures(int[] size, File src, File destDir, boolean force)throws IOException {
        Assert.isTrue((src != null && src.exists()), ERROR_MSG_SOURCE_IMAGE_FILE_NO_EXIST);
        Assert.notNull(size);

        List<int[]> sizes = new ArrayList<int[]>();
        sizes.add(new int[]{size[0],size[1]});

        String sizeStr = size[0] + "X" + size[1];
        Map<String,File> fileMap = genPictures(sizes,src,destDir,force);
        return fileMap.get(sizeStr);
    }

    public static Map<String,File> genPictures(List<int[]> sizes, File src, File destDir, boolean force) throws IOException {
        Assert.isTrue((src != null && src.exists()), ERROR_MSG_SOURCE_IMAGE_FILE_NO_EXIST);
        Assert.notNull(sizes);

        File realDestDir = null;
        if(destDir!=null){
            realDestDir = destDir;
            if(!destDir.exists()){
                Assert.isTrue(destDir.mkdirs());
            }
        }else {
            realDestDir = src.getParentFile();
        }

        String fileName = src.getName();
        HashMap<String,File> files = new HashMap<String,File>();
        for(int[] size: sizes){
            String sizeStr = size[0] + "X" + size[1];
            File newFile = new File(realDestDir,CommonUtil.insertFileNameSuffixToUrl(fileName, "_" + sizeStr));
            doChangeSize(src,newFile,size[0],size[1],force);
            files.put(sizeStr,newFile);
        }
        return files;
    }

    public static void doChangeSize(File imgFile , File dest, int w, int h, boolean force){
        if(imgFile.exists()){
            try {
                // ImageIO 支持的图片类型 : [BMP, bmp, jpg, JPG, wbmp, jpeg, png, PNG, JPEG, WBMP, GIF, gif]
                String types = Arrays.toString(ImageIO.getReaderFormatNames());
                String suffix = null;
                // 获取图片后缀
                if(imgFile.getName().indexOf(".") > -1) {
                    suffix = imgFile.getName().substring(imgFile.getName().lastIndexOf(".") + 1);
                }// 类型和图片后缀全部小写，然后判断后缀是否合法
                if(suffix == null || types.toLowerCase().indexOf(suffix.toLowerCase()) < 0){
                    return ;
                }
                Image img = ImageIO.read(imgFile);
                if(!force){
                    // 根据原图与要求的缩略图比例，找到最合适的缩略图比例
                    int width = img.getWidth(null);
                    int height = img.getHeight(null);
                    if((width*1.0)/w < (height*1.0)/h){
                        if(width > w){
                            h = Integer.parseInt(new java.text.DecimalFormat("0").format(height * w/(width*1.0)));
                        }
                    } else {
                        if(height > h){
                            w = Integer.parseInt(new java.text.DecimalFormat("0").format(width * h/(height*1.0)));
                        }
                    }
                }
                BufferedImage bi = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
                Graphics g = bi.getGraphics();
                g.drawImage(img, 0, 0, w, h, Color.LIGHT_GRAY, null);
                g.dispose();
                String p = imgFile.getPath();
                // 将图片保存在原目录并加上前缀
                ImageIO.write(bi, suffix, dest);
            } catch (IOException e) {

            }
        }else{
            System.out.println("the image is not exist.");
        }
    }
}
