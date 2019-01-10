package simple.project.giis.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import simple.project.giis.entity.Role;

/**
 * @author Simple
 * @date on 2019/1/3 15:40
 */
public interface RoleDao extends JpaRepository<Role, Integer> {

}
