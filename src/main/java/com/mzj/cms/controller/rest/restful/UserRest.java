package com.mzj.cms.controller.rest.restful;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.mzj.cms.architect.utils.Utils;
import com.mzj.cms.domain.bo.Tree;
import com.mzj.cms.domain.dto.Pagination;
import com.mzj.cms.domain.dto.Param;
import com.mzj.cms.domain.dto.Result;
import com.mzj.cms.service.ResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
 * @Author hzj
 * @ClassName UserRest
 * @Description 用户接口类
 * @Date 16:58 2019/1/14
 * @Param
 * @return
 **/
@RequestMapping("ws/user")
@RestController
public class UserRest {

    //用户服务
    @Autowired
    private ResourceService resourceService;

    /**
     * 获取用户列表带分页
     * @param request
     * @return
     */
    @RequestMapping("getUserRoleList")
    @ResponseBody
    public Result getUserRoleList(HttpServletRequest request) {
        Result r = new Result(Result.RESULT_FAILURE, "系统繁忙!");
        try {
            Param param = (Param) request.getAttribute("param");
            JSONObject jobject = JSON.parseObject(param.getData().toString());
            String userName = null;
            if (!Utils.Str.isEmpty(jobject.getString("userName"))) {
                userName = jobject.getString("userName");
            }
            Integer pageNo = 1;
            Integer pageSize = 10;
            if (!Utils.Str.isEmpty(jobject.getString("pageNo"))) {
                pageNo = jobject.getInteger("pageNo");
            }
            if (!Utils.Str.isEmpty(jobject.getString("pageSize"))) {
                pageSize = jobject.getInteger("pageSize");
            }
            Pagination pagination = new Pagination();
            pagination.setPage(pageNo);
            pagination.setRows(pageSize);
            Map<String,Object> paramMap = new HashMap<>();
            paramMap.put("userName",userName);
            List<Tree> roleTree = resourceService.selectResourceAllTree();
            r.setTotal(1);
            r.setData(roleTree);
            r.setCode(Result.RESULT_SUCCESS);
            r.setMsg("获取数据成功！");
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(Result.RESULT_FAILURE, "系统异常，请联系网络管理员!");
        }
        return r;
    }
}
