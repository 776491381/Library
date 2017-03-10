package library.Start;

import library.entity.AppointmentEntity;
import library.entity.BookEntity;
import library.entity.LendEntity;
import library.entity.StudentEntity;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

/**
 * Created by FYY on 12/9/16.
 */
public class libraryUtil {

    public static void main(String[] args) {

        //add();
        //search();
        //lend("2222");

    }
//
//    public static void add(){
//        StudentEntity se = new StudentEntity("14184142","fyy","l6","zhuoyue");
//        LendEntity le = new LendEntity(Date.valueOf("2015-10-16"),Date.valueOf("2016-10-16"));
//        BookEntity book = new BookEntity("222","book2" ,0, "zju" , "author");
//        AppointmentEntity ae = new AppointmentEntity(Date.valueOf("2015-10-16"));
//        le.setBooks(book);
//        se.getOrderbooks().add(ae);
//        se.getLendbooks().add(le);
//        Session session = hibernateUtil.getSession();
//        Transaction tx = session.beginTransaction();
//        session.save(se);
//        session.save(book);
//        session.save(le);
//        session.save(ae);
//        tx.commit();
//        hibernateUtil.closesession(session);
//
//    }
//
//    public static void search(){
//        Session session = hibernateUtil.getSession();
//        StudentEntity se = (StudentEntity) session.get(StudentEntity.class,"14184142");
//        System.out.println(se.getSid()+"  "+se.getSname()+"  "+se.getSclass()+"  "+
//                se.getScollege());
//        Set<LendEntity> le =se.getLendbooks();
//        for(LendEntity l:le){
//            System.out.println(l.getLid()+"   "+l.getLdate()+"  "+l.getRdate());
//            BookEntity books =  l.getBooks();
//            System.out.println(books.getBname()+"  "+books.getBpress());
//        }
//
//
//
//    }

    public static void reg(String sid) {
        Session session = hibernateUtil.getSession();
        Scanner scan = new Scanner(System.in);
        if (session.get(StudentEntity.class, sid) == null) {
            System.out.println("请输入名字,班级,学院");
            StudentEntity se = new StudentEntity(sid, scan.next(), scan.next(), scan.next());
            session.save(se);
            Transaction tx = session.beginTransaction();
            tx.commit();
            hibernateUtil.closesession(session);
            System.out.println("注册成功");
        } else {
            System.out.println("此学号已经注册,可直接借阅书籍");

        }
    }

    public static void info(String sid){
        Session session = hibernateUtil.getSession();
        StudentEntity se = (StudentEntity) session.get(StudentEntity.class, sid);
        if ( se == null){
            Scanner scan = new Scanner(System.in);
            System.out.println("此学号未注册,无法借阅预约书籍");
            System.out.println("是否注册?(y/n)");
            char a = scan.next().charAt(0);
            if(a=='y'||a=='Y'){
                reg(sid);
            }
        }else{
            System.out.println("当前使用者信息: "+"姓名: "+se.getSname()+" 学号: "+se.getSid()+
                    " 班级: "+se.getSclass()+" 学院: "+se.getScollege());
        }
        hibernateUtil.closesession(session);

    }

    public static void lend(String sid) {
        Scanner scan = new Scanner(System.in);
        Session session = hibernateUtil.getSession();
        StudentEntity se = (StudentEntity) session.get(StudentEntity.class, sid);
        java.util.Date nDate = new java.util.Date();
        java.util.Date nDate2 = new java.util.Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String sDate = sdf.format(nDate);
        java.sql.Date now = java.sql.Date.valueOf(sDate);
        Calendar cal = Calendar.getInstance();
        cal.setTime(nDate2);
        cal.add(Calendar.DATE, 90);
        nDate2 =cal.getTime();
        String sDate2 = sdf.format(nDate2);
        java.sql.Date back = java.sql.Date.valueOf(sDate2);
        if(se!=null) {
            Transaction tx = session.beginTransaction();
            System.out.println("请输入书名");
            String bname = scan.next();
            Query query = session.createQuery("from BookEntity where bname=" + "\'" + bname + "\'");
            int flag = -1;
            // System.out.println(query);
            List<BookEntity> books = query.list();
            System.out.println();
            for (BookEntity book : books) {
                if (book != null) {
                    flag++;
                    System.out.println("图书编号" + book.getBid() + " 图书名称 " +
                            book.getBname() + " 作者 " + book.getBauthor() + " 出版社 " +
                            book.getBpress() + " 借书状态 " + book.getBlent());

                } else {
                    System.out.println("查询出错");
                }

            }
            if (flag > 0) {
                System.out.println("选择图书编号");
                String bid = scan.next();
                Query query2 = session.createQuery("from BookEntity where bname=" + "\'" + bname + "\'" + " and  bid=" + "\'" + bid + "\'");
                List<BookEntity> books2 = query2.list();
                BookEntity be = null;
                for (BookEntity book : books2) {
                    if (book.getBlent() != 0) {
                        System.out.println("此书不可借阅");
                    } else {
                        be = book;
                        LendEntity le = new LendEntity(now, back);
                        Query change = session.createQuery("update BookEntity set  blent=" + 1 + " where bname=" + "\'" + bname + "\'" + " and  bid=" + "\'" + bid + "\'");
                        change.executeUpdate();
                        le.setBooks(be);
                        se.getLendbooks().add(le);
                        session.save(le);
                        session.save(se);
                        tx.commit();
                        hibernateUtil.closesession(session);
                        System.out.println("借书成功");
                    }

                }


                display(sid);
                flag = -1;
            } else {
                BookEntity be2 = null;
                for (BookEntity book : books) {
                    if (book.getBlent() != 0) {
                        System.out.println("此书不可借阅");
                    } else {
                        be2 = book;
                        LendEntity le = new LendEntity(now, back);
                        Query change = session.createQuery("update BookEntity set  blent=" + 1 + " where bname=" + "\'" + bname + "\'");
                        change.executeUpdate();
                        le.setBooks(be2);
                        se.getLendbooks().add(le);
                        session.save(le);
                        session.save(se);
                        tx.commit();
                        hibernateUtil.closesession(session);
                        System.out.println("借书成功");
                    }

                }


                display(sid);
            }

        }else{
            System.out.println("未注册,请注册后在尝试");
            System.out.println("是否注册?(y/n)");
            char a = scan.next().charAt(0);
            if(a=='y'||a=='Y'){
                reg(sid);
            }
        }
    }


    public static void display(String sid) {
        Session session = hibernateUtil.getSession();
        StudentEntity se = (StudentEntity) session.get(StudentEntity.class, sid);
        System.out.println("学生学号 " + se.getSid() + " 姓名 " + se.getSname() + " 班级 " + se.getSclass() + " 学院 " +
                se.getScollege());
        Set<LendEntity> le = se.getLendbooks();
        Set<AppointmentEntity> ae = se.getOrderbooks();
        System.out.println("借书情况:");
        int i=1;
        for (LendEntity l : le) {
            System.out.print((i++)+":\t");
            System.out.println("\t" + "\t" + "借书单号: " + l.getLid() + "  借书日期: " + l.getLdate() + " 应还日期: " + l.getRdate());
            BookEntity book = l.getBooks();
            System.out.println("\t" + "\t" +"\t"+ "图书编号: " + book.getBid() + " 图书名称: " +
                    book.getBname() + " 作者: " + book.getBauthor() + " 出版社: " +
                    book.getBpress() + " 借书状态: " + book.getBlent());
        }
        i=1;
        System.out.println("预约情况:\n");
        for (AppointmentEntity a : ae) {

            System.out.print((i++)+":\t");
            System.out.println("\t" + "\t" + "预约单号: " + a.getAid() + "  预约日期: " + a.getAdate());
            BookEntity book = a.getBooks();
            System.out.println("\t" + "\t" + "\t"+"图书编号: " + book.getBid() + " 图书名称: " +
                    book.getBname() + " 作者: " + book.getBauthor() + " 出版社: " +
                    book.getBpress() + " 借书状态: " + book.getBlent());
        }

    }

    public static void orderbook(String sid) {

        Session session = hibernateUtil.getSession();
        StudentEntity se = (StudentEntity) session.get(StudentEntity.class, sid);
        java.util.Date nDate = new java.util.Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String sDate = sdf.format(nDate);
        java.sql.Date now = java.sql.Date.valueOf(sDate);
        Scanner scan = new Scanner(System.in);
        if(se!=null) {

            System.out.println("请输入书名");
            String bname = scan.next();
            Transaction tx = session.beginTransaction();
            Query query = session.createQuery("from BookEntity where bname=" + "\'" + bname + "\'");
            int flag = -1;
            // System.out.println(query);
            List<BookEntity> books = query.list();
            System.out.println();
            for (BookEntity book : books) {
                if (book != null) {
                    flag++;
                    System.out.println("图书编号" + book.getBid() + "图书名称 " +
                            book.getBname() + " 作者 " + book.getBauthor() + " 出版社 " +
                            book.getBpress() + " 借书状态 " + book.getBlent());
                } else {
                    System.out.println("查询出错");
                }

            }
            if (flag > 0) {
                System.out.println("选择图书编号");
                String bid = scan.next();
                Query query2 = session.createQuery("from BookEntity where bname=" + "\'" + bname + "\'" + " and  bid=" + "\'" + bid + "\'");
                List<BookEntity> books2 = query2.list();
                BookEntity be = null;
                for (BookEntity book : books2) {
                    if (book.getBlent() != 0) {
                        System.out.println("此书不可预约");
                    } else {
                        be = book;
                        AppointmentEntity ae = new AppointmentEntity(now);
                        Query change = session.createQuery("update BookEntity set  blent=" + 2 + " where bname=" + "\'" + bname + "\'" + " and  bid=" + "\'" + bid + "\'");
                        change.executeUpdate();
                        ae.setBooks(be);
                        se.getOrderbooks().add(ae);
                        session.save(ae);
                        session.save(se);
                        tx.commit();
                        hibernateUtil.closesession(session);
                        System.out.println("预约成功");
                    }

                }


                display(sid);
            } else {
                BookEntity be2 = null;
                for (BookEntity book : books) {
                    if (book.getBlent() != 0) {
                        System.out.println("此书不可预约");
                    } else {
                        be2 = book;
                        AppointmentEntity ae = new AppointmentEntity(now);
                        Query change = session.createQuery("update BookEntity set  blent=" + 2 + " where bname=" + "\'" + bname + "\'");
                        change.executeUpdate();
                        ae.setBooks(be2);
                        se.getOrderbooks().add(ae);
                        session.save(ae);
                        session.save(se);
                        tx.commit();
                        hibernateUtil.closesession(session);
                        System.out.println("预约成功");
                    }

                }


                display(sid);
            }
        }else{
            System.out.println("未注册,请注册后在尝试");
            System.out.println("是否注册?(y/n)");
            char a = scan.next().charAt(0);
            if(a=='y'||a=='Y'){
                reg(sid);
            }
        }
    }

    public static void search() {

        Scanner scan = new Scanner(System.in);
        Session session = hibernateUtil.getSession();
        System.out.println("请输入书名");
        String bname = scan.next();
        Query query = session.createQuery("from BookEntity where bname=" + "\'" + bname + "\'");
        int flag = -1;
        List<BookEntity> books = query.list();
        for (BookEntity book : books) {
            if (book != null) {
                flag++;
                System.out.println("\t" + "\t" + "图书编号: " + book.getBid() + " 图书名称: " +
                        book.getBname() + " 作者: " + book.getBauthor() + " 出版社: " +
                        book.getBpress() + " 借书状态: " + book.getBlent());

            } else {
                System.out.println("查询出错");
            }

        }
    }



    public static void GoThrough(){

        Session session = hibernateUtil.getSession();
        Query query = session.createQuery("from BookEntity ");
        List<BookEntity> books = query.list();
        int flag=0;
        for (BookEntity book : books) {
            if (book != null) {
                flag++;
                System.out.println("\t" + "\t" + "图书编号: " + book.getBid() + " 图书名称: " +
                        book.getBname() + " 作者: " + book.getBauthor() + " 出版社: " +
                        book.getBpress() + " 借书状态: " + book.getBlent());

            } else {
                System.out.println("查询出错");
            }

        }
        System.out.println("\t"+"\t"+"一共包含"+flag+"本图书");
        hibernateUtil.closesession(session);
    }

    public static void Renew(String sid){
        int flag=0;
        Session session = hibernateUtil.getSession();
        StudentEntity se = (StudentEntity) session.get(StudentEntity.class, sid);
        Transaction tx = session.beginTransaction();
        Scanner scan = new Scanner(System.in);
        if(se!=null){
            display(sid);
            System.out.println("请输入要续借的书籍");
            String renewbook = scan.next();
            Set<LendEntity> le = se.getLendbooks();
            for (LendEntity l : le){
               BookEntity book = l.getBooks();
                if(book.getBname().equals(renewbook)){
                    String lid2 = l.getLid();
                    java.sql.Date rdate = l.getRdate();
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                    Calendar cal = Calendar.getInstance();
                    cal.setTime(rdate);
                    cal.add(Calendar.DATE, 30);
                    java.util.Date renew = new java.util.Date();
                    renew = cal.getTime();
                    String renewdate = sdf.format(renew);
                    java.sql.Date renewdate2 = java.sql.Date.valueOf(renewdate);
                    System.out.println("归还时间由"+rdate+"增加到"+renewdate2);
                    Query query = session.createQuery("update LendEntity set rdate ="+"\'"+renewdate2+"\'"+" where lid = "+"\'"+lid2+"\'");
                    query.executeUpdate();
                    tx.commit();
                    hibernateUtil.closesession(session);
                    flag=1;
                    break;


                }
                if(flag==1){
                    System.out.println("尚未借阅此书");
                }

            }

        }else {
            System.out.println("尚未注册,请注册后在试");
            System.out.println("是否注册?(y/n)");
            char a = scan.next().charAt(0);
            if(a=='y'||a=='Y'){
                reg(sid);
            }
        }


    }
}
