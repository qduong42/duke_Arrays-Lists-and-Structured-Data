import edu.duke.*;
public class CaesarCipher{
	public String encrypt(String input, int key){
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
	public String encryptTwoKeys(String input, int key1, int key2){
		if (key1 > 25)
			key1 = key1 % 26;
		if (key2 > 25)
			key2 = key2 % 26;
		String lowerInput = input.toLowerCase();
		String alphabet = "abcdefghijklmnopqrstuvwxyz";
		String Cipher1 = alphabet.substring(key1) + alphabet.substring(0,key1);
		String Cipher2 = alphabet.substring(key2) + alphabet.substring(0,key2);
		StringBuilder outputsb = new StringBuilder();
		for (int i = 0; i < input.length(); i++) {
			if(outputsb.length() != i)
				outputsb.append(input.charAt(i - 1));
			if (i % 2 == 0){
				for (int j = 0; j < alphabet.length(); j++) {
					if (lowerInput.charAt(i) == alphabet.charAt(j)){
						if(input.charAt(i) == Character.toUpperCase(alphabet.charAt(j)))
							outputsb.append(Character.toUpperCase(Cipher1.charAt(j)));
						else
							outputsb.append(Cipher1.charAt(j));
					}
				}
			}
			else
			{
				for (int j = 0; j < alphabet.length(); j++) {
					if (lowerInput.charAt(i) == alphabet.charAt(j)){
						if(input.charAt(i) == Character.toUpperCase(alphabet.charAt(j)))
							outputsb.append(Character.toUpperCase(Cipher2.charAt(j)));
						else
							outputsb.append(Cipher2.charAt(j));
					}
				}
			}
		}
		return outputsb.toString();
	}
	private void testCaesar(){
		FileResource fr = new FileResource();
		String message = fr.asString();
		int key = 15;
		//int key2 = 23;
		String encrypted = encrypt(message, key);
		System.out.println("key is " + key + "\n" + "Secret message is: " + encrypted);
		//encrypted = encrypt(message, key2);
		//System.out.println("key is " + key2 + "\n" + "Secret message is: " + encrypted);
	}
	private void testEncryptTwoKeys(){
		FileResource fr = new FileResource();
		String message = fr.asString();
		int key1 = 8;
		int key2 = 21;
		String encrypted = encryptTwoKeys(message, key1, key2);
		System.out.println("Secret message is: " + encrypted);
	}
	public static void main(String[] args){
		CaesarCipher cc = new CaesarCipher();
		//testCaesar();
		cc.testEncryptTwoKeys();
		cc.testCaesar();
	}
}