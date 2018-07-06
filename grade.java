package 作业;

import javax.swing.*;
import java.awt.*;
import java.sql.*;

public class grade extends JFrame{
	Object[][] rowData;
	Object[] columnNames;
	JScrollPane sp;
	database cn=new database();
	Statement st=null;
	ResultSet rs=null;
	Connection conn=null;
	PreparedStatement stmt=null;
	JTable tab;
	String user;
	grade(String zhanghao){
		this.setSize(600,200);
		this.setTitle("你的成绩");
		rowData =new Object[][] {};
		columnNames =new Object[] {"学号","姓名","班级","院系","java","数据库","线代"};
		rowData=new Object[1][8];
		tab=new JTable(rowData,columnNames);
		sp=new JScrollPane(tab);
		Connection con = database.getConnect();
		//user=String.valueOf(rs.getInt("user"));
		/*try {                    
			conn=cn.getConnect();
		    st=conn.createStatement();
			rs=st.executeQuery("select * from short");//先试图获取连接statement是sql的查询语句，resultset是遍历 查询结果的返回
			
		}catch(SQLException e1){
			
			e1.printStackTrace();
		}
		try {
			while(rs.next()) {
				user=String.valueOf(rs.getInt("user"));
				System.out.println(zhanghao);
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			
		}
		*/
		try {
			stmt=con.prepareStatement("select * from stumessage where user=?");
			 stmt.setObject(1,zhanghao);
			 rs=stmt.executeQuery();
			 while(rs.next()){    //next（）获取里面的内容
				  //  System.out.println(rs.getString(1)+" "+rs.getString(2)+" "+rs.getString(3)+" "+
			// rs.getString(4)+" "+rs.getString(5)+" "+rs.getString(6)+" ");
				                         //getString（n）//测试
				                            //数据库中的列数是从1开始的
				    int i=0;
				    for(int j=0;j<7;j++) {
						rowData[i][j]=rs.getObject(j+1);
					}
				    
			
			 }
	 		//stmt.close();
	 		//con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

		JPanel jp=new JPanel();
		jp.add(sp);
		this.add(jp);
		
		this.setLocationRelativeTo(null);
		//this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}
}
