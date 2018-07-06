package 作业;

import java.awt.GridLayout;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.*;

import com.mysql.fabric.xmlrpc.base.Value;

public class stumangs extends JFrame implements ActionListener{
	JButton jb1,jb2,jb3;
	Object[][] rowData;
	Object[] columnNames;
	ResultSet rs=null;
	JScrollPane sp;
	JTable tab;
	double[]str=new double[50];
	stumangs(){
		this.setSize(600,500);
		this.setTitle("班级概括");
		this.setLayout(new GridLayout(2,1));
		jb1=new JButton("升序");
		jb1.setActionCommand("shengxun");
		jb1.addActionListener(this);
		jb2=new JButton("降序");
		jb2.setActionCommand("jiangxun");
		jb2.addActionListener(this);
		jb3=new JButton("班级单科高分");
		jb3.setActionCommand("high");
		jb3.addActionListener(this);
		rowData =new Object[][] {};
		columnNames =new Object[] {"学号","姓名","java","数据库","线代"};
		rowData=new Object[50][7];
		
		Connection con = database.getConnect();
		try {
			PreparedStatement stmt=null;
			stmt=con.prepareStatement("select user,name,grade1,grade2,grade3 from stumessage");
			rs=stmt.executeQuery();
			int i=0;
			while(rs.next()) {
				for(int j=0;j<5;j++) {
					rowData[i][j]=rs.getObject(j+1);
					}
				i++;
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		try {
			PreparedStatement stmt=null;
			stmt=con.prepareStatement("select grade1,grade2,grade3 from stumessage");
			rs=stmt.executeQuery();
			int i=0;double ne = 0;
			while(rs.next()) {
				int j=0;
					 ne=rs.getDouble(j+1)+rs.getDouble(j+2)+rs.getDouble(j+3);
					 //rowData[i][6]=ne;
					 str[i]=ne;
					 rowData[i][5]=ne;
					 
				i++;
			}
			
			//System.out.println(ne);
		}catch(SQLException e) {
			e.printStackTrace();
		}
		/*for(int i=0;i<rowData.length;i++) {
			 for(int j=0;j<7;j++)
			     System.out.print (rowData[i][j]+"   ");
			System.out.println( );
		}*/
		tab=new JTable(rowData,columnNames);
		sp=new JScrollPane(tab);
		JPanel jp=new JPanel();
		jp.add(jb1);
		//jp.add(jb2);
		jp.add(jb3);
		JPanel jp1=new JPanel();
		jp1.add(sp);
		this.add(jp);
		this.add(jp1);
		this.setLocationRelativeTo(null);
		//this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}
	

	
	public void actionPerformed(ActionEvent e) {
		String cmd=e.getActionCommand();
		if(cmd.equals("shengxun")) {
			
			Connection con = database.getConnect();
			try {
				PreparedStatement stmt=null;
				stmt=con.prepareStatement("select user, name, grade1,grade2,grade3 as total from stumessage order by total");
				rs=stmt.executeQuery();
				int i=0;
				while(rs.next()) {
					for(int j=0;j<5;j++) {
						rowData[i][j]=rs.getObject(j+1);
						}
					i++;
				}/*
				double min;double max;
				for(int i1=0;i1<str.length-1;i1++) {
					min=str[i1];
					max=i1;
					for(int j=i1+1;j<str.length;j++) {
						if(str[j]<min) {
							min=str[j];
							max=j;
						}
					}
					if(min!=str[i1]&&max!=i1) {
						str[(int) max]=str[i1];
						str[i1]=max;
					}
				}
				for(int x=0;x<str.length;x++) {
					System.out.println(str[x]);
				}
				for(int i1=0;i1<rowData.length;i1++) {
					 for(int j=0;j<7;j++)
					     System.out.print (rowData[i1][j]+"   ");
					System.out.println( );
				}*/
			}catch(SQLException e1) {
				e1.printStackTrace();
			}
			tab.updateUI();
		
		}
		if(cmd.equals("jiangxun")) {
			Connection con = database.getConnect();
			try {
				PreparedStatement stmt=null;
				stmt=con.prepareStatement("select user, name, grade1,grade2,grade3 as total from stumessage order by score  desc");
				rs=stmt.executeQuery();
				int i=0;
				while(rs.next()) {
					for(int j=0;j<5;j++) {
						rowData[i][j]=rs.getObject(j+1);
						}
					i++;
				}
			}catch(SQLException e1) {
				e1.printStackTrace();
			}
			tab.updateUI();
		}
		if(cmd.equals("high")) {
			
			new high();
		}
		
		
	}

}
