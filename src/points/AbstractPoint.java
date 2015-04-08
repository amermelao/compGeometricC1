package points;

public abstract class AbstractPoint<E extends Number> implements Comparable<AbstractPoint<E>> {
	public E x, y;
	
	public abstract E cross(AbstractPoint<E> a, AbstractPoint<E> b);

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "x: " + this.x + " y: " + this.y; 
	}
}