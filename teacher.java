package 作业;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class teacher extends JFrame implements ActionListener{
	JButton jb1,jb2,jb3;
	JTextField txt1;
	JLabel lab2,lab1;
	String zhanghao;
	teacher(String zhanghao){
		this.zhanghao=zhanghao;
		this.setSize(500,350);
		this.setTitle("欢迎   "+zhanghao+" 老师");
		this.setLayout(new GridLayout(3,1));
		jb1=new JButton("学生成绩");
		jb1.setActionCommand("chaxun");
		jb1.addActionListener(this);
		jb2=new JButton("班级概括");
		jb2.setActionCommand("stumessage");
		jb2.addActionListener(this);
		txt1=new JTextField(10);
		lab2=new JLabel("密码修改");
		jb3=new JButton("确定修改");
		jb3.setActionCommand("enter");
		jb3.addActionListener(this);
		lab1=new JLabel("");
		JPanel jp=new JPanel();
		jp.add(jb1);
		jp.add(jb2);
		JPanel jp1=new JPanel();
		jp1.add(lab2);
		jp1.add(txt1);
		jp1.add(jb3);
		JPanel jp2=new JPanel();
		jp1.add(lab1);
		this.add(jp);
		this.add(jp1);
		this.add(jp2);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);

}
	@Override
	public void actionPerformed(ActionEvent e) {
		String cmd =e.getActionCommand();
		if(cmd.equals("chaxun")) {
			new stugrade();
		}
		if(cmd.equals("stumessage")) {
			new stumangs();
			}
		if(cmd.equals("enter")) {
			Connection con = database.getConnect();
			PreparedStatement stmt=null;
			try {
				stmt=con.prepareStatement("update teachers set password=?where user=?");
				stmt.setObject(1,txt1.getText());
				stmt.setObject(2, zhanghao);
				stmt.executeUpdate();//没有这个就不会执行
				JOptionPane.showMessageDialog(null,"修改成功","操作提示",JOptionPane.NO_OPTION );
			}catch(SQLException e1) {
				e1.printStackTrace();
			}
		}
	}
		// TODO Auto-generated method stub
		
	}
