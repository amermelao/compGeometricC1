package geometriC1.edge;


public abstract class Edge<E,F> {
	public E a;
	public E b;
	
	public Edge(E _a, E _b){
		a = _a;
		b = _b;
		}
	
	protected abstract F area2(E a, E b, E c);/*{
		return ((int)b.x - (int)a.x)*((int)c.y - (int)a.y) - ((int)b.y - (int)a.y)*((int)c.x - (int)a.x);
	}*/
	
	public abstract boolean toLeft(Edge<E,F> e, E p);/*{
		IntPoint a = e.a;
		IntPoint b = e.b;
		return area2(a, b, p) > 0;
	}*/
	
}