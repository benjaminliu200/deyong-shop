package com.deyong.service;

import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

/**
 * Created by benjamin on 2017/1/5.
 */
public interface PictureService {
    Map uploadFile(MultipartFile uploadFile);
}
