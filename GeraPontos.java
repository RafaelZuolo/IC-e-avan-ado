import edu.princeton.cs.algs4.*;

public class GeraPontos {
	/** 
	Gerador de instancias para testar o LSH.java, criando alguns clusters
	de pontos a distancia < r, mas cada cluster com distância > Cr de outro cluster
	 */

	
	public static void main(String[] args) {
		Out points = new Out("pointsToLSH.txt");
		
		double r = 1;
		double c = 3;
		int dim = Integer.parseInt(args[0]);
		int numPoints = 9;
		double offset = 0;
		double radius = 1;
		
		points.println(numPoints + " " + dim);
		
		for(int i = 0; i < numPoints; i++) {
			for(int j = 0; j < dim; j++) {
				points.print((StdRandom.gaussian()+ offset)/3 + " ");
			}
			points.println(" ");
			if(i%3 == 2) offset += c;
		}
		
	}
}