package wang.doug.frame.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import wang.doug.frame.dao.UserMapper;
import wang.doug.frame.model.User;
import wang.doug.frame.model.UserExample;
import wang.doug.frame.service.IUserService;

import java.util.List;


@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private UserMapper userMapper;


    @Override
    public List<User> query() {
        UserExample example = new UserExample();
        return userMapper.selectByExample(example);
    }

    @Override
    public long total() {
        UserExample example = new UserExample();
        return userMapper.countByExample(example);
    }

    @Override
    public long countByNickname(String nicknameLike) {
        UserExample example = new UserExample();
        example.createCriteria().andNicknameLike(nicknameLike);
        return userMapper.countByExample(example);
    }

    @Override
    public List<User> query(long rowIndex, int pageSize) {
        UserExample example = new UserExample();
        example.setRowIndex(rowIndex);
        example.setPageSize(pageSize);
        return userMapper.selectByExample(example);
    }

    @Override
    public List<User> queryByNickname(String nickname) {
        UserExample example = new UserExample();
        example.createCriteria().andNicknameEqualTo(nickname);
        return userMapper.selectByExample(example);
    }

    @Override
    public List<User> queryByNicknameAndPwd(String nickname, String pwd) {
        UserExample example = new UserExample();
        example.createCriteria().andNicknameEqualTo(nickname).andPasswordEqualTo(pwd);
        return userMapper.selectByExample(example);
    }

    @Override
    public int insert(User user) {
        return userMapper.insert(user);
    }

    @Override
    public User loadById(int id) {
        return userMapper.selectByPrimaryKey(id);
    }

    @Override
    public int update(User user) {
        UserExample example = new UserExample();
        example.createCriteria().andIdEqualTo(user.getId());
        return userMapper.updateByExample(user,example);
    }

    @Override
    public int delete(int id) {
        return userMapper.deleteByPrimaryKey(id);
    }
}