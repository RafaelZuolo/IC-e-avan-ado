import edu.princeton.cs.algs4.*;

// execucao: java-algs4 FastNorma [int]
// Na implementacao atual de NisanBig, cada operacao de get() é quadratica em l
// O tempo de execução é dominado por size^2 * tamanho da stream 
// o vetor a ser projetado e alterado pela stream deve ter dimensão sizeReal < 2^size
// = O(log^2(tamanho da stream)*tamanho da stream)
public class FastNorma {
	
	//atualiza o vetor aproximado com a entrada na posicao pos do vetor real
	public static void atualiza(int pos, double val, double[] zip, FastNisan G) {
		
		int[] temp = G.get(pos);		          // vetor aleatorio de +-1
		for(int i = 0; i < temp.length; i++) {    // atualizacao do vetor de destino
			zip[i] += temp[i]*val*(1/G.sqrtSize());
		}
	}
	// devolve o vetor "compactado"
	public static double[] zip(In stream, FastNisan G) { 
		int pos;
		double val;
		double[] zip = new double[G.size()-1];
		
		for(int i = 0; i < zip.length; i++) {
			zip[i] = 0.0;
		}
		// leitura do vetor real, tempo = O(tamanho do stream*size^2)
		while(!stream.isEmpty()) { 
			pos = stream.readInt();
			val = stream.readDouble();
			//if(pos % 1000 == 0)StdOut.println(pos);
			atualiza(pos, val, zip, G);
		}
		
		return zip;
	}
	
	public static void main(String[] args) {
		double erro = 0.1;      // erro de 10%
		int teste = Integer.parseInt(args[0]);	// deve ser o N para o GF[2^N]
		double sizeReal;        // tamanho do vetor real 
		int size;               // tamanho do vetor aproximado
		double trueNorma = 0;   // norma do vetor real
		double aproxNorma = 0;  // norma do vetor aproximado
		double val;             // valor do vetor real na posicao pos
		int pos;                // posicao no vetor real
		double[] zip;           // vetor aproximado
		
		In stream = new In("stream.txt");
		sizeReal = stream.readDouble();
		StdOut.println("Tamanho da stream = " + (int)sizeReal);
		size = teste;
		StdOut.println("Tamanho do vetor aprox = " + size);
		//int seed = Integer.parseInt(args[0]); // coloca a semente do gerador
		zip = new double[size];
		
		for(int i = 0; i < zip.length; i++) {
			zip[i] = 0.0;
		}
		// para um controle de tempo de execucao
		Stopwatch w = new Stopwatch();
//		FastNisan G = new FastNisan(size, size);
		FastNisan G = new FastNisan((int)sizeReal, size);
		
		while(!stream.isEmpty()) {  // leitura do vetor real
			pos = stream.readInt();
			val = stream.readDouble();
			trueNorma += val*val;
			//if(pos % 1000 == 0)StdOut.println(pos);
			atualiza(pos, val, zip, G);
		}
		
		for(int i = 0; i < size; i++) {
			aproxNorma += zip[i]*zip[i];
		}
		StdOut.println("\nNorma real  = " + Math.sqrt(trueNorma));
		StdOut.println("Norma aprox = " + Math.sqrt(aproxNorma));
		StdOut.println("Erro = " + 100 * Math.abs(1 -(Math.sqrt(aproxNorma/trueNorma))) + " %");
		StdOut.println("tempo de execucao = " + w.elapsedTime());
	}
}