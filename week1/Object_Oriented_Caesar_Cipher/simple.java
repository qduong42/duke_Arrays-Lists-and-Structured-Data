public class simple{
private String word;
private String phrase;
public simple (int number, String w) {
	word = w;
	phrase = mystery (number, w);
}
private String mystery(int num, String s) {
	String answer = "";
	for (int k=0; k<num; k++) {
		answer = answer + s;
}
	return answer;
}

public String toString() {
return phrase + " is " + word + " repeated";
}
public static void main(String[] args) {
	simple item = new simple(3, "blue"); 
	System.out.println(item);
}
}