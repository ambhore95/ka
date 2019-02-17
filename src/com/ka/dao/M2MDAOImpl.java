package com.ka.dao;

import java.util.List;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.ka.domain.Programmer;
import com.ka.domain.Project;
import com.ka.utility.HibernateUtil;

public class M2MDAOImpl implements M2MDAO {

	@Override
	public void saveDataUsingProgrammer() {
		// Get Session
		Session ses=HibernateUtil.getSession();
		//Create Parent and child obj
		Programmer prgmr1=new Programmer();
		prgmr1.setPid(1);
		prgmr1.setPname("johan");
		prgmr1.setSalary(9000);
		
		Programmer prgmr2=new Programmer();
		prgmr2.setPid(2);
		prgmr2.setPname("root");
		prgmr2.setSalary(8000);
		
		Project proj1=new Project();
		proj1.setProid(1001);
		proj1.setProname("proj1");
		
		Project proj2=new Project();
		proj2.setProid(1002);
		proj2.setProname("proj2");
		
		//set Project to Programmer (childs to Parent)
		prgmr1.getProjects().add(proj1);
		prgmr1.getProjects().add(proj2);
		
		prgmr1.getProjects().add(proj2);
		prgmr2.getProjects().add(proj2);
		
		//set Programmers to Projects (Parents to childs)
		proj1.getProgrammers().add(prgmr1);
		proj1.getProgrammers().add(prgmr2);
		
		//save objs (parent to child)
		Transaction tx=null;
		try
		{
			tx=ses.beginTransaction();
			ses.save(prgmr1);
			ses.save(prgmr2);
			tx.commit();
			System.out.println("Objects are saved");
		}//try
		catch(Exception e) {
			tx.rollback();
		}
	}

	@Override
	public void saveDataUsingProject() {
		// Get Session
		Session ses=HibernateUtil.getSession();
		//Create Parent anc child obj
		Programmer prgmr3=new Programmer();
		prgmr3.setPid(3);
		prgmr3.setPname("whatson");
		prgmr3.setSalary(9000);
		
		Project proj3=new Project();
		proj3.setProid(1003);
		proj3.setProname("proj3");
		
		//set Project to Programmer (child to parent)
		prgmr3.getProjects().add(proj3);
		
		//set Programmer to Project (parent to child)
		proj3.getProgrammers().add(prgmr3);
		
		//save objs (child to parent)
		Transaction tx=null;
		try {
			tx=ses.beginTransaction();
			ses.save(proj3);
			tx.commit();
			System.out.println("Objects are saved");
		}//try
		catch(Exception e) {
			tx.rollback();
		}
	}

	
	@Override
	public void listDataUsingProgrammer() {
		// Get Session
		Session ses=HibernateUtil.getSession();
		Query query=ses.createQuery("from Programmer");
		List<Programmer> list=query.list();
		for(Programmer prgmr:list) {
			System.out.println("parent-----> "+prgmr);
			Set<Project> childs=prgmr.getProjects();
			for(Project proj:childs) {
				System.out.println("Child----> "+proj);
			}//for
		}//for
	}//method

	@Override
	public void listDataUsingProject() {
		// Get Session 
		Session ses=HibernateUtil.getSession();
		Query query=ses.createQuery("from Project");
		List<Project> list=query.list();
		for(Project proj:list) {
			System.out.println("child-----> "+proj);
			Set<Programmer> childs=proj.getProgrammers();
			for(Programmer prgmr:childs) {
				System.out.println("parent-----> "+prgmr);
			}
		}
		
	}

	@Override
	public void addNewProgrammerToExistingProject() {
		// Get Session 
		Session ses=HibernateUtil.getSession();
		//get Existing project object
		Project proj=(Project)ses.get(Project.class, 1003);
		//Create new Programmer
		Programmer prgmr=new Programmer();
		prgmr.setPid(123);
		prgmr.setPname("devid");
		prgmr.setSalary(9000);
		//Add Programmer to project
		Transaction tx=null;
		try {
			tx=ses.beginTransaction();
			proj.getProgrammers().add(prgmr);
			tx.commit();
			System.out.println("New Programmer added");
		}//try
		catch(Exception e) {
			tx.rollback();
		}
		
		
	}

	@Override
	public void addNewExistingProjectToExistingProgrammer() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteProgrammerFromExistingProject() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteAllProgrammersOfExistingProject() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resignProgrammerToCompany() {
		// Get Session 
		Session ses=HibernateUtil.getSession();
		Query query=ses.createQuery("delete from Programmer where pid:no");
		query.setInteger("no", 1);
		//delete programmer
		Transaction tx=null;
				try {
					tx=ses.beginTransaction();
					int result=query.executeUpdate();
					tx.commit();
					if(result==0)
						System.out.println("Programmer not delete");
					else
						System.out.println("Programmer deleted");
				}
		catch(Exception e) {
			tx.commit();
		}
		
	}

}
