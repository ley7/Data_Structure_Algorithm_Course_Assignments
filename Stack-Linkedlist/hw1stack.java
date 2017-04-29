/* Name Le Yu
 *exercise results are at the end of typescript 1.1 Stack 
 * push operation is to do the operation to add a node after the last node(top) and the operation of pop is to delete the last node.So the complexity of 
 *the push and pop is O(1)
*/
public class hw1stack{
   long size = 0;   
   public hw1stack() {
	    top=bottom= null;
	  }
   Node top;
   Node bottom;
   static class Node{
		 Object item;  
		 Node prev;  
		 Node(Object Item){  
		 this.item = Item;  
			 } 
	} 
   
   public void push(Object o) {  
       Node newnode=new Node(o);
       if(top == null){
    	   bottom=newnode;
    	   top = newnode;
    	/*   System.out.println("top(" + top.item + ")");
    	   System.out.println("bottom(" + bottom.item + ")");*/
    	   size++;
       }
       else {
       newnode.prev=top;
       top = newnode;
     //  System.out.println("push(" + top.item + ")");
       size++;
       }
   }  

   public void pop() {
	    if (top == null) {  
	    	    System.out.println("the hw1stack is empty!");
        }  
	   else {  
	       if(top==bottom){
	    	  top=bottom=null;
	    	  size--;
	    	  }
	       else {
	  	       Node n=top;
		       top=null;
	           top=n.prev;
	           size--;
	       	   }
	   }
   }
   
   public static void main(String[] args) {  
	   hw1stack stack = new hw1stack();
	    long t=0;
	    long n =10L;   
	    long m=1000L;
	    long start = System.nanoTime();

        //Question 1
	         System.out.println("--Question1--"); 
	    	for(long i = 0; i < m; i++){
	    	 if(i<n-1){
			 	   stack.push(i); 
			 	   stack.pop();
			 	   }
		 	   else{
				   long start2 = System.nanoTime();
		    	   stack.push(i); 
		    	   stack.pop();
				   long end2 = System.nanoTime();
			       long time = end2-start2;
			       //System.out.println("last_push&pop_time="+time);
			       t=time+t;
			 	   }
		 	   }
	    long T=t/m;
	    System.out.println("m="+m);
	  System.out.println("average_last_push&pop_time="+T);
	    
	 //  Question 2
	  System.out.println("--Question2--"); 
        boolean flag=true;
	    do {
	   	for (long i = 0; i < n; i++) {       
			 	   stack.push(i); 
			 	   stack.pop();
			 	   }
	    long end = System.nanoTime();
	    long total_time = end-start;
    	long total_average_time = (end-start)/n;  
    	System.out.println("n="+n);
    	System.out.println("average_push&pop_time="+total_average_time); 
	    System.out.println("total_time="+total_time); 
	    n=n*10L;
	    if(total_time>10000000000L){
	    	flag=false;
	    }
	} while(flag);
	
   }
}
   
