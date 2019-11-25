package wang.doug.frame.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import wang.doug.frame.model.Res;
import wang.doug.frame.model.ResExample;

public interface ResMapper {
    long countByExample(ResExample example);

    int deleteByExample(ResExample example);

    int insert(Res record);

    int insertSelective(Res record);

    List<Res> selectByExample(ResExample example);

    int updateByExampleSelective(@Param("record") Res record, @Param("example") ResExample example);

    int updateByExample(@Param("record") Res record, @Param("example") ResExample example);
}