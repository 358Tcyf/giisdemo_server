package simple.project.giis.service;

import simple.project.giis.entity.User;

/**
 * @author Simple
 * @date on 2019/1/3 16:25
 */
public interface UserService {
    User login(User user);

    User login(String phone, String passwd);

    void signUp(User user);

    void signUp(String name, String phone, String passwd);

    boolean isExisted(User user);

    boolean isExisted(String phone);

    boolean checkPwd(User user);

    boolean checkPwd(String phone, String passwd);
}
