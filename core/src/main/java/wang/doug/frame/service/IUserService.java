package wang.doug.frame.service;

import wang.doug.frame.model.User;

import java.util.List;

public interface IUserService {
    /**
     * 查询所有
     *
     * @return
     */
    List<User> query();

    /**
     * 总数
     *
     * @return
     */
    long total();

    /**
     * 查询数量
     *
     * @param nicknameLike
     * @return
     */
    long countByNickname(String nicknameLike);

    /**
     * 分页查询
     *
     * @param rowIndex 开始记录索引
     * @param pageSize 查询数量
     * @return
     */
    List<User> query(long rowIndex, int pageSize);


    /**
     * 分页查询
     *
     * @param nickname 名称
     * @return
     */
    List<User> queryByNickname(String nickname);

    List<User> queryByNicknameAndPwd(String nickname,String pwd);
    /**
     * 插入
     *
     * @param user
     */
    int insert(User user);

    /**
     * 根据ID查询
     *
     * @param id
     * @return
     */
    User loadById(int id);

    /**
     * 根据ID更新
     *
     * @param user 学校
     * @return 更新的记录数
     */
    int update(User user);

    /**
     * 根据ID删除
     *
     * @param id ID
     * @return 删除的记录数
     */
    int delete(int id);
}
