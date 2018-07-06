package 作业;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLDataException;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.GridLayout;
import java.awt.event.*;
import javax.swing.*;

public class find extends JFrame {
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
	find(String zhanghao){
		this.setSize(600,200);
		this.setTitle("学生信息");
		rowData =new Object[][] {};
		columnNames =new Object[] {"学号","姓名","班级","院系","java","数据库","线代"};
		rowData=new Object[1][8];
		tab=new JTable(rowData,columnNames);
		sp=new JScrollPane(tab);
		Connection con = database.getConnect();
		
		try {
			stmt=con.prepareStatement("select * from stumessage where user=?");
			 stmt.setObject(1,zhanghao);
			 rs=stmt.executeQuery();
			 while(rs.next()){    
				    int i=0;
				    for(int j=0;j<7;j++) {
						rowData[i][j]=rs.getObject(j+1);
					}
				    
			
			 }
	 		
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		

		JPanel jp=new JPanel();
		jp.add(sp);
		this.add(jp);
		
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}
	}
