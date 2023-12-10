import edu.duke.*;
public class CaesarCipher{
	private static String encrypt(String input, int key){
		if (key > 25)
			key = key % 26;
		String lowerInput = input.toLowerCase();
		String alphabet = "abcdefghijklmnopqrstuvwxyz";
		String Cipher = alphabet.substring(key) + alphabet.substring(0,key);
		StringBuilder outputsb = new StringBuilder();
		for (int i = 0; i < input.length(); i++) {
			if(outputsb.length() != i)
				outputsb.append(input.charAt(i - 1));
			for (int j = 0; j < alphabet.length(); j++) {
				if (lowerInput.charAt(i) == alphabet.charAt(j)){
					if(input.charAt(i) == Character.toUpperCase(alphabet.charAt(j)))
						outputsb.append(Character.toUpperCase(Cipher.charAt(j)));
					else
						outputsb.append(Cipher.charAt(j));
				}
			}
		}
		return outputsb.toString();
	}
	private static void testCaesar(){
		FileResource fr = new FileResource();
		String message = fr.asString();
		int key = 17;
		int key2 = 23;
		String encrypted = encrypt(message, key);
		System.out.println("key is " + key + "\n" + "Secret message is: " + encrypted);
		encrypted = encrypt(message, key2);
		System.out.println("key is " + key2 + "\n" + "Secret message is: " + encrypted);
	}
	
	public static void main(String[] args){
		testCaesar();
	}
}