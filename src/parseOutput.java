import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.io.PrintWriter;
import java.io.File;

import points.AbstractPoint;
import points.IntPoint;
import geometriC1.Triangulation;
import geometriC1.Edge;

public class parseOutput{
	public void parse(List<List> t){
		PrintWriter writer = new PrintWriter("output.off", "UTF-8");
		writer.println("OFF");
		
		/* Vertex count, Face count, Edge count */
		int vertexCount = 0;
		int faceCount = 0;
		int edgeCount = 0;
		
		writer.println(" "); //Esta linea hay que cambiarla despues
		
		/* Leo archivo input y copio los vértices */
		//TODO
		
		//int j = t.size();
		for(int i = 0; i < t.size(); i++){
			List<Edge> currTriang = t.get(i);
			for(int j = 0; j < currTriang.size(); j++){
				Edge edge1 = currTriang.get(j);
				
			}
		}
		writer.close();
	}
}