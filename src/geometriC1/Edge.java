package geometriC1;


import points.IntPoint;

class Edge {
	IntPoint a, b;
	
	public Edge(IntPoint _a, IntPoint _b){
		a = _a;
		b = _b;
		}
	
	private static int area2(IntPoint a, IntPoint b, IntPoint c){
		return ((int)b.x - (int)a.x)*((int)c.y - (int)a.y) - ((int)b.y - (int)a.y)*((int)c.x - (int)a.x);
	}
	
	public static boolean toLeft(Edge e, IntPoint p){
		IntPoint a = e.a;
		IntPoint b = e.b;
		return area2(a, b, p) > 0;
	}
	
}