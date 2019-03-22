package com.mzj.cms.controller.rest;


import com.mzj.cms.domain.dto.Param;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/*
 * @Author hzj
 * @ClassName Rest
 * @Description 接口前缀
 * @Date 14:31 2019/1/14
 * @Param 
 * @return 
 **/
@RestController
@RequestMapping("ws")
public class Rest {
    @RequestMapping("rest")
    public ModelAndView restmain(HttpServletRequest request) {
    	//参数对象
		Param param = new Param();
		//要访问的接口名
		param.setCmd(request.getParameter("cmd"));
		//传入的数据
		param.setData(request.getParameter("data"));
		//版本号
		param.setVersion(request.getParameter("version"));
		//放入到request里面，这样后面的链接可以获取到
		request.setAttribute("param", param);
		return new ModelAndView("forward:/ws/" + param.getCmd());
    }
}
