package simple.project.giis.service.impl;

import org.springframework.stereotype.Service;
import simple.project.giis.model.dao.UserDao;
import simple.project.giis.model.entity.User;
import simple.project.giis.service.UserService;

import javax.annotation.Resource;

/**
 * @author Simple
 * @date on 2019/1/3 16:26
 */
@Service
public class UserServiceImpl implements UserService {
    @Resource
    UserDao userDao;

    @Override
    public User login(User user) {
        return userDao.findByPhoneAndPassword(user.getPhone(), user.getPassword());
    }

    @Override
    public User login(String phone, String password) {
        return userDao.findByPhoneAndPassword(phone, password);
    }

    @Override
    public void signUp(User user) {
        user.setUid(user.getName() + user.getPhone());
        userDao.save(user);
    }

    @Override
    public void signUp(String name, String phone, String password) {
        User user = new User();
        user.setName(name);
        user.setPhone(phone);
        user.setPassword(password);
        user.setUid(user.getName() + user.getPhone());
        userDao.save(user);
    }

    @Override
    public boolean isExisted(User user) {
        return (null != userDao.findByPhone(user.getPhone()));
    }

    @Override
    public boolean isExisted(String phone) {
        return (null != userDao.findByPhone(phone));
    }

    @Override
    public boolean checkPwd(User user) {
        return (null != userDao.findByPhoneAndPassword(user.getPhone(), user.getPassword()));
    }

    @Override
    public boolean checkPwd(String phone, String password) {
        return (null != userDao.findByPhoneAndPassword(phone, password));
    }

}
