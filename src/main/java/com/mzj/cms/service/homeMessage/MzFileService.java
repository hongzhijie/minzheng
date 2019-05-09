/**
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
 * <p>
 * Copyright 2017 © hzj, 873559947@qq.com
 * <p>
 * This file is part of contentManagerSystem.
 * contentManagerSystem is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * <p>
 * contentManagerSystem is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 * <p>
 * You should have received a copy of the GNU Lesser General Public License
 * along with contentManagerSystem.  If not, see <http://www.gnu.org/licenses/>.
 * <p>
 * 这个文件是contentManagerSystem的一部分。
 * 您可以单独使用或分发这个文件，但请不要移除这个头部声明信息.
 * contentManagerSystem是一个自由软件，您可以自由分发、修改其中的源代码或者重新发布它，
 * 新的任何修改后的重新发布版必须同样在遵守GPL3或更后续的版本协议下发布.
 * 关于GPL协议的细则请参考COPYING文件，
 * 您可以在contentManagerSystem的相关目录中获得GPL协议的副本，
 * 如果没有找到，请连接到 http://www.gnu.org/licenses/ 查看。
 * <p>
 * - Author: hzj
 * - Contact: 873559947@qq.com
 * - License: GNU Lesser General Public License (GPL)
 * - source code availability: http://git.oschina.net/hzj_175/contentManagerSystem
 */
package com.mzj.cms.service.homeMessage;

import com.mzj.cms.dao.homeMessa.MzFileMapper;
import com.mzj.cms.domain.homeMessage.MzFile;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 文件存储mapper
 * @author hzj
 * @date 2019/5/9
 */
@Service
public class MzFileService {

    private Logger log = LogManager.getLogger(MzFileService.class);

    //文件存储mapper
    @Autowired
    private MzFileMapper mzFileMapper;

    /**
     * 角色列表信息List
     * @param role 角色实体
     * @return
     */
    public List<MzFile> getMzfileMore(MzFile mzFile){
        List<MzFile> mzFileList = mzFileMapper.selectMzFileList(mzFile);
        return mzFileList;
    }

    /**
     * 根据主键删除图片录音
     * @param id
     * @return
     */
    public void deleteByPrimaryKey(Integer id){
        mzFileMapper.deleteByPrimaryKey(id);
    }

    /**
     * 图片音频新增
     * @param MzFile
     * @return
     */
    public void insertSelective(MzFile mzFile){
        mzFileMapper.insertSelective(mzFile);
    }
}