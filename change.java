package 作业;

import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLDataException;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.GridLayout;
import java.awt.event.*;
import javax.swing.*;

public class change extends JFrame implements ActionListener{
	JButton but1,but2;
	JLabel lab1,lab2,lab3,lab4;
	JTextField txt1,txt2,txt3,txt4;
	 String zhanghao;
	change(String zhanghao){
		this.zhanghao=zhanghao;
		this.setSize(400,500);
		this.setTitle("信息修改");
		this.setLayout(new GridLayout(5,2));
		but1=new JButton("确定");
		but1.setActionCommand("enter");
		but1.addActionListener(this);
		but2=new JButton("取消");
		but2.setActionCommand("quxiao");
		but2.addActionListener(this);
		lab1=new JLabel("名字");
		lab2=new JLabel("班级");
		lab3=new JLabel("院系");
		lab4=new JLabel("密码");
		txt1=new JTextField(10);
		txt2=new JTextField(10);
		txt3=new JTextField(10);
		txt4=new JTextField(10);
		JPanel jp1=new JPanel();
		jp1.add(lab1);
		jp1.add(txt1);
		JPanel jp2=new JPanel();
		jp2.add(lab2);
		jp2.add(txt2);
		JPanel jp3=new JPanel();
		jp3.add(lab3);
		jp3.add(txt3);
		JPanel jp4=new JPanel();
		jp4.add(lab4);
		jp4.add(txt4);
		JPanel jp5=new JPanel();
		jp5.add(but1);
		jp5.add(but2);
		this.add(jp1);
		this.add(jp2);
		this.add(jp3);
		this.add(jp4);
		this.add(jp5);
		
		
		
		
		
		
		
		this.setLocationRelativeTo(null);
		//this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String cmd=e.getActionCommand();
		Statement st=null;
		ResultSet rs=null;
		PreparedStatement stmt=null;
		// TODO Auto-generated method stub
		
		if(cmd.equals("enter")) {
			Connection con = database.getConnect();
		try {
			System.out.println(zhanghao);
			stmt=con.prepareStatement("update stumessage set name=?,class=?,major=?where user=?");
			stmt.setObject(1,txt1.getText());
			stmt.setObject(2,txt2.getText());
			stmt.setObject(3,txt3.getText());
			stmt.setObject(4,zhanghao);
			stmt.executeUpdate();
		
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			stmt=con.prepareStatement("update students set password=?where user=?");
			stmt.setObject(1, txt4.getText());
			stmt.setObject(2, zhanghao);
			stmt.executeUpdate();
			
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		this.setVisible(false);
		
		JOptionPane.showMessageDialog(null,"修改成功！","操作提示",JOptionPane.NO_OPTION );
		}
		if(cmd.equals("quxiao")) {
			this.setVisible(false);
		}
		
	}

}
