package simple.project.giis.model.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import simple.project.giis.model.entity.User;

/**
 * @author Simple
 * @date on 2019/1/3 15:29
 */
public interface UserDao extends JpaRepository<User, Integer> {

    /**
     * 根据名称和密码查询用户数据
     */
    User findByPhoneAndPassword(String phone, String password);

    User findByPhone(String phone);

    User findByUid(String uid);

}
