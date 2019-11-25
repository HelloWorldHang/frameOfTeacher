package wang.doug.frame.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import wang.doug.frame.model.StatYearPv;
import wang.doug.frame.model.StatYearPvExample;

public interface StatYearPvMapper {
    long countByExample(StatYearPvExample example);

    int deleteByExample(StatYearPvExample example);

    int insert(StatYearPv record);

    int insertSelective(StatYearPv record);

    List<StatYearPv> selectByExample(StatYearPvExample example);

    int updateByExampleSelective(@Param("record") StatYearPv record, @Param("example") StatYearPvExample example);

    int updateByExample(@Param("record") StatYearPv record, @Param("example") StatYearPvExample example);
}