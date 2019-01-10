package simple.project.giis.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import simple.project.giis.entity.Permission;

/**
 * @author Simple
 * @date on 2019/1/3 15:40
 */
public interface PermissionDao extends JpaRepository<Permission, Integer> {

}
