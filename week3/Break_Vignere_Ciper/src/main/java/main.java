public class main {
	public static void main(String[] args){
		// CaesarCipher cc = new CaesarCipher(5);
		// char c = cc.encryptLetter('a');
		// System.out.println("char: " + c);
		VigenereBreaker vb = new VigenereBreaker();
		vb.sliceString("abcdefghijklm", 0, 3);
	}
}
