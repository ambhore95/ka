package com.ka.test;

import com.ka.dao.M2MDAO;
import com.ka.dao.M2MDAOFactory;
import com.ka.utility.HibernateUtil;

public class ClientApp {

	public static void main(String[] args) {
		// get Dao
		M2MDAO dao=M2MDAOFactory.getInstance();
		//call method
		dao.saveDataUsingProgrammer();
		//dao.saveDataUsingProject();
		//dao.listDataUsingProgrammer();
		//dao.listDataUsingProject();
		//dao.addNewProgrammerToExistingProject();
		//dao.resignProgrammerToCompany();
		
		//close Session, SessionFactory
		HibernateUtil.closeSession();
		HibernateUtil.getSession();

	}

}
