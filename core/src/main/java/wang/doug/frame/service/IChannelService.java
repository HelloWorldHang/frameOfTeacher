package wang.doug.frame.service;

import wang.doug.frame.model.Channel;

import java.util.List;

public interface IChannelService {
    /**
     * 查询所有
     *
     * @return
     */
    List<Channel> query();

    /**
     * 根据sysId查询
     * @param sysId
     * @return
     */
    List<Channel> queryBySysId(Integer sysId);

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
    List<Channel> query(long rowIndex, int pageSize);


    /**
     * 分页查询
     *
     * @param nameLike 名称
     * @param rowIndex 开始记录索引
     * @param pageSize 查询数量
     * @return
     */
    List<Channel> queryByName(String nameLike, long rowIndex, int pageSize);

    /**
     * 插入
     *
     * @param channel
     */
    int insert(Channel channel);

    /**
     * 根据ID查询
     *
     * @param id
     * @return
     */
    Channel loadById(int id);

    /**
     * 根据ID更新
     *
     * @param channel 系统
     * @return 更新的记录数
     */
    int update(Channel channel);

    /**
     * 根据ID删除
     *
     * @param id ID
     * @return 删除的记录数
     */
    int delete(int id);
}
