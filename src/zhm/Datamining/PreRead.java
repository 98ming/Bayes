package zhm.Datamining;

import java.util.ArrayList;

public class PreRead {

	/**
	 * 读取并预处理数据
	 * @param list
	 */
	public ArrayList<ArrayList<String>> readFile(ArrayList<ArrayList<String>> list){

		ArrayList<ArrayList<String>> outList = new ArrayList<ArrayList<String>>();
		for(ArrayList li : list){
			outList.add(dataConvert(li));
		}
		return outList;
	}

	public ArrayList<String> dataConvert(ArrayList<String> string ){
		int len = string.size();
		StringBuilder sb = new StringBuilder();
		ArrayList<String> res = new ArrayList<>();
		for (int i = 0 ; i < len ; i++){
			String s = string.get(i);
			if (i == 0){
				sb.append(videoDurationConversion(s) + ","); // 视频时长转换
			}
			if (i == 1){
				sb.append(Integer.parseInt(s) + ","); // 做题次数转换
			}
			if (i == 2){
				sb.append(accuracyConversion(s) + ","); // 准确率转换
			}
			if (i == 3){
				sb.append(exerciseTimeConversion(s) + ","); // 做题时长转换
			}
			if (i == 4){
				sb.append(sexConversion(s) + ","); // 性别转换
			}
			if (i == 5){
				sb.append(scoreConversion(s)); // 分数转换
			}
		}
		String tmp [] = sb.toString().split(",");
		for (String s : tmp)
			res.add(s);
		return res;
	}

	private int videoDurationConversion(String string) {
			int ageTemp = Integer.parseInt(string);
			if (!string.contains("?")){
				if (ageTemp<200) {
					return 1;
				} else if (ageTemp<400) {
					return 2;
				} else if (ageTemp<600) {
					return 3;
				} else if (ageTemp<800) {
					return 4;
				} else if (ageTemp<1000) {
					return 5;
				} else if (ageTemp<1200) {
					return 6;
				} else if (ageTemp<1400) {
					return 7;
				} else if (ageTemp<1600) {
					return 8;
				} else if (ageTemp<1800) {
					return 9;
				} else {
					return 10;
				}
			}
			return 0;
	}
	private int sexConversion(String string) {
		if (!string.contains("?")) {
			if (string.contains("男")) {
				return 1;
			} else if (string.contains("女")) {
				return 2;
			}
		}
		return 0;
	}
	private int accuracyConversion(String string) {
		int accuracyTemp = Integer.parseInt(string);
		if(!string.contains("?")){
			if (accuracyTemp<10) {
				return 1;
			} else if (accuracyTemp<20) {
				return 2;
			} else if (accuracyTemp<30) {
				return 3;
			} else if (accuracyTemp<40) {
				return 4;
			} else if (accuracyTemp<50) {
				return 5;
			} else if (accuracyTemp<60) {
				return 6;
			} else if (accuracyTemp<70) {
				return 7;
			} else if (accuracyTemp<80) {
				return 8;
			} else if (accuracyTemp<90){
				return 9;
			} else {
				return 10;
			}
		}
		return 0;
	}
	private int exerciseTimeConversion(String string) {
		int time = Integer.parseInt(string);
		if (!string.contains("?")) {
			if (time<120) {
				return 1;
			} else if (time<240) {
				return 2;
			} else if (time<360) {
				return 3;
			} else if (time<480) {
				return 4;
			} else if (time<600) {
				return 5;
			} else if (time<720) {
				return 6;
			} else if (time<840) {
				return 7;
			} else if (time<960) {
				return 8;
			} else if (time<1080) {
				return 9;
			} else {
				return 10;
			}
		}
		return 0;
	}
	private int scoreConversion(String string) {
		int score = Integer.parseInt(string);
		if (score >= 80) {
			return 2;
		}
		if (score >= 60) {
			return 1;
		}
		return 0;
	}
}