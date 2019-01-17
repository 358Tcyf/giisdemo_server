package simple.project.giis.service.impl;

import org.springframework.stereotype.Service;
import simple.project.giis.model.dao.UserDao;
import simple.project.giis.model.dao.VerifyCodeDao;
import simple.project.giis.model.entity.User;
import simple.project.giis.service.UserService;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.hibernate.annotations.common.util.StringHelper.isEmpty;
import static simple.project.giis.utils.UuidUtil.getNum19;

/**
 * @author Simple
 * @date on 2019/1/3 16:26
 */
@Service
public class UserServiceImpl implements UserService {
    @Resource
    UserDao userDao;

    @Resource
    VerifyCodeDao codeDao;


    @Override
    public boolean checkVerifyCode(String phone, String code) {
        if (null != codeDao.findByPhoneAndCode(phone, code))
            return true;
        else
            return false;
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
    public void signUp(User user) {
        user.setUid(user.getName() + user.getPhone());
        userDao.save(user);
    }

    @Override
    public boolean checkPwd(User user) {
        return (null != userDao.findByPhoneAndPassword(user.getPhone(), user.getPassword()));
    }

    @Override
    public boolean checkPwd(String phone, String password) {
        return (null != userDao.findByPhoneAndPassword(phone, password));
    }

    @Override
    public void signUp(String name, String phone, String password) {
        User user = new User();
        user.setName(name);
        user.setPhone(phone);
        user.setPassword(password);
        user.setUid(getNum19());
        userDao.save(user);
    }

    @Override
    public User login(User user) {
        return userDao.findByPhoneAndPassword(user.getPhone(), user.getPassword());
    }

    @Override
    public User login(String phone, String password) {
        return userDao.findByPhoneAndPassword(phone, password);
    }

    @Override
    public Map<String, Object> getInfo(String phone) {
        User find = userDao.findByPhone(phone);
        Map<String, Object> result = new HashMap<>();
        result.put("phone", find.getPhone());
        result.put("name", find.getName());
        result.put("email", find.getEmail());
        result.put("uid", find.getUid());
        result.put("role", find.getRole().getName());
        return result;
    }

    @Override
    public Map<String, Object> getList() {
        List<User> all = userDao.findAll();
        Map<String, Object> result = new HashMap<>();
        List<Map<String, Object>> manager = new ArrayList<>();
        List<Map<String, Object>> staff = new ArrayList<>();
        for (User user : all) {
            switch (user.getRole().getName()) {
                case "manager":
                    manager.add(getInfo(user.getPhone()));
                    break;
                case "staff":
                    staff.add(getInfo(user.getPhone()));
                    break;
                default:
            }
        }
        result.putIfAbsent("manager", manager);
        result.putIfAbsent("staff", staff);

        return result;
    }

    @Override
    public void updateInfo(String oldPhone, String newPhone, String password) {
        if (!isEmpty(newPhone)) {
            User user = userDao.findByPhone(oldPhone);
            user.setPhone(newPhone);
            userDao.save(user);
        }
        if (!isEmpty(password)) {
            User user = userDao.findByPhone(oldPhone);
            user.setPassword(password);
            userDao.save(user);
        }
    }

}
