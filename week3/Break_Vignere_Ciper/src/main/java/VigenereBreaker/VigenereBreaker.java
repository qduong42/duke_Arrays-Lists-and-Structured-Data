package VigenereBreaker;
import VigenereBreaker.*;
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
    public HashSet<String> readDictionary(FileResource fr){
        HashSet<String> hset = new HashSet<String>();
        for (String line : fr.lines()) {
            hset.add(line.toLowerCase());
        }
        return hset;
    } 
    
    public void validWords(String message, HashSet <String> dictionary){
        String[] words = new String[message.split("\\W+").length];
        words = message.split("\\W+");
        for (int i = 0; i < words.length; i++) {
            if (dictionary.contains(words[i].toLowerCase())){
                System.out.println("Word: " + words[i].toLowerCase());
            }
            else
                System.out.println("Invalid Word: " + words[i].toLowerCase());

        }
    }

    public int countwords(String message, HashSet <String> dictionary){
        String[] words = new String[message.split("\\W+").length];
        words = message.split("\\W+");
        int count = 0;
        for (int index = 0; index < words.length; index++) {
            if (dictionary.contains(words[index].toLowerCase()) && words[index].trim().length()> 0){
                count++;
            }
        }
        // System.out.println("count/words:" + count + "/" + words.length);
        return count;
    }
    
    public String breakForLanguage(String encrypted, HashSet <String> dictionary){
        int max = 0;
        String decrypted = "";
        // int keyl = 0;
        for (int keyLength = 1; keyLength < 100; keyLength++) {
            int [] key = tryKeyLength(encrypted, keyLength, 'e');
            VigenereCipher vc = new VigenereCipher(key);
            String tryDecrypted = vc.decrypt(encrypted);
            int count = countwords(tryDecrypted, dictionary);
            if (count > max)
            {
                max = count;
                decrypted = tryDecrypted;
                // keyl = keyLength;
            }    
        }
        validWords(decrypted, dictionary);
        // System.out.println("KeyLength:" + keyl);
        System.out.println("Max numWords: " + max);
        return (decrypted);
    }

    public String breakVigenere () {
        System.out.println("Select encrypted message!");
        FileResource fr = new FileResource();
        String message = fr.asString();
        System.out.println("Select Dictionary!");
        FileResource dictfr = new FileResource();
        HashSet<String> result = new HashSet<String>();
        result = readDictionary(dictfr);
        // int [] key = tryKeyLength(message, 38, 'e');
        String decrypted = breakForLanguage(message, result);
        // VigenereCipher vc = new VigenereCipher(key);
        // String tryDecrypted = vc.decrypt(message);
        // int count = countwords(tryDecrypted, result);
        // System.out.println("Count:" + count);
        // String decrypted = vc.decrypt(message);
        return decrypted;
    }
    public static void main(String[] args) {
        VigenereBreaker vb = new VigenereBreaker();
        String decrypted = vb.breakVigenere();
        // System.out.println(decrypted);
    }
}
