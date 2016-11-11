package dao;

import java.sql.Connection;
import java.sql.DriverManager;
/**
 * 数据库的工具类
 * 用于获取数据库的链接和关闭链接
 * 
 * @author huawangxin
 *
 * 
 * @date 2016年11月10日 下午5:24:28
 */
public class DBUtil {
	public static Connection getConnection() throws Exception{
		Connection con=null;
		try{
			Class.forName("oracle.jdbc.OracleDriver");
			con=DriverManager.getConnection("jdbc:oracle:thin:@192.168.71.90:1521:orcl","huawangxin","huawangxin");
		}catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return con;
	}
	public static void close(Connection con) throws Exception{
		if(con!=null){
			try{
				con.close();
			}catch (Exception e) {
				e.printStackTrace();
				throw e;
			}
		}
	}
}
