import java.util.*;
public class Main {
	public static void main(String[] args) {
		BinarySearchTree tree = new BinarySearchTree();	
		tree.insert(10);
		tree.insert(6);
		tree.insert(15);
		tree.insert(3);
		tree.insert(8);
		tree.insert(20);
		tree.display();
		// System.out.println(tree.breadthFirstSearch());
		System.out.println("BFS: "+tree.breadthFirstSearch());
		System.out.println("DFS_PreOrder: "+tree.depthFirstSearch_PreOrder());
		System.out.println("DFS_PostOrder: "+tree.depthFirstSearch_PostOrder());
		System.out.println("DFS_InOrder: "+tree.depthFirstSearch_InOrder());
	}
}

/*
Input:Tree
		  	      10
			6			15
		3		8			20	

Output: Tree-> 10, 6, 3, 8, 15, 20,
		BFS: [10, 6, 15, 3, 8, 20]
		DFS_PreOrder: [10, 6, 3, 8, 15, 20]
		DFS_PostOrder: [3, 8, 6, 20, 15, 10]
		DFS_InOrder: [3, 6, 8, 10, 15, 20] */


class Node {
	private Node left, right;
	private int value;

	public Node(int value) {
		this.value = value;
		this.left = null;
		this.right = null;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public void setLeft(Node leftValue) {
		this.left = leftValue;
	}

	public void setRight(Node rightValue) {
		this.right = rightValue;
	}

	public int getValue() {
		return this.value;
	}

	public Node getLeft() {
		return this.left;
	}

	public Node getRight() {
		return this.right;
	}

}


class BinarySearchTree {
	private Node root;

	public BinarySearchTree() {
		this.root = null;
	}

	public void setRoot(int rootValue) {
		this.root = new Node(rootValue);
	}

	public Node getRoot() {
		return this.root;
	}


	public boolean insert(int value) {
		Node newNode = new Node(value);
		Node iterator = this.root;
		if(this.root==null) {
			this.root = newNode;
			return true;
		} else {
			while(true) {
				if(newNode.getValue() == iterator.getValue()) {
					return false;
				} 
				if(newNode.getValue() > iterator.getValue()) {
					if(iterator.getRight() == null) {
						iterator.setRight(newNode);
						return true;
					} else {
						iterator = iterator.getRight();
					}
				} else {
					if(iterator.getLeft() == null) {
						iterator.setLeft(newNode);
						return true;
					} else {
						iterator = iterator.getLeft();
					}
				}
			}
		}
	}

	public boolean find(int value) {
		Node nodeToFind = new Node(value);
		if(this.root == null) {
			return false; 
		}
		Node iterator = this.root;
		while(true) {
			if(nodeToFind.getValue() == iterator.getValue()) {
				return true;
			}
			if(nodeToFind.getValue() > iterator.getValue()) {
				if(iterator.getRight() == null) {
					return false;
				} else {
					iterator = iterator.getRight();
				}
			} else {
				if(iterator.getLeft() == null) {
					return false;
				} else {
					iterator = iterator.getLeft();
				}
			}
		}
	}

	/*_______________TREE TRAVERSAL KA PART_______________*/

	
	//breadthFirstSearch- row wise print karo
	public ArrayList<Integer> breadthFirstSearch() {
		ArrayList<Node> queue = new ArrayList<>();		//just to help us out
		ArrayList<Integer> visited = new ArrayList<>(); //main result wala array.
		Node currentNode = this.root;
		queue.add(currentNode);
		while(!queue.isEmpty()) {
			currentNode = queue.get(0);
			visited.add(currentNode.getValue());
			queue.remove(0);
			if(currentNode.getLeft() != null) {
				queue.add(currentNode.getLeft());
			}
			if(currentNode.getRight() != null) {
				queue.add(currentNode.getRight());
			}
		}
		return visited;
	}


	//DepthFirstSearch(PreOrder)- poora left traversal fiir poora right traversal.
	public ArrayList<Integer> depthFirstSearch_PreOrder() {
		ArrayList<Integer> visited = new ArrayList<>();
		Node iterator = this.root;
		helperMethodDFS_PreOrder(iterator, visited);
		return visited;
	}

	public void helperMethodDFS_PreOrder(Node iterator, ArrayList<Integer> visited) {
		visited.add(iterator.getValue());
		if(iterator.getLeft() != null) {
			helperMethodDFS_PreOrder(iterator.getLeft(), visited);
		}
		if(iterator.getRight() != null) {
			helperMethodDFS_PreOrder(iterator.getRight(), visited);
		}
	}

	//DepthFirstSearch(PreOrder)- poora left traversal fiir poora right traversal fiir
	//beechka traverse karenge but niche se.
	public ArrayList<Integer> depthFirstSearch_PostOrder() {
		ArrayList<Integer> visited = new ArrayList<>();
		Node iterator = this.root;
		helperMethodDFS_PostOrder(iterator, visited);
		return visited;
	} 

	public void helperMethodDFS_PostOrder(Node iterator, ArrayList<Integer> visited) {
		if(iterator.getLeft() != null) {
			helperMethodDFS_PostOrder(iterator.getLeft(), visited);
		} 
		if(iterator.getRight() != null) {
			helperMethodDFS_PostOrder(iterator.getRight(), visited);
		} 
		visited.add(iterator.getValue());
	}


	//DepthFirstSearch(PreOrder)- poora left traversal fiir beechka traversal 
	// fiir poora right traverse karenge but niche se.
	public ArrayList<Integer> depthFirstSearch_InOrder() {
		ArrayList<Integer> visited = new ArrayList<>();
		Node iterator = this.root;
		helperMethodDFS_InOrder(iterator, visited);
		return visited;
	} 

	public void helperMethodDFS_InOrder(Node iterator, ArrayList<Integer> visited) {
		if(iterator.getLeft() != null) {
			helperMethodDFS_InOrder(iterator.getLeft(), visited);
		} 
		visited.add(iterator.getValue());
		if(iterator.getRight() != null) {
			helperMethodDFS_InOrder(iterator.getRight(), visited);
		} 
	}





	public void display() {
		if(this.root == null) {
			System.out.println("No tree");
			return;
		}
		Node iterator = this.root;
		System.out.print("Tree-> ");
		helperMethod(iterator);
		System.out.println();
	}

	public void helperMethod(Node iterator) {
		System.out.print(iterator.getValue()+", ");
		if(iterator.getLeft() != null) {
			helperMethod(iterator.getLeft());
		}
		if(iterator.getRight() != null) {
			helperMethod(iterator.getRight());
		}
	}

}

