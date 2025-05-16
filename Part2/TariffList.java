package Part2;

import java.util.NoSuchElementException;

public class TariffList implements TariffPolicy {

//	Inner Node class
	private class TariffNode implements Cloneable{
		private Tariff data;
		private TariffNode next;

//		Default Constructor
		public TariffNode() {
			this.data = data;
			this.next = next;
		}

//		Parameterized Constructor
		public TariffNode(Tariff data, TariffNode next) {
			this.data = data;
			this.next = next;
		}

//		Copy Contructor(Deep Copy)
		public TariffNode(TariffNode other) {
//			If the object passed is null, there is nothing to copy
			if (other == null) {
				this.data = null;
				this.next = null;
			} else {
				this.data = other.data.clone();
				this.next = other.next;
			}
		}

		public Tariff getData() {
			return data;
		}

		public TariffNode getNext() {
			return next;
		}

		public void setData(Tariff data) {
			this.data = data;
		}

		public void setNext(TariffNode next) {
			this.next = next;
		}

//		Clone Method
		@Override
		public Object clone() throws CloneNotSupportedException {
			TariffNode copy = (TariffNode) super.clone();
			copy.data = (Tariff) this.data.clone();
			copy.next = (TariffNode) this.next.clone();
			
			return copy;
		
		}
		
		public String toString() {
			return "Data is: " +data;
		}

		public boolean equals (Object otherObject) {
			TariffNode otherData = (TariffNode) otherObject;
			return this.data.equals(otherData.data);
		}
	}
	
//	Tariff List class

	private TariffNode head;
	private TariffNode tail;
	private int size;

//	Default Constructor
	public TariffList() {
		this.head = null;
		this.tail = null;
		this.size = 0;
	}

//  Copy Constructor	
	public TariffList(TariffList other) {
	    if (other.head == null) {
	        this.head = null;
	        this.tail = null;
	        this.size = 0;
	    } else {
	        this.head = new TariffNode(other.head.data.clone(), null);
	        TariffNode currentOther = other.head.next;
	        TariffNode currentThis = this.head;
	        
	        while (currentOther != null) {
	            TariffNode newNode = new TariffNode(currentOther.data.clone(), null);
	            currentThis.next = newNode;
	            currentThis = newNode;
	            currentOther = currentOther.next;
	        }	        
	        this.tail = currentThis;
	        this.size = other.size;
	    }
	}

//	Getters

	public TariffNode getHead() {
		return head;
	}

	public TariffNode getTail() {
		return tail;
	}

	public int getSize() {
		return size;
	}

//  setters
	public void setHead(TariffNode head) {
		this.head = head;
	}

	public void setTail(TariffNode tail) {
		this.tail = tail;
	}

	public void setSize(int size) {
		this.size = size;
	}

	// addToStart method
	public void addToStart(Tariff data) {
		// Check for duplicates before adding
		if (contains(data.getOriginCountry(), data.getDestinationCountry(), data.getProductCategory())) {
			return; // Don't add it if it already exists in the list
		}

		head = new TariffNode(data, head);
		size++;

//	    if this is the first element, adjust tail
		if (size == 1)
			tail = head;
	}

//	Method to insert at specific index
	public void insertAtIndex(Tariff obj, int index) {
//		check for invalid index
		if (index < 0 || index > size - 1) {
			throw new NoSuchElementException("Invalid index: " + index);
		}

//		make a new node with the obj passed
		TariffNode newNode = new TariffNode();
		newNode.setData(obj);

//		If inserting at head
		if (index == 0) {
			newNode.setNext(head);
			head = newNode;
		} else {
//			pointer starting at head
			TariffNode current = head;
			for (int i = 0; i < index - 1; i++) {
				current = current.getNext();
			}
//			new node pointing to the node which was after current
			newNode.setNext(current.getNext());
//			current points to new node
			current.setNext(newNode);
//			so it is added between the current and current.next at the supposed index
			size++;
		}
	}

//	Method to delete from certain index
	public void deleteFromIndex(int index) {
//		check for invalid index
		if (index < 0 || index > size - 1) {
			throw new NoSuchElementException("Invalid index: " + index);
		}

//		If the list is empty
		if (head == null)
			System.out.println("The list is empty.");

		// Special case: removing the head
		if (index == 0) {
			deleteFromStart();
		}

		TariffNode position = head;
		for (int i = 0; i < index - 1; i++) {
			position = position.next;
		}

		position.setNext(position.getNext().getNext());
		size--;
	}

//	Method to delete from start
	public Tariff deleteFromStart() {
//		Removing the first element, so head will change
		if (head == null)
			throw new NoSuchElementException("Cannot delete from an empty list");

		Tariff value = head.data;
//		changing the head to the one after
		head = head.next;
		size--;

//		Check if after removing the list is empty
		if (size == 0)
			tail = null;

		return value;
	}

//	method to replace a node at certain index
	public void replaceAtIndex(Tariff obj, int index) {
//		check for invalid index
		if (index < 0 || index > size - 1) {
			return;
		}

		TariffNode position = head;
		for (int i = 0; i < index; i++) {
			position = position.next;
		}

		position.setData(obj);
	}


	
	public Tariff find(String origin, String destination, String category) {
	    TariffNode position = head;
	    int iterations = 0;

	    while (position != null) {
	        iterations++;
	        Tariff data = position.data;

	        if (data.getOriginCountry().equals(origin) &&
	            data.getDestinationCountry().equals(destination) &&
	            data.getProductCategory().equals(category)) {

	            System.out.println("Iterations made: " + iterations);
	            return data;  
	        }
	        position = position.next;
	    }

	    System.out.println("Iterations made: " + iterations);
	    return null;
	}

//	Contains method
	public boolean contains(String origin, String destination, String category) {
//		Check if the list is null
		if (head == null)
			return false;

		TariffNode position = head;

		while (position != null) {
			Tariff tariff = position.getData();

			if (tariff.getOriginCountry().equals(origin) && tariff.getDestinationCountry().equals(destination)
					&& tariff.getProductCategory().equals(category)) {
				return true;
			}

//			Move to next node
			position = position.next;
		}
		return false;
	}

//	equals method to check if nodes in both lists are identical
	public boolean equals(TariffList other) {
	    if (other == null)
	        return false;

	    TariffNode currentNode = this.head;
	    TariffNode otherNode = other.head;

	    // iterate through the list and check each node
	    while (currentNode != null || otherNode != null) {
	        if (!currentNode.getData().equals(otherNode.getData()))
	            return false;

	        currentNode = currentNode.next;
	        otherNode = otherNode.next;
	    }

	    // If one list is longer than the other
	   

	    return true;
	}
	
	public void display() {
	    if (head == null) {
	        System.out.println("The list is empty.");
	        return;
	    }

	    TariffNode position = head;

	    while (position != null) {
	        System.out.println(position.data.toString());  
	        position = position.next;
	    }

	    System.out.println("");
	}



	
	@Override
	public String evaluateTrade(double proposedTariff, double minimumTariff) {
		
		double bounds = minimumTariff*0.20;
		
		if (proposedTariff >=(minimumTariff)) {
			return "Accepted";
					
		}
		
		if (proposedTariff<(minimumTariff)&&proposedTariff>=(minimumTariff-bounds)) {
			return "Conditionally Accepted";
					
		}
		
		else return "Rejected";
		
	
	}

}
