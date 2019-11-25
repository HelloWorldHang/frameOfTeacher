package wang.doug.frame.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import wang.doug.frame.dao.StatHourPvMapper;
import wang.doug.frame.model.StatHourPv;
import wang.doug.frame.model.StatHourPvExample;
import wang.doug.frame.service.IStatHourPvService;

import java.util.List;


@Service
public class StatHourPvServiceImpl implements IStatHourPvService {

    @Autowired
    private StatHourPvMapper statHourPvMapper;

    public List<StatHourPv> query() {
        StatHourPvExample example = new StatHourPvExample();


        List<StatHourPv> statHourPvList = this.statHourPvMapper.selectByExample(example);

        //List<StatHourPv> statHourPvList = this.statHourPvMapper.selectByExampleWithBLOBs(example);

        return statHourPvList;
    }

    /**
     * 总数
     *
     * @return
     */
    @Override
    public long total() {
        StatHourPvExample example = new StatHourPvExample();

        return this.statHourPvMapper.countByExample(example);
    }

    /**
     * 查询数量
     *
     * @param nameLike
     * @return
     */
    @Override
    public long countByName(String nameLike) {

        StatHourPvExample example = new StatHourPvExample();

       // example.createCriteria().andNameLike("%" + nameLike + "%");


        return this.statHourPvMapper.countByExample(example);

    }

    /**
     * 分页查询
     *
     * @param rowIndex
     * @param pageSize
     * @return
     */
    @Override
    public List<StatHourPv> query(long rowIndex, int pageSize) {

        StatHourPvExample example = new StatHourPvExample();

        example.setRowIndex(rowIndex);
        example.setPageSize(pageSize);

        List<StatHourPv> statHourPvList = this.statHourPvMapper.selectByExample(example);

        return statHourPvList;
    }


    /**
     * 分页查询
     *
     * @param nameLike 名称
     * @param rowIndex 开始记录索引
     * @param pageSize 查询数量
     * @return
     */
    @Override
    public List<StatHourPv> queryByName(String nameLike, long rowIndex, int pageSize) {
        StatHourPvExample example = new StatHourPvExample();

        example.setRowIndex(rowIndex);
        example.setPageSize(pageSize);
       // example.createCriteria().andNameLike("%" + nameLike + "%");

        List<StatHourPv> statHourPvList = this.statHourPvMapper.selectByExample(example);

        return statHourPvList;
    }

    /**
     * 插入
     *
     * @param statHourPv
     */
    @Override
    public int insert(StatHourPv statHourPv) {

        return this.statHourPvMapper.insert(statHourPv);

    }

    /**
     * 根据ID查询
     *
     * @param id
     * @return
     */
    @Override
    public StatHourPv loadById(int id) {
        StatHourPvExample example = new StatHourPvExample();
        example.createCriteria().andIdEqualTo(id);

        List<StatHourPv> statHourPvList = this.statHourPvMapper.selectByExample(example);

        return statHourPvList.isEmpty() ? null : statHourPvList.get(0);

    }

    /**
     * 根据ID更新
     *
     * @param statHourPv
     */
    @Override
    public int update(StatHourPv statHourPv) {
        StatHourPvExample example = new StatHourPvExample();
        example.createCriteria().andIdEqualTo(statHourPv.getId());
        return this.statHourPvMapper.updateByExample(statHourPv, example);
    }

    /**
     * 根据ID删除
     *
     * @param id
     */
    @Override
    public int delete(int id) {
        StatHourPvExample example = new StatHourPvExample();
        example.createCriteria().andIdEqualTo(id);
        return this.statHourPvMapper.deleteByExample(example);
    }

    /**
     * @param sysId
     * @param year
     * @param month
     * @param day
     * @param hour
     * @return
     */
    @Override
    public StatHourPv load(int sysId, short year, short month, short day, short hour) {
        StatHourPvExample example = new StatHourPvExample();
        example.createCriteria().andSysIdEqualTo(sysId).andYearEqualTo(year).andMonthEqualTo(month).andDayEqualTo(day).andHourEqualTo(hour);

        List<StatHourPv> statHourPvList = this.statHourPvMapper.selectByExample(example);

        return statHourPvList.isEmpty() ? null : statHourPvList.get(0);
    }

    /**
     * 查询
     *
     * @param sysId
     * @param startYear
     * @param startMonth
     * @param startDay
     * @param startHour
     * @param endYear
     * @param endMonth
     * @param endDay
     * @param endHour
     * @return
     */
    @Override
    public List<StatHourPv> query(int sysId, short startYear, short startMonth, short startDay, short startHour, short endYear, short endMonth, short endDay, short endHour) {
        StatHourPvExample example = new StatHourPvExample();
        example.createCriteria().andSysIdEqualTo(sysId).andYearBetween(startYear,endYear).andMonthBetween(startMonth,endMonth).andDayBetween(startDay,endDay).andHourBetween(startHour,endHour);

        List<StatHourPv> statHourPvList = this.statHourPvMapper.selectByExample(example);

        return statHourPvList;
    }
}