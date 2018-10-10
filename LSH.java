import edu.princeton.cs.algs4.*;

public class LSH {
	
	final double[] randGauss;
	final double   randSum;
	final double   w;
	final double   radius;
	final int      dim;
	
	/*
		Construtor da classe
	*/
	
	public LSH(double w, double radius, int dim) {
		this.w = w;
		this.radius = radius;
		this.randSum = StdRandom.uniform();
		this.dim = dim;
		randGauss = new double[dim];
		for(int i = 0; i < dim; i++) {
			randGauss[i] = StdRandom.gaussian();
		}
	}
	
	/* 
		Função hash
	 */
	
	public int hash(double[] x) {
		if(x.length != dim) {StdOut.println("A DIMENSAO TA DIFERENTE!!!!!!!!!!!!!!!!!!!!!!!!!!!!"); return(0);}
		
		int hs = 0;
		
		for(int i = 0; i < dim; i++) {
			hs += x[i]*randGauss[i];
		}
		hs = (int) Math.abs(Math.floor(hs/(w*radius) + randSum));
		
		return(hs);
	}
	
	public static void main(String[] args) {
		In in = new In("pointsToLSH.txt");
		int numPoints = in.readInt();
		int dim = in.readInt();
		
		LSH func = new LSH(Double.parseDouble(args[0]), 1, dim);
		/* double[] x = {0.1 , 2.2, 10.5};
		double[] y = {3.3, -2.2, -5.4};
		double[] z = {0.0, 2., 10.};
		
		LSH hfunc = new LSH(Double.parseDouble(args[0]), 1, x.length);
		
		StdOut.println(hfunc.hash(x));
		StdOut.println(hfunc.hash(y));
		StdOut.println(hfunc.hash(z)); */
		
		double[][] points = new double[numPoints][dim];
		for(int i = 0; i < numPoints; i++) {
			for(int j = 0; j < dim; j++) {
				points[i][j] = in.readDouble();
			}
		}
		for(int i = 0; i < numPoints; i++) {
			StdOut.println(func.hash(points[i]));
		}
	}
}