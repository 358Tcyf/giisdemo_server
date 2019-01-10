package simple.project.giis.model.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import simple.project.giis.model.entity.Tag;

/**
 * @author Simple
 * @date on 2019/1/3 15:54
 */
public interface TagDao extends JpaRepository<Tag, Integer> {
}
