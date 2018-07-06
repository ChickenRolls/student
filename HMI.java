package 作业;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;
public class HMI extends JFrame implements ActionListener{
	JButton but1,but2,but3;
	JLabel lab1,lab2,lab3;
	JTextField zhanghao;
	 //String user1=zhanghao.getText();
	JPasswordField mima; 
	JComboBox<String> jBox;//定义类的信息放在外面才是可以给全局使用，放在构造方法里面只能在方法内部使用
	public HMI() {
		this.setTitle("登录首页");
		this.setSize(400, 300);
		this.setLayout(new GridLayout(4,1)); 
		jBox=new JComboBox<String>();  
	    jBox.addItem("学生");  
	    jBox.addItem("老师");  
	   // jBox.addActionListener(this);
		lab1=new JLabel("账号:");
		lab2=new JLabel("密码:");
		lab3=new JLabel("登录状态");
		zhanghao=new JTextField(10);
		mima=new JPasswordField(10);
		but1=new JButton("登录");
		but1.setActionCommand("enter");
		but1.addActionListener(this);
		but2=new JButton("清空");
		but2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				zhanghao.setText(" ");
				mima.setText("");
				lab3.setText("重新登入");
			}
		
		});
		but3=new JButton("注册");
		but3.setActionCommand("newuser");
		but3.addActionListener(this);
		JPanel jp=new JPanel();
		jp.add(lab1);
		jp.add(zhanghao);
		jp.add(but1);
		JPanel jp2=new JPanel();
        jp2.add(lab2);
        jp2.add(mima);
        jp2.add(but2);
        JPanel jp4=new JPanel();
        jp4.add(lab3);
        JPanel jp3=new JPanel();
        jp3.add(jBox);
        jp3.add(but3);
        this.add(jp);
		this.add(jp2);
		this.add(jp4);
		this.add(jp3);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}

	public String getuser(String user) {
		user=zhanghao.getText();
		return user;
	}
	public void actionPerformed(ActionEvent e) {
		database cn=new database();
		Statement st=null;
		ResultSet rs=null;
		Connection conn=null;
		String user,password;
		String cmd =e.getActionCommand();
		String s=(String)jBox.getSelectedItem();//获取下拉式的当前字符
		try {                    
			conn=cn.getConnect();
		    st=conn.createStatement();
			rs=st.executeQuery("select * from students");//先试图获取连接statement是sql的查询语句，resultset是遍历 查询结果的返回
			
		}catch(SQLException e1){
			
			e1.printStackTrace();
		}
		if(cmd.equals("enter")) {
			if(zhanghao.getText().equals("")||mima.getPassword().equals("")) {
				JOptionPane.showMessageDialog(null,"账号和密码框不能为空!","操作提示",JOptionPane.NO_OPTION );
			}else {
				if(s.equals("学生")) {
					try {                    
						conn=cn.getConnect();
					    st=conn.createStatement();
						rs=st.executeQuery("select * from students");//先试图获取连接statement是sql的查询语句，resultset是遍历 查询结果的返回
						
					}catch(SQLException e1){
						
						e1.printStackTrace();
					}
					try {
						while(rs.next()) {
							user=String.valueOf(rs.getInt("user"));
							password=String.valueOf(rs.getInt("password"));
							if(user.equals(zhanghao.getText())&&
									password.equals(String.valueOf(mima.getPassword()))) {
								JOptionPane.showMessageDialog(null,"欢迎您 的使用","操作提示",JOptionPane.NO_OPTION );
								PreparedStatement stmt1=null;
								try {
									stmt1=conn.prepareStatement("insert into short(user) values(?)");
									stmt1.setString(1,zhanghao.getText());
									stmt1.execute();
								} catch (SQLException e1) {//短暂的将账号放入数据库中登录用户的记录
									//JOptionPane.showMessageDialog(null,"失败","操作提示",JOptionPane.NO_OPTION );
								}
								new student(zhanghao.getText());
								this.setVisible(false);
								break;
								        //登入页面
							}else {
								lab3.setText("无效账号密码！");
								//JOptionPane.showMessageDialog(null,"no","操作提示",JOptionPane.NO_OPTION );
								
							}
						}
					}catch(SQLException e1) {
						e1.printStackTrace();
					}
				}
				if(s.equals("老师")) {
					try {                    
						conn=cn.getConnect();
					    st=conn.createStatement();
						rs=st.executeQuery("select * from teachers");//先试图获取连接statement是sql的查询语句，resultset是遍历 查询结果的返回
						
					}catch(SQLException e1){
						
						e1.printStackTrace();
					}
					try {
						while(rs.next()) {
							user=String.valueOf(rs.getInt("user"));
							password=String.valueOf(rs.getInt("password"));
							if(user.equals(zhanghao.getText())&&
									password.equals(String.valueOf(mima.getPassword()))) {
								JOptionPane.showMessageDialog(null,"欢迎您的使用","操作提示",JOptionPane.NO_OPTION );
								new teacher(zhanghao.getText());
								this.setVisible(false);
								break;
								        //登入页面
							}else {
								lab3.setText("无效账号密码！");
								//JOptionPane.showMessageDialog(null,"no","操作提示",JOptionPane.NO_OPTION );
								
							}
						}
					}catch(SQLException e1) {
						e1.printStackTrace();
					}
				}
				
			}
		}
		if(cmd.equals("newuser")) {
			this.setVisible(false);
			if(s.equals("学生")) {
			new newuser();
			}
			if(s.equals("老师")) {
				new newroot();
			}
		}
		
	}
	
	
	public static void main(String[]args) {//              main函数的入口在这！
		new HMI();
	}

}
