package wang.doug.frame.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import wang.doug.frame.model.UserState;
import wang.doug.frame.model.UserStateExample;

public interface UserStateMapper {
    long countByExample(UserStateExample example);

    int deleteByExample(UserStateExample example);

    int deleteByPrimaryKey(Short id);

    int insert(UserState record);

    int insertSelective(UserState record);

    List<UserState> selectByExample(UserStateExample example);

    UserState selectByPrimaryKey(Short id);

    int updateByExampleSelective(@Param("record") UserState record, @Param("example") UserStateExample example);

    int updateByExample(@Param("record") UserState record, @Param("example") UserStateExample example);

    int updateByPrimaryKeySelective(UserState record);

    int updateByPrimaryKey(UserState record);
}