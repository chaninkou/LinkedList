package application;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import javax.swing.JFileChooser;

public class LinkedList<E1, E2, E3> {
	// will store a pointer to the first Node in the LinkedList
	protected Node<E1, E2, E3> head;
	
	// will store the total number of elements in the list 
	private int size = 0;
	
	// will store the label for category1 from the Node class
	protected String category1Label;
	
	// will store the label for category2 from the Node class
	protected String category2Label;
	
	// which will store the label for category1 from the Node class
	protected String category3Label;
	
	// will store the current category number upon which the list should be grouped. The default grouping category is category1.
	private int groupingCategory = 1;
	
	// This class shall have one no-arg constructor which creates an empty list.
	public LinkedList() {
		this.head = null;
	}
	
	// This class shall have one constructor which takes an integer parameter, which is the number of the current category upon which you want your list to group. 
	// This constructor should also create an empty list.
	public LinkedList(int groupingCateogry1) {
		this.groupingCategory = groupingCateogry1;
		this.head = null;
	}
	
	// This class shall have one constructor which takes a File object parameter and an integer parameter.  This File is the input file from which a list can be generated and populated.  The integer parameter is the current grouping property.  
	// This constructor should take the File and create a list based on the values in the File.
	public LinkedList(File object, int parameter){
		String line;
		String[] fields;
		int i = 0;
			try {
				Scanner freader = new Scanner(object);
				this.groupingCategory = parameter;
				while (freader.hasNextLine()) {
					if(i == 0){
						category1Label = freader.nextLine();
					}
					if(i == 1){
						category2Label = freader.nextLine();
					}
					if(i == 2){
						category3Label = freader.nextLine();
					}
					if(i > 3){
  				    line = freader.nextLine();
					fields = line.split(", ");
					if(i > 4){
						add((E1)fields[0], (E2)fields[1], (E3)fields[2]);				
					}
					}
					i++;
				}
				freader.close(); // Close to unlock.
			} catch (IOException e) {
				e.printStackTrace();
			}
	}
	
	
	// This method shall have three parameters. These parameters are the values of the categories in a Node.
	// Use the parameters to create a new Node and add it to the list.
	// The Node must be added in such a way that it maintains the current grouping category of the list.
	public void add(E1 value1, E2 value2, E3 value3){
		Node<E1,E2,E3> newLink = new Node<E1,E2,E3>(value1, value2,value3);
		
		if(this.size == 0){
			this.head = newLink;
		}
		else{
			Node<E1,E2,E3> temp = this.head;
			while(temp.right != null){
				temp.left = temp;
				temp = temp.right;
			}
			temp.right = newLink;
			newLink.left = temp;
		}
		this.size++;
	}
	
	// This method shall clear the list.
	public void clear() {
		this.head = null;
		this.size = 0;
	}
	
	// This method shall delete the first Node in the main list.
	// This method shall NOT delete any nodes in the first Node's sublist.  The remaining Nodes in the sublist should be "reattached" to the beginning of the main list.
	public void deleteFirst() {
		if (this.size == 0) {
			System.out.println("The list is empty");
		}
		else {
			head = head.right;
			head.left = null;
			size--;
		}
	}
	
	// This method shall delete the last Node in the main list.
	// This method shall NOT delete any nodes in the last Node's sublist.  The remaining Nodes in the sublist should be "reattached" to the end of the main list.
	public void deleteLast() {
		if (this.size == 0) {
			System.out.println("The list is empty");
		}
		else{
			Node current = head;
			Node temp = current.left;
		while(current.right != null){
			temp = current;
			current = current.right;
		}
			temp.right = null;
			size--;
		}
	}
	
	// This method shall delete a specific node from anywhere in the list, given the mainIndex and subIndex.
	// This method should ONLY delete the requested Node and should "reconnect" any Nodes that may be attached to the deleted Node.
	public void delete(int mainIndex, int subIndex){
		if(this.size == 0){
			System.out.println("The list is empty");
		}
		if(mainIndex < 0 || mainIndex > size){
			System.out.println("The list is empty");
		}
		else{
			Node current = head;
			Node temp = current;
			int i = 0;
			int j = 0;
			while(i < mainIndex){
				
				temp = current.left;
				current = current.right;
				
				i++;
			}
			if(subIndex == 0){
				temp.right = current.right;
			}
			else if(subIndex > 0){
				while(j < subIndex){
					temp = current;
					current = current.down;
				}
				temp.down = current.down;
			}
		}
	}
	
	
	// This method shall have an integer parameter, mainIndex, which is an index (starting from 0) which indicates the Node from the main branch you want to retrieve.
	// This method shall have another integer parameter, category, which is a category number (1 - 3) that will indicate which category's value you want to retrieve.  
	// This method shall return the chosen category value, from the chosen Node in the main list.  The category value should be returned as a String.
	// If the index is out of bounds, this method should throw an IndexOutOfBoundsException with an appropriate error message indicating whether it was the main index or category value that was out of bounds.
	public String get(int category, int mainIndex){
		if(this.size == 0){
			System.out.println("The list is empty");
		}
		if(mainIndex < 0 || mainIndex > size - 1){
			System.out.println("The list is empty");
		}
		else{
			Node current = head;
			int i = 0;
			while(i < mainIndex){
				current = current.right;
				i++;
			}
			if(category == 1){
				return current.getCategory1() + "";
				
			}
			if(category == 2){
				return current.getCategory2() + "";
				
			}
			if(category == 3){
				return current.getCategory3() + "";
			}
		}
		return "";
	}
		
	
	// This method shall have an integer parameter, mainIndex, which is an index (starting from 0) which indicates the Node from the main branch you want to retrieve.
	// This method shall have an integer parameter, subIndex, which is an index (starting from 0) which indicates the Node from the sub-list of the chosen main branch Node.
	// This method shall have another integer parameter, category, which is a category number (1 - 3) that will indicate which category's value you want to retrieve.
	// This method shall return the value from the chosen category, in the indicated sub-list.  The category value should be returned as a String.
	// If any of the parameters are out of bounds, this method should throw an IndexOutOfBoundsException with an appropriate error message indicating whether it was the main index, sub index or category value that was out of bounds.
	public String get(int mainIndex, int subIndex, int category){
		
		return null;
	}
	
	
	
	//This method shall be responsible for regrouping your LinkedList based on the given regrouping category number.
	public void regroup(int groupingCategoryNumber){
		Node<E1,E2,E3> current = this.head;
		Node<E1,E2,E3> c = this.head;
		Node<E1,E2,E3> temp = current;
		Node d = current;
		int i = 0;
		
		if(groupingCategoryNumber == 1){
		while(c != null){
			while(current != null){
				if(c.getCategory1().equals(current.getCategory1()) && c != current)
				{
					if(c.down != null){
						while(d.down != null){
							d = d.down;
						}
						d.down = current;
						delete(i, 0);
						current = current.right;
					}
					else if(c.down == null){
						d = c.down;
						delete(i,0);
						d = current;
						current = current.right;
					}
				}
				else{
					current = current.right;
					i++;
				}
					c.down = current;
					temp.right = current.right;
				
			}
				current = head;
				c = c.right;
				i = 0;
			}
		}
		if(groupingCategoryNumber == 2){
			while(c != null){
				while(current != null){
					if(c.getCategory2().equals(current.getCategory2()) && c != current)
					{
						if(c.down != null){
							while(d.down != null){
								d = d.down;
							}
							d.down = current;
							delete(i, 0);
							current = current.right;
						}
						else if(c.down == null){
							d = c.down;
							delete(i,0);
							d = current;
							current = current.right;
						}
					}
					else{
						current = current.right;
						i++;
					}
						c.down = current;
						temp.right = current.right;
					
				}
					current = head;
					c = c.right;
					i = 0;
				}
			}
		if(groupingCategoryNumber == 3){
			while(c != null){
				while(current != null){
					if(c.getCategory3().equals(current.getCategory3()) && c != current)
					{
						if(c.down != null){
							while(d.down != null){
								d = d.down;
							}
							d.down = current;
							delete(i, 0);
							current = current.right;
						}
						else if(c.down == null){
							d = c.down;
							delete(i,0);
							d = current;
							current = current.right;
						}
					}
					else{
						current = current.right;
						i++;
					}
						c.down = current;
						temp.right = current.right;
					
				}
					current = head;
					c = c.right;
					i = 0;
				}
			}
		}
	
	// This method shall return the size of the main list.
	// The size is the number of nodes in the main list.
	public int size() {
		return this.size;
	}
	
	// This method shall return the size of the sub-list at the given index.
	// If the given index is out of bounds, this method should throw an IndexOutOfBoundsException with an appropriate error message.
	public int size(int index){
		return index;
	}
}
