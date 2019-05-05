package zhm.Datamining;


import java.util.ArrayList;
import java.util.Scanner;

public class Main {
	public static void main(String[] args){
		service s = new service();
        Scanner sc = new Scanner(System.in);
        /*System.out.println("请输入测试数量（小于1000）");
        int testNum = -1;
        while (true){
            testNum = sc.nextInt();
            if (testNum <= 0 || testNum >= 1000)
                System.out.println("输入错误,请重新输入!");
            else
                break;
        }
		ArrayList<Double> res = s.getNumber(testNum);
        System.out.println("优秀：" + res.get(0).intValue() + "---及格：" + res.get(1).intValue() + "---挂科：" + res.get(2).intValue() );
        System.out.println("测试总个数" + testNum);
        System.out.println("正确率：" + res.get(3) + "%");
        System.out.println("错误率：" + res.get(4) + "%");*/
        ArrayList<String> test = new ArrayList<>();
        System.out.print("请输入已看视频时长（单位 ： min）：");
        test.add(sc.next());
        System.out.print("请输入测评次数（ 0 - 10 ）：");
        test.add(sc.next());
        System.out.print("请输入做题正确率（0 - 100）：");
        test.add(sc.next());
        System.out.print("请输入做题时长（单位 ： min）：");
        test.add(sc.next());
        System.out.print("请输入你的性别（男 or 女）：");
        test.add(sc.next());
        ArrayList<Double> res = s.bayes(test);
        /*if (res == "2")
            System.out.println("你的预测结果为优秀，优秀是一种习惯，这句话用在你身上一点都没有错！");
        if (res == "1")
            System.out.println("你的预测结果为及格，继续努力，要变优秀呀！");
        if (res == "0")
            System.out.println("差一点点就及格了呢，加把劲！");*/
        System.out.println("优秀率：" + res.get(0));
        System.out.println("及格率：" + res.get(1));
        System.out.println("挂科率：" + res.get(2));
	}
}