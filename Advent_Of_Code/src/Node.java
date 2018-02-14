public class Node {
	public String name;
	public int value;
	public Node child;
	
	public Node(String name) {
		this(name,0,null);
	}
	
	public Node(String name, int value, Node child) {
		this.name = name;
		this.value = value;
		this.child = child;
	}
}
