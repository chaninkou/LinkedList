package application;
public class Node<E1, E2, E3> {
	// will store information related to the first grouping category
	protected E1 category1;

	// will store information related to the second grouping category
	protected E2 category2;

	// will store information related to the third grouping category
	protected E3 category3;

	// a pointer to the next item in the main list.
	protected Node<E1, E2, E3> right;

	// a pointer to the previous item in the main list
	protected Node<E1, E2, E3> left;

	// a pointer to the next item in the sub-list
	protected Node<E1, E2, E3> down;
	
	// one constructor which has parameters to initialize the three category data fields.
	public Node(E1 category1, E2 category2, E3 category3) {
		this.category1 = category1;
		this.category2 = category2;
		this.category3 = category3;
	}
	
	// getters for each of the three category variables.
	public E1 getCategory1() {
		return category1;
	}
	
	public void setCategory1(E1 Category1) {
		this.category1 = Category1;
	}

	public E2 getCategory2() {
		return category2;
	}
	
	public void setCategory2(E2 Category2) {
		this.category2 = Category2;
	}
	
	public E3 getCategory3() {
		return category3;
	}
	
	public void setCategory3(E3 Category3) {
		this.category3 = Category3;
	}
}
