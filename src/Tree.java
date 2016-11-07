
public class Tree<T extends Comparable<T>> {
	Node<T> head;
	
	public Tree(){}
//
	public void rotateRight(Node<T> node){
		Node<T> Pivot = node.getLeft(),PivotRS = node.getLeft().getRight(),RootOS = node.getRight();
		
		node.setLeft(RootOS);
		node.setRight(PivotRS);
		Pivot.setLeft(node);
		if (node.getParent() == null)
			head = Pivot;
		else 
			if (node.getParent().getLeft() == node)
				node.getParent().setLeft(Pivot);
			else
				node.getParent().setRight(Pivot);
	}
	
	public void rotateLeft(Node<T> node){
		
		Node<T> Pivot = node.getRight(),PivotRS = node.getRight().getLeft(),RootOS = node.getLeft();
		
		node.setLeft(RootOS);
		node.setRight(PivotRS);
		Pivot.setLeft(node);
		if (node.getParent() == null)
			head = Pivot;
		else 
			if (node.getParent().getLeft() == node)
				node.getParent().setLeft(Pivot);
			else
				node.getParent().setRight(Pivot);
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
			newNode.setParent(current);
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

		if ((node == node.getParent().getRight()) && (node.getParent() == g.getLeft()))
			rotateLeft(node.getParent());
		else if ((node == node.getParent().getLeft()) && (node.getParent() == g.getRight())) 
			rotateRight(node.getParent());
		insertCase5(node);
		
	}
	public void insertCase5(Node<T> node){
		Node<T> g = node.getGrandParent(); 

		 node.getParent().setColor(Color.Black);
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
	public boolean isLeaf(Node node){
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
          