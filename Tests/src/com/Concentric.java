package com;

public class Concentric {


	public static void main(String[] args) {

		
		Linkedlist ll=new Linkedlist();
		ll.add(new Node(1));
		ll.add(new Node(2));
		ll.add(new Node(3));
		ll.add(new Node(4));
		ll.add(new Node(5));
		ll.print();

	}
//1->2>3>4>5->
	

}
class Node{
	Node next;
	int val;
	Node(int val){
		this.val=val;
		this.next=null;
	}
	
}

 class Linkedlist{
	 private Node head;
	
	public void add(Node newNode) {
		if(head==null) {
			head=newNode;
			head.next=null;
		}else {
			Node temp=head;
			while(temp.next!=null) {
				temp=temp.next;
				
			}
			temp.next=newNode;
		}
		
	}

	public void print() {
		Node temp=head;
		while(temp!=null) {
			System.out.println(temp.val);
			temp=temp.next;
		}
		
	}
	
	
}

