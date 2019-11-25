package wang.doug.frame.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import wang.doug.frame.model.StatMonthPv;
import wang.doug.frame.model.StatMonthPvExample;

public interface StatMonthPvMapper {
    long countByExample(StatMonthPvExample example);

    int deleteByExample(StatMonthPvExample example);

    int insert(StatMonthPv record);

    int insertSelective(StatMonthPv record);

    List<StatMonthPv> selectByExample(StatMonthPvExample example);

    int updateByExampleSelective(@Param("record") StatMonthPv record, @Param("example") StatMonthPvExample example);

    int updateByExample(@Param("record") StatMonthPv record, @Param("example") StatMonthPvExample example);
}