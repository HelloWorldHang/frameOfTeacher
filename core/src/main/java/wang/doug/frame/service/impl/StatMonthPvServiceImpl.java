package wang.doug.frame.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import wang.doug.frame.dao.StatMonthPvMapper;
import wang.doug.frame.model.StatMonthPv;
import wang.doug.frame.model.StatMonthPvExample;
import wang.doug.frame.model.StatYearPv;
import wang.doug.frame.model.StatYearPvExample;
import wang.doug.frame.service.IStatMonthPvService;

import java.util.List;


@Service
public class StatMonthPvServiceImpl implements IStatMonthPvService {

    @Autowired
    private StatMonthPvMapper statMonthPvMapper;

    public List<StatMonthPv> query() {
        StatMonthPvExample example = new StatMonthPvExample();


        List<StatMonthPv> statMonthPvList = this.statMonthPvMapper.selectByExample(example);

        //List<StatMonthPv> statMonthPvList = this.statMonthPvMapper.selectByExampleWithBLOBs(example);

        return statMonthPvList;
    }

    /**
     * 总数
     *
     * @return
     */
    @Override
    public long total() {
        StatMonthPvExample example = new StatMonthPvExample();

        return this.statMonthPvMapper.countByExample(example);
    }

    /**
     * 查询数量
     *
     * @param nameLike
     * @return
     */
    @Override
    public long countByName(String nameLike) {

        StatMonthPvExample example = new StatMonthPvExample();

       // example.createCriteria().andNameLike("%" + nameLike + "%");


        return this.statMonthPvMapper.countByExample(example);

    }

    /**
     * 分页查询
     *
     * @param rowIndex
     * @param pageSize
     * @return
     */
    @Override
    public List<StatMonthPv> query(long rowIndex, int pageSize) {

        StatMonthPvExample example = new StatMonthPvExample();

        example.setRowIndex(rowIndex);
        example.setPageSize(pageSize);

        List<StatMonthPv> statMonthPvList = this.statMonthPvMapper.selectByExample(example);

        return statMonthPvList;
    }


    /**
     *
     * @param sysId
     * @param year
     * @param rowIndex
     * @param pageSize
     * @return
     */
    @Override
    public List<StatMonthPv> query(int sysId, short year, long rowIndex, int pageSize) {
        StatMonthPvExample example = new StatMonthPvExample();

        example.setRowIndex(rowIndex);
        example.setPageSize(pageSize);
        example.createCriteria().andSysIdEqualTo(sysId).andYearEqualTo(year);


        List<StatMonthPv> statMonthPvList = this.statMonthPvMapper.selectByExample(example);

        return statMonthPvList;
    }


    /**
     * 插入
     *
     * @param statMonthPv
     */
    @Override
    public int insert(StatMonthPv statMonthPv) {

        return this.statMonthPvMapper.insert(statMonthPv);

    }

    /**
     * 根据ID查询
     *
     * @param id
     * @return
     */
    @Override
    public StatMonthPv loadById(int id) {
        StatMonthPvExample example = new StatMonthPvExample();
        example.createCriteria().andIdEqualTo(id);

        List<StatMonthPv> statMonthPvList = this.statMonthPvMapper.selectByExample(example);

        return statMonthPvList.isEmpty() ? null : statMonthPvList.get(0);

    }

    /**
     * 根据ID更新
     *
     * @param statMonthPv
     */
    @Override
    public int update(StatMonthPv statMonthPv) {
        StatMonthPvExample example = new StatMonthPvExample();
        example.createCriteria().andIdEqualTo(statMonthPv.getId());
        return this.statMonthPvMapper.updateByExample(statMonthPv, example);
    }

    /**
     * 根据ID删除
     *
     * @param id
     */
    @Override
    public int delete(int id) {
        StatMonthPvExample example = new StatMonthPvExample();
        example.createCriteria().andIdEqualTo(id);
        return this.statMonthPvMapper.deleteByExample(example);
    }

    /**
     * 数量
     *
     * @param sysId
     * @param year
     * @return
     */
    @Override
    public long count(int sysId, short year) {

        StatMonthPvExample example = new StatMonthPvExample();

        example.createCriteria().andSysIdEqualTo(sysId).andYearEqualTo(year);


        return this.statMonthPvMapper.countByExample(example);
    }

    /**
     * @param sysId
     * @param year
     * @param month
     * @return
     */
    @Override
    public StatMonthPv load(int sysId, short year, short month) {
        StatMonthPvExample example = new StatMonthPvExample();

        example.createCriteria().andSysIdEqualTo(sysId).andYearEqualTo(year);


        List<StatMonthPv> statMonthPvList=this.statMonthPvMapper.selectByExample(example);


        return statMonthPvList.isEmpty()?null:statMonthPvList.get(0);
    }
}