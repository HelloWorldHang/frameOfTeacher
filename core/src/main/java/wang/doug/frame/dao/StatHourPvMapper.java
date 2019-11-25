package wang.doug.frame.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import wang.doug.frame.model.StatHourPv;
import wang.doug.frame.model.StatHourPvExample;

public interface StatHourPvMapper {
    long countByExample(StatHourPvExample example);

    int deleteByExample(StatHourPvExample example);

    int insert(StatHourPv record);

    int insertSelective(StatHourPv record);

    List<StatHourPv> selectByExample(StatHourPvExample example);

    int updateByExampleSelective(@Param("record") StatHourPv record, @Param("example") StatHourPvExample example);

    int updateByExample(@Param("record") StatHourPv record, @Param("example") StatHourPvExample example);
}