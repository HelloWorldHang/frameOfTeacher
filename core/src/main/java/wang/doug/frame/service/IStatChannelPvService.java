package wang.doug.frame.service;

import wang.doug.frame.model.StatChannelPv;

import java.util.List;

public interface IStatChannelPvService {
    /**
     * 查询所有
     *
     * @return
     */
    List<StatChannelPv> query();

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
    List<StatChannelPv> query(long rowIndex, int pageSize);


    /**
     * 分页查询
     *
     * @param nameLike 名称
     * @param rowIndex 开始记录索引
     * @param pageSize 查询数量
     * @return
     */
    List<StatChannelPv> queryByName(String nameLike, long rowIndex, int pageSize);

    /**
     * 插入
     *
     * @param statChannelPv
     */
    int insert(StatChannelPv statChannelPv);

    /**
     * 根据ID查询
     *
     * @param id
     * @return
     */
    StatChannelPv loadById(int id);

    /**
     * 根据ID更新
     *
     * @param statChannelPv 系统
     * @return 更新的记录数
     */
    int update(StatChannelPv statChannelPv);

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
     * @param month
     * @return
     */
    long count(int sysId, short year, short month);

    /**
     *
     * @param sysId
     * @param year
     * @param month
     * @param rowIndex
     * @param pageSize
     * @return
     */
    List<StatChannelPv> query(int sysId, short year, short month, long rowIndex, int pageSize);

    /**
     *
     * @param sysId
     * @param year
     * @param month
     * @param channelId
     * @return
     */
    StatChannelPv load(int sysId, short year, short month, int channelId);
}
