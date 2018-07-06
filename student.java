package 作业;

import java.awt.GridLayout;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.*;

public class student extends JFrame implements ActionListener {
	JButton jb1,jb2,jb3,jb4;
	JLabel lab2,lab1;
	String zhanghao;
	student(String zhanghao){
		this.zhanghao=zhanghao;
		this.setSize(600,400);
		this.setTitle("欢迎 "+zhanghao+"同学");
		this.setLayout(new GridLayout(3,1));
		jb1=new JButton("成绩查询");
		jb2=new JButton("//课表查询");
		jb3=new JButton("信息修改");
		jb4=new JButton("//农大论坛");
		lab1=new JLabel("校园公告");
		JPanel jp=new JPanel();
		jp.add(jb1);
		jb1.setActionCommand("grade");
		jb1.addActionListener(this);
		jp.add(jb2);
		jp.add(jb3);
		jb3.setActionCommand("change");
		jb3.addActionListener(this);
		jp.add(jb4);
		JPanel jp1=new JPanel();
		jp1.add(lab1);
		this.add(jp);
		this.add(jp1);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}




	public void actionPerformed(ActionEvent e) {
		String cmd =e.getActionCommand();
		if(cmd.equals("grade")) {
			new grade(zhanghao);


		}
		if(cmd.equals("change")) {
			new change(zhanghao);
		}
		
	}
	

}

