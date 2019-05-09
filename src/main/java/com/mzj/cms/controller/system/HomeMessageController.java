package com.mzj.cms.controller.system;

import com.mzj.cms.architect.annotation.SystemControllerLog;
import com.mzj.cms.architect.constant.BussinessCode;
import com.mzj.cms.architect.utils.BussinessMsgUtil;
import com.mzj.cms.domain.bo.BussinessMsg;
import com.mzj.cms.domain.dto.Result;
import com.mzj.cms.domain.homeMessage.HomeMessage;
import com.mzj.cms.domain.homeMessage.MzFile;
import com.mzj.cms.domain.homeMessage.SubjectMessage;
import com.mzj.cms.domain.homeMessage.SubjectPeopel;
import com.mzj.cms.service.homeMessage.HomeMessageService;
import com.mzj.cms.service.homeMessage.MzFileService;
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
import java.util.List;
import java.util.UUID;

/**
 * @auther: LiuHonghui
 * @date: 2019/3/31 13:46
 * @classname: HomeMessageController
 */
@Controller
@RequestMapping("homeMessage")
public class HomeMessageController {

    private static final Logger logger = LoggerFactory.getLogger(HomeMessageController.class);

    @Autowired
    private HomeMessageService homeMessageService;

    //文件存储service
    @Autowired
    private MzFileService mzFileService;

    // 文件上传路径
    @Value("${spring.http.multipart.location}")
    private String filePath;
    // 文件前缀地址
    @Value("${upload.images.prefixUrl}")
    private String imgPrefix;

    /**
     *跳转到角色列表页面
     * @return
     */
    @RequestMapping("/homeMessage_list.do")
    public String toHomeMessageListPage() {
        return "homeMessage/homeMessage_list";
    }

    /**
     * 加载贫困户实体List
     * @param homeMessage 角色实体
     * @return
     */
    @RequestMapping("/ajax_homeMessage_list.do")
    @ResponseBody
    public String ajaxHomeMessageList(HomeMessage homeMessage){
        return homeMessageService.selectHomeMessageListByPage(homeMessage);
    }

    /**
     *跳转到主体列表页面
     * @return
     */
    @RequestMapping("/subjectMessage_list.do")
    public String toSubjecttPage() {
        return "homeMessage/subjectMessage_list";
    }

    /**
     * 加载主体实体List
     * @param subjectMessage 角色实体
     * @return
     */
    @RequestMapping("/ajax_subjectMessage_list.do")
    @ResponseBody
    public String ajaxSubjectList(SubjectMessage subjectMessage){
        return homeMessageService.selectSubjectMessageListByPage(subjectMessage);
    }
    /**
     * 跳转到主体信息修改页面
     * @param id 主键id
     * @return
     */
    @RequestMapping("/subjectMessage_update.do")
    public String roleUpdatePage(Model model, Integer id){
        SubjectMessage subjectMessage = homeMessageService.findSubjectMessageById(id);
        //修改页面标识
        model.addAttribute("pageFlag", "updatePage");
        model.addAttribute("subjectMessage", subjectMessage);
        return "homeMessage/subjectMessage_edit";
    }
    /**
     * 跳转到主体员工信息修改页面
     * @param id 主键id
     * @return
     */
    @RequestMapping("/subjectPeople_update.do")
    public String roleUpdatePage2(Model model, Integer id){
        SubjectPeopel subjectPeopel = homeMessageService.findSubjectPeopleById(id);
        //修改页面标识
        model.addAttribute("pageFlag", "updatePage");
        model.addAttribute("subjectPeople", subjectPeopel);
        return "homeMessage/subjectPeople_edit";
    }
    /**
     * 修改主体信息
     * @param subjectMessage 角色实体
     * @return
     */
    @RequestMapping("/updateSubjectMessage.do")
    @ResponseBody
    @SystemControllerLog(description="保存角色信息")
    public BussinessMsg updateSubjectMessage(SubjectMessage subjectMessage){
        try {
            homeMessageService.updateSubjectMessage(subjectMessage);
            return BussinessMsgUtil.returnCodeMessage(BussinessCode.GLOBAL_SUCCESS);
        } catch (Exception e) {
            return BussinessMsgUtil.returnCodeMessage(BussinessCode.ROLE_SAVE_ERROR);
        }
    }
    /**
     * 修改主体员工信息
     * @param subjectPeopel 员工实体
     * @return
     */
    @RequestMapping("/updateSubjectPeople.do")
    @ResponseBody
    @SystemControllerLog(description="保存角色信息")
    public BussinessMsg updateSubjectPeople(SubjectPeopel subjectPeopel){
        try {
            homeMessageService.updateSubjectPeople(subjectPeopel);
            return BussinessMsgUtil.returnCodeMessage(BussinessCode.GLOBAL_SUCCESS);
        } catch (Exception e) {
            return BussinessMsgUtil.returnCodeMessage(BussinessCode.ROLE_SAVE_ERROR);
        }
    }
    /**
     *跳转到主体员工列表页面
     * @return
     */
    @RequestMapping("/subjectPeopleList.do")
    public String tosubjectPeopleList(Model model, Integer id) {
        model.addAttribute("id", id);
        return "homeMessage/subjectPeople_list";
    }

    /**
     * 加载主体员工实体List
     * @param id 角色实体
     * @return
     */
    @RequestMapping("/ajax_subjectPeople_list.do")
    @ResponseBody
    public String ajaxsubjectPeopleList(Integer id , SubjectPeopel subjectPeopel){
        return homeMessageService.selectSubjectPeopleList(id);
    }

    /**
     * 获取主体图片录音列表
     * @param roleId 角色Id
     * @return
     */
    @RequestMapping("/mzfile_more.do")
    public String mzfileMore(Model model,Integer homeMessageId){
        MzFile mzFile = new MzFile();
        mzFile.setOpenId(homeMessageId);
        mzFile.setOpenType(0);
        List<MzFile> mzFileList = mzFileService.getMzfileMore(mzFile);
        model.addAttribute("mzFileList", mzFileList);
        model.addAttribute("homeMessageId", homeMessageId);
        return "homeMessage/mzfile_more";
    }

    /**
     * 主键删除图片录音
     * @param id
     * @return
     */
    @RequestMapping("/delete_mzfile.do")
    @ResponseBody
    @SystemControllerLog(description="主键删除图片录音")
    public BussinessMsg deleteMzfile(HttpServletRequest request){
        try {
            Integer mzfileId = Integer.parseInt(request.getParameter("mzfileId"));
            mzFileService.deleteByPrimaryKey(mzfileId);
            return BussinessMsgUtil.returnCodeMessage(BussinessCode.GLOBAL_SUCCESS);
        } catch (Exception e) {
            return BussinessMsgUtil.returnCodeMessage(BussinessCode.ROLE_SAVE_ERROR);
        }
    }

    //上传文件请求
    @RequestMapping(value = "/moreUpload")
    @ResponseBody
    public Result moreUpload(@RequestParam("file") MultipartFile file,
                         @RequestParam("homeMessageId")Integer homeMessageId) {
        Result result = new Result(1,"上传成功!");
//        List<MultipartFile> list = file.getFiles("file");
        if (file.isEmpty()) {
            result.setCode(9);
            result.setMsg("上传失败,文件为空!");
        }else {
//            for (MultipartFile files : list){
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
                    MzFile mzFile = new MzFile();
                    mzFile.setOpenId(homeMessageId);
                    mzFile.setOpenType(0);
                    mzFile.setFileType(0);
                    mzFile.setFileUrl(url);
                    mzFile.setStatus(0);
                    mzFileService.insertSelective(mzFile);
                } catch (IllegalStateException e) {
                    e.printStackTrace();
                    result.setCode(9);
                    result.setMsg("上传失败!");
                } catch (IOException e) {
                    e.printStackTrace();
                    result.setCode(9);
                    result.setMsg("上传失败!");
                }
//            }
        }
        return result;
    }

}
