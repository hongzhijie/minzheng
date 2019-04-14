package com.mzj.cms.service.homeMessage;

import com.mzj.cms.dao.homeMessa.HomeMessageMapper;
import com.mzj.cms.domain.homeMessage.HomeMessage;
import com.mzj.cms.domain.homeMessage.SubjectMessage;
import com.mzj.cms.domain.homeMessage.SubjectPeopel;
import org.nutz.json.Json;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @auther: LiuHonghui
 * @date: 2019/3/20 16:40
 * @classname: HomeMessageService
 */
@Service
public class HomeMessageService {

    @Autowired
    private HomeMessageMapper homeMessageMapper;

    /**
     * 根据身份证号查询HomeMessage
     * @param cardNum
     * @return
     */
    public List<HomeMessage> findHomeMessageByCardNum(String cardNum){
        return homeMessageMapper.findHomeMessageByCardNum(cardNum);
    }

    /**
     * 新增主体
     * @param subjectMessage
     * @return
     */
    public int addSubjectMessage(SubjectMessage subjectMessage){
        return homeMessageMapper.addSubjectMessage(subjectMessage);
    }
    /**
     * 新增主体员工
     * @param subjectPeopel
     * @return
     */
    public int addSubjectPeople(SubjectPeopel subjectPeopel){
        return homeMessageMapper.addSubjectPeople(subjectPeopel);
    }

    /**
     * 分页查询救助核查信息
     * @param homeMessage
     * @return
     */
    public String selectHomeMessageListByPage(HomeMessage homeMessage){
        List<HomeMessage> list = homeMessageMapper.selectHomeMessageListByPage(homeMessage);
        Long count = homeMessageMapper.selectCountHomeMessage(homeMessage);

        Map<String, Object> map = new HashMap<String, Object>();

        map.put("code",0);
        map.put("msg","");
        map.put("count",count);
        map.put("data", list);

        return Json.toJson(map);
    }
    /**
     * 分页查询主体信息
     * @param subjectMessage
     * @return
     */
    public String selectSubjectMessageListByPage(SubjectMessage subjectMessage){
        List<SubjectMessage> list = homeMessageMapper.selectSubjectMessageListByPage(subjectMessage);
        Long count = homeMessageMapper.selectCountSubjectMessage(subjectMessage);

        Map<String, Object> map = new HashMap<String, Object>();

        map.put("code",0);
        map.put("msg","");
        map.put("count",count);
        map.put("data", list);

        return Json.toJson(map);
    }

    /**
     * 主键查询主体信息
     * @param id
     * @return
     */
    public SubjectMessage findSubjectMessageById(Integer id){
        return homeMessageMapper.findSubjectMessageById(id);
    }

    /**
     * 修改主体信息
     * @param subjectMessage
     */
    public  void updateSubjectMessage(SubjectMessage subjectMessage){
        homeMessageMapper.updateSubjectMessage(subjectMessage);
    }

    /**
     * 根据主体id查询主体员工信息
     * @param id
     * @return
     */
    public  String selectSubjectPeopleList(Integer id){
        List<SubjectPeopel> list  = homeMessageMapper.selectSubjectPeopleList(id);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("code",0);
        map.put("msg","");
        map.put("count",list.size());
        map.put("data", list);
        return Json.toJson(map);

    }

}
