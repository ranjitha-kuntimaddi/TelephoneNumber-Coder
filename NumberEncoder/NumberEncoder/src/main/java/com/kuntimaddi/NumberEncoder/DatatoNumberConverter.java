package com.kuntimaddi.NumberEncoder;

/**
 * Class to compute and convert a String into corresponding integer number through a mapped string array.
 */
public class DatatoNumberConverter 
{
	public String[] map = new String[]{"e","jnq","rwx", "dsy","ft", "am", "civ", "bku", "lop", "ghz"};
	
	private String number;
	private String word;

	/**
	 * Initializes the given input string with a lower case form
	 * @param str The input String to be converted into an corresponding number string
	 */
	public DatatoNumberConverter(String str)
	{
		this.word = str.toLowerCase();
		number="";
	}

	/**
	 * Converts the string into  the corresponding number string
	 * @return Converted number string
	 */
	public String convert()
	{
		for(char ch:word.toCharArray())
		{
			for(int i=0;i<10;++i)
			{
				String str = map[i];
				int position = str.indexOf(ch);
				if((position<10)&&(position>=0))
				{
					number = number + i;
				}
			}			
		}
		return number;
	}
	
}
