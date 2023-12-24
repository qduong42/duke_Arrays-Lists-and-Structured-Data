import org.junit.Test;

public class VignereBreakerTest{

	@Test
	public void VerifySliceString(){
		VignereBreaker vb = new VignereBreaker();
		String result = vb.sliceString("abcdefghijklm", 0, 3);
		assertEquals(result, "adgjm");
	}
}