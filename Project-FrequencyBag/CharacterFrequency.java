import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class CharacterFrequency {
	public static void main (String[] args) throws IOException 
	{
		FileReader in = new FileReader("letter1.txt");
	    BufferedReader reader = new BufferedReader(in);
	    FrequencyBag<String> bag=new FrequencyBag<String>();
	    
	    String line;
	    while((line=reader.readLine()) != null)
	    {
	    	String[] a = line.split("(?!^)");
			for (int i = 0;i < a.length; i++)
			{		
				bag.add(a[i].toLowerCase());
			}
	    }
		in.close();
		
		String alphabet = "abcdefghijklmnopqrstuvwxyz";
		
		for(int i = 0; i < 26; i++) 
		{
			char c = alphabet.charAt(i);
			int freq = bag.getFrequencyOf(String.valueOf(c));
			System.out.println(c + ": " + freq);
		}
	}
}
