package 作业;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.security.sasl.Sasl;
import javax.swing.*;

public class stugrade extends JFrame implements ActionListener{
	Object[][] rowData;
	Object[] columnNames;
	JScrollPane sp;
	JTable tab;
	ResultSet rs=null;
	database cn=new database();
	JTextField txt1,txt2,txt3,txt4,txt5,txt6,txt7;
	stugrade(){
		this.setSize(600,500);
		this.setTitle("学生成绩");
		this.setLayout(new GridLayout(3,1));
		rowData =new Object[][] {};
		columnNames =new Object[] {"学号","姓名","班级","院系","java","数据库","线代","总分"};
		rowData=new Object[100][9];
		Connection con = database.getConnect();
		try {
			PreparedStatement stmt=null;
			stmt=con.prepareStatement("select *from stumessage");
			rs=stmt.executeQuery();
			int i=0;
			while(rs.next()) {
				for(int j=0;j<7;j++) {
					rowData[i][j]=rs.getObject(j+1);}
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
					 rowData[i][7]=ne;
					 
				i++;
			}
			
			//System.out.println(ne);
		}catch(SQLException e) {
			e.printStackTrace();
		}
		JPanel jp1=new JPanel();
		JButton but1,but2,but3,but4,but5;
		but1=new JButton("增加");
		but1.setActionCommand("add");
		but1.addActionListener(this);
		but2=new JButton("查找");
		but2.setActionCommand("find");
		but2.addActionListener(this);
		but3=new JButton("删除");
		but3.setActionCommand("delete");
		but3.addActionListener(this);
		but4=new JButton("修改");
		but4.setActionCommand("xiugai");
		but4.addActionListener(this);
		but5=new JButton("刷新");
		but5.setActionCommand("new");
		but5.addActionListener(this);
		JPanel jp=new JPanel();
		jp.add(but1);
		jp.add(but2);
		jp.add(but3);
		jp.add(but4);
		jp.add(but5);
		txt1=new JTextField(5);
		txt2=new JTextField(5);
		txt3=new JTextField(5);
		txt4=new JTextField(5);
		txt5=new JTextField(5);
		txt6=new JTextField(5);
		txt7=new JTextField(5);
		
		JPanel jp3=new JPanel();
		jp3.add(txt1);
		jp3.add(txt2);
		jp3.add(txt3);
		jp3.add(txt4);
		jp3.add(txt5);
		jp3.add(txt6);
		jp3.add(txt7);
		tab=new JTable(rowData,columnNames);
		sp=new JScrollPane(tab);
		jp1.add(sp);
		this.add(jp);
		this.add(jp3);
		this.add(jp1);
		this.setLocationRelativeTo(null);
		//this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}
	@SuppressWarnings("unlikely-arg-type")
	@Override
	public void actionPerformed(ActionEvent e) {
		String cmd=e.getActionCommand();
		if(cmd.equals("add")) {
			if(txt1.equals(" ")||txt2.equals(" ")||txt3.equals(" ")||
					txt4.equals(" ")||txt5.equals(" ")||txt6.equals(" ")||txt7.equals("")) {
				JOptionPane.showMessageDialog(null,"不允许出现空的情况","操作提示",JOptionPane.NO_OPTION );
			}else {
			//System.out.println(txt1.getText());
			String n1=txt1.getText();
			String n2=txt2.getText();
			String n3=txt3.getText();
			String n4=txt4.getText();
			String n5=txt5.getText();
			String n6=txt6.getText();
			String n7=txt7.getText();
			String str[]={n1,n2,n3,n4,n5,n6,n7};
			Connection sin=database.getConnect();
			database.insert(sin, (String[]) str);
			}
		}
		if(cmd.equals("find")) {
			String ne=txt1.getText();
			new find(ne);
			
			
			
			/*String ne=txt1.getText();
			PreparedStatement stmt=null;
			Connection con=database.getConnect();
			try {
				stmt=con.prepareStatement("select * from stumessage where user=?");
				 stmt.setObject(1,ne);
				 rs=stmt.executeQuery();
				 int i=0;
				 while(rs.next()){ 			
					 for(int j=0;j<7;j++) {
						 rowData[i][j]=rs.getObject(j+1);
					             }
				       }  
			System.out.println("ok");
			
		
			
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}*/
		}
		if(cmd.equals("delete")) {
			String ne1=txt1.getText();
			Connection sin=database.getConnect();
			database.delete(sin,ne1);
		}
		if(cmd.equals("xiugai")) {
			Connection con = database.getConnect();
			PreparedStatement stmt=null;
			try {
				stmt=con.prepareStatement("update stumessage set name=?,class=?,major=?,grade1=?,grade2=?,grade3=?where user=?");
				stmt.setObject(1,txt2.getText());
				stmt.setObject(2,txt3.getText());
				stmt.setObject(3,txt4.getText());
				stmt.setObject(4,txt5.getText());
				stmt.setObject(5,txt6.getText());
				stmt.setObject(6,txt7.getText());
				stmt.setObject(7,txt1.getText());
				stmt.executeUpdate();
			
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		if(cmd.equals("new")) {
			this.setVisible(false);
			new stugrade();
		}
		
		
		// TODO Auto-generated method stub
		
		}

}
