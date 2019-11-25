package wang.doug.frame.service;

import wang.doug.frame.model.School;

import java.util.List;

public interface ISchoolService {
    /**
     * 查询所有
     *
     * @return
     */
    List<School> query();

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
    List<School> query(long rowIndex, int pageSize);


    /**
     * 分页查询
     *
     * @param nameLike 名称
     * @param rowIndex 开始记录索引
     * @param pageSize 查询数量
     * @return
     */
    List<School> queryByName(String nameLike, long rowIndex, int pageSize);

    /**
     * 插入
     *
     * @param school
     */
    int insert(School school);

    /**
     * 根据ID查询
     *
     * @param id
     * @return
     */
    School loadById(int id);

    /**
     * 根据ID更新
     *
     * @param school 学校
     * @return 更新的记录数
     */
    int update(School school);

    /**
     * 根据ID删除
     *
     * @param id ID
     * @return 删除的记录数
     */
    int delete(int id);
}
