package com.mzj.cms.domain.dto;

import java.io.Serializable;

/*
 * @Author hzj
 * @ClassName Param
 * @Description 接口:统一接收参数类型
 * @Date 14:33 2019/1/14
 * @Param 
 * @return 
 **/
public class Param implements Serializable {
	
	private static final long serialVersionUID = 7222428979812453935L;
	private String cmd;// 接口的方法名
	private String version;// 版本号
	private Object data; // 参数

	public Param() {
	}

	public String getCmd() {
		return cmd;
	}

	public void setCmd(String cmd) {
		this.cmd = cmd;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}
}
