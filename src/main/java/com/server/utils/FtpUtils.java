package com.server.utils;


import org.apache.commons.net.ftp.FTPClient;

import javax.servlet.http.HttpServletResponse;
import java.io.*;

public class FtpUtils {

    private final static String HOST = "192.168.56.104";
    private final static Integer PORT = 21;
    private final static String USER_NAME = "fftp";
    private final static String PASSWORD = "fftp";

    private final static String UPLOAD_PATH = "/home/vsftpd/upload/";
    private final static String DOWNLOAD_FILE_PATH = "D:\\javadownloadfile\\";

    private static FTPClient ftpClient;

    private static boolean connectFtp() throws IOException {
        ftpClient = new FTPClient();
        ftpClient.connect(HOST, PORT);
        boolean login = ftpClient.login(USER_NAME, PASSWORD);
        return login;
    }

    private static void setConfig() throws IOException {
        ftpClient.enterLocalPassiveMode();
        ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE);
    }

    /**
     * @param inputStream
     * @return
     * @throws IOException
     */
    private static boolean uploadFile(InputStream inputStream, String realName) throws IOException {
        boolean ifUploadSuccess = ftpClient.storeFile(UPLOAD_PATH + realName, inputStream);
        return ifUploadSuccess;
    }

    /**
     * @param fileName 文件真名
     * @param realName
     * @return
     * @throws FileNotFoundException
     */
    private static boolean downLoadFIle(String fileName, String realName, HttpServletResponse response) throws IOException {
   /*     response.reset();
        response.setCharacterEncoding("UTF-8");
        response.setHeader("content-disposition", "attachment;filename="+fileName);
        OutputStream outputStream = response.getOutputStream();*/
        OutputStream outputStream = new FileOutputStream(DOWNLOAD_FILE_PATH + fileName);
        boolean ifSuccess = ftpClient.retrieveFile(UPLOAD_PATH + realName, outputStream);
        outputStream.flush();
        outputStream.close();
        return ifSuccess;
    }

    private static void disConnect() {
        try {
            if (ftpClient != null) {
                ftpClient.abort();
                ftpClient.disconnect();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String createRealFileName(String fileName, String uuid) {
        String suffix = fileName.substring(fileName.lastIndexOf("."), fileName.length());
        return uuid + suffix;
    }

    /**
     * 上传单个文件
     */
    public static boolean uploadFileMethod(InputStream inputStream, String realName) {
        boolean flag = false;
        try {
            if (connectFtp()) {
                setConfig();
                flag = uploadFile(inputStream, realName);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            disConnect();
        }
        return flag;
    }

    /**
     * 下载单个文件
     */
    public static boolean downLoadFileMethod(String fileName, String realName, HttpServletResponse response) {
        boolean flag = false;
        try {
            if (connectFtp()) {
             //   setConfig();
                flag = downLoadFIle(fileName, realName, response);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            disConnect();
        }
        return flag;
    }

    private static boolean deleteFile(String realName) throws IOException {
        boolean changeSuccess = ftpClient.changeWorkingDirectory(UPLOAD_PATH);
        if (changeSuccess) {
            return ftpClient.dele(realName) == 250 ? true : false;
        }
        return false;
    }

    public static boolean deleteFileMethod(String realName) {
        boolean flag = false;
        try {
            if (connectFtp()) {
                flag = deleteFile(realName);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            disConnect();
        }
        return flag;
    }

    public static void main(String[] args) throws IOException {
        // boolean b1 = uploadFileMethod(new FileInputStream(new File("C:\\Users\\Administrator\\Desktop\\图片\\1.jpg")), "666.jpg");
        // System.out.println(b1);
        boolean b = deleteFileMethod("666.jpg");
        System.out.println(b);
    }

}
