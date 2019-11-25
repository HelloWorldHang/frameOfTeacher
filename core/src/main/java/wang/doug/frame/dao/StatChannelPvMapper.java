package wang.doug.frame.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import wang.doug.frame.model.StatChannelPv;
import wang.doug.frame.model.StatChannelPvExample;

public interface StatChannelPvMapper {
    long countByExample(StatChannelPvExample example);

    int deleteByExample(StatChannelPvExample example);

    int insert(StatChannelPv record);

    int insertSelective(StatChannelPv record);

    List<StatChannelPv> selectByExample(StatChannelPvExample example);

    int updateByExampleSelective(@Param("record") StatChannelPv record, @Param("example") StatChannelPvExample example);

    int updateByExample(@Param("record") StatChannelPv record, @Param("example") StatChannelPvExample example);
}