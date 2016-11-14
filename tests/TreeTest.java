import org.junit.Before;
import org.junit.Test;

public class TreeTest {
	Tree<String> tree;
	Tree<String> treec;
	WordReader w;
	@Before
	public void setup(){
		tree = new Tree<String>();
		treec = new Tree<String>();
		w = new WordReader("asset/53524-0.txt");
	}
	@Test
	public void TreeBuildingTest(){
		tree.insert("D");
		tree.head.print();
		tree.insert("B");
		tree.head.print();
		tree.insert("E");
		tree.head.print();
		tree.insert("C");
		tree.head.print();
		tree.insert("F");
		tree.head.print();
		tree.insert("A");
		tree.head.print();
	}
	@Test
	public void CorpusTest(){
		for(String word : w.words)
			treec.insert(word);
		treec.head.print();
	}
}
