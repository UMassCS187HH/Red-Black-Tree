import org.junit.Before;
import org.junit.Test;

public class TreeTest {
	Tree<String> tree;
	@Before
	public void setup(){
		tree = new Tree<String>();
	}
	@Test
	public void TreeBuildingTest(){
		tree.insert("D");
		tree.insert("B");
		tree.insert("E");
		tree.insert("C");
		tree.insert("F");
		tree.insert("A");
	}
}
