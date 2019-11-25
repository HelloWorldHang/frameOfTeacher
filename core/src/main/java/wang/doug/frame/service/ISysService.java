package wang.doug.frame.service;

import wang.doug.frame.model.Sys;

import java.util.List;

public interface ISysService {
    /**
     * 查询所有
     *
     * @return
     */
    List<Sys> query();

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
    List<Sys> query(long rowIndex, int pageSize);


    /**
     * 分页查询
     *
     * @param nameLike 名称
     * @param rowIndex 开始记录索引
     * @param pageSize 查询数量
     * @return
     */
    List<Sys> queryByName(String nameLike, long rowIndex, int pageSize);

    /**
     * 插入
     *
     * @param sys
     */
    int insert(Sys sys);

    /**
     * 根据ID查询
     *
     * @param id
     * @return
     */
    Sys loadById(int id);

    /**
     * 根据ID更新
     *
     * @param sys 系统
     * @return 更新的记录数
     */
    int update(Sys sys);

    /**
     * 根据ID删除
     *
     * @param id ID
     * @return 删除的记录数
     */
    int delete(int id);
}
