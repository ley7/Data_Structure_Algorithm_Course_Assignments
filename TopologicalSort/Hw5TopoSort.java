import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.HashMap;
import java.util.*;
 
public class Hw5TopoSort {
	static class Vertex {  
	    private String value;  
	    private Color color;  
	    private Vertex parent;  
	    private int discover;  //discover time
	    private int finish;  //finish time
	    public Vertex(String value) {  
	        this.value = value;  
	        this.color = Color.WHITE;  
	    }    

	    public void setValue(String value) {  
	        this.value = value;  
	    }  
	  
	    public void setColor(Color color) {  
	        this.color = color;  
	    }  
	  
	    public void setDiscover(int discover) {  
	        this.discover = discover;  
	    }  
	  
	    public void setFinish(int finish) {  
	        this.finish = finish;  
	    }  
	  
		
	    
	    @Override  
	    public int hashCode() {  
	        final int prime = 31;  
	        int result = 1;  
	        result = prime * result + 3;  
	        return result;  
	    }  
	 
	    @Override  
	    public boolean equals(Object obj) {  
	        if (this.value == obj)  
	            return true;  
	        if (obj == null)  
	            return false;  
	        if (getClass() != obj.getClass())  
	            return false;  
	        Vertex other = (Vertex) obj;  
	        if (this.value == null) {  
	            if (other.value != null)  
	                return false;  
	        } else if (!this.value.equals(other.value))  
	            return false;  
	        return true;  
	    }    

	}  
	
	enum Color {  
	    WHITE, GRAY, BLACK  
	}  
	
	static class Graph {  
	    private Map<Vertex, Vertex> vertexSet = new HashMap<Vertex, Vertex>();   
	    private Map<Vertex, ArrayList<Vertex>> adjacencys = new HashMap<Vertex, ArrayList<Vertex>>();    
	    public void setVertexSet(Map<Vertex, Vertex> vertexSet) {  
	        this.vertexSet = vertexSet;  
	    }  
	  
	    public void setAdjacencys(Map<Vertex, ArrayList<Vertex>> adjacencys) {  
	        this.adjacencys = adjacencys;  
	    } 
	}  
	
	static class TimeRecorder {  
	    private int time = 0;  
	    public void setTime(int time) {  
	        this.time = time;  
	    }  
	}  
	  
	public static Vertex[] topologicalSort(Graph graph) throws IOException {  
		Map<Vertex, Vertex> vertexSet = graph.vertexSet;  
	    if (vertexSet.size() < 2) {  
	        return vertexSet.keySet().toArray(new Vertex[0]);
	    }  
	    LinkedList<Vertex> sortedList = new LinkedList<Vertex>();  
	    TimeRecorder recorder = new TimeRecorder();  
	    for (Vertex vertex : vertexSet.keySet()) {  
	        if (vertex.color == Color.WHITE) {  
	            visitVertex(graph, vertex, recorder, sortedList);  
	        }  
	    }  
	    return sortedList.toArray(new Vertex[0]);  
	}  
	   
	public static void visitVertex(Graph graph, Vertex vertex,  
	    TimeRecorder recorder, LinkedList<Vertex> sortedList) {  
	    recorder.time += 1;  
	    vertex.color = Color.GRAY;  
	    vertex.discover = recorder.time;  
	    Map<Vertex, ArrayList<Vertex>> edgeMap = graph.adjacencys;  
	    ArrayList<Vertex> adjacencys = edgeMap.get(vertex);  
	    if (adjacencys != null && adjacencys.size() > 0) {  
	        for (Vertex adjacency : adjacencys) {  
	            if (adjacency.color == Color.WHITE) {  
	                adjacency.parent = vertex;  
	                visitVertex(graph, adjacency, recorder, sortedList);  
	            }  
		        if (adjacency.color == Color.GRAY){
		        flag=false;
	            break;
	        }
	        }  
	    }  
	    recorder.time += 1;  
	    vertex.color = Color.BLACK;  
	    vertex.finish = recorder.time;  
	    sortedList.addLast(vertex); 
	}  
	
	public static String toString(Vertex[] t){
		StringBuffer s = new StringBuffer();
		Vertex[] toposort=t;
	       for (int i=toposort.length-1;i>=0;i--) {  
	   		if (i < toposort.length-1)
				s.append(",");
				s.append(toposort[i].value);
		        }
	       return s.toString();
	}
	static boolean flag=true;
	public static void main(String[] args) throws IOException {  
        Graph graph = new Graph();  
        Map<Vertex, Vertex> vertexSet = graph.vertexSet;  
        Map<Vertex, ArrayList<Vertex>> edgeMap = graph.adjacencys;  
        BufferedReader br = null;
      		try {
      			String sCurrentLine;
      			br = new BufferedReader(new FileReader("/users/ugrad/2016/fall/ley7/hw5/graphdata.txt"));
      			while ((sCurrentLine = br.readLine()) != null) {
      				String[] tokens = sCurrentLine.split(" ");
      				String S1 = tokens[0];
                    String S2 = tokens[1];
                    Vertex W = new Vertex(S1);
                    Vertex E = new Vertex(S2);              
                	if(edgeMap.containsKey(W)){
                		if(!vertexSet.containsKey(E)){
                        vertexSet.put(E,E);
                		}
                        ArrayList<Vertex> temp=edgeMap.get(W);
                		temp.add(vertexSet.get(E));
                		edgeMap.put(W, temp);
      			}
                	else {
                		if(!vertexSet.containsKey(E)){
                			vertexSet.put(E,E);
                    		}
                		if(!vertexSet.containsKey(W)){
                			vertexSet.put(W,W);
                    		}
                		ArrayList<Vertex> temp=new ArrayList<Vertex>();
                		temp.add(vertexSet.get(E));
                		edgeMap.put(W, temp);  
                	}
      			}
      		} catch (IOException e) {
      			e.printStackTrace();
      		} finally {
      			try {
      				if (br != null)br.close();
      			} catch (IOException ex) {
      				ex.printStackTrace();
      			}
      		}
				Vertex[] toposort = topologicalSort(graph); 
	  		        if(flag){
	  		        System.out.println("result");
				    System.out.println(toString(toposort));
	  		       }
				else System.out.println("This graph has a cycle");
	    }  
    }  