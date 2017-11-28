package com.imall.commons.base.shutdown;

import java.io.*;
import java.util.StringTokenizer;

/**
 * Created by ygw on 2017/6/5.
 * Linux cpu 内存 获取 工具类
 */
public class CpuMemTool {

    public static void getinfo() {
        String os = System.getProperty("os.name");
        System.out.println("The system is " + os);
        if(os!=null && !os.toLowerCase().startsWith("win")){ //&& os.toLowerCase().indexOf("linux") > -1
            try {
                int[] memInfo = LinuxSystemTool.getMemInfo();
                System.out.println("MemTotal(MB):" + memInfo[0]);
                System.out.println("MemFree(MB):" + memInfo[1]);
                System.out.println("SwapTotal(MB):" + memInfo[2]);
                System.out.println("SwapFree(MB):" + memInfo[3]);
                System.out.println("CPU利用率:" + LinuxSystemTool.getCpuInfo());
            }
            catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}

/**
 * <p>Title: LinuxSystemTool</p>
 * <p>
 * <p>Description:  取得linux系统下的cpu、内存信息</p>
 * <p>
 * <p>Copyright: Copyright (c) 2006</p>
 * <p>
 * <p>Company: wri</p>
 *
 * @version 1.0
 * @tang
 */

final class LinuxSystemTool {
    /**
     * get memory by used info
     *
     * @return int[] result
     * result.length==4;int[0]=MemTotal;int[1]=MemFree;int[2]=SwapTotal;int[3]=SwapFree;
     * @throws IOException
     * @throws InterruptedException
     */
    public static int[] getMemInfo() throws IOException, InterruptedException {
        File file = new File("/proc/meminfo");
        BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
        int[] result = new int[4];
        String str = null;
        StringTokenizer token = null;
        while ((str = br.readLine()) != null) {
            token = new StringTokenizer(str);
            if (!token.hasMoreTokens()) {
                continue;
            }
            str = token.nextToken();
            if (!token.hasMoreTokens()) {
                continue;
            }
            if (str.equalsIgnoreCase("MemTotal:")) {
                result[0] = Integer.parseInt(token.nextToken()) / 1024;
            } else if (str.equalsIgnoreCase("MemFree:")) {
                result[1] = Integer.parseInt(token.nextToken())  / 1024;
            } else if (str.equalsIgnoreCase("SwapTotal:")) {
                result[2] = Integer.parseInt(token.nextToken())  / 1024;
            } else if (str.equalsIgnoreCase("SwapFree:")) {
                result[3] = Integer.parseInt(token.nextToken())  / 1024;
            }
        }
        return result;
    }

    /**
     * get memory by used info
     *
     * @return float efficiency
     * @throws IOException
     * @throws InterruptedException
     */
    public static float getCpuInfo() throws IOException, InterruptedException {
        File file = new File("/proc/stat");
        BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
        StringTokenizer token = new StringTokenizer(br.readLine());
        token.nextToken();
        int user1 = Integer.parseInt(token.nextToken());
        int nice1 = Integer.parseInt(token.nextToken());
        int sys1 = Integer.parseInt(token.nextToken());
        int idle1 = Integer.parseInt(token.nextToken());
        Thread.sleep(3000);
        br = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
        token = new StringTokenizer(br.readLine());
        token.nextToken();
        int user2 = Integer.parseInt(token.nextToken());
        int nice2 = Integer.parseInt(token.nextToken());
        int sys2 = Integer.parseInt(token.nextToken());
        int idle2 = Integer.parseInt(token.nextToken());

        return (float) ((user2 + sys2 + nice2) - (user1 + sys1 + nice1)) /
                (float) ((user2 + nice2 + sys2 + idle2) -
                        (user1 + nice1 + sys1 + idle1));
    }
}
