package wang.doug.frame.service;

import wang.doug.frame.model.StatYearPv;

import java.util.List;

public interface IStatYearPvService {
    /**
     * 查询所有
     *
     * @return
     */
    List<StatYearPv> query();

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
    List<StatYearPv> query(long rowIndex, int pageSize);


    /**
     * 分页查询
     *
     * @param nameLike 名称
     * @param rowIndex 开始记录索引
     * @param pageSize 查询数量
     * @return
     */
    List<StatYearPv> queryByName(String nameLike, long rowIndex, int pageSize);

    /**
     * 插入
     *
     * @param statYearPv
     */
    int insert(StatYearPv statYearPv);

    /**
     * 根据ID查询
     *
     * @param id
     * @return
     */
    StatYearPv loadById(int id);

    /**
     * 根据ID更新
     *
     * @param statYearPv 系统
     * @return 更新的记录数
     */
    int update(StatYearPv statYearPv);

    /**
     * 根据ID删除
     *
     * @param id ID
     * @return 删除的记录数
     */
    int delete(int id);

    /**
     *
     * @param sysId
     * @param year
     * @return
     */
    StatYearPv load(int sysId,short year);

}
