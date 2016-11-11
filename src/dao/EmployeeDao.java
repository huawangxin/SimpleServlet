package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import sun.print.PeekGraphics;

import entity.Employee;
/**
 * 用于封装了对t_emp的增删改查操作
 * @author wangxin
 */
public class EmployeeDao {

	public List<Employee> findAll() throws Exception {
		List<Employee> emps=new ArrayList<Employee>();
		Connection con=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		try{
			con=DBUtil.getConnection();
			ps=con.prepareStatement("select * from t_emp_wangxin");
			rs=ps.executeQuery();
			if(rs.next()){
				Employee emp=new Employee(
						rs.getInt("id"),
						rs.getString("name"), 
						rs.getDouble("salary"),
						rs.getInt("age"));
				emps.add(emp);
			}
		}catch (Exception e) {
			e.printStackTrace();
			throw e;
		}finally{
			DBUtil.close(con);
		}
		return emps;
	}

	public void delById(int id) throws Exception {
		Connection con=null;
		PreparedStatement ps=null;
		int rs=-1;
		try{
			con=DBUtil.getConnection();
			ps=con.prepareStatement("delete from t_emp_wangixn where id=?");
			ps.setInt(1,id);
			rs=ps.executeUpdate();
		}catch (Exception e) {
			e.printStackTrace();
			throw e;
		}finally{
			DBUtil.close(con);
		}
	}

	public Employee findById(int id) throws Exception {
		Employee emp=new Employee();
		Connection con=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		try{
			con=DBUtil.getConnection();
			ps=con.prepareStatement("select * from t_emp_wangxin where id=?");
			ps.setInt(1,id);
			rs=ps.executeQuery();
			if(rs.next()){
					emp=new Employee(
						rs.getInt("id"),
						rs.getString("name"), 
						rs.getDouble("salary"),
						rs.getInt("age"));
			}
		}catch (Exception e) {
			e.printStackTrace();
			throw e;
		}finally{
			DBUtil.close(con);
		}
		return emp;
	}

	public void save(Employee emp) throws Exception {
		Connection con=null;
		PreparedStatement ps=null;
		try{
			con=DBUtil.getConnection();
			ps=con.prepareStatement("update t_emp_wangixn values(?,?,?) where id=?");
			ps.setString(1, emp.getName());
			ps.setDouble(2, emp.getSalary());
			ps.setInt(3, emp.getAge());
			ps.setInt(4,emp.getId());
			ps.executeUpdate();
		}catch (Exception e) {
			e.printStackTrace();
			throw e;
		}finally{
			DBUtil.close(con);
		}
	}

	public void add(Employee emp) throws Exception {
		Connection con=null;
		PreparedStatement ps=null;
		try{
			con=DBUtil.getConnection();
			ps=con.prepareStatement("insert into t_emp_wangxin values(emp_id_seq_wangxin.nextval,?,?,?)");
			ps.setString(1, emp.getName());
			ps.setDouble(2, emp.getSalary());
			ps.setInt(3, emp.getAge());
			ps.executeUpdate();
		}catch (Exception e) {
			e.printStackTrace();
			throw e;
		}finally{
			DBUtil.close(con);
		}
	}
}
