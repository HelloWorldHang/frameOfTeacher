package wang.doug.frame.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import wang.doug.frame.dao.UserMapper;
import wang.doug.frame.dao.UserRoleMapper;
import wang.doug.frame.model.UserRole;
import wang.doug.frame.model.UserRoleExample;
import wang.doug.frame.service.IUserRoleService;

import java.util.List;

/**
 * @description: *
 * @author: 司云航
 * @create: 2019-07-26 16:39
 */
@Service
public class UserRoleServiceImpl implements IUserRoleService {
    @Autowired
    private UserRoleMapper userRoleMapper;
    @Override
    public List<UserRole> queryByUserId(Integer userId) {
        UserRoleExample example = new UserRoleExample();
        example.createCriteria().andUserIdEqualTo(userId);
        return userRoleMapper.selectByExample(example);
    }
}
