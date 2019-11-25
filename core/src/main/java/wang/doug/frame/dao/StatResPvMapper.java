package wang.doug.frame.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import wang.doug.frame.model.StatResPv;
import wang.doug.frame.model.StatResPvExample;

public interface StatResPvMapper {
    long countByExample(StatResPvExample example);

    int deleteByExample(StatResPvExample example);

    int insert(StatResPv record);

    int insertSelective(StatResPv record);

    List<StatResPv> selectByExample(StatResPvExample example);

    int updateByExampleSelective(@Param("record") StatResPv record, @Param("example") StatResPvExample example);

    int updateByExample(@Param("record") StatResPv record, @Param("example") StatResPvExample example);
}