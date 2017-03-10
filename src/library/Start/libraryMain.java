package library.Start;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

/**
 * Created by FYY on 12/9/16.
 */
public class libraryMain {

    public static void main(String[] args) throws IOException {
        System.out.println("\n");
        System.out.println("------------------------图书管理系统------------------------");
        System.out.print("请输入学号:");
        Scanner scan = new Scanner(System.in);
        String sid = scan.next();
        int flag = 0;
        System.out.println("功能选择:");
        System.out.println("1:注册");
        System.out.println("2:借书（已注册）");
        System.out.println("3:预约书籍");
        System.out.println("4:查看借书（预约）情况");
        System.out.println("5:查询图书");
        System.out.println("6:遍历图书");
        System.out.println("7:更换学号");
        System.out.println("8:续借");
        System.out.println("9:退出系统");

        int func = scan.nextInt();
        while (func != 9) {

            switch (func) {
                case 1:
                    libraryUtil.reg(sid);
                    break;
                case 2:
                    libraryUtil.lend(sid);
                    break;
                case 3:
                    libraryUtil.orderbook(sid);
                    break;
                case 4:
                    libraryUtil.display(sid);
                    break;
                case 5:
                    libraryUtil.search();
                    break;
                case 6:
                    libraryUtil.GoThrough();
                    break;
                case 7:
                    System.out.println("请输入学号（将会退出当前学号）");
                    sid = scan.next();
                    break;
                case 8:
                    libraryUtil.Renew(sid);
                    break;
                case 9:
                    System.exit(0);
                    break;
                default:
                    break;


            }
            System.out.println("单击回车确认");
            BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
            String str = "";
            str = bf.readLine();
            while(str.length()!=0){
                str = "";
                str = bf.readLine();
            }
            System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
            libraryUtil.info(sid);
            System.out.println("1:注册");
            System.out.println("2:借书（已注册）");
            System.out.println("3:预约书籍");
            System.out.println("4:查看借书（预约）情况");
            System.out.println("5:查询图书");
            System.out.println("6:遍历图书");
            System.out.println("7:更换学号");
            System.out.println("8:续借");
            System.out.println("9:退出系统");
            func = scan.nextInt();
        }


    }

}
