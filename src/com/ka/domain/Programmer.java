package com.ka.domain;

import java.util.HashSet;
import java.util.Set;

public class Programmer {
	private int pid;
	private String pname;
	private double salary;
	private Set<Project> projects=new HashSet<Project>();
	
	public Programmer() {
		System.out.println("Programmer:0-Param consturctor");
	}

	public int getPid() {
		return pid;
	}

	public void setPid(int pid) {
		this.pid = pid;
	}

	public String getPname() {
		return pname;
	}

	public void setPname(String pname) {
		this.pname = pname;
	}

	public double getSalary() {
		return salary;
	}

	public void setSalary(double salary) {
		this.salary = salary;
	}

	public Set<Project> getProjects() {
		return projects;
	}

	public void setProjects(Set<Project> projects) {
		this.projects = projects;
	}

	@Override
	public String toString() {
		return "Programmer [pid=" + pid + ", pname=" + pname + ", salary=" + salary + "]";
	}
	
	
}
