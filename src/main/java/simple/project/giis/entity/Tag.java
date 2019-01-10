package simple.project.giis.entity;

import javax.persistence.Column;
import javax.persistence.Entity;

/**
 * @author Simple
 * @date on 2019/1/3 15:52
 */
@Entity
public class Tag extends BaseEntity {

    @Column(nullable = false, unique = true)
    private String name;

    @Column
    private String description;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
