package wang.doug.frame.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import wang.doug.frame.dao.RoleMapper;
import wang.doug.frame.model.Role;
import wang.doug.frame.service.IRoleService;

import java.util.List;

/**
 * @description: *
 * @author: 司云航
 * @create: 2019-07-26 16:45
 */
@Service
public class RoleServiceImpl implements IRoleService {
    @Autowired
    private RoleMapper roleMapper;
    @Override
    public List<Role> query() {
        return null;
    }

    @Override
    public long total() {
        return 0;
    }

    @Override
    public int insert(Role role) {
        return 0;
    }

    @Override
    public Role loadById(int id) {
        return roleMapper.selectByPrimaryKey(id);
    }

    @Override
    public int update(Role role) {
        return 0;
    }

    @Override
    public int delete(int id) {
        return 0;
    }
}
