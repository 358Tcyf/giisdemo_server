package simple.project.giis.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import simple.project.giis.entity.Tag;

/**
 * @author Simple
 * @date on 2019/1/3 15:54
 */
public interface TagDao extends JpaRepository<Tag, Integer> {
}
