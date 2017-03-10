package library.entity;

import javax.persistence.*;

/**
 * Created by FYY on 12/10/16.
 */
@Entity
@Table(name = "book", schema = "Library", catalog = "")
public class BookEntity {
    private String bid;
    private String bname;
    private Integer blent;
    private String bpress;
    private String bauthor;
//    private LendEntity lendbooks;
//    private AppointmentEntity orderbooks;

    public BookEntity(String bid, String bname, Integer blent, String bpress, String bauthor) {
        this.bid = bid;
        this.bname = bname;
        this.blent = blent;
        this.bpress = bpress;
        this.bauthor = bauthor;
    }

    public BookEntity() {
    }

//    public LendEntity getLendbooks() {
//        return lendbooks;
//    }
//
//    public void setLendbooks(LendEntity lendbooks) {
//        this.lendbooks = lendbooks;
//    }
//
//    public AppointmentEntity getOrderbooks() {
//        return orderbooks;
//    }
//
//    public void setOrderbooks(AppointmentEntity orderbooks) {
//        this.orderbooks = orderbooks;
//    }

    @Id
    @Column(name = "bid")
    public String getBid() {
        return bid;
    }

    public void setBid(String bid) {
        this.bid = bid;
    }

    @Basic
    @Column(name = "bname")
    public String getBname() {
        return bname;
    }

    public void setBname(String bname) {
        this.bname = bname;
    }

    @Basic
    @Column(name = "blent")
    public Integer getBlent() {
        return blent;
    }

    public void setBlent(Integer blent) {
        this.blent = blent;
    }

    @Basic
    @Column(name = "bpress")
    public String getBpress() {
        return bpress;
    }

    public void setBpress(String bpress) {
        this.bpress = bpress;
    }

    @Basic
    @Column(name = "bauthor")
    public String getBauthor() {
        return bauthor;
    }

    public void setBauthor(String bauthor) {
        this.bauthor = bauthor;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BookEntity that = (BookEntity) o;

        if (bid != null ? !bid.equals(that.bid) : that.bid != null) return false;
        if (bname != null ? !bname.equals(that.bname) : that.bname != null) return false;
        if (blent != null ? !blent.equals(that.blent) : that.blent != null) return false;
        if (bpress != null ? !bpress.equals(that.bpress) : that.bpress != null) return false;
        if (bauthor != null ? !bauthor.equals(that.bauthor) : that.bauthor != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = bid != null ? bid.hashCode() : 0;
        result = 31 * result + (bname != null ? bname.hashCode() : 0);
        result = 31 * result + (blent != null ? blent.hashCode() : 0);
        result = 31 * result + (bpress != null ? bpress.hashCode() : 0);
        result = 31 * result + (bauthor != null ? bauthor.hashCode() : 0);
        return result;
    }
}
