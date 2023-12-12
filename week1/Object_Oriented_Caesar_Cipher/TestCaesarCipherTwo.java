import edu.duke.*;

public class TestCaesarCipherTwo{
	private static String halfOfString(String message, int start){
		StringBuilder sb = new StringBuilder();
		for (int i = start; i < message.length(); i += 2) {
			sb.append(message.charAt(i));
		}
		return (sb.toString());
	}
	private static int getKey(String s){
		int [] freqs = countLetters(s);
		int maxDex = maxIndex(freqs);
		int dkey = maxDex - 4;
		if(maxDex < 4){
			dkey = 26 - (4 - maxDex);
		}
		return dkey;
	}
	private static int [] countLetters(String message){
		String alph = "abcdefghijklmnopqrstuvwxyz";
		int [] counts = new int[26];
		for (int i = 0; i < message.length(); i++) {
			char ch = Character.toLowerCase(message.charAt(i));
			int dex = alph.indexOf(ch);
				if (dex != -1)
					counts[dex] ++;
		}
		return counts;
	}
	public static int maxIndex(int [] vals){
		int maxDex = 0;
		for (int i = 0; i < vals.length; i++) {
			if(vals[i] > vals[maxDex]){
				maxDex = i;
			}
		}
		return maxDex;
	}
	private static String breakCaesarCipher(String input){
		String odd = halfOfString(input, 0);
		String even = halfOfString(input, 1);
		int key1 = getKey(odd);
		int key2 = getKey(even);
		System.out.println("Key 1: " + key1);
		System.out.println("Key 2: " + key2);
		CaesarCipherTwo cct = new CaesarCipherTwo(key1, key2);
		String decrypted = cct.decryptTwoKeys(input);
		return decrypted;
	}
	private static void simpleTests(){
		FileResource fr = new FileResource();
		String message = fr.asString();
		CaesarCipherTwo cct = new CaesarCipherTwo(15, 5);
		String encrypted = cct.encryptTwoKeys(message);
		//System.out.println("Encrypted string: " + encrypted);
		String decrypted = breakCaesarCipher(encrypted);
		System.out.println("Decrypted string: " + decrypted);
	}
	public static void main(String[] args){
		simpleTests();
	}
}