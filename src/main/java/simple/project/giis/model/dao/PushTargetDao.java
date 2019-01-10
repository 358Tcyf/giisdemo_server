package simple.project.giis.model.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import simple.project.giis.model.entity.PushTarget;

public interface PushTargetDao extends JpaRepository<PushTarget, Integer> {
}
