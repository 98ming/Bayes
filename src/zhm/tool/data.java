package zhm.tool;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class data {
    private static final String DBDRIVER = "org.gjt.mm.mysql.Driver";
    private static final String DBURL = "jdbc:mysql://localhost:3306/test";
    private static final String DBUSER = "root";
    private static final String DBPASS = "123456";

    Connection  conn = null;


    public ArrayList<ArrayList<String>> getData(){
        ArrayList<ArrayList<String>> trainList = new ArrayList<>();
        try {
            String table = "scoretest";
            /*if(select == 1)
                table = "score";
            else if(select == 2)
                table = "scoretest";*/
            Class.forName(DBDRIVER);
            conn = DriverManager.getConnection(DBURL,DBUSER,DBPASS);
            String sql = "select * from " + table;
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                ArrayList<String> list = new ArrayList<>();
                for (int i=3;i<=8;i++){
                    String s = rs.getString(i);
                    list.add(s);
                }
                trainList.add(list);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return trainList;
    }
}
