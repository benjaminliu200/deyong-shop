package com.deyong.service.impl;

import com.deyong.service.PictureService;
import com.ldy.common.util.FtpUtil;
import com.ldy.common.util.IDUtils;
import com.ldy.common.util.SftpUtil;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * Created by benjamin on 2017/1/5.
 */
@Service
public class PictureServiceImpl implements PictureService {

    @Value("${ftp.host}")
    private String FTP_HOST;

    @Value("${ftp.port}")
    private Integer FTP_PORT;

    @Value("${ftp.username}")
    private String FTP_USERNAME;

    @Value("${ftp.password}")
    private String FTP_PASSWORD;

    @Value("${ftp.basepath}")
    private String FTP_BASEPATH;

    @Value("${ftp.timeout}")
    private Integer FTP_TIMEOUT;

    @Value("${image.base.url}")
    private String IMAGE_BASE_URL;

    /*@Override
    public Map uploadFile(MultipartFile uploadFile) {

        Map resultMap = new HashMap();
        // 生成新文件名
        String originalFilename = uploadFile.getOriginalFilename();

        String newName = IDUtils.genImageName();

        newName = newName + originalFilename.lastIndexOf(".");
        String imagePath = new DateTime().toString("/yyyy-MM-dd");
        try {
            FtpUtil.uploadFile(FTP_HOST, FTP_PORT, FTP_USERNAME, FTP_PASSWORD, FTP_BASEPATH, imagePath, newName, uploadFile.getInputStream());
            resultMap.put("error", 0);
            resultMap.put("url", IMAGE_BASE_URL + imagePath + '/' + newName);
        } catch (Exception e) {
            // 说明 用这种 SSH的 上传文件会出错。 用jsch来上传文件
            try {
                boolean b = SftpUtil.uploadFile(FTP_HOST, FTP_USERNAME, FTP_PASSWORD, FTP_PORT, FTP_TIMEOUT, FTP_BASEPATH, imagePath, newName, uploadFile.getInputStream());
                if (!b) {
                    resultMap.put("error", 1);
                    resultMap.put("massage", "上传失败");
                } else {
                    resultMap.put("error", 0);
                    resultMap.put("url", IMAGE_BASE_URL + imagePath + '/' + newName);
                }
            } catch (IOException e1) {
                resultMap.put("error", 1);
                resultMap.put("massage", "上传发生异常");
                e1.printStackTrace();
            }
        }
        return resultMap;
    }*/

    @Override
    public Map uploadFile(MultipartFile uploadFile) {

        Map resultMap = new HashMap();
        // 生成新文件名
        String originalFilename = uploadFile.getOriginalFilename();

        String newName = IDUtils.genImageName();

        newName = newName + originalFilename.lastIndexOf(".");
        String imagePath = new DateTime().toString("/yyyy-MM-dd");
        // 说明 用这种 SSH的 上传文件会出错。 用jsch来上传文件
        try {
            boolean b = SftpUtil.uploadFile(FTP_HOST, FTP_USERNAME, FTP_PASSWORD, FTP_PORT, FTP_TIMEOUT, FTP_BASEPATH, imagePath, newName, uploadFile.getInputStream());
            if (!b) {
                resultMap.put("error", 1);
                resultMap.put("massage", "上传失败");
            } else {
                resultMap.put("error", 0);
                resultMap.put("url", IMAGE_BASE_URL + imagePath + '/' + newName);
            }
        } catch (IOException e1) {
            resultMap.put("error", 1);
            resultMap.put("massage", "上传发生异常");
            e1.printStackTrace();
        }
        return resultMap;
    }
}
