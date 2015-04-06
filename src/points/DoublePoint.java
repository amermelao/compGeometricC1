package points;

public class DoublePoint extends AbstractPoint<Double>{

	@Override
	public int compareTo(AbstractPoint<Double> p) {
		
		Double tmp;
		
		if (this.x == p.x) {
			tmp = this.y - p.y;
		} else {
			tmp = this.x - p.x;
			
		}
		
		return  tmp >= 0? (tmp == 0? 0 : 1 ): -1;
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
	public Double cross(AbstractPoint<Double> A, AbstractPoint<Double> B) {
		// TODO Auto-generated method stub
		return (A.x - this.x) * (B.y - this.y) - (A.y - this.y) * (B.x - this.x);
	}


}
