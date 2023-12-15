import java.util.*;

import edu.duke.DirectoryResource;
import edu.duke.FileResource;

import java.io.File;
import java.io.IOException;
public class WordsInFiles{
	private HashMap<String, ArrayList<String>> wordMap;
	public WordsInFiles(){
		wordMap = new HashMap<String, ArrayList<String>>();
	}
	private void addWordsFromFile(File f) throws IOException{
		FileResource fr = new FileResource(f.getCanonicalPath());
		for (String word : fr.words()) {
			if (wordMap.get(word) == null){
				{
					ArrayList <String> al = new ArrayList <String> ();
					al.add(f.getName());
					wordMap.put(word, al);
				}
			}
			else{
				ArrayList <String> existingAl = wordMap.get(word);
				existingAl.add(f.getName());
			}
		}
	}
	private void buildWordFileMap() throws IOException{
		wordMap.clear();
		DirectoryResource dr = new DirectoryResource();
		for(File f : dr.selectedFiles())
		{
			addWordsFromFile(f);
		}
	}
	private int maxNumber(){
		int max = 0;
		for (String word:wordMap.keySet()) {
			if (wordMap.get(word).size() > max)
				max = wordMap.get(word).size();
		}
		return max;
	}
	private ArrayList<String> wordsInNumFiles(int number){
		ArrayList<String> wordsInXFiles  = new ArrayList<>() ;
		for (String word : wordMap.keySet()) {
			if (wordMap.get(word).size() == number)
				wordsInXFiles = wordMap.get(word);
		}
		return wordsInXFiles;
	}
	private void printFilesln(String word){
		for (String toSearch : wordMap.keySet()) {
			if (toSearch == word){
				ArrayList<String> al = wordMap.get(word);
				for (int counter = 0; counter < al.size(); counter++) { 		      
					System.out.println(al.get(counter));		
				}
			}
		}
	}
	public void tester() throws IOException{
		buildWordFileMap();
		int max = maxNumber();
		System.out.println("Max number of files any word is in: " + max);
		ArrayList<String> numOccurences = wordsInNumFiles(500);
		System.out.println("String occuring 2 times in files: ");
		for(int i = 0; i < numOccurences.size();i++)
		{
			System.out.println(numOccurences.get(i));
		}
		printFilesln("the");
		System.out.println("Complete map:");
		for (String str : wordMap.keySet()) {
			System.out.print("Key: " + str);
			for (int index = 0; index < wordMap.get(str).size(); index++) {
				System.out.print(" value: " + wordMap.get(str).get(index));
			}
			System.out.print("\n");
		}
	}
	public static void main(String[] args) {
		WordsInFiles wif = new WordsInFiles();
		try {
			wif.tester();
		} catch (IOException e) {
			// Auto-generated catch block
			e.printStackTrace();
		}
	}
}