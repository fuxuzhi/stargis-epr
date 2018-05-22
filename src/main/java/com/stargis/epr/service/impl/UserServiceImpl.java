package com.stargis.epr.service.impl;

import com.stargis.epr.dao.UserDao;
import com.stargis.epr.entity.User;
import com.stargis.epr.service.UserService;
import com.stargis.epr.utils.AntiXssUtil;
import com.stargis.epr.utils.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service("userService")
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;

    @Override
    public User login(User user) {
        return userDao.login(user);
    }

    @Override
    public List<User> queryList(Map<String, Object> map) {
        return userDao.findUsers(map);
    }

    @Override
    public int queryTotal(Map<String, Object> map) {
        return userDao.getTotalUser(map);
    }

    @Override
    public void save(User user) {
        user.setPassword(MD5Util.MD5Encode(user.getPassword(), "UTF-8"));
        user.setUserName(AntiXssUtil.replaceHtmlCode(user.getUserName()));
        userDao.addUser(user);
    }

    @Override
    public void update(User user) {
        //防止有人胡乱修改导致其他人无法正常登陆
        if (!"admin".equals(user.getUserName())) {
            user.setPassword(MD5Util.MD5Encode(user.getPassword(), "UTF-8"));
            user.setUserName(AntiXssUtil.replaceHtmlCode(user.getUserName()));
            userDao.updateUser(user);
        }

    }

    @Override
    public void delete(Integer id) {
        userDao.deleteUser(id);
    }

    @Override
    public void deleteBatch(Integer[] ids) {
        userDao.deleteBatch(ids);
    }

    @Override
    public User queryObject(Integer id) {
        User user = userDao.getUserById(id);
        if (user != null) {
            user.setPassword(null);
        }
        return user;
    }

}
