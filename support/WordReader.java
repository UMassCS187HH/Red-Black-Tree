import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class WordReader {

	public ArrayList<String> words;
	
	public WordReader(String file){
		words = readWordFile(file);
	}
	
	private ArrayList<String> readWordFile(String file) {
		try {
			FileReader 	      fr    = new FileReader(file);
			BufferedReader    br    = new BufferedReader(fr);
			ArrayList<String> words = new ArrayList<String>();
			String word; //       = null;
			while ((word = br.readLine()) != null) {
				String[] w = word.trim().split("\\s+");
				for(String wor : w)
					words.add(wor);
			}
			br.close();
			return words;
		} catch (FileNotFoundException e) {
			System.err.println("Could not read the file " + file);
			return new ArrayList<String>();
		} catch (IOException e) {
			System.err.println("Problem reading the file " + file);
			return new ArrayList<String>();
		}
	}
}
