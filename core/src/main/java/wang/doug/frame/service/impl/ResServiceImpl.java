package wang.doug.frame.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import wang.doug.frame.dao.ResMapper;
import wang.doug.frame.model.Res;
import wang.doug.frame.model.ResExample;
import wang.doug.frame.service.IResService;

import java.util.List;


@Service
public class ResServiceImpl implements IResService {

    @Autowired
    private ResMapper resMapper;

    public List<Res> query() {
        ResExample example = new ResExample();


        List<Res> resList = this.resMapper.selectByExample(example);

        //List<Res> resList = this.resMapper.selectByExampleWithBLOBs(example);

        return resList;
    }

    /**
     * 总数
     *
     * @return
     */
    @Override
    public long total() {
        ResExample example = new ResExample();

        return this.resMapper.countByExample(example);
    }

    /**
     * 查询数量
     *
     * @param nameLike
     * @return
     */
    @Override
    public long countByName(String nameLike) {

        ResExample example = new ResExample();

        example.createCriteria().andNameLike("%" + nameLike + "%");


        return this.resMapper.countByExample(example);

    }

    /**
     * 分页查询
     *
     * @param rowIndex
     * @param pageSize
     * @return
     */
    @Override
    public List<Res> query(long rowIndex, int pageSize) {

        ResExample example = new ResExample();

        example.setRowIndex(rowIndex);
        example.setPageSize(pageSize);

        List<Res> resList = this.resMapper.selectByExample(example);

        return resList;
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
    public List<Res> queryByName(String nameLike, long rowIndex, int pageSize) {
        ResExample example = new ResExample();

        example.setRowIndex(rowIndex);
        example.setPageSize(pageSize);
        example.createCriteria().andNameLike("%" + nameLike + "%");

        List<Res> resList = this.resMapper.selectByExample(example);

        return resList;
    }

    /**
     * 插入
     *
     * @param res
     */
    @Override
    public int insert(Res res) {

        return this.resMapper.insert(res);

    }

    /**
     * 根据ID查询
     *
     * @param id
     * @return
     */
    @Override
    public Res loadById(int id) {
        ResExample example = new ResExample();
        example.createCriteria().andIdEqualTo(id);

        List<Res> resList = this.resMapper.selectByExample(example);

        return resList.isEmpty() ? null : resList.get(0);

    }

    /**
     * 根据ID更新
     *
     * @param res
     */
    @Override
    public int update(Res res) {
        ResExample example = new ResExample();
        example.createCriteria().andIdEqualTo(res.getId());
        return this.resMapper.updateByExample(res, example);
    }

    /**
     * 根据ID删除
     *
     * @param id
     */
    @Override
    public int delete(int id) {
        ResExample example = new ResExample();
        example.createCriteria().andIdEqualTo(id);
        return this.resMapper.deleteByExample(example);
    }

    /**
     * @param code
     * @return
     */
    @Override
    public Res loadByCode(String code) {
        ResExample example = new ResExample();
        example.createCriteria().andCodeEqualTo(code);
        List<Res> resList=this.resMapper.selectByExample(example);

        return resList.isEmpty()?null:resList.get(0);
    }
}