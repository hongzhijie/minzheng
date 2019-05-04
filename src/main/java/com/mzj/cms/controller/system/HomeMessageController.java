package com.mzj.cms.controller.system;

import com.mzj.cms.architect.annotation.SystemControllerLog;
import com.mzj.cms.architect.constant.BussinessCode;
import com.mzj.cms.architect.utils.BussinessMsgUtil;
import com.mzj.cms.domain.bo.BussinessMsg;
import com.mzj.cms.domain.homeMessage.HomeMessage;
import com.mzj.cms.domain.homeMessage.SubjectMessage;
import com.mzj.cms.domain.homeMessage.SubjectPeopel;
import com.mzj.cms.service.homeMessage.HomeMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @auther: LiuHonghui
 * @date: 2019/3/31 13:46
 * @classname: HomeMessageController
 */
@Controller
@RequestMapping("homeMessage")
public class HomeMessageController {

    @Autowired
    private HomeMessageService homeMessageService;

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

}
