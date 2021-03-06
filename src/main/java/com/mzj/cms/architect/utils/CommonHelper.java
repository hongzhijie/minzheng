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
package com.mzj.cms.architect.utils;

import com.mzj.cms.architect.view.ExcelView;
import com.mzj.cms.domain.bo.ExcelExport;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;



/**
 * 公共帮助类
 *
 * @author hzj
 * @date 2017/8/5
 *
 */
public class CommonHelper{
	
	
	/**
	 * 获得excel model and view
	 * 
	 * @param excelExportBean
	 * @return excel model and view
	 */
	public static ModelAndView getExcelModelAndView(Object excelExportBean){
		return getExcelModelAndView(excelExportBean, null);
	}
	
	/**
	 * 获得excel model and view
	 * 
	 * @param excelExportBean
	 * @return excel model and view
	 */
	public static ModelAndView getExcelModelAndView(Object excelExportBean, String excelName){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(ExcelView.EXCEL_EXPORT_BEAN, excelExportBean);
		map.put(ExcelView.EXCEL_EXPORT_NAME, excelName);
		if(excelExportBean instanceof ExcelExport){
			map.put(ExcelView.EXCEL_EXPORT_TYPE, ExcelView.EXCEL_EXPORT_TYPE_SINGLE_SHEET);
		}else{
			map.put(ExcelView.EXCEL_EXPORT_TYPE, ExcelView.EXCEL_EXPORT_TYPE_MULTIPLE_SHEET);
		}
		return new ModelAndView(new ExcelView(), map);
	}

}
