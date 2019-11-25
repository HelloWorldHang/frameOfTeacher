package wang.doug.frame.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import wang.doug.frame.dao.SysMapper;
import wang.doug.frame.model.Sys;
import wang.doug.frame.model.SysExample;
import wang.doug.frame.service.ISysService;

import java.util.List;


@Service
public class SysServiceImpl implements ISysService {

    @Autowired
    private SysMapper sysMapper;

    public List<Sys> query() {
        SysExample example = new SysExample();


        List<Sys> sysList = this.sysMapper.selectByExample(example);

        //List<Sys> sysList = this.sysMapper.selectByExampleWithBLOBs(example);

        return sysList;
    }

    /**
     * 总数
     *
     * @return
     */
    @Override
    public long total() {
        SysExample example = new SysExample();

        return this.sysMapper.countByExample(example);
    }

    /**
     * 查询数量
     *
     * @param nameLike
     * @return
     */
    @Override
    public long countByName(String nameLike) {

        SysExample example = new SysExample();

        example.createCriteria().andNameLike("%" + nameLike + "%");


        return this.sysMapper.countByExample(example);

    }

    /**
     * 分页查询
     *
     * @param rowIndex
     * @param pageSize
     * @return
     */
    @Override
    public List<Sys> query(long rowIndex, int pageSize) {

        SysExample example = new SysExample();

        example.setRowIndex(rowIndex);
        example.setPageSize(pageSize);

        List<Sys> sysList = this.sysMapper.selectByExample(example);

        return sysList;
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
    public List<Sys> queryByName(String nameLike, long rowIndex, int pageSize) {
        SysExample example = new SysExample();

        example.setRowIndex(rowIndex);
        example.setPageSize(pageSize);
        example.createCriteria().andNameLike("%" + nameLike + "%");

        List<Sys> sysList = this.sysMapper.selectByExample(example);

        return sysList;
    }

    /**
     * 插入
     *
     * @param sys
     */
    @Override
    public int insert(Sys sys) {

        return this.sysMapper.insert(sys);

    }

    /**
     * 根据ID查询
     *
     * @param id
     * @return
     */
    @Override
    public Sys loadById(int id) {
        SysExample example = new SysExample();
        example.createCriteria().andIdEqualTo(id);

        List<Sys> sysList = this.sysMapper.selectByExample(example);

        return sysList.isEmpty() ? null : sysList.get(0);

    }

    /**
     * 根据ID更新
     *
     * @param sys
     */
    @Override
    public int update(Sys sys) {
        SysExample example = new SysExample();
        example.createCriteria().andIdEqualTo(sys.getId());
        return this.sysMapper.updateByExample(sys, example);
    }

    /**
     * 根据ID删除
     *
     * @param id
     */
    @Override
    public int delete(int id) {
        SysExample example = new SysExample();
        example.createCriteria().andIdEqualTo(id);
        return this.sysMapper.deleteByExample(example);
    }
}