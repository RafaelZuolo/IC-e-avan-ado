import edu.princeton.cs.algs4.*;
import java.util.Arrays;

public class HeavyHitter {
	
	public static double normaCalculator(double[] vec) {
		double norma = 0;
		for(int i = 0; i < vec.length; i++) {
			norma += vec[i]*vec[i];
		}
		return norma;
	}
	
	public static void main(String[] args) {
		In streamBackUp = new In("stream.txt");
		int streamSize = (int)streamBackUp.readDouble();
		In stream = new In("stream.txt");
		int size = Integer.parseInt(args[0]);
		double sizeReal = stream.readDouble();
		double normaStream = 0;
		double normaDelta = 0;
		double[] zip;
		double[] zipTemp;
		
		NisanBig G = new NisanBig(size);
		zip = BigNorma.zip(stream, G);
		normaStream = normaCalculator(zip);
		StdOut.println("Norma aproximada: " + Math.sqrt(normaStream));
		
		double maxHitter = 0;
		double tempHitter;
		int maxIndex = 0;
		for(int i = 0; i < streamSize; i++) {
			zipTemp = Arrays.copyOf(zip, zip.length);
			BigNorma.atualiza(i, -1.0, zipTemp, G);
			normaDelta = normaCalculator(zipTemp);
			tempHitter = ((1 + normaStream - normaDelta)/2);
			if(tempHitter > maxHitter) {
				maxHitter = tempHitter;
				maxIndex = i;
			}
		}
		StdOut.println("Coordenada grande: " + maxIndex + ", com valor aproximado de " + maxHitter);
		
	}
}