package wang.doug.frame.service;

import wang.doug.frame.model.UserRole;

import java.util.List;

public interface IUserRoleService {
    /**
     * 根据用户ID查询其对应的所有权限
     * @param userId
     * @return
     */
    List<UserRole> queryByUserId(Integer userId);
}
