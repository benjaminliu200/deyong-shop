package com.ldy.common.util;

import com.jcraft.jsch.*;
import com.jcraft.jsch.ChannelSftp.LsEntry;
import org.apache.log4j.Logger;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Vector;

/**
 * SFTP(Secure File Transfer Protocol)，安全文件传送协议。
 *
 * @author dongliyang
 * @version 1.0 2014/12/18
 */
public class SftpUtilCopy {

    /**
     * 日志记录器
     */
    private static Logger logger = Logger.getLogger(SftpUtilCopy.class);
    /**
     * Session
     */
    private static Session session = null;
    /**
     * Channel
     */
    private static ChannelSftp channel = null;


    /**
     * Description: 向FTP服务器上传文件
     * @param host FTP服务器hostname
     * @param port FTP服务器端口
     * @param username FTP登录账号
     * @param password FTP登录密码
     * @param timeout 超时时间，超过多长时间报错
     * @param basePath FTP服务器基础目录
     * @param filePath FTP服务器文件存放路径。例如分日期存放：/2015/01/01。文件的路径为basePath+filePath
     * @param filename 上传到FTP服务器上的文件名
     * @param input 输入流
     * @return 成功返回true，否则返回false
     */
    public static boolean uploadFile(String host, String username, String password, Integer port, Integer timeout, String basePath,
                              String filePath, String filename, InputStream input) {
        try {
            JSch jsch = new JSch();
            session = jsch.getSession(username, host, port);
            session.setPassword(password);
            Properties config = new Properties();
            config.put("StrictHostKeyChecking", "no");
            session.setConfig(config);
            session.setTimeout(timeout);
            session.connect();
            logger.debug("sftp session connected");
            logger.debug("opening channel");
            channel = (ChannelSftp) session.openChannel("sftp");
            channel.connect();

            logger.debug("connected successfully");

            String start;
            String resultPath = basePath + filePath;
            if (channel.ls(resultPath) == null) {
                String[] dirs = resultPath.split("/");
                String tempPath = "/";
                for (String dir: dirs) {
                    channel.mkdir();
                }
            }

            channel.put(input, basePath + filename, ChannelSftp.OVERWRITE);
            logger.debug("upload successful");
            logout();
            return true;
        } catch (JSchException e) {
            logger.error("sftp login failed", e);
            return false;
        } catch (SftpException e) {
            logger.error("上传失败", e);
            e.printStackTrace();
        }
        return false;
    }
    /**
     * 登出
     */
    public static void logout() {
        if (channel != null) {
            channel.quit();
            channel.disconnect();
        }
        if (session != null) {
            session.disconnect();
        }
        logger.debug("logout successfully");
    }
}