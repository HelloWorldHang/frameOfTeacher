package wang.doug.frame.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import wang.doug.frame.dao.StatResPvMapper;
import wang.doug.frame.model.StatResPv;
import wang.doug.frame.model.StatResPvExample;
import wang.doug.frame.service.IStatResPvService;

import java.util.List;


@Service
public class StatResPvServiceImpl implements IStatResPvService {

    @Autowired
    private StatResPvMapper statResPvMapper;

    public List<StatResPv> query() {
        StatResPvExample example = new StatResPvExample();


        List<StatResPv> statResPvList = this.statResPvMapper.selectByExample(example);

        //List<StatResPv> statResPvList = this.statResPvMapper.selectByExampleWithBLOBs(example);

        return statResPvList;
    }

    /**
     * 总数
     *
     * @return
     */
    @Override
    public long total() {
        StatResPvExample example = new StatResPvExample();

        return this.statResPvMapper.countByExample(example);
    }

    /**
     * 查询数量
     *
     * @param nameLike
     * @return
     */
    @Override
    public long countByName(String nameLike) {

        StatResPvExample example = new StatResPvExample();

       // example.createCriteria().andNameLike("%" + nameLike + "%");


        return this.statResPvMapper.countByExample(example);

    }

    /**
     * 分页查询
     *
     * @param rowIndex
     * @param pageSize
     * @return
     */
    @Override
    public List<StatResPv> query(long rowIndex, int pageSize) {

        StatResPvExample example = new StatResPvExample();

        example.setRowIndex(rowIndex);
        example.setPageSize(pageSize);

        List<StatResPv> statResPvList = this.statResPvMapper.selectByExample(example);

        return statResPvList;
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
    public List<StatResPv> queryByName(String nameLike, long rowIndex, int pageSize) {
        StatResPvExample example = new StatResPvExample();

        example.setRowIndex(rowIndex);
        example.setPageSize(pageSize);
       // example.createCriteria().andNameLike("%" + nameLike + "%");

        List<StatResPv> statResPvList = this.statResPvMapper.selectByExample(example);

        return statResPvList;
    }

    /**
     * 插入
     *
     * @param statResPv
     */
    @Override
    public int insert(StatResPv statResPv) {

        return this.statResPvMapper.insert(statResPv);

    }

    /**
     * 根据ID查询
     *
     * @param id
     * @return
     */
    @Override
    public StatResPv loadById(int id) {
        StatResPvExample example = new StatResPvExample();
        example.createCriteria().andIdEqualTo(id);

        List<StatResPv> statResPvList = this.statResPvMapper.selectByExample(example);

        return statResPvList.isEmpty() ? null : statResPvList.get(0);

    }

    /**
     * 根据ID更新
     *
     * @param statResPv
     */
    @Override
    public int update(StatResPv statResPv) {
        StatResPvExample example = new StatResPvExample();
        example.createCriteria().andIdEqualTo(statResPv.getId());
        return this.statResPvMapper.updateByExample(statResPv, example);
    }

    /**
     * 根据ID删除
     *
     * @param id
     */
    @Override
    public int delete(int id) {
        StatResPvExample example = new StatResPvExample();
        example.createCriteria().andIdEqualTo(id);
        return this.statResPvMapper.deleteByExample(example);
    }

    /**
     * @param sysId
     * @param channelId
     * @param year
     * @param month
     * @return
     */
    @Override
    public long count(int sysId, int channelId, short year, short month) {
        StatResPvExample example = new StatResPvExample();
        example.createCriteria().andChannelIdEqualTo(channelId).andSysIdEqualTo(sysId).andYearEqualTo(year).andMonthEqualTo(month);

        return this.statResPvMapper.countByExample(example);
    }

    /**
     * @param sysId
     * @param channelId
     * @param year
     * @param month
     * @param rowIndex
     * @param pageSize
     * @return
     */
    @Override
    public List<StatResPv> query(int sysId, int channelId, short year, short month, long rowIndex, int pageSize) {

        StatResPvExample example = new StatResPvExample();
        example.createCriteria().andChannelIdEqualTo(channelId).andSysIdEqualTo(sysId).andYearEqualTo(year).andMonthEqualTo(month);

        example.setRowIndex(rowIndex);
        example.setPageSize(pageSize);


        List<StatResPv> statResPvList = this.statResPvMapper.selectByExample(example);

        return statResPvList;

    }

    /**
     * @param sysId
     * @param year
     * @param month
     * @param channelId
     * @param resId
     * @return
     */
    @Override
    public StatResPv load(int sysId, short year, short month, int channelId, int resId) {

        StatResPvExample example = new StatResPvExample();
        example.createCriteria().andChannelIdEqualTo(channelId)
                .andSysIdEqualTo(sysId).andYearEqualTo(year).andMonthEqualTo(month).andChannelIdEqualTo(channelId).andResIdEqualTo(resId);

        List<StatResPv> statResPvList = this.statResPvMapper.selectByExample(example);

        return statResPvList.isEmpty()?null:statResPvList.get(0);
    }
}