package library.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;

/**
 * Created by FYY on 12/10/16.
 */
@Entity
@Table(name = "lend", schema = "Library", catalog = "")
public class LendEntity implements Serializable {
    private Date ldate;
    private Date rdate;
    private String lid;
    private BookEntity books;

    public BookEntity getBooks() {
        return books;
    }

    public LendEntity() {
    }

    public void setBooks(BookEntity books) {
        this.books = books;
    }

    public LendEntity(Date ldate, Date rdate) {
        this.ldate = ldate;
        this.rdate = rdate;
    }

    @Basic
    @Column(name = "ldate")
    public Date getLdate() {

        return ldate;
    }

    public void setLdate(Date ldate) {
        this.ldate = ldate;
    }

    @Basic
    @Column(name = "rdate")
    public Date getRdate() {
        return rdate;
    }

    public void setRdate(Date rdate) {
        this.rdate = rdate;
    }

    @Id
    @Column(name = "lid")
    public String getLid() {
        return lid;
    }

    public void setLid(String lid) {
        this.lid = lid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        LendEntity that = (LendEntity) o;

        if (ldate != null ? !ldate.equals(that.ldate) : that.ldate != null) return false;
        if (rdate != null ? !rdate.equals(that.rdate) : that.rdate != null) return false;
        if (lid != null ? !lid.equals(that.lid) : that.lid != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = ldate != null ? ldate.hashCode() : 0;
        result = 31 * result + (rdate != null ? rdate.hashCode() : 0);
        result = 31 * result + (lid != null ? lid.hashCode() : 0);
        return result;
    }
}
