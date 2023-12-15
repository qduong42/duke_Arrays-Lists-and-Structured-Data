import edu.duke.*;
import java.util.*;

public class GladLibMap {
/* 	private ArrayList<String> adjectiveList;
	private ArrayList<String> nounList;
	private ArrayList<String> colorList;
	private ArrayList<String> countryList;
	private ArrayList<String> nameList;
	private ArrayList<String> animalList;
	private ArrayList<String> timeList;
	private ArrayList<String> verbList;
	private ArrayList<String> fruitList; */
	private ArrayList<String> seenList;
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
/* 		adjectiveList= readIt(source+"/adjective.txt");	
		nounList = readIt(source+"/noun.txt");
		colorList = readIt(source+"/color.txt");
		countryList = readIt(source+"/country.txt");
		nameList = readIt(source+"/name.txt");		
		animalList = readIt(source+"/animal.txt");
		timeList = readIt(source+"/timeframe.txt");	
		verbList = readIt(source + "/verb.txt");
		fruitList = readIt(source + "/fruit.txt"); */
		seenList = new ArrayList<String>();
	}
	
	private String randomFrom(ArrayList<String> source){
		int index = myRandom.nextInt(source.size());
		return source.get(index);
	}
	
	private String getSubstitute(String label) {
		// System.out.println("label " + label);
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
		long start = System.currentTimeMillis();
		long end = 0;
		while (seenList.contains(sub) && end - start < 1000)
		{
			end = System.currentTimeMillis();
			sub = getSubstitute(w.substring(first+1,last));
		}
		seenList.add(sub);
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
		seenList.clear();
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
