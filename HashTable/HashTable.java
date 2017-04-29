
import java.lang.Math;
public class HashTable {
	      int size;
	      int size(){return this.size;}
	      DoubleLinkedList[] table;
	      static double n=0;
	      HashTable() {
	    	    this.size = 4;
	            table = new DoubleLinkedList[size];
	            for (int i = 0; i < size; i++)
	                  table[i] = null;
	      }
	      
	        HashTable(int number) {
	    	    this.size = number;
	            table = new DoubleLinkedList[size];
	            for (int i = 0; i < size; i++)
	                  table[i] = null;
	      }

	        
	       public synchronized Object get(Object key) {
	           int hash = key.hashCode()%this.size;
	            if (table[hash] == null){
	            	//System.out.println("can't find this key");
	                 return null;
	            }
	            else{
	            	Object v=table[hash].searchNode(key);
	            	if(v==null){
	            		//System.out.println("can't find this key");
	            		return null;
	            	}
	            	return v;
	            }
	      }

	       public synchronized void put(Object key, Object value) {
	            int hash = (key.hashCode())%(this.size);
	            
	            if (table[hash] != null) {
	            	if(!table[hash].searchswap(key,value)){
	            		//System.out.println("same_hashcode");
	            		table[hash].add(key,value);
	            	    n++;
	            	}
	            }
	            
	            else if(table[hash] == null){
	                table[hash] = new DoubleLinkedList();
		            table[hash].add(key,value);
		            n++;
	            }
	            
	            rehash();
	            }
	      
public boolean isCollide(Object key, Object value){
    int index = key.hashCode()%this.size;
    if (this.table[index]==null) {
    	return false;
    	}
    else return true;
}


public  void rehash(){
	double v = this.n/this.size;
	if (v>0.75){
		int oldsize = this.size;
		this.size=oldsize*2+1;
		DoubleLinkedList oldTable[] = this.table;
		this.table=new DoubleLinkedList[size];
		for (int i=0;i<oldsize;i++) {
			if(oldTable[i]!=null){
				ListNode node=oldTable[i].head.next;
				while (node!= null && node.key!=null && node.value!=null){
					this.put(node.key,node.value);
					node = node.next;
		        }
		     }
	     }
      }
   }

class ListNode{
	ListNode prev= this;
	ListNode next= this;
    Object key;
    Object value;
     int g=0;
	ListNode(Object key, Object value) {	            
		this.key = key;
		this.value = value;
		}
}
   
  class DoubleLinkedList
{
ListNode head = new ListNode(null,null);
ListNode tail = new ListNode(null,null);
int size;

public void add(Object key,Object value)
{
	ListNode newNode = new ListNode(key,value);
	if(head.next.key==null&&tail.prev.key==null){ 
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
}

boolean searchswap(Object key,Object value){
	ListNode node = head.next;
	for (int i = 0; i < size; i++) {    
		 if (node.key.equals(key)){
		  node.value=value;
		  return true;
	  }
	  else { 
		  node = node.next; 
		 }
	}
		return false;
	}


    Object searchNode(Object key){
	ListNode node = head.next;
	for (int i = 0; i < size; i++) {    
		 if (node.key.equals(key)){
		  return node.value;
	  }
	  else { 
		  node = node.next; 
		 }
		
	}
		return null;
	}

}
}
	  
	

  
