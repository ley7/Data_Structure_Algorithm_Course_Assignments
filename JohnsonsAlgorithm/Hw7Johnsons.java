import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.PriorityQueue;
public class Hw7Johnsons { 
	   static final int MAX = 27;  
	    static int[][] g;  
	    static int[] h = new int[MAX];      
	    static PriorityQueue<Elem> Q = new PriorityQueue<Elem>(); //Q = V-S    
	    static ArrayList<Elem> nodes = new ArrayList<Elem>();  
	    static int[][] D;  
	      
	    static int ver;   
	    static int edge;  
	    static final int BELLMAN_FORD = 1;  
	    static final int DIJKSTRA = 2;  
	    static class Elem implements Comparable<Elem>    
	    {    
	        public int s; 
	        public int d;   
	        public Elem(int s,int d){    
	                this.s = s;    
	                this.d = d;    
	        }  
	        public int compareTo(Elem e){return d - e.d;}    
	    }  
 
     static void johnson(){  
        int s = ver; 
        int[][] g_new = new int[ver+1][ver+1];  
        for(int u = 0;u < g_new.length;u++){  
            for(int v = 0;v < g_new.length;v++){  
                if(v == g.length){g_new[u][v] = Integer.MAX_VALUE;continue;}  
                if(u == g.length){g_new[u][v] = 0; continue;}  
                g_new[u][v] = g[u][v];  
            }  
        }  
          
        if(bellman_ford(g_new,s) == false) {System.out.println("exist circle");return;}  

        for(Elem e:nodes) h[e.s] = e.d;   
        for(int u = 0;u < ver;u++){  
            for(int v = 0;v < ver;v++){  
                if(g[u][v] == Integer.MAX_VALUE) continue;  
                g[u][v] = g[u][v] + h[u]-h[v];  
            }  
        }   
        
        initD(g);  
        for(int u = 0;u < ver;u++){  
            dijkstra(g,u);  
            for(int v = 0;v < ver;v++){  
                if(nodes.get(v).d == Integer.MAX_VALUE) continue;  
                D[u][v] = nodes.get(v).d + h[v] - h[u];  
                //D[u][v] = nodes.get(v).d;  
            }  
        }  
          
        System.out.println("shortest path:");
 		System.out.print("\t");
		for(int u = 0;u < 26;u++){
		if(lines.contains(u)){
			char c=(char)((Integer)u+97);
			System.out.print(c+"\t");
		}
		}  
   	 	Object[] nums=  lines.toArray();
   	 	System.out.print("\t");
   	 	System.out.println();
   	 	for(int u = 0;u < 26;u++){  
   	 		if(lines.contains(u)){
   	 			char c=(char)((Integer)u+97);
   	 			System.out.print(c+"\t");
   	 		}
   	 		for(int v = 0;v < 26;v++){ 
   	 			if(lines.contains(u) && lines.contains(v)){
   	 				if(D[u][v] == Integer.MAX_VALUE){System.out.print("!\t"); continue;}  
   	 				System.out.print(D[u][v]+"\t");  
   	 			}
   	 		}
   	 		if(lines.contains(u))
   	 			System.out.println();  
   	 	}  
     }  
    
    static int Size=0;
    static HashSet<Integer> lines=new HashSet<Integer>();
    
    static void init(int[][] g,int source,int mode){   
        nodes.clear();   
        for(int i=0; i < g.length;i++){    
            Elem e = new Elem(i,Integer.MAX_VALUE);    
            nodes.add(e);    
            if(i == source && mode == DIJKSTRA) Q.add(e);  
        }  
        nodes.get(source).d = 0;  
    }  
     static void initD(int[][] g){      
        for(int i = 0;i < g.length;i++)      
            for(int j = 0;j < g.length;j++){      
                D[i][j] = g[i][j];      
            }      
        }      
     
     static boolean bellman_ford(int[][] g,int source){      
        init(g,source,BELLMAN_FORD);     
        int s=0,e=0,w=0;        
        for(int i=0;i < g.length-1;i++){      
            for(int u = 0;u < g.length;u++){  
                for(int v = 0;v < g.length;v++){  
                    if(g[u][v] == Integer.MAX_VALUE||nodes.get(u).d == Integer.MAX_VALUE) continue;  
                    nodes.get(v).d = Math.min(nodes.get(v).d, nodes.get(u).d + g[u][v]);  
                }  
            }  
        }          
        for(int u = 0;u < g.length;u++){  
            for(int v = 0;v < g.length;v++){   
                if(g[u][v] == Integer.MAX_VALUE||nodes.get(u).d == Integer.MAX_VALUE) continue;  
                if(nodes.get(v).d > nodes.get(u).d + g[u][v]) return false;  
            }  
        }      
        return true;      
    }  
      
     static void dijkstra(int[][] g,int source){    
        init(g,source,DIJKSTRA);  
        while(Q.size() > 0){    
            Elem u = Q.poll();        
            for(int v = 0;v < g.length;v++){    
            if(g[u.s][v] != Integer.MAX_VALUE && nodes.get(v).d > u.d + g[u.s][v]){    
                Elem nv = nodes.get(v);        
                Q.remove(nv);    
                nv.d = u.d + g[u.s][v];    
                Q.offer(nv);    
            }    
            }    
        }  
    }  
     
    public static void main(String[] args){         
        ver = 26;       
        g  = new int[ver][ver];  
        D = new int[ver+1][ver+1];  
        for(int i = 0;i < ver;i++){  
            for(int j = 0;j < ver;j++) {g[i][j] = Integer.MAX_VALUE;}  
        } 
    
        BufferedReader br = null;
      		try {
      			String sCurrentLine;
      			br = new BufferedReader(new FileReader("johnsonsGraphData.txt"));
      			while ((sCurrentLine = br.readLine()) != null) {
      				String[] tokens = sCurrentLine.split(" ");
      				String S1 = tokens[0];
                    String S2 = tokens[1];
                    String S3 = tokens[2];
                    
                    char c1=S1.toCharArray()[0];
                    char c2=S2.toCharArray()[0];
                    
                    int s=(int)c1-97;
                    int e=(int)c2-97;
                    int w=Integer.parseInt(S3);
                    
                    lines.add(s);
                    lines.add(e);
                    g[s][e] = w;
                    Size++;
      			}
      		} catch (IOException e1) {
      			e1.printStackTrace();
      		} finally {
      			try {
      				if (br != null)br.close();
      			} catch (IOException ex) {
      				ex.printStackTrace();
      			}
      		} 
        johnson();  
    }  
}  
	      
