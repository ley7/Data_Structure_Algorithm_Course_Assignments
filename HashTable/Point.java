public class Point {
	  int x;
	  int y;
	  int z;

	  public Point(int x, int y) {
	    this.x = x;
	    this.y = y;
	  }

	  @Override 
	  public int hashCode(){
		  //z = (((this.x + this.y)*(this.x + this.y + 1)/2) + this.y);//third fast
		  z= this.x*997+this.y;//fast
		  //z=((this.x & this.y) & 0x7FFFFFFF);//slow
		  //z=Math.abs((x * 0x1f1f1f1f) ^ y);//slow
		  //z= this.x*(this.size+1)+this.y;//slow
		  return z;
	  }
	  
	  @Override
	  public boolean equals(Object o){	 
		  if(((Point)o).x==this.x&&((Point)o).y==this.y) {
			  return true;
		  }
		  else return false;
		  }
}