import edu.princeton.cs.algs4.*;

public class Hitter {
	
	public static void main(String[] args) {
		In stream = new In("stream.txt");
		int hitter = Integer.parseInt(args[0]);
		double erro = 0.05;
		double sizeReal = stream.readDouble();
		int size = (int)( 0.1*Math.log(sizeReal)*(1.0 / (erro*erro)));  // tamanho de size para o erro ser de erro

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