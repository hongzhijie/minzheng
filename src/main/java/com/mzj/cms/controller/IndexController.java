/**
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
 *
 * Copyright 2017 © hzj, 873559947@qq.com
 *
 * This file is part of contentManagerSystem.
 * contentManagerSystem is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * contentManagerSystem is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with contentManagerSystem.  If not, see <http://www.gnu.org/licenses/>.
 *
 * 这个文件是contentManagerSystem的一部分。
 * 您可以单独使用或分发这个文件，但请不要移除这个头部声明信息.
 * contentManagerSystem是一个自由软件，您可以自由分发、修改其中的源代码或者重新发布它，
 * 新的任何修改后的重新发布版必须同样在遵守GPL3或更后续的版本协议下发布.
 * 关于GPL协议的细则请参考COPYING文件，
 * 您可以在contentManagerSystem的相关目录中获得GPL协议的副本，
 * 如果没有找到，请连接到 http://www.gnu.org/licenses/ 查看。
 *
 * - Author: hzj
 * - Contact: 873559947@qq.com
 * - License: GNU Lesser General Public License (GPL)
 * - source code availability: http://git.oschina.net/hzj_175/contentManagerSystem
 */
package com.mzj.cms.controller;

import com.mzj.cms.domain.dto.Result;
import com.mzj.cms.service.DataCleaningService;
import com.mzj.cms.architect.constant.Constants;
import com.mzj.cms.handler.RedisClient;
import com.mzj.cms.service.ScImageService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;


/**
 * 主页Controller
 *
 * @author hzj
 * @date 2017/7/6
 */
@Controller
@RequestMapping("main")
public class IndexController extends BasicController {

    @Autowired
    private DataCleaningService dataCleaningService;
    @Autowired
    private RedisClient redisClient;

    @Autowired
    private ScImageService scImageService;

    // 文件上传路径
    @Value("${spring.http.multipart.location}")
    private String filePath;
    // 文件前缀地址
//    @Value("${spring.http.multipart.prefix}")
    private String imgPrefix = "https://www.cloudwxapp.cn/upload/";

    private static final Logger logger = LoggerFactory.getLogger(IndexController.class);

    //测试上传文件请求
    @RequestMapping(value = "upload")
    @ResponseBody
    public Result upload(@RequestParam("file") MultipartFile file) {
        Result result = new Result(1,"上传成功!");
        if (file.isEmpty()) {
            result.setCode(9);
            result.setMsg("上传失败,文件为空!");
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
            String url = imgPrefix+fileName;
            scImageService.insert(url);
        } catch (IllegalStateException e) {
            e.printStackTrace();
            result.setCode(9);
            result.setMsg("上传失败!");
        } catch (IOException e) {
            e.printStackTrace();
            result.setCode(9);
            result.setMsg("上传失败!");
        }
        return result;
    }

    /**
     *跳转到主页
     * @return
     */
    @RequestMapping("/index.do")
    public String toIndexPage() {
        return "main/index";
    }
    /**
     *跳转到主页
     * @return
     */
    @RequestMapping("/viewChose.do")
    public String toIndexViewChosePage() {
        return "main/viewChose";
    }

    /**
     *跳转到民政主页
     * @return
     */
    @RequestMapping("/index_mz.do")
    public String toIndexMZPage() {
        return "main/index_mz";
    }

    /**
     * 跳转到欢迎页
     * @return
     */
    @RequestMapping("/home.do")
    public String toHomePage(Model model) {
        List<Map<String,Object>> list = scImageService.getImages();
        model.addAttribute("list",list);
        return "main/home";
    }

    /**
     * 跳转到权限不足页面
     * @return
     */
    @RequestMapping("/unauthorized.do")
    public String toUnauthorizedPage() {
        return "error/unauthorized";
    }

    /**
     * 网站访问量,图表展示
     * @return
     */
    @RequestMapping("/ajax_echarts_login_info.do")
    @ResponseBody
    public String  ajaxEchartsByLoginInfo() {

        String userPv = redisClient.get(Constants.REDIS_KEY_ECHARTS_USER_PV);
        if(StringUtils.isNotEmpty(userPv)){
            return userPv;
        }
        log.info("redis值为空，查询数据库，并重新set到redis");
        String dataUserPv = dataCleaningService.selectEchartsByLoginInfo();
        redisClient.set(Constants.REDIS_KEY_ECHARTS_USER_PV,dataUserPv);
        return dataUserPv;

    }
}
