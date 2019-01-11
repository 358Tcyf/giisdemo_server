package simple.project.giis.model.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import simple.project.giis.model.entity.PushSetting;

public interface PushSettingDao extends JpaRepository<PushSetting, Integer> {
}
