package com.mzj.cms.controller.rest.restful;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.mzj.cms.architect.utils.Utils;
import com.mzj.cms.domain.dto.Param;
import com.mzj.cms.domain.dto.Result;
import com.mzj.cms.domain.homeMessage.HomeMessage;
import com.mzj.cms.domain.homeMessage.SubjectMessage;
import com.mzj.cms.domain.homeMessage.SubjectPeopel;
import com.mzj.cms.service.homeMessage.HomeMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @auther: LiuHonghui
 * @date: 2019/3/19 15:14
 * @classname: HomeMessaRest
 */
@RequestMapping("ws/homeMessage")
@RestController
public class HomeMessaRest {
    @Autowired
    private HomeMessageService homeMessageService;
    /**
     * 根据身份证号查询平困户户主信息
     * @param request
     * @return
     */
    @RequestMapping("/getHomeMessageByCardNum")
    @ResponseBody
    public Result getHomeMessageByCardNum(HttpServletRequest request) {
        Result r = new Result(Result.RESULT_FAILURE, "系统繁忙!");
        try {
            Param param = (Param) request.getAttribute("param");
            JSONObject jobject = JSON.parseObject(param.getData().toString());
            if (!Utils.Str.isEmpty(jobject.getString("cardNum"))) {
                String cardNum = jobject.getString("cardNum");
                List<HomeMessage> homeMessagesList = homeMessageService.findHomeMessageByCardNum(cardNum);
                r.setTotal(1);
                r.setData(homeMessagesList);
                r.setCode(Result.RESULT_SUCCESS);
                r.setMsg("获取数据成功！");
            }else{
                r.setTotal(0);
                r.setMsg("请输入身份证号");
                return r ;
            }

        } catch (Exception e) {
            e.printStackTrace();
            return new Result(Result.RESULT_FAILURE, "系统异常，请联系网络管理员!");
        }
        return r;
    }
    /**
     * 分页查询贫困户信息
     * @param request
     * @return
     */
    public Result getHomeMessagePage(HttpServletRequest request){
        Result r = new Result(Result.RESULT_FAILURE, "系统繁忙!");
        try {
            Param param = (Param) request.getAttribute("param");
            JSONObject jobject = JSON.parseObject(param.getData().toString());
            Integer pageNo = 1;
            Integer pageSize = 10;
            HomeMessage hm = new HomeMessage();
            if (!Utils.Str.isEmpty(jobject.getString("pageNo"))) {
                pageNo = jobject.getInteger("pageNo");
            }
            if (!Utils.Str.isEmpty(jobject.getString("pageSize"))) {
                pageSize = jobject.getInteger("pageSize");
            }
            if (!Utils.Str.isEmpty(jobject.getString("homeMessage"))) {
                hm.setHomeMessage(jobject.getString("homeMessage"));
            }
            if (!Utils.Str.isEmpty(jobject.getString("cardNum"))) {
                hm.setCardNum(jobject.getString("cardNum"));
            }
            hm.setPage(pageNo);
            hm.setLimit(pageSize);
            String jsList =  homeMessageService.selectHomeMessageListByPage(hm);
            r.setTotal(1);
            r.setData(jsList);
            r.setCode(Result.RESULT_SUCCESS);
            r.setMsg("获取数据成功！");
        }catch (Exception e) {
        e.printStackTrace();
        return new Result(Result.RESULT_FAILURE, "系统异常，请联系网络管理员!");
        }
        return r;

    }












    //主体模块相关





    /**
     * 新增主体信息
     * @param request
     * @return
     */
    @RequestMapping("/addSubjectMessage")
    @ResponseBody
    public Result addSubjectMessage(HttpServletRequest request) {
        Result r = new Result(Result.RESULT_FAILURE, "系统繁忙!");
        try {
            Param param = (Param) request.getAttribute("param");
            JSONObject jobject = JSON.parseObject(param.getData().toString());
            SubjectMessage subjectMessage = jobject.getObject("subjectMessage" ,SubjectMessage.class);
            int num =  homeMessageService.addSubjectMessage(subjectMessage);
            r.setTotal(1);
            r.setData(num);
            r.setCode(Result.RESULT_SUCCESS);
            r.setMsg("新增成功！");
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(Result.RESULT_FAILURE, "系统异常，请联系网络管理员!");
        }
        return r;
    }

    /**
     * 新增主体员工
     * @param request
     * @return
     */
    @RequestMapping("/addSubjectPeople")
    @ResponseBody
    public Result addSubjectPeople(HttpServletRequest request) {
        Result r = new Result(Result.RESULT_FAILURE, "系统繁忙!");
        try {
            Param param = (Param) request.getAttribute("param");
            JSONObject jobject = JSON.parseObject(param.getData().toString());
            SubjectPeopel subjectPeopel = jobject.getObject("subjectPeopel" ,SubjectPeopel.class);
            int num =  homeMessageService.addSubjectPeople(subjectPeopel);
            r.setTotal(1);
            r.setData(num);
            r.setCode(Result.RESULT_SUCCESS);
            r.setMsg("新增成功！");
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(Result.RESULT_FAILURE, "系统异常，请联系网络管理员!");
        }
        return r;
    }

    /**
     * 分页查询主体信息
     * @param request
     * @return
     */
    @RequestMapping("/getSubjectMessagePage")
    @ResponseBody
    public Result getSubjectMessagePage(HttpServletRequest request){
        Result r = new Result(Result.RESULT_FAILURE, "系统繁忙!");
        try {
            Param param = (Param) request.getAttribute("param");
            JSONObject jobject = JSON.parseObject(param.getData().toString());
            Integer pageNo = 1;
            Integer pageSize = 10;
            SubjectMessage sm = new SubjectMessage();
            if (!Utils.Str.isEmpty(jobject.getString("pageNo"))) {
                pageNo = jobject.getInteger("pageNo");
            }
            if (!Utils.Str.isEmpty(jobject.getString("pageSize"))) {
                pageSize = jobject.getInteger("pageSize");
            }
            if (!Utils.Str.isEmpty(jobject.getString("subjectName"))) {
                sm.setSubjectName(jobject.getString("subjectName"));
            }
            if (!Utils.Str.isEmpty(jobject.getString("subjectManager"))) {
                sm.setSubjectManager(jobject.getString("subjectManager"));
            }
            sm.setPage(pageNo);
            sm.setLimit(pageSize);
            String jsList = homeMessageService.selectSubjectMessageListByPage(sm);
            r.setTotal(1);
            r.setData(jsList);
            r.setCode(Result.RESULT_SUCCESS);
            r.setMsg("获取数据成功！");
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(Result.RESULT_FAILURE, "系统异常，请联系网络管理员!");
        }
        return r;
    }
    /**
     * 根据主体id查询帮扶主体员工
     * @param request
     * @return
     */
    @RequestMapping("/getSubjectMessageByName")
    @ResponseBody
    public Result getSubjectMessageByName(HttpServletRequest request){
        Result r = new Result(Result.RESULT_FAILURE, "系统繁忙!");
        try{
            Param param = (Param) request.getAttribute("param");
            JSONObject jobject = JSON.parseObject(param.getData().toString());
            Map<String,Object> paramMap = new HashMap<>();
            if (null!=(jobject.getInteger("id"))) {
                String js = homeMessageService.selectSubjectPeopleList(jobject.getInteger("id"));
                r.setTotal(1);
                r.setData(js);
                r.setCode(Result.RESULT_SUCCESS);
                r.setMsg("获取数据成功！");
            }else {
                r.setMsg("主体id为空");
            }
        }catch(Exception e) {
            e.printStackTrace();
            return new Result(Result.RESULT_FAILURE, "系统异常，请联系网络管理员!");
        }
        return r;
    }
}
