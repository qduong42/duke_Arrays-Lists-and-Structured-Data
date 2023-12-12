import edu.duke.FileResource;

public class TestCaesarCipher{
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
		int [] freqs = countLetters(input);
		//System.out.println("freqs: " + Arrays.toString(freqs));
		int maxDex = maxIndex(freqs);
		int dkey = maxDex - 4;
		if(maxDex < 4){
			dkey = 26 - (4 - maxDex);
		}
		CaesarCipher cc = new CaesarCipher(dkey);
		return (cc.decrypt(input));
	}
	private static void simpleTests(){
		FileResource fr = new FileResource();
		String str = fr.asString();
		CaesarCipher cc = new CaesarCipher(18);
		String encrypted = cc.encrypt(str);
		System.out.println("Encrypted String: " + encrypted);
		System.out.println("Decrypted String:" + cc.decrypt(encrypted));
		System.out.println("Automatically Decrypted String: " + breakCaesarCipher(encrypted));
	}
	public static void main(String[] args){
		simpleTests();
	}
}