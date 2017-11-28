package com.imall.commons.base.util;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.HanyuPinyinVCharType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;
import org.apache.log4j.Logger;

public class Pinyin4jUtil {

    private static Logger logger = Logger.getLogger(Pinyin4jUtil.class);

    private Pinyin4jUtil(){}
/*    public static void main(String[] args) {
        String str = "达克宁软膏";
        System.out.println(getPinYin(str));  
        System.out.println(getPinYinHeadChar(str));  
        System.out.println(getStrASCII(str));  
    }*/

    /**
     * 将汉字转换为全拼 
     *
     * @param str 汉字 
     * @return String 返回汉字的全拼音 
     */
    public static String getPinYin(String str) {
        char[] strCharArray = null;
        strCharArray = str.toCharArray();
        String[] pinYinArray = new String[strCharArray.length];  //NOSONAR
        // 设置汉字拼音输出的格式  
        HanyuPinyinOutputFormat format = new HanyuPinyinOutputFormat();
        //设置拼音的大小写,UPPERCASE表示大写，LOWERCASE表示小写  
        format.setCaseType(HanyuPinyinCaseType.LOWERCASE);
        format.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
        format.setVCharType(HanyuPinyinVCharType.WITH_V);
        StringBuilder result = new StringBuilder();
        int flag = strCharArray.length;
        try {
            for (int i = 0; i < flag; i++) {
                // 判断能否为汉字字符  
                if (Character.toString(strCharArray[i]).matches("[\\u4E00-\\u9FA5]+")) {
                    pinYinArray = PinyinHelper.toHanyuPinyinStringArray(strCharArray[i], format);// 将汉字的几种全拼都存到pinYinArray数组中
                    result.append(pinYinArray[0]);// 取出该汉字全拼的第一种读音并连接到字符串result后
                } else {
                    // 如果不是汉字字符，间接取出字符并连接到字符串result后
                    result.append(Character.toString(strCharArray[i]));
                }
            }
        } catch (BadHanyuPinyinOutputFormatCombination e) {
            logger.error("设置拼音大小写出错：", e);
        }
        return result.toString();
    }

    /**
     * 提取每个汉字的首字母 
     *
     * @param str 汉字 
     * @return String 返回汉字的拼音首字母 
     */
    public static String getPinYinHeadChar(String str) {
        StringBuilder convert = new StringBuilder();
        for (int j = 0; j < str.length(); j++) {
            char word = str.charAt(j);
            // 提取汉字的首字母  
            String[] pinyinArray = PinyinHelper.toHanyuPinyinStringArray(word);
            if (pinyinArray != null) {
                convert.append(pinyinArray[0].charAt(0));
            } else {
                convert.append(word);
            }
        }
        return convert.toString();
    }

    /**
     * 将字符串转换成ASCII码 
     *
     * @param str 需要转换的字符串 
     * @return String 字符串转换后的ASCII码 
     */
    public static String getStrASCII(String str) {
        StringBuffer strBuf = new StringBuffer();
        // 将字符串转换成字节序列  
        byte[] bGBK = str.getBytes();
        for (int i = 0; i < bGBK.length; i++) {
            // 将每个字符转换成ASCII码  
            strBuf.append(Integer.toHexString(bGBK[i] & 0xff));
        }
        return strBuf.toString();
    }

}  