package com.ka.dao;

public interface M2MDAO {
	public void saveDataUsingProgrammer();
	public void saveDataUsingProject();
	public void listDataUsingProgrammer();
	public void listDataUsingProject();
	public void addNewProgrammerToExistingProject();
	public void addNewExistingProjectToExistingProgrammer();
	public void deleteProgrammerFromExistingProject();
	public void deleteAllProgrammersOfExistingProject();
	public void resignProgrammerToCompany();
}
