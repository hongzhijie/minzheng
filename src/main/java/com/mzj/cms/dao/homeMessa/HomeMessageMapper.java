package com.mzj.cms.dao.homeMessa;

import com.mzj.cms.domain.homeMessage.HomeMessage;
import com.mzj.cms.domain.homeMessage.SubjectMessage;
import com.mzj.cms.domain.homeMessage.SubjectPeopel;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @auther: LiuHonghui
 * @date: 2019/3/19 15:33
 * @classname: HomeMessageMapper
 */
@Mapper
public interface HomeMessageMapper {
    /**
     * 通过身份证号查询户主信息
     * @param cardNum
     * @return
     */
    List<HomeMessage> findHomeMessageByCardNum(String cardNum);

    int addSubjectMessage(SubjectMessage subjectMessage);

    int addSubjectPeople(SubjectPeopel subjectPeopel);

    int addSubjectPeopel(SubjectPeopel subjectPeopel);

    /**
     * homeMessage信息分页查询
     * @param homeMessage
     * @return
     */
    List<HomeMessage> selectHomeMessageListByPage(HomeMessage homeMessage);

    Long selectCountHomeMessage(HomeMessage homeMessage);

    /**
     * 分页查询主体信息
     * @param subjectMessage
     * @return
     */
    List<SubjectMessage> selectSubjectMessageListByPage(SubjectMessage subjectMessage);
    SubjectMessage findSubjectMessageById(Integer id);
    Long selectCountSubjectMessage(SubjectMessage subjectMessage);
    void updateSubjectMessage(SubjectMessage subjectMessage);

    List<SubjectPeopel> selectSubjectPeopleList(Integer id);
}
