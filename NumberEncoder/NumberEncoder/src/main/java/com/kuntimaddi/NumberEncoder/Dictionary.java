package com.kuntimaddi.NumberEncoder;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Dictionary class to read all the data from the dictionary text file and store in an Array List
 */
public class Dictionary {

	private String fileName;
	private ArrayList<String> data;

	/**
	 * Constructor to initialise the name of the source file.
	 * @param file
	 */
	public Dictionary(String file)
	{

		//fileName = file;
		fileName = System.getProperty("user.dir")  + file;
	}

	/**
	 * Method to read the file line by line and store in the list
	 * @throws Throwable
	 */
	public void readFile() throws Throwable
	{
		Scanner in = new Scanner(new File(fileName));
		data = new ArrayList<String>();
		while(in.hasNext())
		{
			data.add(in.next());			
		}
		in.close();		
	}

	/**
	 *  Method to get the extracted data
	 * @return Returns all the list data from the file
	 */
	public ArrayList<String> getData()
	{
		return data;
	}
	

	
}
