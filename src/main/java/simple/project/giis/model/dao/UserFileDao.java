package simple.project.giis.model.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import simple.project.giis.model.entity.UserFile;

public interface UserFileDao extends JpaRepository<UserFile, Integer> {

    UserFile getUserFileByUuid(String uuid);
}
