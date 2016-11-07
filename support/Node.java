
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
	}

	public Node<T> getRight() {
		return right;
	}

	public void setRight(Node<T> right) {
		this.right = right;
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

}

enum Color {
	Red, Black;
}