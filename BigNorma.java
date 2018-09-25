import edu.princeton.cs.algs4.*;

public class BigNorma {
	
	//atualiza o vetor aproximado
	public static void atualiza(int pos, double val, double[] zip, NisanBig G) {
		
		int[] temp = G.get(pos);		          // vetor aleatorio de +-1
		for(int i = 0; i < temp.length; i++) {    // atualizacao do vetor de destino
			zip[i] += temp[i]*val*(1/G.sqrtSize());
		}
	}
	
	public static void main(String[] args) {
		double erro = 0.3;
		int teste = 3000;
		int size;               // tamanho do vetor aproximado
		double trueNorma = 0;   // norma do vetor real
		double aproxNorma = 0;  // norma do vetor aproximado
		double val;             // valor do vetor real na posicao pos
		int pos;
		double[] zip;           // vetor aproximado
		
		In stream = new In("stream.txt");
		
		//size = teste;
		size = (int)( 20.*Math.log(stream.readDouble())*(1.0 / (erro*erro)));
		StdOut.println("Size = " + size);
		int seed = Integer.parseInt(args[0]); // coloca a semente do gerador
		zip = new double[size];
		
		for(int i = 0; i < zip.length; i++) {
			zip[i] = 0.0;
		}
		
		NisanBig G = new NisanBig(size, seed);
		
		while(!stream.isEmpty()) {  // leitura do vetor real
			pos = stream.readInt();
			val = stream.readDouble();
			trueNorma += val*val;
			if(pos % 1000 == 0)StdOut.println(pos);
			atualiza(pos, val, zip, G);
		}
		
		for(int i = 0; i < size; i++) {
			aproxNorma += zip[i]*zip[i];
		}
		StdOut.println(Math.sqrt(trueNorma));
		StdOut.println(Math.sqrt(aproxNorma));
		StdOut.println((Math.sqrt(trueNorma/aproxNorma)));
	}
}