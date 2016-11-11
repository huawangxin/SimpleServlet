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
 * ���ڴ���������Ŀ�����
 * ͨ������������Դ·���е���Դ����
 * ʵ�ֶԲ�ͬ����ķַ�
 * @author wangxin
 *
 */
public class ActionServlet extends HttpServlet {
	public void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//��������
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		//��ȡ������Դ·��
		String uri=request.getRequestURI();
		//��ȡ��Դ��
		uri=uri.substring(uri.lastIndexOf("/"),uri.lastIndexOf("."));
		//����daoʵ��
		EmployeeDao empDao=new EmployeeDao();
		//��֧�ж�
		if(uri.equals("/list")){
			try {
				//����dao��findAll()������ȡ����
				List<Employee> emps=empDao.findAll();
				//��ʾ����
				out.println("<table>");
				out.println("<caption>Ա����Ϣ��</caption>");
				out.println("<tr><td>���</td><td>����</td><td>нˮ</td><td>����</td><td>����</td></tr>");
				for(Employee emp:emps){
					out.println("<tr>");
					out.println("<td>"+emp.getId()+"</td>");
					out.println("<td>"+emp.getName()+"</td>");
					out.println("<td>"+emp.getSalary()+"</td>");
					out.println("<td>"+emp.getAge()+"</td>");
					out.println("<td><a href='find.do?id="+emp.getId()+"'>�޸�</td>");
					//out.println("<td><a href='del.do?id="+emp.getId()+"'>ɾ��</td>");
					out.println("<td><a href='del.do?id="+emp.getId()+"' onclick=\"return confirm('ȷ��ɾ��"+
							emp.getName()+"��?')\">ɾ��</a></td>");
					out.println("</tr>");
				}
				out.println("</table>");
				out.println("<a href='addEmp.html'>�����Ա��</a>");
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
