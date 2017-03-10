package library.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;

/**
 * Created by FYY on 12/10/16.
 */
@Entity
@Table(name = "appointment", schema = "Library", catalog = "")
public class AppointmentEntity implements Serializable{
    private Date adate;
    private String aid;
    private BookEntity books;

    public BookEntity getBooks() {
        return books;
    }

    public void setBooks(BookEntity books) {
        this.books = books;
    }

    public AppointmentEntity() {
    }

    public AppointmentEntity(Date adate) {
        this.adate = adate;
    }

    @Basic
    @Column(name = "adate")
    public Date getAdate() {
        return adate;
    }

    public void setAdate(Date adate) {
        this.adate = adate;
    }

    @Id
    @Column(name = "aid")
    public String getAid() {
        return aid;
    }

    public void setAid(String aid) {
        this.aid = aid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AppointmentEntity that = (AppointmentEntity) o;

        if (adate != null ? !adate.equals(that.adate) : that.adate != null) return false;
        if (aid != null ? !aid.equals(that.aid) : that.aid != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = adate != null ? adate.hashCode() : 0;
        result = 31 * result + (aid != null ? aid.hashCode() : 0);
        return result;
    }
}
