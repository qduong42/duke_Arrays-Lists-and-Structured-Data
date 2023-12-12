import edu.duke.*;

public class CaesarCipherTwo{
	private String alphabet;
	private String shiftedAlphabet1;
	private String shiftedAlphabet2;
	private int main_key1;
	private int main_key2;
	public CaesarCipherTwo(int key1, int key2){
		if (key1 > 25)
			key1 = key1 % 26;
		if (key2 > 26)
			key2 = key2 % 26;
		main_key1 = key1;
		main_key2 = key2;
		alphabet = "abcdefghijklmnopqrstuvwxyz";
		shiftedAlphabet1 = alphabet.substring(main_key1) + alphabet.substring(0, main_key1);
		shiftedAlphabet2 = alphabet.substring(main_key2) + alphabet.substring(0, main_key2);
	}
	public String encryptTwoKeys(String input){
		String lowerInput = input.toLowerCase();
		StringBuilder outputsb = new StringBuilder();
		for (int i = 0; i < input.length(); i++) {
			if(!Character.isLetter(input.charAt(i)))
				outputsb.append(input.charAt(i));
			if (i % 2 == 0){
				for (int j = 0; j < alphabet.length(); j++) {
					if (lowerInput.charAt(i) == alphabet.charAt(j)){
						if(input.charAt(i) == Character.toUpperCase(alphabet.charAt(j)))
							outputsb.append(Character.toUpperCase(shiftedAlphabet1.charAt(j)));
						else
							outputsb.append(shiftedAlphabet1.charAt(j));
					}

				}
			}
			else
			{
				for (int j = 0; j < alphabet.length(); j++) {
					if (lowerInput.charAt(i) == alphabet.charAt(j)){
						if(input.charAt(i) == Character.toUpperCase(alphabet.charAt(j)))
							outputsb.append(Character.toUpperCase(shiftedAlphabet2.charAt(j)));
						else
							outputsb.append(shiftedAlphabet2.charAt(j));
					}
				}
			}
		}
		return outputsb.toString();
	}
	private static String halfOfString(String message, int start){
		StringBuilder sb = new StringBuilder();
		for (int i = start; i < message.length(); i += 2) {
			sb.append(message.charAt(i));
		}
		return (sb.toString());
	}
	public String decryptTwoKeys(String encrypted){
		CaesarCipherTwo cc = new CaesarCipherTwo(26 - main_key1, 26 - main_key2);
		String decryptedstr = cc.encryptTwoKeys(encrypted);
		return decryptedstr;
	}
}