package wang.doug.frame.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import wang.doug.frame.dao.StatChannelPvMapper;

import wang.doug.frame.model.StatChannelPvExample;
import wang.doug.frame.model.StatChannelPv;
import wang.doug.frame.service.IStatChannelPvService;


import java.util.List;


@Service
public class StatChannelPvServiceImpl implements IStatChannelPvService {

    @Autowired
    private StatChannelPvMapper statChannelPvMapper;

    public List<StatChannelPv> query() {
        StatChannelPvExample example = new StatChannelPvExample();


        List<StatChannelPv> statChannelPvList = this.statChannelPvMapper.selectByExample(example);

        //List<StatChannelPv> statChannelPvList = this.statChannelPvMapper.selectByExampleWithBLOBs(example);

        return statChannelPvList;
    }

    /**
     * 总数
     *
     * @return
     */
    @Override
    public long total() {
        StatChannelPvExample example = new StatChannelPvExample();

        return this.statChannelPvMapper.countByExample(example);
    }

    /**
     * 查询数量
     *
     * @param nameLike
     * @return
     */
    @Override
    public long countByName(String nameLike) {

        StatChannelPvExample example = new StatChannelPvExample();

       // example.createCriteria().andNameLike("%" + nameLike + "%");


        return this.statChannelPvMapper.countByExample(example);

    }

    /**
     * 分页查询
     *
     * @param rowIndex
     * @param pageSize
     * @return
     */
    @Override
    public List<StatChannelPv> query(long rowIndex, int pageSize) {

        StatChannelPvExample example = new StatChannelPvExample();

        example.setRowIndex(rowIndex);
        example.setPageSize(pageSize);

        List<StatChannelPv> statChannelPvList = this.statChannelPvMapper.selectByExample(example);

        return statChannelPvList;
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
    public List<StatChannelPv> queryByName(String nameLike, long rowIndex, int pageSize) {
        StatChannelPvExample example = new StatChannelPvExample();

        example.setRowIndex(rowIndex);
        example.setPageSize(pageSize);
       // example.createCriteria().andNameLike("%" + nameLike + "%");

        List<StatChannelPv> statChannelPvList = this.statChannelPvMapper.selectByExample(example);

        return statChannelPvList;
    }

    /**
     * 插入
     *
     * @param statChannelPv
     */
    @Override
    public int insert(StatChannelPv statChannelPv) {

        return this.statChannelPvMapper.insert(statChannelPv);

    }

    /**
     * 根据ID查询
     *
     * @param id
     * @return
     */
    @Override
    public StatChannelPv loadById(int id) {
        StatChannelPvExample example = new StatChannelPvExample();
        example.createCriteria().andIdEqualTo(id);

        List<StatChannelPv> statChannelPvList = this.statChannelPvMapper.selectByExample(example);

        return statChannelPvList.isEmpty() ? null : statChannelPvList.get(0);

    }

    /**
     * 根据ID更新
     *
     * @param statChannelPv
     */
    @Override
    public int update(StatChannelPv statChannelPv) {
        StatChannelPvExample example = new StatChannelPvExample();
        example.createCriteria().andIdEqualTo(statChannelPv.getId());
        return this.statChannelPvMapper.updateByExample(statChannelPv, example);
    }

    /**
     * 根据ID删除
     *
     * @param id
     */
    @Override
    public int delete(int id) {
        StatChannelPvExample example = new StatChannelPvExample();
        example.createCriteria().andIdEqualTo(id);
        return this.statChannelPvMapper.deleteByExample(example);
    }

    /**
     * @param sysId
     * @param year
     * @param month
     * @return
     */
    @Override
    public long count(int sysId, short year, short month) {
        StatChannelPvExample example = new StatChannelPvExample();
        example.createCriteria().andSysIdEqualTo(sysId).andYearEqualTo(year).andMonthEqualTo(month);

        return this.statChannelPvMapper.countByExample(example);
    }

    /**
     * @param sysId
     * @param year
     * @param month
     * @param rowIndex
     * @param pageSize
     * @return
     */
    @Override
    public List<StatChannelPv> query(int sysId, short year, short month, long rowIndex, int pageSize) {
        StatChannelPvExample example = new StatChannelPvExample();

        example.setRowIndex(rowIndex);
        example.setPageSize(pageSize);
        example.createCriteria().andSysIdEqualTo(sysId).andYearEqualTo(year).andMonthEqualTo(month);

        List<StatChannelPv> statChannelPvList = this.statChannelPvMapper.selectByExample(example);

        return statChannelPvList;
    }

    /**
     * @param sysId
     * @param year
     * @param month
     * @param channelId
     * @return
     */
    @Override
    public StatChannelPv load(int sysId, short year, short month, int channelId) {
        StatChannelPvExample example = new StatChannelPvExample();
        example.createCriteria().andSysIdEqualTo(sysId).andYearEqualTo(year).andMonthEqualTo(month).andChannelIdEqualTo(channelId);
        List<StatChannelPv> statChannelPvList = this.statChannelPvMapper.selectByExample(example);

        return statChannelPvList.isEmpty()?null:statChannelPvList.get(0);
    }
}