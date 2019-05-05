package zhm.Datamining;

import zhm.tool.DecimalCalculate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Bayes {
    /**
     *
     * @param trainList
     * @param testList
     * @return 学生分类概率
     */
	public ArrayList<Double> predictArray(ArrayList<ArrayList<String >> trainList, ArrayList<String> testList){
		Map<String, ArrayList<ArrayList<String>>> resultMap = dataSet(trainList);
		ArrayList<Double> res = new ArrayList<>();
		// double mMax = 0.0;
		// String finalResult  = "";
		for(int i = 0;i < resultMap.size();i++){
			String key = "";
			if(i == 0){
				key = "2";
			}
			else if(i == 1){
				key = "1";
			}
			else{
				key = "0";
			}
			// 优秀 or 及格 or 不及格的学生数据
			ArrayList<ArrayList<String>> temp = resultMap.get(key);
			// 计算总概率
			double mCurrent = culCofV(temp.size(),trainList.size());
			// 计算分概率
			for(int j = 0;j < testList.size();j++){
				// 计算条件概率
				double pv = culPofV(temp,testList.get(j),j);
				// 精确乘法
				mCurrent = DecimalCalculate.mul(mCurrent, pv);
			}
			// System.out.println("分类为" + key +  "此次概率："  + mCurrent);
			res.add(mCurrent);
			// 分类结果
			/*if(mMax <= mCurrent){
				if(i == 0){
					finalResult = "2";
				}
				else if(i == 1){
					finalResult = "1";
				}
				else{
					finalResult = "0";
				}
				mMax = mCurrent;
			}*/
		}
		// System.out.println("-------------------------------------");
		return res;
	}

    /**
     *
     * @param trainList
     * @param testList
     * @return 学生分类结果
     */
    public String predictClass(ArrayList<ArrayList<String >> trainList, ArrayList<String> testList){
        Map<String, ArrayList<ArrayList<String>>> resultMap = dataSet(trainList);
        double mMax = 0.0;
        String finalResult  = "";
        for(int i = 0;i < resultMap.size();i++){
            String key = "";
            if(i == 0){
                key = "2";
            }
            else if(i == 1){
                key = "1";
            }
            else{
                key = "0";
            }
            // 优秀 or 及格 or 不及格的学生数据
            ArrayList<ArrayList<String>> temp = resultMap.get(key);
            // 计算总概率
            double mCurrent = culCofV(temp.size(),trainList.size());
            // 计算分概率
            for(int j = 0;j < testList.size();j++){
                // 计算条件概率
                double pv = culPofV(temp,testList.get(j),j);
                // 精确乘法
                mCurrent = DecimalCalculate.mul(mCurrent, pv);
            }
            // System.out.println("分类为" + key +  "此次概率："  + mCurrent);
            // 分类结果
			if(mMax <= mCurrent){
				if(i == 0){
					finalResult = "2";
				}
				else if(i == 1){
					finalResult = "1";
				}
				else{
					finalResult = "0";
				}
				mMax = mCurrent;
			}
        }
        // System.out.println("-------------------------------------");
        return finalResult;
    }
	/**
	 * 计算总概率P(y)
	 * @param ySize
	 * @param nSize
	 * @return
	 */
	public double culCofV(int ySize,int nSize){
		return DecimalCalculate.div(ySize, nSize);
	}
	/**
	 * 分类
	 * @param list
	 * @return
	 */
	public Map<String, ArrayList<ArrayList<String>>> dataSet(List<ArrayList<String>> list){
		Map<String, ArrayList<ArrayList<String>>> culMap = new HashMap<String, ArrayList<ArrayList<String>>>();
		ArrayList<ArrayList<String>> great = new ArrayList<ArrayList<String>>();
		ArrayList<ArrayList<String>> pass = new ArrayList<ArrayList<String>>();
		ArrayList<ArrayList<String>> unpass = new ArrayList<ArrayList<String>>();
		for(int i = 0;i < list.size();i++){
			ArrayList<String> temp = new ArrayList<String>();
			temp = list.get(i);
			String mResult = temp.get(temp.size()-1);//获取最后一项
			if(mResult.equals("2")){
				great.add(temp);
			}
			if(mResult.equals("1")){
				pass.add(temp);
			}
			else{
				unpass.add(temp);
			}
		}
		culMap.put("2", great);
		culMap.put("1", pass);
		culMap.put("0", unpass);
		return culMap;
	}
	/**
	 * 计算条件概率
	 * @param mList : 分类集
	 * @param mStr  : 每条学生数据的value
	 * @param index : 每条学生数据的key
	 */
	public double culPofV(ArrayList<ArrayList<String>> mList,String mStr,int index){
		int count = 0;
		for(int i = 0;i < mList.size();i++){
			if(mStr.equals(mList.get(i).get(index))){
				count++;
			}
		}
		return DecimalCalculate.div(count, mList.size(), 3);
	}
}