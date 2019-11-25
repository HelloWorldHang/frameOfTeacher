package wang.doug.frame.service;

import wang.doug.frame.model.StatMonthPv;

import java.util.List;

public interface IStatMonthPvService {
    /**
     * 查询所有
     *
     * @return
     */
    List<StatMonthPv> query();




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
    List<StatMonthPv> query(long rowIndex, int pageSize);


    /**
     * 分页查询
     *
     * @param sysId 系统
     * @param year 年
     * @param rowIndex 开始记录索引
     * @param pageSize 查询数量
     * @return
     */
    List<StatMonthPv> query(int sysId,short year, long rowIndex, int pageSize);

    /**
     * 插入
     *
     * @param statMonthPv
     */
    int insert(StatMonthPv statMonthPv);

    /**
     * 根据ID查询
     *
     * @param id
     * @return
     */
    StatMonthPv loadById(int id);

    /**
     * 根据ID更新
     *
     * @param statMonthPv 系统
     * @return 更新的记录数
     */
    int update(StatMonthPv statMonthPv);

    /**
     * 根据ID删除
     *
     * @param id ID
     * @return 删除的记录数
     */
    int delete(int id);

    /**
     * 数量
     * @param sysId
     * @param year
     * @return
     */
    long count(int sysId, short year);

    /**
     *
     * @param id
     * @param year
     * @param month
     * @return
     */
    StatMonthPv load(int id, short year, short month);


}
