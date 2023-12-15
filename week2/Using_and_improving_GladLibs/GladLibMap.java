import edu.duke.*;
import java.util.*;

public class GladLibMap {
	private HashMap <String, ArrayList <String>> myMap;
	
	private Random myRandom;
	
	private static String dataSourceURL = "http://dukelearntoprogram.com/course3/data";
	private static String dataSourceDirectory = "data";
	
	public GladLibMap(){
		initializeFromSource(dataSourceDirectory);
		myRandom = new Random();
	}
	
	public GladLibMap(String source){
		initializeFromSource(source);
		myRandom = new Random();
	}
	
	private void initializeFromSource(String source) {
		myMap = new HashMap<String, ArrayList<String>>();
		String [] categories = {"adjective", "animal", "color", "country", 
		"fruit", "name", "noun", "verb", "timeframe"};
		for (int index = 0; index < categories.length; index++) {
			ArrayList<String> al;
			al = readIt(source + "/" + categories[index] + ".txt");
			myMap.put(categories[index], al);
		}
	}
	
	private String randomFrom(ArrayList<String> source){
		String answer = "";
		if (source.size() == 0)
			return "";
		int index = myRandom.nextInt(source.size());
		answer = source.get(index);
		source.remove(index);
		return answer;
	}
	
	private String getSubstitute(String label) {
		if (label.equals("number"))
			return ""+myRandom.nextInt(50)+5;
		if(myMap.containsKey(label) == false)
			return "**UNKNOWN**";
		return(randomFrom(myMap.get(label)));
	}
	
	private String processWord(String w){
		int first = w.indexOf("<");
		int last = w.indexOf(">",first);
		if (first == -1 || last == -1){
			return w;
		}
		String prefix = w.substring(0,first);
		String suffix = w.substring(last+1);
		String sub = getSubstitute(w.substring(first+1,last));
		if (sub == "")
			return w;
		return prefix+sub+suffix;
	}
	
	private void printOut(String s, int lineWidth){
		int charsWritten = 0;
		for(String w : s.split("\\s+")){
			if (charsWritten + w.length() > lineWidth){
				System.out.println();
				charsWritten = 0;
			}
			System.out.print(w+" ");
			charsWritten += w.length() + 1;
		}
	}
	
	private String fromTemplate(String source){
		String story = "";
		if (source.startsWith("http")) {
			URLResource resource = new URLResource(source);
			for(String word : resource.words()){
				story = story + processWord(word) + " ";
			}
		}
		else {
			FileResource resource = new FileResource(source);
			for(String word : resource.words()){
				story = story + processWord(word) + " ";
			}
		}
		return story;
	}
	private String fromTemplate(){
		String story = "";
			FileResource resource = new FileResource();
			for(String word : resource.words()){
				story = story + processWord(word) + " ";
			}
		return story;
	}
	
	private ArrayList<String> readIt(String source){
		ArrayList<String> list = new ArrayList<String>();
		if (source.startsWith("http")) {
			URLResource resource = new URLResource(source);
			for(String line : resource.lines()){
				list.add(line);
			}
		}
		else {
			FileResource resource = new FileResource(source);
			for(String line : resource.lines()){
				list.add(line);
			}
		}
		return list;
	}
	private void makeStory(){
	    System.out.println("\n");
		String story = fromTemplate();
		// String story = fromTemplate("data/madtemplate2.txt");
		printOut(story, 60);
		System.out.print("\n");
	}
	
	public static void main(String[] args) {
		GladLibMap gl = new GladLibMap();
		gl.makeStory();
		
	}

}
