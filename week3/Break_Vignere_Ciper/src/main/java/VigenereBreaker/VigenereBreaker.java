package VigenereBreaker;
import VigenereBreaker.*;

import java.io.File;
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
        char c = mostCommonCharIn(dictionary);
        for (int keyLength = 1; keyLength < 100; keyLength++) {
            int [] key = tryKeyLength(encrypted, keyLength, c);
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
        // validWords(decrypted, dictionary);
        // System.out.println("KeyLength:" + keyl);
        // System.out.println("Max numWords: " + max);
        return (decrypted);
    }
    public char mostCommonCharIn(HashSet<String> dictionary){
        HashMap <Character, Integer> counts = new HashMap <Character, Integer>(); 
        for (String word :dictionary){
            for(int i = 0; i < word.length();i++){
                char c = word.charAt(i);
                if (counts.containsKey(c)){
                    counts.put(c, counts.get(c) + 1);
                }
                else{
                    counts.put(c, 1);
                }
            }
        }
        int max = 0;
        char result = '\0';
        for (Map.Entry<Character, Integer> entry  : counts.entrySet()) {
            if (entry.getValue() > max)
            {
                max = entry.getValue();
                result = entry.getKey();
            }
        }
        return result;
    }
    public void breakForAllLangs(String encrypted, HashMap<String, HashSet <String>> languages){
        int max = 0;
        int count = 0;
        String language = "";
        String decrypted = "";
        for (Map.Entry<String, HashSet<String>> entry : languages.entrySet()) {
            String tryDecrypted = breakForLanguage(encrypted, entry.getValue());
            count = countwords(tryDecrypted, entry.getValue());
            // System.out.println("Count: " + count + "language: " + entry.getKey());
            if (count > max){
                language = entry.getKey();
                decrypted = tryDecrypted;
                max = count;
            }
        }
        System.out.println("Language: " + language);
        System.out.println("Decrypted msg: " + decrypted);
    }

    public void breakVigenere () {
        System.out.println("Select encrypted message!");
        FileResource fr = new FileResource();
        String message = fr.asString();
        System.out.println("Select Dictionaries!");
        DirectoryResource dr = new DirectoryResource();
        HashMap <String, HashSet<String>> map = new HashMap <String, HashSet<String>>();
        for (File f : dr.selectedFiles()) {
            HashSet<String> result = new HashSet<String>();
            FileResource dictfr = new FileResource(f);
            result = readDictionary(dictfr);
            map.put(f.getName(), result);
        }
        breakForAllLangs(message, map);
        // int [] key = tryKeyLength(message, 38, 'e');
        // VigenereCipher vc = new VigenereCipher(key);
        // String tryDecrypted = vc.decrypt(message);
        // int count = countwords(tryDecrypted, result);
        // System.out.println("Count:" + count);
        // String decrypted = vc.decrypt(message);
    }
    public static void main(String[] args) {
        VigenereBreaker vb = new VigenereBreaker();
        vb.breakVigenere();
        // System.out.println(decrypted);
    }
}
