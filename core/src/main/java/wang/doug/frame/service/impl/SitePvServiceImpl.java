package wang.doug.frame.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import wang.doug.frame.dao.SitePvMapper;
import wang.doug.frame.model.SitePv;
import wang.doug.frame.model.SitePvExample;
import wang.doug.frame.service.ISitePvService;

import java.util.List;


@Service
public class SitePvServiceImpl implements ISitePvService {

    @Autowired
    private SitePvMapper sitePvMapper;

    public List<SitePv> query() {
        SitePvExample example = new SitePvExample();


        List<SitePv> sitePvList = this.sitePvMapper.selectByExample(example);

        //List<SitePv> sitePvList = this.sitePvMapper.selectByExampleWithBLOBs(example);

        return sitePvList;
    }

    /**
     * 总数
     *
     * @return
     */
    @Override
    public long total() {
        SitePvExample example = new SitePvExample();

        return this.sitePvMapper.countByExample(example);
    }

    /**
     * 查询数量
     *
     * @param nameLike
     * @return
     */
    @Override
    public long countByName(String nameLike) {

        SitePvExample example = new SitePvExample();

       // example.createCriteria().andNameLike("%" + nameLike + "%");


        return this.sitePvMapper.countByExample(example);

    }

    /**
     * 分页查询
     *
     * @param rowIndex
     * @param pageSize
     * @return
     */
    @Override
    public List<SitePv> query(long rowIndex, int pageSize) {

        SitePvExample example = new SitePvExample();

        example.setRowIndex(rowIndex);
        example.setPageSize(pageSize);

        List<SitePv> sitePvList = this.sitePvMapper.selectByExample(example);

        return sitePvList;
    }


    /**
     * 分页查询
     *
     * @param ipLike IP
     * @param sort 排序
     * @param rowIndex 开始记录索引
     * @param pageSize 查询数量
     * @return
     */
    @Override
    public List<SitePv> queryByIp(String ipLike,String sort, long rowIndex, int pageSize) {
        SitePvExample example = new SitePvExample();

        example.setRowIndex(rowIndex);
        example.setPageSize(pageSize);
        //根据IP地址查询
       example.createCriteria().andIpLike("%" + ipLike + "%");
       example.setOrderByClause(sort);



        List<SitePv> sitePvList = this.sitePvMapper.selectByExample(example);

        return sitePvList;
    }

    /**
     * 插入
     *
     * @param sitePv
     */
    @Override
    public int insert(SitePv sitePv) {

        return this.sitePvMapper.insert(sitePv);

    }

    /**
     * 根据ID查询
     *
     * @param id
     * @return
     */
    @Override
    public SitePv loadById(int id) {
        SitePvExample example = new SitePvExample();
        example.createCriteria().andIdEqualTo(id);

        List<SitePv> sitePvList = this.sitePvMapper.selectByExample(example);

        return sitePvList.isEmpty() ? null : sitePvList.get(0);

    }

    /**
     * 根据ID更新
     *
     * @param sitePv
     */
    @Override
    public int update(SitePv sitePv) {
        SitePvExample example = new SitePvExample();
        example.createCriteria().andIdEqualTo(sitePv.getId());
        return this.sitePvMapper.updateByExample(sitePv, example);
    }

    /**
     * 根据ID删除
     *
     * @param id
     */
    @Override
    public int delete(int id) {
        SitePvExample example = new SitePvExample();
        example.createCriteria().andIdEqualTo(id);
        return this.sitePvMapper.deleteByExample(example);
    }

    @Override
    public int count(int sysId, short startYear, short startMonth, short startDay, short endYear, short endMonth, short endDay) {
        return 0;
    }

    /**
     * 统计年的IP
     *
     * @param sysId
     * @param year
     * @return
     */
    @Override
    public long count(int sysId, short year) {

        SitePvExample example = new SitePvExample();
        example.createCriteria().andSysIdEqualTo(sysId).andYearEqualTo(year);


        return this.sitePvMapper.countByExample(example);

    }

    /**
     * 按月统计
     *
     * @param sysId
     * @param year
     * @param month
     * @return
     */
    @Override
    public long count(int sysId, short year, short month) {

        SitePvExample example = new SitePvExample();
        example.createCriteria().andSysIdEqualTo(sysId).andYearEqualTo(year).andMonthEqualTo(month);


        return this.sitePvMapper.countByExample(example);

    }

    /**
     * @param sysId
     * @param year
     * @param month
     * @param channelId
     * @return
     */
    @Override
    public long count(int sysId, short year, short month, int channelId) {

        SitePvExample example = new SitePvExample();
        example.createCriteria().andSysIdEqualTo(sysId).andYearEqualTo(year).andMonthEqualTo(month).andChannelIdEqualTo(channelId);


        return this.sitePvMapper.countByExample(example);
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
    public long count(int sysId, short year, short month, int channelId, int resId) {
        SitePvExample example = new SitePvExample();
        example.createCriteria().andSysIdEqualTo(sysId).andYearEqualTo(year).andMonthEqualTo(month).andChannelIdEqualTo(channelId).andResIdEqualTo(resId);


        return this.sitePvMapper.countByExample(example);
    }

    /**
     * @param sysId
     * @param year
     * @param month
     * @param hour
     * @return
     */
    @Override
    public long countByHour(int sysId, short year, short month, short hour) {
        SitePvExample example = new SitePvExample();
        example.createCriteria().andSysIdEqualTo(sysId).andYearEqualTo(year).andMonthEqualTo(month).andHourEqualTo(hour);


        return this.sitePvMapper.countByExample(example);
    }
}