import edu.princeton.cs.algs4.*;
// Resolve o problema do heavy hitter para uma coordenada 
// específica
// execução: java-algs4 Hitter [int]
// o argumento é a coordenada a ser aproximada
// resultados bons se a coordenada contribui com pelo menos 10% da norma
public class Hitter {
	
	public static void main(String[] args) {
		In stream = new In("stream.txt");
		int hitter = Integer.parseInt(args[0]);
		double erro = 0.05;
		double sizeReal = stream.readDouble();
		int size = Integer.parseInt(args[1]);
		double normaStream = 0;
		double normaDelta = 0;
		double [] zip;
		
		NisanBig G = new NisanBig(size);
		
		zip = BigNorma.zip(stream, G);
		
		for(int i = 0; i < zip.length; i++) {
			normaStream += zip[i]*zip[i];
		}
		
		//normaStream = Math.sqrt(normaStream);
		StdOut.println(Math.sqrt(normaStream));
		BigNorma.atualiza(hitter, -1.0, zip, G);
		
		
		for(int i = 0; i < zip.length; i++) {
			normaDelta += zip[i]*zip[i];
		}
		//normaDelta = Math.sqrt(normaDelta);
		
		StdOut.println(((1 + normaStream - normaDelta)/2));
		
	}
}