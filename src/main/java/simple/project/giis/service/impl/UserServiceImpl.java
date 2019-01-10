package simple.project.giis.service.impl;

import org.springframework.stereotype.Service;
import simple.project.giis.dao.UserDao;
import simple.project.giis.entity.User;
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
        return userDao.findByPhoneAndPasswd(user.getPhone(), user.getPasswd());
    }

    @Override
    public User login(String phone, String passwd) {
        return userDao.findByPhoneAndPasswd(phone, passwd);
    }

    @Override
    public void signUp(User user) {
        user.setAlias(user.getName() + user.getPhone());
        userDao.save(user);
    }

    @Override
    public void signUp(String name, String phone, String passwd) {
        User user = new User();
        user.setName(name);
        user.setPhone(phone);
        user.setPasswd(passwd);
        user.setAlias(user.getName() + user.getPhone());
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
        return (null != userDao.findByPhoneAndPasswd(user.getPhone(), user.getPasswd()));
    }

    @Override
    public boolean checkPwd(String phone, String passwd) {
        return (null != userDao.findByPhoneAndPasswd(phone, passwd));
    }

}
