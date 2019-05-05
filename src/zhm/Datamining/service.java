package zhm.Datamining;

import zhm.tool.DecimalCalculate;
import zhm.tool.data;

import java.util.ArrayList;

public class service {
    /**
     *
     * @param testNum 测试数量
     * @return
     */
    public ArrayList<Double> getNumber(int testNum){
        ArrayList<Double> res = new ArrayList<>();
        ArrayList<ArrayList<String>> trainList = new ArrayList<>();
        ArrayList<ArrayList<String>> testList = new ArrayList<>();
        ArrayList<ArrayList<String>> list;
        data d = new data();
        /*trainList = d.getData(1);
        testList = d.getData(2);*/
        list = d.getData();
        int train = 1;
        int trainNum = 1000 - testNum;
        for(ArrayList li : list)
        {
            if(train <= trainNum)
                trainList.add(li);
            else
                testList.add(li);
            train++;
        }

        String finalStr = "";
        int wrong_number = 0;  // 记录错误的数量

        PreRead convert = new PreRead();
        trainList = convert.readFile(trainList);  // 训练样本转换
        testList = convert.readFile(testList);   // 测试数据转换

        /*for(ArrayList li : trainList){
        	for (int i = 0 ; i < li.size() ; i++){
        		System.out.print(li.get(i) + ",");
			}
        	System.out.println();
		}*/
        double great = 0;
        double pass = 0;
        double unpass = 0;
        Bayes bayes = new Bayes();
        for(int i = 0;i < testList.size();i++){
            ArrayList<String> test;
            test = testList.get(i);
            String demo; // 获取分数
            demo = test.get(test.size()-1);
            test.remove(test.size() - 1); // 删除分数字段
            finalStr = bayes.predictClass(trainList, test);
            if(finalStr == "2")
                great++;
            if (finalStr == "1")
                pass++;
            if (finalStr == "0")
                unpass++;
            if(!demo.equals(finalStr)){
                wrong_number ++;
            }
        }
        double correct = (DecimalCalculate.sub(1.00000000, DecimalCalculate.div(wrong_number, testList.size())))*100;
        double uncorrect = 100 - correct ;
        res.add(great);
        res.add(pass);
        res.add(unpass);
        res.add(correct);
        res.add(uncorrect);
       /* System.out.println("预试错误个数："+ wrong_number+", 测试总个数"+testList.size());
        System.out.println("优秀：" + great + "---及格：" + pass + "---挂科：" + unpass );*/
        return res;
    }

    /**
     * 学生数据预测
     * @param test 学生数据
     * @return
     */
    public ArrayList<Double> bayes(ArrayList<String> test){
        ArrayList<Double> res = new ArrayList<>();
        data d = new data();
        PreRead convert = new PreRead();
        Bayes bayes = new Bayes();
        ArrayList<ArrayList<String>> list = d.getData();
        list = convert.readFile(list);
        test = convert.dataConvert(test);
        res = bayes.predictArray(list,test);
        return res;
    }
}
