package 作业;

import java.awt.GridLayout;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.*;

public class high extends JFrame{
	
	JLabel lab1,lab2,lab3,lab4;
	JLabel bu1,bu2,bu3,bu4;
	ResultSet rs=null;
	high(){
		this.setSize(350,400);
		this.setTitle("最高分");
		this.setLayout(new GridLayout(4,2));
		lab1=new JLabel("java:");
		lab2=new JLabel("数据库:");
		lab3=new JLabel("线代:");
		lab4=new JLabel("总分:");
		bu1=new JLabel("99");
		bu2=new JLabel("88");
		bu3=new JLabel("98");
		bu4=new JLabel("285");
		
		
		Connection con = database.getConnect();
		try {
			int []stu=new int[50];
			PreparedStatement stmt=null;
			stmt=con.prepareStatement("select grade1 from stumessage where order by desc ");
			rs=stmt.executeQuery();
		
		}catch(SQLException e1) {
			
		}
		
		
		
		
		
		
		
		JPanel jp1=new JPanel();
		jp1.add(lab1);
		jp1.add(bu1);
		JPanel jp2=new JPanel();
		jp2.add(lab2);
		jp2.add(bu2);
		JPanel jp3=new JPanel();
		jp3.add(lab3);
		jp3.add(bu3);
		JPanel jp4=new JPanel();
		jp4.add(lab4);
		jp4.add(bu4);
		
		
		
		
		
		
		
		
		
		
		
		
		this.add(jp1);
		this.add(jp2);
		this.add(jp3);
		this.add(jp4);
		
		
		
		
		
		
		
		this.setLocationRelativeTo(null);
		//this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
		
	}

}
