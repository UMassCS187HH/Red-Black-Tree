import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Node<T extends Comparable<T>> {
	private T data;
	private Node<T> parent, left, right;
	private Color color = Color.Red;

	public Node(T data) {
		this(data, null);
	}

	public Node(T data, Node<T> parent) {
		this.data = data;
		this.parent = parent;
	}
	

	public Node<T> getGrandParent() {
		if (parent == null)
			return null;
		return parent.getParent();
	}

	public Node<T> getUncle() {
		Node<T> g = getGrandParent();
		if (g == null)
			return null;
		if (parent == g.left)
			return g.right;
		return g.left;
	}
	public Node<T> getSibling() {
		if (parent == null)
			return null;
		if (parent.getLeft() == this)
			return parent.getRight();
		else
			return parent.getLeft();
	}

	public Node<T> getParent() {
		return parent;
	}

	public void setParent(Node<T> parent) {
		this.parent = parent;
	}

	public Node<T> getLeft() {
		return left;
	}

	public void setLeft(Node<T> left) {
		this.left = left;
		if(left!=null)
			left.setParent(this);
	}

	public Node<T> getRight() {
		return right;
	}

	public void setRight(Node<T> right) {
		this.right = right;
		if(right!=null)
			right.setParent(this);
	}
	

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public void flipColor() {
		if (this.color == Color.Black)
			color = Color.Red;
		else
			color = Color.Black;
	}
	public void print(){
		BTreePrinter.printNode(this);
	}
	public Node<T> copy(){
		Node<T> newNode = new Node<T>(data);
		newNode.color=color;
		newNode.parent=parent;
		newNode.left=left;
		newNode.right=right;
		return newNode;
	}

}

enum Color {
	Red, Black;
}
class BTreePrinter {

    public static <T extends Comparable<T>> void printNode(Node<T> root) {
        int maxLevel = BTreePrinter.maxLevel(root);

        printNodeInternal(Collections.singletonList(root), 1, maxLevel);
    }

    private static <T extends Comparable<T>> void printNodeInternal(List<Node<T>> nodes, int level, int maxLevel) {
        if (nodes.isEmpty() || BTreePrinter.isAllElementsNull(nodes))
            return;

        int floor = maxLevel - level;
        int endgeLines = (int) Math.pow(2, (Math.max(floor - 1, 0)));
        int firstSpaces = (int) Math.pow(2, (floor)) - 1;
        int betweenSpaces = (int) Math.pow(2, (floor + 1)) - 1;

        BTreePrinter.printWhitespaces(firstSpaces);

        List<Node<T>> newNodes = new ArrayList<Node<T>>();
        for (Node<T> node : nodes) {
            if (node != null) {
            	if(node.getColor()==Color.Black)
            		System.out.print(node.getData());
            	else
            		System.err.print(node.getData());
                newNodes.add(node.getLeft());
                newNodes.add(node.getRight());
            } else {
                newNodes.add(null);
                newNodes.add(null);
                System.out.print(" ");
            }

            BTreePrinter.printWhitespaces(betweenSpaces);
        }
        System.out.println("");

        for (int i = 1; i <= endgeLines; i++) {
            for (int j = 0; j < nodes.size(); j++) {
                BTreePrinter.printWhitespaces(firstSpaces - i);
                if (nodes.get(j) == null) {
                    BTreePrinter.printWhitespaces(endgeLines + endgeLines + i + 1);
                    continue;
                }

                if (nodes.get(j).getLeft() != null)
                    System.out.print("/");
                else
                    BTreePrinter.printWhitespaces(1);

                BTreePrinter.printWhitespaces(i + i - 1);

                if (nodes.get(j).getRight() != null)
                    System.out.print("\\");
                else
                    BTreePrinter.printWhitespaces(1);

                BTreePrinter.printWhitespaces(endgeLines + endgeLines - i);
            }

            System.out.println("");
        }

        printNodeInternal(newNodes, level + 1, maxLevel);
    }

    private static void printWhitespaces(int count) {
        for (int i = 0; i < count; i++)
            System.out.print(" ");
    }

    private static <T extends Comparable<T>> int maxLevel(Node<T> node) {
        if (node == null)
            return 0;

        return Math.max(BTreePrinter.maxLevel(node.getLeft()), BTreePrinter.maxLevel(node.getRight())) + 1;
    }

    private static <T> boolean isAllElementsNull(List<T> list) {
        for (Object object : list) {
            if (object != null)
                return false;
        }

        return true;
    }

}