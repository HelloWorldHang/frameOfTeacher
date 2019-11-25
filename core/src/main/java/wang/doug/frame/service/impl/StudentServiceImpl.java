package wang.doug.frame.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import wang.doug.frame.dao.StudentMapper;
import wang.doug.frame.model.Student;
import wang.doug.frame.model.StudentExample;
import wang.doug.frame.service.IStudentService;

import java.util.List;


@Service
public class StudentServiceImpl implements IStudentService {

    @Autowired
    private StudentMapper studentMapper;

    public List<Student> query() {
        StudentExample example = new StudentExample();


        List<Student> studentList = this.studentMapper.selectByExample(example);

        //List<Student> studentList = this.studentMapper.selectByExampleWithBLOBs(example);

        return studentList;
    }

    /**
     * 总数
     *
     * @return
     */
    @Override
    public long total() {
        StudentExample example = new StudentExample();

        return this.studentMapper.countByExample(example);
    }

    /**
     * 查询数量
     *
     * @param nameLike
     * @return
     */
    @Override
    public long countByName(String nameLike) {

        StudentExample example = new StudentExample();
        //
       example.createCriteria().andRealNameLike("%" + nameLike + "%");


        return this.studentMapper.countByExample(example);

    }

    /**
     * 分页查询
     *
     * @param rowIndex
     * @param pageSize
     * @return
     */
    @Override
    public List<Student> query(long rowIndex, int pageSize) {

        StudentExample example = new StudentExample();

        //example.setRowIndex(rowIndex);
        //example.setPageSize(pageSize);

        List<Student> studentList = this.studentMapper.selectByExample(example);

        return studentList;
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
    public List<Student> queryByName(String nameLike, long rowIndex, int pageSize) {
        StudentExample example = new StudentExample();

        //example.setRowIndex(rowIndex);
        //example.setPageSize(pageSize);
        example.createCriteria().andRealNameLike("%" + nameLike + "%");

        List<Student> studentList = this.studentMapper.selectByExample(example);

        return studentList;
    }

    /**
     * 插入
     *
     * @param student
     */
    @Override
    public int insert(Student student) {

        return this.studentMapper.insert(student);

    }

    /**
     * 根据ID查询
     *
     * @param id
     * @return
     */
    @Override
    public Student loadById(int id) {
        StudentExample example = new StudentExample();
        example.createCriteria().andIdEqualTo(id);

        List<Student> studentList = this.studentMapper.selectByExampleWithBLOBs(example);

        return studentList.isEmpty() ? null : studentList.get(0);

    }

    /**
     * 根据ID更新
     *
     * @param student
     */
    @Override
    public int update(Student student) {
        StudentExample example = new StudentExample();
        example.createCriteria().andIdEqualTo(student.getId());
        return this.studentMapper.updateByExampleWithBLOBs(student, example);
    }

    /**
     * 根据ID删除
     *
     * @param id
     */
    @Override
    public int delete(int id) {
        StudentExample example = new StudentExample();
        example.createCriteria().andIdEqualTo(id);
        return this.studentMapper.deleteByExample(example);
    }
}