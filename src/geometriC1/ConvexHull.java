package geometriC1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

import points.IntPoint;
 
 
public class ConvexHull {
 
	public long cross(IntPoint O, IntPoint A, IntPoint B) {
		return O.cross(A, B);
	}
 
	public List<IntPoint> convexHull(List<IntPoint> P) {
 
		if (P.size() > 1) {
			int k1 = -1,k2;
			List<IntPoint> upper = new ArrayList<IntPoint>();
			List<IntPoint> lower = new ArrayList<IntPoint>();
			
			List<IntPoint> toReturn = new ArrayList<IntPoint>();
 
			Collections.sort(P);
 
			// Build lower hull
			for (IntPoint aPoint:P) {
				while (k1 >= 1 && cross(lower.get(k1 - 1), lower.get(k1), aPoint) <= 0){
					lower.remove(k1); 
					k1--;
				}
				lower.add(aPoint);
				k1++;
			}
 
			// Build upper hull
			
			k2 = -1;
			for (int cont = P.size() - 1; cont >= 0; cont--) {
				while (k2 >= 1 && cross(upper.get(k2 - 1), upper.get(k2), P.get(cont)) <= 0){
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
			return new ArrayList<IntPoint>(P);
		} else{
			return null;
		}
	}
 
	public PoligonoConvexo<Integer, IntPoint> conVexPoligonFromSquare(int x, int y){
		List<IntPoint> points = new ArrayList<IntPoint>();
		points.add(new IntPoint(0, 0));
		points.add(new IntPoint(x, 0));
		points.add(new IntPoint(x, y));
		points.add(new IntPoint(0, y));
		
		return new PoligonoConvexo<Integer, IntPoint>(points);
	}
	public List<PoligonoConvexo<Integer,IntPoint>> getRingsOfConvexFigure() throws IOException{
		List<PoligonoConvexo<Integer, IntPoint>> toReturn = new ArrayList<PoligonoConvexo<Integer, IntPoint>>();
		
		BufferedReader f = new BufferedReader(new FileReader("input10int.txt")); 	// "hull.in"  Input Sample => size x y x y x y x y
		StringTokenizer st = new StringTokenizer(f.readLine());
		List<IntPoint> p = new ArrayList<IntPoint>();
		
		int largoY = Integer.parseInt(st.nextToken());
		int anchoX = Integer.parseInt(st.nextToken());
		int pointsN = Integer.parseInt(st.nextToken());
		
		toReturn.add(conVexPoligonFromSquare(anchoX, largoY));
		
		for (int i = 0; i < pointsN; i++) {
			st = new StringTokenizer(f.readLine());
			IntPoint aux = new IntPoint(Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken()));
			p.add(aux);
		}
		
		f.close();
		
		while(p.size() > 0){
			
			List<IntPoint> hull = new ConvexHull().convexHull(p);		
		
			for (IntPoint aPoint:hull) {
				if (aPoint != null){
					p.remove(aPoint);
				}
			}
			
			toReturn.add(new PoligonoConvexo<Integer, IntPoint>(hull)); 
		}
		return toReturn;
	}
	
	public static void main(String[] args) throws IOException {
 
		for(PoligonoConvexo<Integer,IntPoint> aHull:new ConvexHull().getRingsOfConvexFigure()){
			System.out.println(aHull.toString());
		}
		
	}
 
}