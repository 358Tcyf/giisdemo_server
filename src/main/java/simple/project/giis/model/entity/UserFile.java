package simple.project.giis.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class UserFile extends BaseEntity {

    @Column(nullable = false, unique = true)
    private String uuid;

    private String name;

    private String path;

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
