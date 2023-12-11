import java.util.Arrays;

import edu.duke.*;

public class WordLengths{
	private static void countWordLengths(FileResource resource, int [] counts){
		int i = 0;
		int count = 0;
		for (String word : resource.words()) {
			count = word.length();
			if (!Character.isLetter(word.charAt(0)))
				count--;
			if (!Character.isLetter(word.charAt(word.length() - 1)))
				count--;
			counts[i] = count;
			i++;
		}
	}
	private static int indexOfMax(int [] values){
		int max = 0;
		int iIndexOfMax = 0;
		for (int i = 0; i < values.length; i++) {
			if (values[i] > max)
			{
				max = values[i];
				iIndexOfMax = i;
			}
		}
		System.out.println("max: " + max);
		return iIndexOfMax;
	}
	private static void testCountWordLengths()
	{
		int i = 0;
		FileResource fr = new FileResource();
		for (String y : fr.words()){
			i++;
		}
		System.out.println(i);
		int [] arr = new int [i];
		countWordLengths(fr, arr);
		System.out.println(Arrays.toString(arr));
		System.out.println("Index of Max: " + indexOfMax(arr));
	}
	public static void main(String[] args){
		testCountWordLengths();
	}
}