import edu.princeton.cs.algs4.*;

public final class Nisan{ // gerador de muitas palavras aleatórias na forma {+1, -1}^l com acesso randomico
	private final int l;  // o tamanho das palavras (na implementação atual, podemos gerar apenas para l < 32)
	private int[] sigma; //nosso vetor semente
	
	//inicializa o gerador de numeros aleatorios
	public Nisan(int[] sigma) {
		this.sigma = sigma;
		this.l = (sigma.length - 1) / 2;
	}
	
	// inicializa com o vetor gerado aleatoriamente com a semente desejada
	public Nisan(int l, int randSeed) {
		this.l = l;
		int[] temp = new int[2*l + 1];
		StdRandom.setSeed(randSeed);
		for(int i = 0; i < 2*l + 1; i++) {
			temp[i] = StdRandom.uniform(Integer.MAX_VALUE) + 1;
		}
		this.sigma = temp;
	}
	// inicializa com o vetor gerado aleatoriamente.
	public Nisan(int l) {
		this.l = l;
		int[] temp = new int[2*l + 1];
		for(int i = 0; i < 2*l + 1; i++) {
			temp[i] = StdRandom.uniform(Integer.MAX_VALUE) + 1;
		}
		this.sigma = temp;
	}
	
	public int size() {
		return this.l;
	}
	
	public double sqrtSize() {
		return Math.sqrt(this.l);
	}
	
	// funcao que retorna a i-esima palavra aleatoria (lembre-se, i <= 2^l - 1)
	public int[] get(int i) { 
		long prodtemp;
		int x_j = sigma[0];
		int[] value = new int[l];
		String bits = Integer.toBinaryString(i);
		
		while(bits.length() < l) {    // precisamos completar a string binária
			bits = "0".concat(bits);  // bits -> "00 ... 00"bits
		}
		
		//percorremos a arvore geradora
		for(int j = 0; j < l; j++) {
			if(bits.charAt(j) == '1') {
				prodtemp = (long)sigma[2*j + 1]*(long)x_j + (long)sigma[2*j+2]; // fazemos o produto com a soma mod 2^l
				prodtemp = prodtemp%(long)Math.pow(2., (double)l);              //razao para l <= 32
				x_j = (int) prodtemp;
			}
		}
		
		//convertemos o int em uma palavra de bits onde 1 -> 1 e 0 -> -1
		bits = Integer.toBinaryString(x_j);
		while(bits.length() < l) {
			bits = "0".concat(bits);
		}

		for(int j = 0; j < l; j++) {
			if(bits.charAt(j) == '1') value[j] =  1;
			else					  value[j] = -1;
		}
		return value;
	}
	
	public static void main(String[] args) {
		Nisan G = new Nisan(32, 988888);
		int[] word;
		for(int i = 0; i < 10000; i++) {
			word = G.get(i);	
			for(int j = 0; j < 32; j++) {
				StdOut.print(word[j]);
			}
			StdOut.print("\n");
		}
	}
}