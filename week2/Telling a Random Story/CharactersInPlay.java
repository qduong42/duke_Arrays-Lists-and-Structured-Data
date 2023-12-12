import java.util.ArrayList;

import edu.duke.*;

public class CharactersInPlay{
	private ArrayList<String> characterNames;
	private ArrayList<Integer> characterCounts;
	public CharactersInPlay(){
		characterNames = new ArrayList<String>();
		characterCounts = new ArrayList<Integer>();
	}
	public void update(String person){
			boolean containsPerson = characterNames.contains(person);
			if (containsPerson == false){
				characterNames.add(person);
				characterCounts.add(1);
			}
			else{
				int index = characterNames.indexOf(person);
				int count = characterCounts.get(index);
				characterCounts.set(index, count + 1);
			}
	}
	public void findAllCharacters(){
		FileResource fr = new FileResource();
		for (String line : fr.lines()) {
			int endIndex = line.indexOf('.');
			int startIndex = 0;
			if (endIndex != -1)
			{
				for (int i = 0; i < line.length(); i++) {
					if(Character.isLetter(line.charAt(i)))
					{
						startIndex = i;
						break;
					}	
				}
				System.out.println("Person:" + line.substring(startIndex, endIndex));
				update(line.substring(startIndex, endIndex));
			}
		}
	}
	public void characterWithNumParts(int num1, int num2){
		for (int i = 0; i < characterCounts.size(); i++) {
			if (characterCounts.get(i) >= num1 && characterCounts.get(i) <= num2){
				System.out.println("Main character: " + 
				characterNames.get(i) + " no of speaking parts: " + characterCounts.get(i));
			}
		}
	}
	public void tester(){
		findAllCharacters();
		//System.out.println("characters name size: " + characterNames.size());
		//System.out.println("characters count size: " + characterCounts.size());
		// for (int i = 0; i < characterCounts.size(); i++) {
		// 	if (characterCounts.get(i) > 50){
		// 		System.out.println("Main character: " + 
		// 		characterNames.get(i) + " no of speaking parts: " + characterCounts.get(i));
		// 	}
		// }
		characterWithNumParts(20, 40);
	}
	public static void main(String[] args) {
		CharactersInPlay cp = new CharactersInPlay();
		cp.tester();
	}
}