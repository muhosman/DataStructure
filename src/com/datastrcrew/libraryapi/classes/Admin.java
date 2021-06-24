package com.datastrcrew.libraryapi.classes;
import java.util.ArrayList;
import java.util.List;

import com.datastrcrew.libraryapi.entity.AdminEntity;
import com.datastrcrew.libraryapi.entity.LibraryEntity;
import com.datastrcrew.libraryapi.service.Database;

/**
 * Administrator of the libraries.
 * @author Mustafa Gurler
 * */
public class Admin extends User {
	//Database gelince = Database.getLibraries() olacak.
    private static final List<Library> libraries = new ArrayList<>();
    
    /**
     * Default constructor of Admin
     */
    /* It will add when we redesign this class.
    public Admin(){super();}
    */
    /**
    * Constructor of Admin.
    */
    public Admin()
    {
        super("name","surname","pw");
    }

    public Admin(String name,String surname,String pw)
    {
        super(name,surname,pw);
    }
    /**
     * To add library in Data
     * @param Library New Library to add
     * @return true/false If Library adds to Libraries it will return true otherwise false.
     */
    public boolean addLibrary(Library Library)
    {
        return libraries.add(Library);
    }
    /**
     * To remove library from Data
     * @param ID Librarys' id
     * @return true/false If Library id is true and removes it will return true otherwise false.
     */
    public boolean removeLibrary(String ID)
    {
    	for(int i=0 ; i < libraries.size(); i++)
    	{
    		if(libraries.get(i).getID().equals(ID))
    		{
    			libraries.remove(i);
    			return true;
    		}
    	}
    	return false;
    }
    /**
     * To add manager in Data
     * @param Manager New Manager to add
     * @return true/false If Manager adds to Managers it will return true otherwise false.
     */
    public boolean addManager(Manager Manager,String LibraryID)
    {
    	Library result = searchLibrary(LibraryID);
    	if( result != null)
    	{
    		if (result.getManager() != null) {
				return false;
			}
			else
			{
				result.setManager(Manager.getID());
			}
    	}
    	return false;
    }
    /**
     * To remove manager in Data
     * @param ID Managers' id
     * @return true/false If Manager id is true and removes it will return true otherwise false
     */
    public boolean removeManager(String LibraryID)
    {
    	Library result = searchLibrary(LibraryID);
    	if( result != null)
    	{
    		if (result.getManager() != null) 
    		{
				return false;
			}
			else
			{
				result.setManager(null);
			}
    	}
    	return false;    	
    }
    /**
     * To search library information in Data
     * @param searchText Text to search
     * @return true/false If Library name or address contains given text.
     */
    public Library searchLibrary(String searchText)
    {
		for (Library library : libraries) {
			if (library.getName().contains(searchText) || library.getAddress().contains(searchText)) {
				return library;
			}
		}
    	return null;
    }

    public Library getLibrary(String id){
		for (Library library : libraries) {
			if (library.getID().equals(id)) {
				return library;
			}
		}
		return null;
	}
    /**
     * To search manager information in Data
     * @param ID Managers' id
     * @return true/false If Manager id finds it will return true otherwise false.
     */
    public Manager getManager(String ID)
    {
        for (Manager manager : Database.managers) {
            if (manager.getID().equals(ID)) {
                return manager;
            }
        }
    	return null;
    }

    /**
     * To search manager information in Data
     * @param searchText Managers' name
     * @return true/false If Manager id finds it will return true otherwise false.
     */
    public Manager searchManager(String searchText){
        for (Manager manager : Database.managers) {
            if (manager.getName().contains(searchText) || manager.getSurname().contains(searchText)) {
                return manager;
            }
        }
		return null;
	}

    /**
     * Method to return list of libraries.
     * @return list of Libraries.
     */
    public static List<Library> getLibraries(){
    	return libraries;
	}

        //-----------------------------------------ENTITY METHODS--------------------------

    /**
     * Admin constructor for Database operations.
     * @param entity AdminEntity class object.
     *
     */
    public Admin(AdminEntity entity){
        super(entity.name, entity.surname, entity.password);

        for (LibraryEntity lib : entity.getLibraries())           
           Admin.libraries.add(new Library(lib));   
    }

    /**
     * Method to save Admin data field to AdminEntity object.
     * @return AdminEntity object.
     */
    public AdminEntity getEntity(){
        AdminEntity entity = new AdminEntity(name, surname, password);

        for (Library lib : libraries)           
           entity.libraries.add(lib.getEntity());        
        
        return entity;
    }


}
