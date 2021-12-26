package test7;

public class test {
	 public static void main(String[] args) {
		 
	        ShortestPathFloyd floyd = new ShortestPathFloyd();
	        floyd.createGraph(8);
	        
	        floyd.floyd();
	        
	        floyd.print();
	        
	    }
}
