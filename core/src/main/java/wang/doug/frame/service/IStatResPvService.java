package wang.doug.frame.service;

import wang.doug.frame.model.StatResPv;

import java.util.List;

public interface IStatResPvService {
    /**
     * 查询所有
     *
     * @return
     */
    List<StatResPv> query();

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
    List<StatResPv> query(long rowIndex, int pageSize);


    /**
     * 分页查询
     *
     * @param nameLike 名称
     * @param rowIndex 开始记录索引
     * @param pageSize 查询数量
     * @return
     */
    List<StatResPv> queryByName(String nameLike, long rowIndex, int pageSize);

    /**
     * 插入
     *
     * @param statResPv
     */
    int insert(StatResPv statResPv);

    /**
     * 根据ID查询
     *
     * @param id
     * @return
     */
    StatResPv loadById(int id);

    /**
     * 根据ID更新
     *
     * @param statResPv 系统
     * @return 更新的记录数
     */
    int update(StatResPv statResPv);

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
     * @param channelId
     * @param year
     * @param month
     * @return
     */
    long count(int sysId, int channelId, short year, short month);

    /**
     *
     * @param sysId
     * @param channelId
     * @param year
     * @param month
     * @param pageIndex
     * @param pageSize
     * @return
     */
    List<StatResPv> query(int sysId, int channelId, short year, short month, long pageIndex, int pageSize);

    /**
     *
     * @param sysId
     * @param year
     * @param month
     * @param channelId
     * @param resId
     * @return
     */
    StatResPv load(int sysId, short year, short month, int channelId, int resId);
}
