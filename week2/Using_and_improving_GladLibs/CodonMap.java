import java.util.HashMap;
import java.util.Map;
import edu.duke.FileResource;

public class CodonMap{
	private HashMap<String, Integer> codonMap;
	public CodonMap()
	{
		codonMap = new HashMap<String, Integer>();
	}
/* 	public HashMap<String, Integer> getMap()
	{
		return codonMap;
	} */
	public void buildCodonMap(int start, String dna)
	{
		codonMap.clear();
		dna = dna.trim();
		for (int i = start; i < dna.length(); i = i + 3) {
			//if its >= dna.length => can not build 3 codons
			if (i + 3 <= dna.length()) {
				//codon built of 3 dna
				String codon = dna.substring(i, i + 3);
				if (codonMap.containsKey(codon)) {
					codonMap.put(codon, codonMap.get(codon) + 1);
				} else {
					codonMap.put(codon, 1);
				}
			}
		}
	}
	public String getMostCommonCodon(){
		if (codonMap.isEmpty())
			return ("No map, invoke build codon map function first!");
		int max = 0;
		String mostCommonCodon = "";
		for (String codon: codonMap.keySet()){
			if (codonMap.get(codon) > max)
			{
				max = codonMap.get(codon);
				mostCommonCodon = codon;
			}
		}
		return mostCommonCodon;
	}
	public void printCodonCounts(int start, int end){
		System.out.println("Counts of codon between " + start + " and " + end + " inclusive are:");
		for (String codon: codonMap.keySet()){
			int count = codonMap.get(codon);
			if (count >= start && count <= end)
				System.out.println(codon + " " + count);
		}
	}
	public void tester(){
		FileResource fr = new FileResource();
		String str = fr.asString().toUpperCase();
		for (int i = 0; i < 3; i++) {	
			buildCodonMap(i, str);
			System.out.println("Reading frame starting with " + i + " results in " + codonMap.size() +" unique codons");		
			String codon = getMostCommonCodon();
			System.out.println("Most common codon is " + codon + " with count " + codonMap.get(codon));
			printCodonCounts(7, 7);
			System.out.println("\n\n");
		}
	}
	public static void main(String[] args) {
		CodonMap cmap = new CodonMap();
		cmap.tester();
		// HashMap<String, Integer> map = cmap.getMap();
		// for (String codon : map.keySet()) {
		// 	System.out.println(codon + "\t" + map.get(codon));
		// }
		// System.out.println(cmap.getMostCommonCodon());
	}
}