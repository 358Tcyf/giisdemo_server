package simple.project.giis.model.entity;

import javax.persistence.*;
import java.util.List;

/**
 * @author Simple
 * @date on 2019/1/3 15:20
 */
@Entity
public class User extends BaseEntity {

    @Column(nullable = false, unique = true)
    private String phone;

    @Column
    private String name;

    @Column
    private String email;

    @Column(nullable = false)
    private String password;

    @ManyToOne
    private Role role;

    @Column(unique = true)
    private String uid;

    @ManyToMany
    private List<Tag> care;

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public List<Tag> getCare() {
        return care;
    }

    public void setCare(List<Tag> care) {
        this.care = care;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
