package simple.project.giis.model.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import simple.project.giis.model.entity.PushMessage;

public interface PushMessageDao extends JpaRepository<PushMessage, Integer> {
}
