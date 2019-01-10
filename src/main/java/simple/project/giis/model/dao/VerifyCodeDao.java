package simple.project.giis.model.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import simple.project.giis.model.entity.VerifyCode;

public interface VerifyCodeDao extends JpaRepository<VerifyCode, Integer> {
}
