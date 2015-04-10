package geometriC1.convexhull;

import geometriC1.points.AbstractPoint;
import geometriC1.poligonoconvexo.PoligonoConvexo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.StringTokenizer;
 
public abstract class ConvexHull<F extends Number,E extends AbstractPoint<F>> {
 
	private List<E> p;
	
	public abstract long cross(E O, E A, E B);
	public abstract E doApoint(F x, F y);
	public abstract int aCompare(F tmp);
	
	public List<E> convexHull(List<E> P) {
 
		if (P.size() > 1) {
			int k1 = -1,k2;
			List<E> upper = new ArrayList<E>();
			List<E> lower = new ArrayList<E>();
			
			List<E> toReturn = new ArrayList<E>();
 
			Collections.sort(P, new Comparator<E>() {

				@Override
				public int compare(E o1, E o2) {
					F tmp;
					
					if(o1.x.equals(o2.x)){
						tmp = o1.difference(o1.y,o2.y);
					}
					else{
						tmp = o1.difference(o1.x,o2.x);
					}
					
					return aCompare(tmp);
				}
			});
			
			// Build lower hull
			for (E aPoint:P) {
				while ( k1 >= 1 && cross(lower.get(k1 - 1), lower.get(k1), aPoint) < 0){
					lower.remove(k1); 
					k1--;
				}
				lower.add(aPoint);
				k1++;
			}
 
			// Build upper hull
			
			k2 = -1;
			for (int cont = P.size() - 1; cont >= 0; cont--) {
				while (k2 >= 1 &&  cross(upper.get(k2 - 1), upper.get(k2), P.get(cont)) < 0){
					upper.remove(k2);
					k2--;
				}
				upper.add( P.get(cont));
				k2++;
			}
			
			lower.remove(lower.size()-1);
			upper.remove(upper.size()-1);
			
			toReturn.addAll(lower);
			toReturn.addAll(upper);
			
			return toReturn;
		} else if (P.size() <= 1) {
			return new ArrayList<E>(P);
		} else{
			return null;
		}
	}
 
	public abstract F parseSide(String value);
	
	public List<E> readDate(String name) throws IOException{
		BufferedReader f = new BufferedReader(new FileReader(name)); 	// "hull.in"  Input Sample => size x y x y x y x y
		StringTokenizer st = new StringTokenizer(f.readLine());
		List<E> p = new ArrayList<E>();
		
		F y = parseSide(st.nextToken());
		F x = parseSide(st.nextToken());
		F z = parseSide("0");
		
		int pointsN = Integer.parseInt(st.nextToken());
		
		p.add(doApoint(z, z));
		p.add(doApoint(x, z));
		p.add(doApoint(x, y));
		p.add(doApoint(z, y));
		//toReturn.add(conVexPoligonFromSquare(anchoX, largoY));
		
		for (int i = 0; i < pointsN; i++) {
			st = new StringTokenizer(f.readLine());
			E aux = doApoint(parseSide(st.nextToken()),parseSide(st.nextToken()));
			p.add(aux);
		}
		
		
		f.close();
		
		this.p = p;
		return new ArrayList<E>(p);
	}
	
	public List<PoligonoConvexo<E>> getRingsOfConvexFigure() throws IOException{
		List<PoligonoConvexo< E>> toReturn = new ArrayList<PoligonoConvexo< E>>();
		
		while(p.size() > 0){
			
			List<E> hull = this.convexHull(p);		
		
			for (E aPoint:hull) {
				if (aPoint != null){
					p.remove(aPoint);
				}
			}
			
			toReturn.add(new PoligonoConvexo< E>(hull)); 
		}
		return toReturn;
	}
	
	public void doDebug(List<E> points,List<PoligonoConvexo<E>> AllHulls) throws IOException{
		int cont = 0;
		
		BufferedWriter outp = new BufferedWriter(new FileWriter(new File("points.dat")));
		for(E aPoint : points){
			outp.write("" + aPoint.x + " " + aPoint.y);
			outp.newLine();
		}
		outp.close();
		
		for(PoligonoConvexo<E> aHull : AllHulls){
			BufferedWriter out = new BufferedWriter(new FileWriter(new File("data" + cont + ".dat")));
			cont++;
			for(E aPoint: aHull.points){
				out.write("" + aPoint.x + " " + aPoint.y);
				out.newLine();
			}
			out.close();
		}
	}

 
}