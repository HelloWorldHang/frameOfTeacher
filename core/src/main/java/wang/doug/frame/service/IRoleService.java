package wang.doug.frame.service;

import wang.doug.frame.model.Role;

import java.util.List;

public interface IRoleService {
    /**
     * 查询所有
     *
     * @return
     */
    List<Role> query();

    /**
     * 总数
     *
     * @return
     */
    long total();


    /**
     * 插入
     *
     * @param role
     */
    int insert(Role role);

    /**
     * 根据ID查询
     *
     * @param id
     * @return
     */
    Role loadById(int id);

    /**
     * 根据ID更新
     *
     * @param role 系统
     * @return 更新的记录数
     */
    int update(Role role);

    /**
     * 根据ID删除
     *
     * @param id ID
     * @return 删除的记录数
     */
    int delete(int id);
}
