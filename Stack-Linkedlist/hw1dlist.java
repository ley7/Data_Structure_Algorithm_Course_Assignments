/* Name Le Yu
 * The exercise results and the answer to Question 3 are at the end of typescript 1.2 Doubly Linked List
 */
public class hw1dlist
{
ListNode head = new ListNode(null);
ListNode tail = new ListNode(null);
int size;

static class ListNode{
	ListNode prev= this;
	ListNode next= this;
	Object data;
	ListNode(Object Data) {this.data = Data;}
}

public boolean add(Object o)
{
	ListNode newNode = new ListNode(o);
	if(head.next.data==null&&tail.prev.data==null){ 
		newNode.next = tail;
	    newNode.prev = head;
	    newNode.next.prev = newNode;
	    newNode.prev.next = newNode;
	}
	else{
    newNode.next = tail;
    newNode.prev = tail.prev;
    newNode.next.prev = newNode;
    newNode.prev.next = newNode;
	} 
    size++;
  return true;
}

private ListNode FirstNode(){
	return head.next;
}

private ListNode LastNode(){
	return tail.prev;
}

private ListNode prevNode(ListNode node){
	ListNode n = searchNode(node);
	  if (n==null) {
		System.out.println( "the prevnode is null");
		return head;
	  }
	  else  return n.prev;
}

private ListNode nextNode(ListNode node){
	ListNode n = searchNode(node);
	  if (n==null) {
		System.out.println( "the nextnode is null");
		return head;
	  }
	  else  return n.next;
}

private ListNode searchNode(ListNode Node){
	ListNode node = head.next;
	ListNode n=new ListNode(null);
	int flag=0;
	for (int i = 0; i < size; i++) {
		int p=Integer.parseInt(String.valueOf(Node.data)); 
		int q=Integer.parseInt(String.valueOf(node.data));   
		 if (p==q){
		  flag=1;
		  n=node;
	  }
	  else { 
		  node = node.next; 
		 }
		
	}
	if(flag==0){
		System.out.println( "can't find the node!");
		System.out.println( Node.data);
		return head;
	}
	else{
		//System.out.println( "find the node!");
		return n;
	}
	}

private void deleteNode(ListNode node){
		ListNode n = searchNode(node);
		//System.out.println( "remove:"+n.data);
		n.prev.next = n.next;
		n.next.prev = n.prev;
		size--;
}

private void insertNode(ListNode node,ListNode newnode){
	ListNode n = searchNode(node);
	newnode.next=n.next;
	n.next = newnode;
        newnode.next.prev=newnode;
	newnode.prev = n;
	//System.out.println( "insert:"+newnode.data+" after "+node.data);
	size++;
}

public String toString()
{
	StringBuffer s = new StringBuffer();
	ListNode node = head;
	for (int i = 0; i < size; i++)
	{
		node = node.next;
		if (i > 0)
			s.append(", ");
			s.append(node.data);
  }
  return s.toString();
}

public static void main(String[] args)
{	
  hw1dlist d1 = new hw1dlist();
  hw1dlist d2 = new hw1dlist();
  long n =10;
  int m=1000;
 
  int x=(int)(Math.random()*n);
 // System.out.println(d);
  ListNode node1 =new ListNode(x) ;
  
  //Question 1
  System.out.println("--Question1--"); 
    for(int i=0;i<m;i++){
   d1.add(i);
  }
  System.out.println( "FirstNode:"+d1.FirstNode().data);
  System.out.println( "LastNode:"+d1.LastNode().data);
  long start = System.nanoTime();
  for(int j = 0; j < m; j++){
	  int y=(int)(Math.random()*m);
	  ListNode node3 =new ListNode(y) ;
	  d1.searchNode(node3);
  }
  long end = System.nanoTime();
  long time = end-start;
  long average_time=time/m;
  System.out.println("m="+m);
  System.out.println("total_time="+time); 
  System.out.println("search_average_time="+average_time); 
  System.out.println(d1.searchNode(node1).data+"'"+"nextNode:"+d1.nextNode(node1).data);
  System.out.println(d1.searchNode(node1).data+"'"+"prevNode:"+d1.prevNode(node1).data);
 
 
    // Question 2
  System.out.println("--Question2--"); 
  boolean flag=true;
  do{  
	  for(int i=0;i<n;i++){
	   d2.add(i);
	  }
	  long start2 = System.nanoTime();
	  for(int j = 0; j < n; j++){
	  int z=(int)(Math.random()*n);
      int q=(int)(Math.random()*n);
	  ListNode node2 =new ListNode(z) ;
	  ListNode node4 =new ListNode(q) ;
	  d2.insertNode(node4,node2);
	  d2.deleteNode(node2);
	  //System.out.println(d);
	 
	  //System.out.println(d);
  }
  long end2 = System.nanoTime();
  long time2 = end2-start2;
  long average_time2=time2/n;
  System.out.println("n="+n);
  System.out.println("insert&delete_average_time="+average_time2); 
  System.out.println("total_time="+time2); 
  n=n*10L;
  if(time2>10000000000L){
	  	flag=false;
	  }
	} while(flag);

}
}


