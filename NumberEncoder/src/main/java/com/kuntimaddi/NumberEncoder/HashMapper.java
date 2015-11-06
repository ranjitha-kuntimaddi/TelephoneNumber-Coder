package com.kuntimaddi.NumberEncoder;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Set;

/**
 * Class to hold the Hash Mapped dictionary data and search the strings from the data.
 * All the dictionary data is stored in form of collided buckets in an ArrayList of strings
 * with its corresponding number acting as a key to the list to reduce the search time.
 */
public class HashMapper 
{
	
	private HashMap<String,ArrayList<String>> container;
	private static final int MAX_SIZE =75000;

	/**
	 * Initialisation of the Hash Map to hold maximum of 75000 bucket lists.
	 */
	public HashMapper()
	{
		container = new HashMap<String,ArrayList<String>>(MAX_SIZE);
	}

	/**
	 * Method to add dictionary data into the map with their corresponding number formed using
     * DatatoNumberConverter methods
	 * @param number The key element of the data.
	 * @param word The word to be assigned as Array list element.
	 * @return
	 */
	public boolean addWord(String number,String word)
	{				
		if(!container.containsKey(number))
		{
			ArrayList<String> wordList = new ArrayList<String>();
			wordList.add(word);
			container.put(number,wordList);
			return true;
		}else{			
			ArrayList<String> wordList = container.get(number);
			if(wordList.contains(word))
			{
				return false;
			}
			wordList.add(word);
			container.put(number,wordList);
			return true;
		}
		
	}

    /**
     * Recursive Method to search the passed number through the Mapped data.
     * The number is first searched to find the exact match in the Mapped data if
     * the word is not found then the a sub string either starting the number or a sub string
     * starting with a single digit is found. Then the rest of the string is passed as a parameter
     * into the recursive function to find all the matching solutions.
     * returned solutions are then appended the previously computed sub string and the Array List of
     * possible solutions for the given number are returned.
     * @param number Number to which the corresponding words have to be found from the dictionary
     * @return returns the Array List of all possible words searched form the dictionary
     */

    public ArrayList<String> findSolution(String number)
    {
        ArrayList<String> solution = new ArrayList<String>();
        Set<String> keys = container.keySet();

        if((number.length()==1))
        {
            solution.add(number);
            return solution;
        }

        boolean flag=false;
        for(String str:keys)
        {
            if(number.startsWith(str))
            {
                flag=true;
            }
        }
        for(String key:keys)
        {
            ;
            int pos = number.indexOf(key);
            if(number.equals(key))
            {
                ArrayList<String> wordList = container.get(number);
                for(String str:wordList)
                {
                    solution.add(str);
                }
            }else if(number.contains(key))
            {
                if((pos==0))
                {

                    ArrayList<String> wordList = container.get(key);
                    Collections.sort(wordList);
                    ArrayList<String> innerSolution = findSolution(number.substring(key.length()));
                  for(String word: wordList)
                    {
                        for(String str:innerSolution)
                        {
                            solution.add(word + " " + str);
                        }
                    }

                }else if((pos==1)&&(!flag))
                {
                    ArrayList<String> innerSolution = findSolution(number.substring(1));
                    for(String str:innerSolution)
                    {
                        solution.add(number.charAt(0) + " " + str);
                    }
                }
            }
        }

        return solution;
    }
}



