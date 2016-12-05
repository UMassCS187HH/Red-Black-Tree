#Red And Black Trees
###1. Specifications
Each node has it's data and a one bit __color__ variable that is either Black or Red
The rules that govern this tree are that:
- A node is either red or black.
- The root is black. This rule is sometimes omitted. Since the root can always be changed from red to black, but not necessarily vice versa, this rule has little effect on analysis.
- All leaves (NIL) are black.
- If a node is red, then both its children are black.
- Every path from a given node to any of its descendant NIL nodes contains the same number of black nodes. Some definitions: the number of black nodes from the root to a node is the node's black depth; the uniform number of black nodes in all paths from root to the leaves is called the black-height of the red–black tree.
###2. Adding a Node
Adding a node is standard for binary search trees. The new node is painted red, which creates a few cases that may exist after adding that need to be taken care of. 
- Case 1:
	- The node that you added is at the root of the tree which means that it needs to be repainted black.
- Case 2: 
	- If the current node's parent is black that means that the tree has not been invalidated otherwise there is something that needs to be fixed
- Case 3:
	- If both the parent node and the Uncle node are red, they can be repainted black and the grandparent can be repainted red. This may violate rules so you must check the grandparent for case 1 and 2
- Case 4:
	- If the parent node is red but the uncle is black, you must do a same side rotation and check for the next case
- Case 5:
	- If the same case is satisfied as before, you then must do an opposite side rotation on the grandparent of the child from its parent
###3. Removing a Node
Removing a node, similarly, is a binary search tree removal but with some added checks to see if balance is maintained.
- Case 1:
	- 