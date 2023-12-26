package VigenereBreaker;
import java.util.*;
import edu.duke.*;

public class VigenereBreaker {
    public String sliceString(String message, int whichSlice, int totalSlices) {
        StringBuilder sb = new StringBuilder();
        for(int i = whichSlice; i < message.length(); i += totalSlices){
            sb.append(message.charAt(i));
        }
        // System.out.println("Message: " + sb.toString());
        return sb.toString();
    }

    public int[] tryKeyLength(String encrypted, int klength, char mostCommon) {
        int[] key = new int[klength];
        //param constructed mostCommonChar = mostCommon;
        CaesarCracker cc = new CaesarCracker(mostCommon);
        for (int i = 0; i < key.length; i++) {
            String slice = sliceString(encrypted, i, klength);
            // System.out.println("Slice: " + slice);
            int sliceKey = cc.getKey(slice);
            key[i] = sliceKey;
        }
        return key;
    }

    /*
     * step 1: slicestring depends on klength => klength 3 => eg. key
     * Message: "referee electees" => most common => e
     * ref|ere|e e|lec|tee|s
     * sliceString(message, 0, 3) => should be |reelts|
     * 1, 3 => |er ee|
     * 2, 3 => |feece|
     * Theoretically in every slice, l should occur most often with big enough dataset for it to be the most common letter in every slice. With this artitificially made two words with 8/14 e + ' ' in each slice 2-3 e vs 1 of every other letter.
     * for loop to collect all slices. go through each slice and find keyshift to have e => convert to get original key EXTENSION: convert key back to string key.
     */

    
    
    public String breakVigenere () {
        FileResource fr = new FileResource();
        String message = fr.asString();
        int [] result = tryKeyLength(message, 4, 'e');
        VigenereCipher vc = new VigenereCipher(result);
        String decrypted = vc.decrypt(message);
        System.out.println(decrypted);
        return decrypted;
    }
    public static void main(String[] args) {
        FileResource fr = new FileResource();
        String message = fr.asString();
        VigenereBreaker vb = new VigenereBreaker();
        int [] result = vb.tryKeyLength(message, 4, 'e');
        System.out.println("Key: " + Arrays.toString(result));
        VigenereCipher vc = new VigenereCipher(result);
        String decrypted = vc.decrypt(message);
        System.out.println(decrypted);
    }
}
