package points;

public class IntPoint extends AbstractPoint<Integer>{

	public IntPoint(int x, int y) {
		this.x = x;
		this.y = y;
	}
	@Override
	public int compareTo(AbstractPoint<Integer> p) {
		
		int tmp;
		
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
		if(obj instanceof IntPoint){
			IntPoint aux = (IntPoint) obj;
			if(this.x.equals(aux.x) && this.y.equals(aux.y)){
				return true;
			}
		}
		
		return false;
	}

	@Override
	public Integer cross(AbstractPoint<Integer> A, AbstractPoint<Integer> B) {
		// TODO Auto-generated method stub
		return (A.x - this.x) * (B.y - this.y) - (A.y - this.y) * (B.x - this.x);
	}


}
