package simple.project.giis.model.entity;

import javax.persistence.*;
import java.util.List;

/**
 * @author Simple
 * @date on 2019/1/3 15:20
 */
@Entity
public class User extends BaseEntity {

    public User() {
    }

    public User(String phone, String name) {
        this.phone = phone;
        this.name = name;
    }

    /*可公开*/
    @Column(nullable = false, unique = true)
    private String phone;

    /*可公开*/
    @Column
    private String name;

    /*可公开*/
    @Column
    private String email;

    /*不可公开*/
    @Column(nullable = false)
    private String password;

    /*可公开*/
    @ManyToOne
    private Role role;

    /*可公开*/
    @Column(unique = true)
    private String uid;

    /*可公开*/
    @OneToOne
    private UserFile pic;

    /*不必公开*/
    @ManyToMany
    private List<Tag> care;

    /*不必公开*/
    @OneToOne
    private PushSetting pushSetting;

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

    public PushSetting getPushSetting() {
        return pushSetting;
    }

    public void setPushSetting(PushSetting pushSetting) {
        this.pushSetting = pushSetting;
    }

    public UserFile getPic() {
        return pic;
    }

    public void setPic(UserFile pic) {
        this.pic = pic;
    }
}
