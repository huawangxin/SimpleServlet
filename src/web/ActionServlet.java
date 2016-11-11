package web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.EmployeeDao;
import entity.Employee;
/**
 * 用于处理多个请求的控制类
 * 通过分析请求资源路径中的资源名称
 * 实现对不同请求的分发
 * @author wangxin
 *
 */
public class ActionServlet extends HttpServlet {
	public void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//常规三步
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		//获取请求资源路径
		String uri=request.getRequestURI();
		//获取资源名
		uri=uri.substring(uri.lastIndexOf("/"),uri.lastIndexOf("."));
		//创建dao实例
		EmployeeDao empDao=new EmployeeDao();
		//分支判断
		if(uri.equals("/list")){
			try {
				//调用dao的findAll()方法获取数据
				List<Employee> emps=empDao.findAll();
				//显示数据
				out.println("<table>");
				out.println("<caption>员工信息表</caption>");
				out.println("<tr><td>编号</td><td>姓名</td><td>薪水</td><td>年龄</td><td>操作</td></tr>");
				for(Employee emp:emps){
					out.println("<tr>");
					out.println("<td>"+emp.getId()+"</td>");
					out.println("<td>"+emp.getName()+"</td>");
					out.println("<td>"+emp.getSalary()+"</td>");
					out.println("<td>"+emp.getAge()+"</td>");
					out.println("<td><a href='find.do?id="+emp.getId()+"'>修改</td>");
					//out.println("<td><a href='del.do?id="+emp.getId()+"'>删除</td>");
					out.println("<td><a href='del.do?id="+emp.getId()+"' onclick=\"return confirm('确定删除"+
							emp.getName()+"吗?')\">删除</a></td>");
					out.println("</tr>");
				}
				out.println("</table>");
				out.println("<a href='addEmp.html'>添加新员工</a>");
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}else if(uri.equals("/del")){
			int id=Integer.parseInt(request.getParameter("id"));
			try{
				empDao.delById(id);
				response.sendRedirect("list.do");
			}catch (Exception e) {
				e.printStackTrace();
			}
		}else if(uri.equals("/find")){
			int id=Integer.parseInt(request.getParameter("id"));
			try{
				empDao.findById(id);
				response.sendRedirect("list.do");
			}catch (Exception e) {
				e.printStackTrace();
			}
		}else if(uri.equals("/add")){
			Employee emp=new Employee();
			try{
			
			emp.setName(request.getParameter("name"));
			emp.setSalary(Double.parseDouble(request.getParameter("salary")));
			emp.setAge(Integer.parseInt(request.getParameter("age")));
			empDao.save(emp);
			response.sendRedirect("list.do");
			}catch (Exception e) {
				e.printStackTrace();
			}
		}
		out.close();
	}

}
