package simple.project.giis.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import simple.project.giis.entity.User;

/**
 * @author Simple
 * @date on 2019/1/3 15:29
 */
public interface UserDao extends JpaRepository<User, Integer> {

    /**
     * 根据名称和密码查询用户数据
     */
    User findByPhoneAndPasswd(String phone, String passwd);

    User findByPhone(String phone);
}
