package entity;
/*
 * create table t_emp_wangxin(
	id number(4) primary key,
	name varchar2(20),
	salary number(7,2),
	age number(3)
)
实体类：用于描述t_emp表
 */
public class Employee {
	private int id;
	private String name;
	private double salary;
	private int age;
	public String toString() {
		return this.id+" "+this.name+" "+this.salary+" "+this.age;
	}
	public Employee() {
		super();
	}
	public Employee(int id, String name, double salary, int age) {
		super();
		this.id = id;
		this.name = name;
		this.salary = salary;
		this.age = age;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getSalary() {
		return salary;
	}
	public void setSalary(double salary) {
		this.salary = salary;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	
}
