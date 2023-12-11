import java.util.Arrays;

import edu.duke.*;
public class CaesarBreaker{
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
	private static void testDecrypt(){
		//CaesarBreaker cb = new CaesarBreaker();
		//FileResource fr = new FileResource();
		//String message = fr.asString();
		//CaesarCipher cc = new CaesarCipher();
		//String encrypted = cc.encrypt((message), 13);
		//System.out.println("Encrypted message: " + encrypted);
		String decrypted = decryptTwoKeys("Xifqvximt tsdtlxzrx iijirvtl ek Uybi afvbw yehvv xyi gfqdse iekmfrrpzdrxzse fj xyi jzich sw tsdtlxrxzseec xifqvxic, fjkie xmmie zr xyi trwk, xyek klv nsipu rvfyeh yj zw xyvvi-hzqvrjmfrrp eeh ulijxzsew lfa xymekj zr xymj nsipu iiceki xf vetl sklvv eii melvvvrkpp xifqvximt. Xrov dsmmek e tzees xyvfyxl e hfsi-wvrqv rru gprremek e jcmxlk-gekl xyek rzfmuw gfpcmjmfrj nmkl sklvv ezvgprrvw ej kaf vbrqgpvw. Zx wyslpu klvvvjfvv esk jyitimji xyek tsdtlxzrx gvftvvkmvw esslx xyiji kvsdikvzg xymekj rru klvmi zrkiietxzse rvv tsdqfr-tceti eeh mdtfvkeex. Nlzpv klzw mj jxzpc r mecmu rvxydiex, ni afych pzov ks edieh xyek dsjx sw klv xifqvximt hyvwkmfrj giftci gfrtiir xyidwvpmij nmkl lrzv ks hf nmkl lfa xymekj rvv tservgkiu. Mk zw mdtfvkeex xyek ymxlnepw eii wljwmtmvrkpp jxiezkyx eeh wdsfxy ks wltgsix xyi himmmek sw wejx grvj, flx eesklvv ijwvrkmrp tisgiixp, aymtl av lwlecpp kebi jfv kieexvh, zw xyek ymxlnepw eii gfrkmeyfyj, mehviu tservgkmek E xf S, eeh rfx nlwk rtgvfbzqrxvpp. Xyi gfviijtfrumek wlfwmvpu fj gfqgykekmfrrp kvsdikvp zw swxvr vvjvviiu ks ej tsdtlxrxzseec ksgscsxc. R xsfh tfvkmfr sw fyi vjwsixj dep si gcejwzjziu ks fvpfrx ks xymj jysjzich eeh eii himmie sc egtcmtekmfrj zr e zrvzikc sw fxyii wmvpuw, klv gvvhzgkmfr sw klv jxiytxlvv fj jfpuiu gvfxvmew eeh xyi vvgfrjxiytxzse fj llqrr sikrrj sizrx kaf. Xyi lrpcqrvb fj slv afvb zw jrwk rpxsimkldw xyek zqgpvqvrk deklvqrxzgrp qfhvpj ks swjvv mewzkyxj zrks eeh eewniiw xf jytl ulijxzsew.\r\n" + //
				"\r\n" + //
				"Av rvv vbgpfvzrx zwjyvw wlgy rw lfa xvgyrzulij wsi jsczzrx gvffcidw grr fv umjgfzvvvh, zqgvfzvh, rrrppdvh, rru uidsewkvrxvh xf si gfviitx si ftkmdec. Av vbgitx xf debi pveumek gfrkvzflxzsew me tsdtlxrxzseec xifqvxic, xifqvximt dsuicmek, ueke wkvlgkyiij, lzky-giijfvdeegv tsdtlxzrx, M/S-iwjzgziegp wsi vbkiirrp qvqfvp, kvsxvrtymt zrwsiqrxzse jcjxvqj (KZW), fzscsxmtec tsdtlxzrx, eeh hrxr tsdtiijwzse.\r\n" + //
				"\r\n" + //
				"Sitelwv fj xyi kvsdikvzg rrxlvv fj xyi tycjmtec nsipu zr aymtl av cmmi, xifqvximt gvffcidw eimji me rrp rvve xyek zrkiietxj nmkl xyi tycjmtec nsipu. Kvsdikvzg gfqgykmek jfglwvw se uijmxrzrx, eeeccqmek, rru zqgpvqvrkmek iwjzgziex eckfvzxyqj wsi xifqvximt gvffcidw. Klv xifqvximt tsdtlxzrx xvfyg fj xyi hvtrvkqvrk zw mexvveekmfrrpcc vvrfaeiu wsi zxj tseximsykmfrj ks xyi jlruediexrp tisspvqj zr gfqgykekmfrrp kvsdikvp, euhiijwzrx dejwzzv ueke qrrrkvqvrk zwjyvw me xifqvximt gvffcidw, rru rtgppmek kvsdikvzg xvgyrzulij ks e zrvzikc sw rvvej, megcyumek qfpvglprv fzscsxc, xifqvximt dsuicmek, issskmtw, xifkieglzg mejfvdekmfr wpwkidw, vgfpfkp, eeh tysksemtw.\r\n" + //
				"\r\n" + //
				"Xyi kislt etxzzvpp tscprffvrxvw azxy fxyii xvfygw azxyme klv uigeixdiex eeh azxy klv iijirvtlvvj zr sklvv hvtrvkqvrkw ek Uybi. Klvc gfpcessieki azxy wetycxp zr Qrxyidekmtw, Smfpfkp, Fzstlvqzwkvp, Icitximtec rru Tsdtlxvv Iekzrviimek, rru klv Emtlfprw Wtlfsc fj Iezzvfrdiex. Sipseh Hlov, xyi kislt ecwf tscprffvrxvw azxy iijirvtlvvj rx zrvzslw xft mewkmkykij. Fvgryji sw zxj uigxy rru svveuxy, xyi kvsdikvzg gfqgykmek kislt ek Uybi mj rvxyrfcc xyi xft kvsdikvzg gfqgykmek kislt me klv eekmfr.");
		System.out.println("Decrypted message: " + decrypted);
	}
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
	private static String decryptTwoKeys(String encrypted){
		CaesarCipher cc = new CaesarCipher();
		String odd = halfOfString(encrypted, 0);
		String even = halfOfString(encrypted, 1);
		int key1 = getKey(odd);
		int key2 = getKey(even);
		System.out.println("Key1: " + key1 + " key2: " + key2);
		String decryptedstr = cc.encryptTwoKeys(encrypted, 26-key1, 26-key2);
		return decryptedstr;
	}
	private static String decryptTwoKeys(String encrypted, int key1, int key2){
		CaesarCipher cc = new CaesarCipher();
		String odd = halfOfString(encrypted, 0);
		String even = halfOfString(encrypted, 1);
		System.out.println("Key1: " + key1 + " key2: " + key2);
		String decryptedstr = cc.encryptTwoKeys(encrypted, 26-key1, 26-key2);
		return decryptedstr;
	}
	public static void main(String []args){
		testDecrypt();
		//String str = "Qbkm Zgis";
		//System.out.println("test half: " + halfOfString(str, 0));
		//System.out.println("test half: " + halfOfString(str, 1));
	}
	private String decrypt (String encrypted){
		CaesarCipher cc = new CaesarCipher();
		int [] freqs = countLetters(encrypted);
		System.out.println("freqs: " + Arrays.toString(freqs));
		int maxDex = maxIndex(freqs);
		int dkey = maxDex - 4;
		if(maxDex < 4){
			dkey = 26 - (4 - maxDex);
		}
		return (cc.encrypt(encrypted, 26-dkey));
	}
}