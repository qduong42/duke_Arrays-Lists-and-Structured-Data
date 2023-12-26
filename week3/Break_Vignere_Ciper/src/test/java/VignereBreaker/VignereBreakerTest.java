package VignereBreaker;


import static org.junit.Assert.*;

import org.junit.Test;

import VigenereBreaker.*;

public class VignereBreakerTest{

	@Test
	public void givenString_whenSliceString_thenReturnString(){
		VigenereBreaker vb = new VigenereBreaker();
		String result = vb.sliceString("abcdefghijklm", 0, 3);
		assertEquals(result, "adgjm");
	}

	@Test
	public void givenString_whenSliceString_thenReturnString2(){
		VigenereBreaker vb = new VigenereBreaker();
		String result = vb.sliceString("abcdefghijklm", 1, 3);
		assertEquals(result, "behk");
	}

	@Test
	public void givenString_whenSliceString_thenReturnString3(){
		VigenereBreaker vb = new VigenereBreaker();
		String result = vb.sliceString("abcdefghijklm", 2, 3);
		assertEquals(result, "cfil");
	}
	@Test
	public void givenString_whenSliceString_thenReturnString4(){
		VigenereBreaker vb = new VigenereBreaker();
		String result = vb.sliceString("abcdefghijklm", 0, 4);
		assertEquals(result, "aeim");
	}
	@Test
	public void givenString_whenSliceString_thenReturnString5(){
		VigenereBreaker vb = new VigenereBreaker();
		String result = vb.sliceString("abcdefghijklm",1 ,4 );
		assertEquals(result, "bfj");
	}
	@Test
	public void givenString_whenSliceString_thenReturnString6(){
		VigenereBreaker vb = new VigenereBreaker();
		String result = vb.sliceString("abcdefghijklm", 2, 4);
		assertEquals(result, "cgk");
	}
	@Test
	public void givenString_whenSliceString_thenReturnString7(){
		VigenereBreaker vb = new VigenereBreaker();
		String result = vb.sliceString("abcdefghijklm",3 ,4 );
		assertEquals(result, "dhl");
	}
	@Test
	public void givenString_whenSliceString_thenReturnString8(){
		VigenereBreaker vb = new VigenereBreaker();
		String result = vb.sliceString("abcdefghijklm", 0, 5);
		assertEquals(result, "afk");
	}
	@Test
	public void givenString_whenSliceString_thenReturnString9(){
		VigenereBreaker vb = new VigenereBreaker();
		String result = vb.sliceString("abcdefghijklm", 1, 5);
		assertEquals(result, "bgl");
	}
	@Test
	public void givenString_whenSliceString_thenReturnString10(){
		VigenereBreaker vb = new VigenereBreaker();
		String result = vb.sliceString("abcdefghijklm",2 ,5 );
		assertEquals(result, "chm");
	}
	@Test
	public void givenString_whenSliceString_thenReturnString11(){
		VigenereBreaker vb = new VigenereBreaker();
		String result = vb.sliceString("abcdefghijklm", 3, 5);
		assertEquals(result, "di");
	}
	@Test
	public void givenString_whenSliceString_thenReturnString12(){
		VigenereBreaker vb = new VigenereBreaker();
		String result = vb.sliceString("abcdefghijklm", 4, 5);
		assertEquals(result, "ej");
	}
	@Test
	public void givenEncryptedString_whenTryKeyLength_thenReturnKeyArray(){
		VigenereBreaker vb = new VigenereBreaker();
		String key = "key";
		int [] keyarr = new int [key.length()];
		for (int i = 0; i < key.length(); i++) {
			keyarr[i] = key.charAt(i) - 97;
			// System.out.println("Key: " + keyarr[i]);
		}
		VigenereCipher vc = new VigenereCipher(keyarr);
		String encrypted = vc.encrypt("referee electees");
		int [] result = vb.tryKeyLength(encrypted, 3, 'e');
		assertArrayEquals(new int[]{10, 4, 24}, result);
	}
	@Test
	public void givenEncryptedString_whenBreakVignere_ThenReturnUnencrypted(){
		VigenereBreaker vb = new VigenereBreaker();
		String decrypted = vb.breakVigenere();
		String expected = "SCENE II. Athens. QUINCE'S house.";
		assertEquals(expected, decrypted.substring(0, expected.length()));
	}
}