package com.ding.util;

import java.io.*;
import java.nio.charset.Charset;
import java.util.Scanner;

/**
 * @author daniel 2020-9-23 0023.
 */
public class TestFileGBKToUtf8Main {
    //要转换的目录--目标资源URL
    public final static String targetFileUrl = "E:\\DingSai\\DingProjectAs_new\\SpringBootStudy\\src\\main\\java\\designpatterns\\abstratfactory";

    //遍历目录，将文件从GBK转换成UTF-8
    public static void fileList(File file) {
        File rootFile = file;
        File[] files = rootFile.listFiles();
        if (files != null) {
            for (File f : files) {
                if (!f.isDirectory()) {
                    codeConvert(f);
                }
                System.out.println(f.getPath());
                fileList(f);//递归调用子文件夹下的文件
            }
        }
    }

    public static void main(String[] args) {
      //  File file = new File(targetFileUrl);
       // TestFileGBKToUtf8Main.fileList(file);
        System.out.println("aa");
    }

    public static void codeConvert(File file) {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader
                    (new FileInputStream(file), Charset.forName("GBK")));
            StringBuilder sb = new StringBuilder();
            String str;
            while ((str = br.readLine()) != null) {
                sb.append(str);
                sb.append("\n");
            }
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file), Charset.forName("UTF-8")));
            bw.write(sb.toString());
            bw.flush();
            bw.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}

