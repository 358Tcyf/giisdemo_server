package simple.project.giis.model.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import simple.project.giis.model.entity.VerifyCode;

public interface VerifyCodeDao extends JpaRepository<VerifyCode, Integer> {
    /**
     * 根据手机号和验证码查询
     */
    VerifyCode findByPhoneAndCode(String phone, String code);

}
