package geometriC1.points;

public abstract class AbstractPoint<E extends Number>  {

	public E x,y;
	
	public abstract Object doApoint( E x, E y);

	public abstract E difference(E a, E b);
}