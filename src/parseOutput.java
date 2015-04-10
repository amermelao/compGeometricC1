

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.List;

import geometriC1.edge.IntEdge;

public class parseOutput{
	public void parse(List<List> t) throws FileNotFoundException, UnsupportedEncodingException{
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
			List<IntEdge> currTriang = t.get(i);
			for(int j = 0; j < currTriang.size(); j++){
				IntEdge edge1 = currTriang.get(j);
				
			}
		}
		writer.close();
	}
}