package library.entity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by FYY on 12/10/16.
 */
@Entity
@Table(name = "Student", schema = "Library", catalog = "")
public class StudentEntity {
    private String sid;
    private String sname;
    private String sclass;
    private String scollege;
    private Set<LendEntity> lendbooks = new HashSet<>();
    private Set<AppointmentEntity> orderbooks = new HashSet<>();

    public StudentEntity() {
    }

    public Set<AppointmentEntity> getOrderbooks() {
        return orderbooks;

    }

    public void setOrderbooks(Set<AppointmentEntity> orderbooks) {
        this.orderbooks = orderbooks;
    }

    public Set<LendEntity> getLendbooks() {
        return lendbooks;
    }

    public void setLendbooks(Set<LendEntity> lendbooks) {
        this.lendbooks = lendbooks;
    }

    public StudentEntity(String sid, String sname, String sclass, String scollege) {
        this.sid = sid;
        this.sname = sname;
        this.sclass = sclass;
        this.scollege = scollege;
    }

    @Id
    @Column(name = "sid")
    public String getSid() {
        return sid;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }

    @Basic
    @Column(name = "sname")
    public String getSname() {
        return sname;
    }

    public void setSname(String sname) {
        this.sname = sname;
    }

    @Basic
    @Column(name = "sclass")
    public String getSclass() {
        return sclass;
    }

    public void setSclass(String sclass) {
        this.sclass = sclass;
    }

    @Basic
    @Column(name = "scollege")
    public String getScollege() {
        return scollege;
    }

    public void setScollege(String scollege) {
        this.scollege = scollege;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        StudentEntity that = (StudentEntity) o;

        if (sid != null ? !sid.equals(that.sid) : that.sid != null) return false;
        if (sname != null ? !sname.equals(that.sname) : that.sname != null) return false;
        if (sclass != null ? !sclass.equals(that.sclass) : that.sclass != null) return false;
        if (scollege != null ? !scollege.equals(that.scollege) : that.scollege != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = sid != null ? sid.hashCode() : 0;
        result = 31 * result + (sname != null ? sname.hashCode() : 0);
        result = 31 * result + (sclass != null ? sclass.hashCode() : 0);
        result = 31 * result + (scollege != null ? scollege.hashCode() : 0);
        return result;
    }
}
