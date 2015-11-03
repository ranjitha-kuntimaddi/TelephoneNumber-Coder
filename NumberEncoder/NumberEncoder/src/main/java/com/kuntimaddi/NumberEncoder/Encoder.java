package com.kuntimaddi.NumberEncoder;


import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

/**
 * The main Class of Number Encoder program. It creates a Hash mapping of data from dictionary file
 * using Hash Mapper class and DatatoNumberConverter class. The data is read from the file and converted
 * into its corresponding number and then stored as Hashmaps.
 *
 * It accepts an input file of numbers and finds the corresponding possible strings with given limitations of
 * having a single number at the ends or in the middle of the solution and printed.
 */
public class Encoder {
    /**
     * Main method initialising the dictionary file and input file, converting the dictionary dat into HashMaps.
     * It searches the number from the input file for corresponding solutions line by line and prints them.
     *
     * @param args
     * @throws Throwable
     */
    public static void main(String[] args) throws Throwable {


            long startTime = System.currentTimeMillis();
            Dictionary dict = new Dictionary("\\resources\\dictionary.txt");
            HashMapper map = new HashMapper();
            DatatoNumberConverter convertor;
            dict.readFile();
            FileInputStream inputFile = new FileInputStream(System.getProperty("user.dir")+"\\resources\\input.txt");
            Scanner scanner = new Scanner(inputFile);
            String phNumber;
            String no;
            while (scanner.hasNextLine()) {
                no = scanner.nextLine();
                phNumber = no;
                no = no.replaceAll("-", "");
                no = no.replaceAll("/", "");

                for (String word : dict.getData()) {
                    convertor = new DatatoNumberConverter(word);
                    map.addWord(convertor.convert(), word);
                }


                ArrayList<String> allSolutions = map.findSolution(no);
                Set<String> solution = new TreeSet<String>();

                for (int i = 0; i < allSolutions.size(); ++i) {
                    String str = allSolutions.get(i);
                    if (str.length() >= no.length()) {
                        solution.add(str);
                    }
                }

                if (solution.size() > 0) {
                    for (String str : solution) {
                        System.out.println(phNumber + ": " + str);
                    }
                }
            }
            scanner.close();

    }

}
