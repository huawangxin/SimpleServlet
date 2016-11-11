package web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.EmployeeDao;

import entity.Employee;
/**
 * ���ڴ���������Ŀ�����
 * ͨ������������Դ·���е���Դ����
 * ʵ�ֶԲ�ͬ����ķַ�
 * @author wangxin
 *
 */
public class ActiveSerlvet extends HttpServlet {
	public void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		String uri=request.getRequestURI();
		uri=uri.substring(uri.lastIndexOf("/"), uri.lastIndexOf("."));
		EmployeeDao dao=new EmployeeDao();
		if(uri.equals("/list")){
			List<Employee> emps=new ArrayList<Employee>();
			try {
				emps=dao.findAll();
				out.println("<table>");
				out.println("<tr><td>���</td><td>����</td><td>нˮ</td><td>����</td><td>����</td></tr><br/>");
				for(Employee emp:emps){
					out.println("<tr><td>"+emp.getId()+
							"</td><td>"+emp.getName()+
							"</td><td>"+emp.getSalary()+
							"</td><td>"+emp.getAge()+"</td>" +
									"<td><a href='find.do?id="+emp.getId()+"'>�޸�</a></td>" +
									"<td><a href='del.do?id="+emp.getId()+"' onclick=\"return confirm('ȷ��ɾ��"+emp.getName()+"��?')\">ɾ��</a><td>" +
									"</tr><br/>");
				}
				out.println("</table>");
				out.println("<tr><a href='addEmp.html'>���Ա��</a></tr>");
				} catch (Exception e) {
				e.printStackTrace();
			}
		}else if(uri.equals("/del")){
			int id=Integer.parseInt(request.getParameter("id"));
			try {
				dao.delById(id);
				response.sendRedirect("list.do");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else if(uri.equals("/find")){
			int id=Integer.parseInt(request.getParameter("id"));
			try {
				dao.findById(id);
				response.sendRedirect("list");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else if(uri.equals("/save")){
			Employee emp=new Employee();
			try {
				emp.setId(Integer.parseInt(request.getParameter("id")));
				emp.setName(request.getParameter("name"));
				emp.setSalary(Double.parseDouble(request.getParameter("salary")));
				emp.setAge(Integer.parseInt(request.getParameter("age")));
				dao.save(emp);
				response.sendRedirect("list");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else if(uri.equals("/add")){
			Employee emp=new Employee();
			try {
				emp.setName(request.getParameter("name"));
				emp.setSalary(Double.parseDouble(request.getParameter("salary")));
				emp.setAge(Integer.parseInt(request.getParameter("age")));
				dao.add(emp);
				response.sendRedirect("list");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		out.close();
	}

}
