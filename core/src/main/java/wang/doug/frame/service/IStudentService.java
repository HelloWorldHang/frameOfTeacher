package wang.doug.frame.service;

import wang.doug.frame.model.Student;

import java.util.List;

public interface IStudentService {
    /**
     * 查询所有
     *
     * @return
     */
    List<Student> query();

    /**
     * 总数
     *
     * @return
     */
    long total();

    /**
     * 查询数量
     *
     * @param nameLike
     * @return
     */
    long countByName(String nameLike);

    /**
     * 分页查询
     *
     * @param rowIndex 开始记录索引
     * @param pageSize 查询数量
     * @return
     */
    List<Student> query(long rowIndex, int pageSize);


    /**
     * 分页查询
     *
     * @param nameLike 名称
     * @param rowIndex 开始记录索引
     * @param pageSize 查询数量
     * @return
     */
    List<Student> queryByName(String nameLike, long rowIndex, int pageSize);

    /**
     * 插入
     *
     * @param student
     */
    int insert(Student student);

    /**
     * 根据ID查询
     *
     * @param id
     * @return
     */
    Student loadById(int id);

    /**
     * 根据ID更新
     *
     * @param student 学校
     * @return 更新的记录数
     */
    int update(Student student);

    /**
     * 根据ID删除
     *
     * @param id ID
     * @return 删除的记录数
     */
    int delete(int id);
}
