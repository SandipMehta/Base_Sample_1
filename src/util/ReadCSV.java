package util;

import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.io.FileNotFoundException;

import com.opencsv.CSVReader;

public class ReadCSV {
	
	public void readCSVFile(String path) throws FileNotFoundException {
		
		//CSVReader reader = new CSVReader(new FileReader(dataPath + "data.csv"));
		CSVReader reader = new CSVReader(new FileReader(path));
     
	    //Read CSV line by line and use the string array as you want
	    String[] nextLine;
	    try {
			while ((nextLine = reader.readNext()) != null) {
				if (nextLine != null) {
					//Verifying the read data here
					System.out.println(Arrays.toString(nextLine));
			    }
			 }
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    try {
			reader.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
