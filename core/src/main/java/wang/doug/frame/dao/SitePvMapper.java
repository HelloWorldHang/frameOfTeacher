package wang.doug.frame.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import wang.doug.frame.model.SitePv;
import wang.doug.frame.model.SitePvExample;

public interface SitePvMapper {
    long countByExample(SitePvExample example);

    int deleteByExample(SitePvExample example);

    int insert(SitePv record);

    int insertSelective(SitePv record);

    List<SitePv> selectByExample(SitePvExample example);

    int updateByExampleSelective(@Param("record") SitePv record, @Param("example") SitePvExample example);

    int updateByExample(@Param("record") SitePv record, @Param("example") SitePvExample example);
}