public class CaesarCipher{
	private String alphabet;
	private String shiftedAlphabet;
	private int main_key;
	public CaesarCipher(int key){
		if (key > 25)
			key = key % 26;
		main_key = key;
		alphabet = "abcdefghijklmnopqrstuvwxyz";
		shiftedAlphabet = alphabet.substring(key) + alphabet.substring(0, key);
	}
	public String encrypt(String input){
		String lowerInput = input.toLowerCase();
		StringBuilder outputsb = new StringBuilder();
		for (int i = 0; i < input.length(); i++) {
			if(outputsb.length() != i)
				outputsb.append(input.charAt(i - 1));
			for (int j = 0; j < alphabet.length(); j++) {
				if (lowerInput.charAt(i) == alphabet.charAt(j)){
					if(input.charAt(i) == Character.toUpperCase(alphabet.charAt(j)))
						outputsb.append(Character.toUpperCase(shiftedAlphabet.charAt(j)));
					else
						outputsb.append(shiftedAlphabet.charAt(j));
				}
			}
		}
		return outputsb.toString();
	}
	public String decrypt (String encrypted){
		CaesarCipher cc = new CaesarCipher(26 - main_key);
		return (cc.encrypt(encrypted));
	}
	public static void main(String[] args){
		CaesarCipher cc = new CaesarCipher(15);
		System.out.println(cc.decrypt(cc.encrypt("testing")));
	}
}