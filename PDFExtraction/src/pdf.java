
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStreamWriter;

import java.io.Writer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map.Entry;
import java.util.HashMap;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.apache.pdfbox.text.TextPosition;

import io.github.jonathanlink.PDFLayoutTextStripper;

import org.apache.pdfbox.pdfparser.PDFParser;
import org.apache.pdfbox.io.RandomAccessFile;


public class pdf {
	
	public static void main(String[] args) throws Exception
	{
		String str= null;
		
		try {
			PDFParser pdfParser = new PDFParser(new RandomAccessFile(new File("C:/Users/Nbons/LifeandMin2.pdf"), "r"));
			pdfParser.parse();
			
			PDDocument pdDocument = new PDDocument(pdfParser.getDocument());
			PDFTextStripper pdfStrip = new PDFLayoutTextStripper();
			str = pdfStrip.getText(pdDocument);
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}catch (IOException e) {
			e.printStackTrace();
		};
		
		//System.out.println(str);
		//this array holds each line as a string
		String[] lines = str.split("\r\n|\r|\n");
		
	
		
		
		HashMap<String,String[]>AssignmentMap = new HashMap<>();
		ArrayList<String> dates = new ArrayList<String>();
		String currentDate = "";
		String copy = "";
	
		int j = 0;
		for(String temp:lines) {
			if(temp.contains("|")) {
				int index = temp.indexOf("|");
				int count = 0;
				while(count < index) {
					if(temp.charAt(count) != ' ') {
						currentDate += temp.charAt(count);
						//
								
					}
					//Add spacein between for the date number
					if((Character.isDigit(temp.charAt(count)) == false) && Character.isDigit(temp.charAt(count+1)) == true){
						currentDate += ' ';
						
						// can i set temp to be equal to this current date, so that line jsut equals that date??
						
						//want to basically 
					}
									
					count++;
				}
				lines[j] = currentDate;
				copy = currentDate;
				dates.add(copy);			
				copy = "";
				currentDate = "";
				
			}
			j++;
		}
		
		String newstr = "";
		
		for(String curr:lines) {
			newstr += curr + "\n";
		}
		
		
		//System.out.println(newstr);
		
		
		
		
		
		//Will save this is the hashmap later
		
		for(int i = 0; i <= dates.size() - 1; i++) {
			
			String currentString = dates.get(i);
			int lastindexOfWord = newstr.indexOf(currentString) + (currentString.length()- 1);
			
			//Basically if you are at the last date just get the rest

			if(i == dates.size() -1) {
				String WeekAssignments = newstr.substring(lastindexOfWord + 1, newstr.length()-1);
				String[] lineAssignments = WeekAssignments.split("\r\n|\r|\n");
				AssignmentMap.put(currentString, lineAssignments);
				
			} else {
			
			String WeekAssignments = newstr.substring(lastindexOfWord + 1, newstr.indexOf(dates.get(i + 1)));
			
			String[] lineAssignments = WeekAssignments.split("\r\n|\r|\n");
			
			AssignmentMap.put(currentString, lineAssignments);
			
			}
			
			
		}
		
		String nameString = "Edward";
		String assignmentOutput = "";
		//first look through all arrays in 
		
		
		for(Entry<String, String[]> entry : AssignmentMap.entrySet()) {
			for(String temp:entry.getValue()) {
				if(temp.contains(nameString)) {
					assignmentOutput += " " + " " +  entry.getKey() + temp + "\n";
				}
		
			}	
	}
	
		System.out.println(assignmentOutput);
	
	}
	
	
}

