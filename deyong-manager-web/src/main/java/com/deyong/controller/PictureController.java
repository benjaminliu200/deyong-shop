package com.deyong.controller;

import com.deyong.service.PictureService;
import com.ldy.common.util.JsonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

/**
 * Created by Benjamin on 2017/1/5.
 */
@Controller
public class PictureController {
    @Autowired
    private PictureService pictureService;

    @RequestMapping("/pic/upload")
    @ResponseBody
    public String picUpload(MultipartFile uploadFile) {
        Map result = pictureService.uploadFile(uploadFile);
        String json = JsonUtils.objectToJson(result);
        return json;
    }
}
