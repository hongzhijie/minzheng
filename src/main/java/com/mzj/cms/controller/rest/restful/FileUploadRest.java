package com.mzj.cms.controller.rest.restful;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

/*
 * @Author hzj
 * @ClassName FileUploadRest
 * @Description 文件上传接口
 * @Date 15:33 2019/3/21
 **/
@RestController
@RequestMapping(value = "ws/file")
public class FileUploadRest {

    // 文件上传路径
    @Value("${spring.http.multipart.location}")
    private String filePath;
    // 文件保存访问url
    @Value("${upload.images.prefixUrl}")
    private String prefixUrl;

    private static final Logger logger = LoggerFactory.getLogger(FileUploadRest.class);

    /*
     * @Author hzj
     * @ClassName FileUploadRest
     * @Description 文件上传接口
     * @Date 14:18 2019/5/9
     * @Param MultipartFile
     **/
    @RequestMapping(value = "upload")
    @ResponseBody
    public String upload(@RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            return "文件为空";
        }
        // 获取文件名
        String fileName = file.getOriginalFilename();
        logger.info("上传的文件名为：" + fileName);
        // 获取文件的后缀名
        String suffixName = fileName.substring(fileName.lastIndexOf("."));
        logger.info("上传的后缀名为：" + suffixName);
        // 解决中文问题，liunx下中文路径，图片显示问题
         fileName = UUID.randomUUID() + suffixName;
        File dest = new File(filePath + fileName);
        // 检测是否存在目录
        if (!dest.getParentFile().exists()) {
            dest.getParentFile().mkdirs();
        }
        try {
            file.transferTo(dest);
            return "上传成功,文件名为："+fileName;
        } catch (IllegalStateException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "上传失败";
    }

}