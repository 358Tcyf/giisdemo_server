package simple.project.giis.service;

import simple.project.giis.model.entity.User;

import java.util.Map;

/**
 * @author Simple
 * @date on 2019/1/3 16:25
 */
public interface UserService {

    boolean checkVerifyCode(String phone, String code);

    boolean isExisted(User user);

    boolean isExisted(String phone);

    void signUp(User user);

    void signUp(String name, String phone, String password);

    boolean checkPwd(User user);

    boolean checkPwd(String phone, String password);

    User login(User user);

    User login(String phone, String password);

    Map<String, Object> getInfo(String phone);

    Map<String, Object> getList();

}
