package wang.doug.frame.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import wang.doug.frame.dao.StatYearPvMapper;
import wang.doug.frame.model.StatYearPv;
import wang.doug.frame.model.StatYearPvExample;
import wang.doug.frame.service.IStatYearPvService;

import java.util.List;


@Service
public class StatYearPvServiceImpl implements IStatYearPvService {

    @Autowired
    private StatYearPvMapper statYearPvMapper;

    public List<StatYearPv> query() {
        StatYearPvExample example = new StatYearPvExample();


        List<StatYearPv> statYearPvList = this.statYearPvMapper.selectByExample(example);

        //List<StatYearPv> statYearPvList = this.statYearPvMapper.selectByExampleWithBLOBs(example);

        return statYearPvList;
    }

    /**
     * 总数
     *
     * @return
     */
    @Override
    public long total() {
        StatYearPvExample example = new StatYearPvExample();

        return this.statYearPvMapper.countByExample(example);
    }

    /**
     * 查询数量
     *
     * @param nameLike
     * @return
     */
    @Override
    public long countByName(String nameLike) {

        StatYearPvExample example = new StatYearPvExample();

       // example.createCriteria().andNameLike("%" + nameLike + "%");


        return this.statYearPvMapper.countByExample(example);

    }

    /**
     * 分页查询
     *
     * @param rowIndex
     * @param pageSize
     * @return
     */
    @Override
    public List<StatYearPv> query(long rowIndex, int pageSize) {

        StatYearPvExample example = new StatYearPvExample();

        example.setRowIndex(rowIndex);
        example.setPageSize(pageSize);

        List<StatYearPv> statYearPvList = this.statYearPvMapper.selectByExample(example);

        return statYearPvList;
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
    public List<StatYearPv> queryByName(String nameLike, long rowIndex, int pageSize) {
        StatYearPvExample example = new StatYearPvExample();

        example.setRowIndex(rowIndex);
        example.setPageSize(pageSize);
       // example.createCriteria().andNameLike("%" + nameLike + "%");

        List<StatYearPv> statYearPvList = this.statYearPvMapper.selectByExample(example);

        return statYearPvList;
    }

    /**
     * 插入
     *
     * @param statYearPv
     */
    @Override
    public int insert(StatYearPv statYearPv) {

        return this.statYearPvMapper.insert(statYearPv);

    }

    /**
     * 根据ID查询
     *
     * @param id
     * @return
     */
    @Override
    public StatYearPv loadById(int id) {
        StatYearPvExample example = new StatYearPvExample();
        example.createCriteria().andIdEqualTo(id);

        List<StatYearPv> statYearPvList = this.statYearPvMapper.selectByExample(example);

        return statYearPvList.isEmpty() ? null : statYearPvList.get(0);

    }

    /**
     * 根据ID更新
     *
     * @param statYearPv
     */
    @Override
    public int update(StatYearPv statYearPv) {
        StatYearPvExample example = new StatYearPvExample();
        example.createCriteria().andIdEqualTo(statYearPv.getId());
        return this.statYearPvMapper.updateByExample(statYearPv, example);
    }

    /**
     * 根据ID删除
     *
     * @param id
     */
    @Override
    public int delete(int id) {
        StatYearPvExample example = new StatYearPvExample();
        example.createCriteria().andIdEqualTo(id);
        return this.statYearPvMapper.deleteByExample(example);
    }

    /**
     * @param sysId
     * @param year
     * @return
     */
    @Override
    public StatYearPv load(int sysId, short year) {
        StatYearPvExample example = new StatYearPvExample();
        example.createCriteria().andSysIdEqualTo(sysId).andYearEqualTo(year);

        List<StatYearPv> statYearPvList=this.statYearPvMapper.selectByExample(example);

        return statYearPvList.isEmpty()?null:statYearPvList.get(0);
    }

}