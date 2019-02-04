import edu.princeton.cs.algs4.*;

// está quebrado, preciso descobrir pq

public class FastNisan { // tentar deixar o nisan mais rápido mudando p produto
	private final int l;
	private final int[][] sigma;
	//private final int suitable;
	
	// lembre-se que dim deve ser um valor 'bom', ou seja, 
	// dim + 1 deve ser primo e deve ter 2 como raiz primitiva
	// Use uma tabela com valores bons de dim
	
/*	Lista de valores "bons" de primos:
101 107 131 139 149 163 173 179 181 197 211 227 269 293
653 659 661 677 701 709 757 773 787 797 821 827 829
1019 1061 1091 1109 1117 1123 1171 1187
*/
	
	public FastNisan(int l, int dim) {
		this.l = l;
		//this.suitable = suitable;
		int[][] sigma = new int[2*l + 1][dim];
		for(int i = 0; i < 2*l + 1; i++) {
			for(int j = 0; j < dim; j++) {
				sigma[i][j] = StdRandom.uniform(2);
			}
		}
		this.sigma = sigma;
	}
	
	public void printSigma() {
		for(int i = 0; i < sigma.length; i++) {
			for(int j = 0; j < sigma[i].length; j++) {
				StdOut.print(sigma[i][j]);
			}
		}
	}
	
	public static int[] shiftSum(int[] a, int[] b, int shift) { //soma dois vetores binarios com shift right no primeiro
		int sa = a.length;
		int sb = b.length;
		int warp;
		if (sa != sb) {
			StdOut.println("OMG SOMANDO VETOR DE TAMANHO DIFERENteS");
			return(a);
		}
		int[] c = new int[sa];
		
		for(int i = 0; i < sa; i++) {
			if(i - shift < 0) warp = i - shift + sa;
			else                warp = i - shift;
			c[i] = a[i] ^ b[warp];
		}
		for(int i = 0; i < a.length; i++) {
			a[i] = c[i];
		}
		return a;
	}
	
	public static int[] multiply(int[] a, int[] b) { //uma convolução melhor?
		int shift = 0;
		int[] c = new int[a.length];
		for(int i = 0; i < c.length; i++)
			c[i] = 0;
		
		for(int i = 0; i < a.length; i++) {
			if(a[i] == 1) shiftSum(c, b, shift);
			shift++;
		}
		return c;
	}
	
	public int[] get(int i) { //vamos percorrer os bits de i truncando a divisão por 2
		int[] x_j = sigma[0];
		int bit;
		
		for(int j = 0; j < this.l; j++) {
			bit = i % 2;
			i = i/2;
			if( bit == 1) {
				x_j = shiftSum(multiply(x_j, sigma[2*j + 1]), sigma[2*j +2], 0);
			}
		}
		if(x_j[x_j.length-1] == 1) {
			for(int j = 0; j < x_j.length; j++) {
				x_j[j] = x_j[j] ^ 1;
			}
		}
		
		int[] randBits = new int[x_j.length - 1];
		for(int j = 0; j < x_j.length - 1; j++) {
			if(x_j[j] == 0) randBits[j] = -1;
			else      		randBits[j] =  1;
		}
		return randBits;
	}
	
	public int size() {
		return this.l;
	}
	
	public double sqrtSize() {
		return Math.sqrt(this.l);
	}
	
	public static void printVec(int[] a) {
		for(int i = 0; i <a.length; i++) {
			StdOut.print(a[i]);
		}
		StdOut.print("\n");
	}
	
	public static void main(String[] args) {
 	/* 	int sum = 0;
		int dim = Integer.parseInt(args[0]);
		FastNisan G = new FastNisan(dim, dim);
		G.printSigma();
		int[] word;
		for(int i = 0; i < 10000; i++) {
			word = G.get(i);	
			for(int j = 0; j < 101 - 1; j++) {
				sum += word[j];
				StdOut.print(word[j]);
			}
			StdOut.println("\nSoma: " + sum);
			sum = 0;
			StdIn.readChar();
		}  */
		int[] a = {0,0,1,0,0,0,0};
		int[] b = {1,0,0,0,0,0,0};
		int shift = Integer.parseInt(args[0]);
		int[] c = multiply(a, b);
		int[] s = shiftSum(a, b, shift);
		printVec(c);
		printVec(a);
		printVec(s);
	}
}