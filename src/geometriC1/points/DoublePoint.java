package geometriC1.points;

public class DoublePoint extends AbstractPoint<Double>{
	

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "x: " + this.x + " y: " + this.y; 
	}
	
	public DoublePoint(double x, double y) {
		this.x = x;
		this.y = y;
	}
	
	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		if(obj instanceof DoublePoint){
			DoublePoint aux = (DoublePoint) obj;
			if(this.x.equals(aux.x) && this.y.equals(aux.y)){
				return true;
			}
		}
		
		return false;
	}

	@Override
	public Object doApoint(Double x, Double y) {
		// TODO Auto-generated method stub
		return new DoublePoint(x, y);
	}

	@Override
	public Double difference(Double a, Double b) {
		// TODO Auto-generated method stub
		return a-b;
	}

	

}
