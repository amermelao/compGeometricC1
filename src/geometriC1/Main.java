package geometriC1;

import geometriC1.convexhull.DoubleConvexHull;
import geometriC1.convexhull.IntConvexHull;
import geometriC1.edge.Edge;
import geometriC1.points.DoublePoint;
import geometriC1.points.IntPoint;
import geometriC1.poligonoconvexo.PoligonoConvexo;
import geometriC1.triangulacion.DoubleTriangulation;
import geometriC1.triangulacion.IntTriangulation;

import java.io.IOException;
import java.util.List;

public class Main {

	public static void doInt(String location) throws IOException{
		System.err.println("\nProcesiong Int traingulation");
		IntConvexHull convexHull =new IntConvexHull();
		List<IntPoint> points = convexHull.readDate(location);
		List<PoligonoConvexo<IntPoint>> poligonos = convexHull.getRingsOfConvexFigure();
		
		convexHull.doDebug(points, poligonos);
		
		IntTriangulation tri = new IntTriangulation();
		
		List<List<Edge<IntPoint,Integer>>> triAng = tri.triangulate(poligonos);
		
		
		
		parseOutput<Integer, IntPoint> parser = new parseOutput<Integer, IntPoint>(){

			@Override
			public geometriC1.parseOutput.Triangulo<IntPoint> newOne(
					IntPoint p1, IntPoint p2, IntPoint p3) {
				// TODO Auto-generated method stub
				return new Triangulo<IntPoint>(p1, p2, p3);
			}
			
		};
		
		parser.parse(triAng, points);
	}
	
	public static void doDouble(String location) throws IOException{
		System.err.println("\nProcesiong Double traingulation");
		DoubleConvexHull convexHull =new DoubleConvexHull();
		List<DoublePoint> points = convexHull.readDate(location);
		List<PoligonoConvexo<DoublePoint>> poligonos = convexHull.getRingsOfConvexFigure();
		
		convexHull.doDebug(points, poligonos);
		
		DoubleTriangulation tri = new DoubleTriangulation();
		
		List<List<Edge<DoublePoint,Double>>> triAng = tri.triangulate(poligonos);
		
		parseOutput<Double, DoublePoint> parser = new parseOutput<Double, DoublePoint>(){

			@Override
			public geometriC1.parseOutput.Triangulo<DoublePoint> newOne(
					DoublePoint p1, DoublePoint p2, DoublePoint p3) {
				// TODO Auto-generated method stub
				return new Triangulo<DoublePoint>(p1, p2, p3);
			}
			
		};
		
		parser.parse(triAng, points);
	}
	static public void main(String [] args) throws IOException{
		
		if(args.length > 0){
			if(args[0].equals("-i")){
				doInt(args[1]);
				return;
			}
			if(args[0].equals("-d")){
				doDouble(args[1]);
				return;
			}
			
		}
		
		System.err.println(" use arguments <option> <file>\n\noption:\n-i for integer triangulation\n-d for double triangulation");

		
	}
}
