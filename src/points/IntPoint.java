package points;

public class IntPoint extends AbstractPoint<Integer> {

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "x: " + this.x + " y: " + this.y; 
	}
	
	public IntPoint(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		if(obj instanceof IntPoint){
			IntPoint aux = (IntPoint) obj;
			if(this.x.equals(aux.x) && this.y.equals(aux.y)){
				return true;
			}
		}
		
		return false;
	}

	@Override
	public IntPoint doApoint(Integer x, Integer y) {
		// TODO Auto-generated method stub
		return new IntPoint(x, y);
	}
	
	public long cross(IntPoint O, IntPoint A, IntPoint B) {
		return (A.x - O.x) * (B.y - O.y) - (A.y - O.y) * (B.x - O.x);
	}

	@Override
	public Integer difference(Integer a, Integer b) {
		// TODO Auto-generated method stub
		return a-b;
	}

}
