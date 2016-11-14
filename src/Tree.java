
public class Tree<T extends Comparable<T>> {
	Node<T> head;

	public Tree(){}

	public void rotateRight(Node<T> node){
		if (node == null)
			return;
		Node<T> k2 = node.getRight(), ncop = node.copy();
		node.setRight(k2!=null?k2.getLeft():null);
		if (k2!=null)
			k2.setLeft(node);
		if (ncop.getParent()==null)
			head=k2;
		else
			if (ncop.getParent().getRight() == node){
				ncop.getParent().setRight(k2);
			} else {
				ncop.getParent().setLeft(k2);
			}
	}
	public void rotateLeft(Node<T> node){
		if (node == null)
			return;
		Node<T> k1 = node.getLeft(),ncop = node.copy();
		node.setLeft(k1!=null?k1.getRight():null);
		if (k1!=null)
			k1.setRight(node);
		if (ncop.getParent()==null)
			head=k1;
		else
			if (ncop.getParent().getRight() == node){
				ncop.getParent().setRight(k1);
			} else {
				ncop.getParent().setLeft(k1);
			}

	}
	public void insert(T data){
		Node<T> newNode = new Node<T>(data);
		if (head == null){
			head = newNode;
		} else {
			Node<T> current = head;
			while(true){
				if (data.compareTo(current.getData()) < 0){
					if (current.getLeft() == null){
						current.setLeft(newNode);
						break;
					}
					current = current.getLeft();

				} else{
					if (current.getRight() == null){
						current.setRight(newNode);
						break;
					}
					current = current.getRight();
				}
			}
		}
		insertCase1(newNode);

	}
	public void insertCase1(Node<T> node){
		if (node.getParent()==null)
			node.setColor(Color.Black);
		else 
			insertCase2(node);
	}
	public void insertCase2(Node<T> node){
		if (node.getParent()!= null)
			if (node.getParent().getColor() == Color.Black)
				return;
		insertCase3(node);
	}
	public void insertCase3(Node<T> node){
		Node<T> g,u = node.getUncle();
		if (u != null && u.getColor() == Color.Red){
			node.getParent().setColor(Color.Black);
			node.setColor(Color.Black);
			g = node.getGrandParent();
			g.setColor(Color.Red);
			insertCase1(g);
		} else
			insertCase4(node);
	}
	public void insertCase4(Node<T> node){
		Node<T> g = node.getGrandParent(); 
		boolean isPLeft = node.getParent().getLeft() == node;

		if ((node == node.getParent().getRight()) && (node.getParent() == g.getLeft())){
			rotateLeft(node.getParent());
			if (isPLeft)
				node.getParent().setLeft(node.getLeft());
			else
				node.getParent().setRight(node.getLeft());
		}else if ((node == node.getParent().getLeft()) && (node.getParent() == g.getRight())) {
			rotateRight(node.getParent());
			if (isPLeft)
				node.getParent().setLeft(node.getRight());
			else
				node.getParent().setRight(node.getRight());
		}
		insertCase5(node);

	}
	public void insertCase5(Node<T> node){
		Node<T> g = node.getGrandParent(); 

		node.getParent().setColor(Color.Black);
		if (g!=null)
			g.setColor(Color.Red);
		if (node == node.getParent().getLeft())
			rotateRight(g);
		else
			rotateLeft(g);
	}
	public void remove(Node<T> node){
		Node<T> child = isLeaf(node.getRight()) ? node.getLeft() : node.getRight();
		child.setParent(child.getGrandParent());
		if (child.getGrandParent().getLeft() == node)
			child.getGrandParent().setLeft(child);
		else
			child.getGrandParent().setRight(child);
		if (node.getColor() == Color.Black)
			if (child.getColor() == Color.Red)
				child.setColor(Color.Black);
			else 
				removeCase1(child);

	}
	public boolean isLeaf(Node<T> node){
		if (node == null)
			return false;
		return node.getLeft() == null && node.getRight()== null;
	}
	public void removeCase1(Node<T> node){
		if (node.getParent() != null)
			removeCase2(node);

	}
	public void removeCase2(Node<T> node){
		Node<T> s = node.getSibling();

		if (s.getColor() == Color.Red) {
			node.getParent().setColor(Color.Red); 
			s.setColor(Color.Black);
			if (node == node.getParent().getLeft())
				rotateLeft(node.getParent());
			else
				rotateRight(node.getParent());
		}
		removeCase3(node);		
	}
	public void removeCase3(Node<T> n){
		Node<T> s = n.getSibling();

		if ((n.getParent().getColor() == Color.Black) &&
				(s.getColor() == Color.Black) &&
				(s.getLeft().getColor() == Color.Black) &&
				(s.getRight().getColor() == Color.Black)) {
			s.setColor(Color.Red);
			removeCase1(n.getParent());
		} else
			removeCase4(n);
	}
	public void removeCase4(Node<T> n){
		Node<T> s =n.getSibling();

		if ((n.getParent().getColor() == Color.Red) &&
				(s.getColor() == Color.Black) &&
				(s.getLeft().getColor() == Color.Black) &&
				(s.getRight().getColor() == Color.Black)) {
			s.setColor(Color.Red);
			n.getParent().setColor(Color.Black);
		} else
			removeCase5(n);	
	}
	public void removeCase5(Node<T> n){
		Node<T> s= n.getSibling();

		if  (s.getColor() == Color.Black) { /* this if statement is trivial,
		due to case 2 (even though case 2 changed the sibling to a sibling's child,
		the sibling's child can't be red, since no red parent can have a red child). */
			/* the following statements just force the red to be on the left of the left of the parent,
		   or right of the right, so case six will rotate correctly. */
			if ((n == n.getParent().getLeft()) &&
					(s.getRight().getColor() == Color.Black) &&
					(s.getLeft().getColor() == Color.Red)) { /* this last test is trivial too due to cases 2-4. */
				s.setColor(Color.Red);
				s.getLeft().setColor(Color.Black);
				rotateRight(s);
			} else if ((n == n.getParent().getRight() &&
					(s.getLeft().getColor() == Color.Black) &&
					(s.getRight().getColor() == Color.Red))) {/* this last test is trivial too due to cases 2-4. */
				s.setColor( Color.Red);
				s.getRight().setColor(Color.Black);
				rotateLeft(s);
			}
		}
		removeCase6(n);
	}
	public void removeCase6(Node<T> n){
		Node<T> s = n.getSibling();

		s.setColor(n.getParent().getColor());
		n.getParent().setColor(Color.Black);

		if (n == n.getParent().getLeft()) {
			s.getRight().setColor(Color.Black);
			rotateLeft(n.getParent());
		} else {
			s.getLeft().setColor(Color.Black);
			rotateRight(n.getParent());
		}	
	}
}