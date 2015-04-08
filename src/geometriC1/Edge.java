package geometriC1;

import java.util.ArrayList;
import java.util.List;
import points.AbstractPoint;
import geometriC1.ConvexHull;

class Edge {
	AbstractPoint a, b;
	
	public Edge(AbstractPoint _a, AbstractPoint _b){
		a = _a;
		b = _b;
		}
	
	private static int area2(AbstractPoint a, AbstractPoint b, AbstractPoint c){
		return ((int)b.x - (int)a.x)*((int)c.y - (int)a.y) - ((int)b.y - (int)a.y)*((int)c.x - (int)a.x);
	}
	
	public static boolean toLeft(Edge e, AbstractPoint p){
		AbstractPoint a = e.a;
		AbstractPoint b = e.b;
		return area2(a, b, p) > 0;
	}
	
}