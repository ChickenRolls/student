package 作业;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;
public class database {
	String driver="com.mysql.jdbc.Driver";
	static String url="jdbc:mysql://127.0.0.1:3306/mansge?useSSL=false";
	static String user="root";
	static String password="123456";
	static Connection conn=null;
	Statement st=null;
	PreparedStatement pst=null;
	ResultSet rs=null;
	public static Connection getConnect() {
		try {
			DriverManager.registerDriver(new com.mysql.jdbc.Driver());
			conn=DriverManager.getConnection(url,user,password);//这个链接承载了密码和用户名
		}catch(SQLException e){
			e.printStackTrace();
		}
		return conn;
	}
	public static void insert(Connection con,String obj[]) {
		PreparedStatement stmt=null;
		try {
			stmt=con.prepareStatement("insert into stumessage(user,name,class,major,grade1,grade2,grade3) values(?,?,?,?,?,?,?)");
			
			stmt.setInt(1,Integer.parseInt(obj[0]));
			stmt.setString(2,(String) obj[1]);
			stmt.setInt(3,Integer.parseInt(obj[2]));
			stmt.setString(4,(String) obj[3]);
			stmt.setInt(5,Integer.parseInt(obj[4]));
			stmt.setInt(6,Integer.parseInt(obj[5]));
			stmt.setInt(7,Integer.parseInt(obj[6]));
			stmt.executeUpdate();			
			
		} catch (SQLException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null,"失败","操作提示",JOptionPane.NO_OPTION );
			//这个是提示框还是有点 意思的
		}
	}
	public static void delete(Connection con,String s) {
		try {
			PreparedStatement stmt;
			 stmt=con.prepareStatement("delete from stumessage where user=?");
		    stmt.setObject(1,s);
			stmt.execute();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally{
			
		}
	}
	
	public static ResultSet find(Connection con,String s) {
		ResultSet rs=null;
		PreparedStatement stmt=null;//sql语句的执行
		try {
			stmt=con.prepareStatement("select * from stumessage where user=?");
			 stmt.setObject(1,s);
			 rs=stmt.executeQuery();
			 while(rs.next()){    //next（）获取里面的内容
				 for(int j=0;j<2;j++) {
						
					}
				    }
			
	 		
	 		//stmt.close();
	 		//con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rs;
	}
	public static void alter(Connection con,Object obj[]) {
		try {
			PreparedStatement stmt;
			 stmt=con.prepareStatement("delete from tongxun where xingming=?");
		    stmt.setObject(1,obj[0].toString());
			stmt.execute();
   stmt=con.prepareStatement("insert into tongxun(xingming,haoma) values(?,?)");
			
			stmt.setString(1,obj[0].toString());
			stmt.setString(2,obj[1].toString());
			stmt.execute();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally{
			
		}
	}

}
