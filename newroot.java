package 作业;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.*;
import javax.swing.*;
public class newroot extends JFrame  implements ActionListener{
	JButton jb1,jb2;
	JLabel lab1,lab2,lab3,lab4;
	JTextField txt1,txt2,txt3,txt4;
	newroot(){
		this.setSize(400,445);
		this.setTitle("新老师的注册");
		this.setLayout(new GridLayout(5,1));
		lab1=new JLabel("账号：");
		lab2=new JLabel("密码：");
		lab3=new JLabel("确定密码：");
		lab4=new JLabel("使用者：");
		txt1=new JTextField(10);
		txt2=new JTextField(10);
		txt3=new JTextField(8);
		txt4=new JTextField(9);
		jb1=new JButton("确定");
		jb2=new JButton("取消");
		jb2.setActionCommand("calcel");
		jb2.addActionListener(this);
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
		jp4.add(jb1);
		jb1.setActionCommand("enter");
		jb1.addActionListener(this);
		jp4.add(jb2);
		this.add(jp1);
		this.add(jp2);
		this.add(jp3);
		this.add(jp4);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		database cn=new database();
		Statement st=null;
		ResultSet rs=null;
		Connection con = database.getConnect();;
		PreparedStatement stmt=null;
		String cmd =e.getActionCommand();
		/*try {                    
			conn=cn.getConnect();
		    st=conn.createStatement();
			rs=st.executeQuery("select * from teachers");//先试图获取连接statement是sql的查询语句，resultset是遍历 查询结果的返回
			
		}catch(SQLException e1){
			
			e1.printStackTrace();
		}*/
		if(cmd.equals("enter")) {
			if(txt1.getText().equals("")||txt2.getText().equals("")||txt3.getText().equals("")) {
				JOptionPane.showMessageDialog(null,"不允许出现空的情况","操作提示",JOptionPane.NO_OPTION );
			}else {
			if(txt2.getText().equals(txt3.getText())) {
				PreparedStatement stmt1=null;
				try {
					stmt1=con.prepareStatement("insert into teachers(user,password) values(?,?)");
					stmt1.setString(1,txt1.getText());
					stmt1.setString(2,txt2.getText());
					stmt1.execute();
					stmt1=con.prepareStatement("insert into rootmessage(user) values(?)");
					stmt1.setString(1,txt1.getText());
					stmt1.execute();
					
					this.setVisible(false);
					
					JOptionPane.showMessageDialog(null,"注册成功","操作提示",JOptionPane.NO_OPTION );
					new HMI();
					
				} catch (SQLException e1) {
					JOptionPane.showMessageDialog(null,"失败","操作提示",JOptionPane.NO_OPTION );
				}
			}else {
				JOptionPane.showMessageDialog(null,"前后密码不一致","操作提示",JOptionPane.NO_OPTION );
			}
			}
		}
		if(cmd.equals("calcel")) {
			this.setVisible(false);
		}
		
		
		
	}

}
