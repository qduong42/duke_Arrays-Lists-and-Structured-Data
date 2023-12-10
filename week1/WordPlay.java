public class WordPlay{
	private static boolean isVowel(char ch){
		char c = Character.toLowerCase(ch);
		if (c == 'a' || c =='e' || c == 'i' || c == 'o' || c == 'u' )
			return true;
		else
			return false;
}
	private static String replaceVowels(String phrase, char ch){
		StringBuilder sb = new StringBuilder(phrase);
		for (int i = 0; i < sb.length(); i++) {
			if (isVowel(phrase.charAt(i)))
				sb.setCharAt(i, ch);
		}
		return sb.toString();
	}
	public static void main(String[]args){
		//test 1
		//boolean bisVowel = isVowel('a');
		// System.out.println(bisVowel);
		//test2
		String phrase = "Hello World";
		String result = replaceVowels(phrase, '*');
		System.out.println("result: " + result);
	}
}