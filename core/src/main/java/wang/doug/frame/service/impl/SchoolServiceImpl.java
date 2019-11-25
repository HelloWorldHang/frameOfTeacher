package wang.doug.frame.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import wang.doug.frame.dao.SchoolMapper;
import wang.doug.frame.model.School;
import wang.doug.frame.model.SchoolExample;
import wang.doug.frame.service.ISchoolService;

import java.util.List;


@Service
public class SchoolServiceImpl implements ISchoolService {

    @Autowired
    private SchoolMapper schoolMapper;

    public List<School> query() {
        SchoolExample example = new SchoolExample();


        List<School> schoolList = this.schoolMapper.selectByExample(example);

        //List<School> schoolList = this.schoolMapper.selectByExampleWithBLOBs(example);

        return schoolList;
    }

    /**
     * 总数
     *
     * @return
     */
    @Override
    public long total() {
        SchoolExample example = new SchoolExample();

        return this.schoolMapper.countByExample(example);
    }

    /**
     * 查询数量
     *
     * @param nameLike
     * @return
     */
    @Override
    public long countByName(String nameLike) {

        SchoolExample example = new SchoolExample();

        example.createCriteria().andNameLike("%" + nameLike + "%");


        return this.schoolMapper.countByExample(example);

    }

    /**
     * 分页查询
     *
     * @param rowIndex
     * @param pageSize
     * @return
     */
    @Override
    public List<School> query(long rowIndex, int pageSize) {

        SchoolExample example = new SchoolExample();

        example.setRowIndex(rowIndex);
        example.setPageSize(pageSize);

        List<School> schoolList = this.schoolMapper.selectByExample(example);

        return schoolList;
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
    public List<School> queryByName(String nameLike, long rowIndex, int pageSize) {
        SchoolExample example = new SchoolExample();

        example.setRowIndex(rowIndex);
        example.setPageSize(pageSize);
        example.createCriteria().andNameLike("%" + nameLike + "%");

        List<School> schoolList = this.schoolMapper.selectByExample(example);

        return schoolList;
    }

    /**
     * 插入
     *
     * @param school
     */
    @Override
    public int insert(School school) {

        return this.schoolMapper.insert(school);

    }

    /**
     * 根据ID查询
     *
     * @param id
     * @return
     */
    @Override
    public School loadById(int id) {
        SchoolExample example = new SchoolExample();
        example.createCriteria().andIdEqualTo(id);

        List<School> schoolList = this.schoolMapper.selectByExampleWithBLOBs(example);

        return schoolList.isEmpty() ? null : schoolList.get(0);

    }

    /**
     * 根据ID更新
     *
     * @param school
     */
    @Override
    public int update(School school) {
        SchoolExample example = new SchoolExample();
        example.createCriteria().andIdEqualTo(school.getId());
        return this.schoolMapper.updateByExampleWithBLOBs(school, example);
    }

    /**
     * 根据ID删除
     *
     * @param id
     */
    @Override
    public int delete(int id) {
        SchoolExample example = new SchoolExample();
        example.createCriteria().andIdEqualTo(id);
        return this.schoolMapper.deleteByExample(example);
    }
}