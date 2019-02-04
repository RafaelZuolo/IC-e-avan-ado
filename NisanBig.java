import edu.princeton.cs.algs4.*;
import java.math.BigInteger;
import java.util.Random;
//potencialmente errado pois as operacoes no GF[2^n] estao erradas
// Cada operacao de get() leva um tempo quadratico em l, se otimizarmos a operação
// com os bits e a multiplicacao com os bits podemos torna-la linearitmica em l (O(l*log(l)))


public final class NisanBig{ // gerador de vetores na forma {+1, -1}^l com acesso randomico
	private final int l;  // o tamanho das palavras
	private BigInteger[] sigma;  //nosso vetor semente
	private Random rand = new Random();
	private final BigInteger lBig; // = 2^l em BigInteger

	
	//inicializa o gerador de numeros aleatorios  tempo = O(l^2)
	public NisanBig(BigInteger[] sigma) {
		this.sigma = sigma;
		this.l = (sigma.length - 1) / 2;
		
		String doisL = new String("1");
		for(int i = 0; i < l; i++) {      // potencialmente quadratico *******************
			doisL = doisL.concat("0");
		}
		this.lBig = new BigInteger(doisL, 2);
	}
	
	// inicializa com o vetor gerado aleatoriamente com a semente desejada tempo = O(l^2)
	public NisanBig(int l, long randSeed) {
		this.l = l;
		BigInteger[] temp = new BigInteger[2*l + 1];
		rand.setSeed(randSeed);
		for(int i = 0; i < 2*l + 1; i++) {
			temp[i] = new BigInteger(l, rand);
		}
		this.sigma = temp;
		
		String doisL = new String("1");
		for(int i = 0; i < l; i++) {      // potencialmente quadratico *******************
			doisL = doisL.concat("0");
		}
		this.lBig = new BigInteger(doisL, 2);
	}
	// inicializa com o vetor gerado aleatoriamente. O(l^2)
	public NisanBig(int l) {
		this.l = l;
		BigInteger[] temp = new BigInteger[2*l + 1];
		for(int i = 0; i < 2*l + 1; i++) {
			temp[i] = new BigInteger(l, rand);
		}
		this.sigma = temp;
		
		String doisL = new String("1");
		for(int i = 0; i < l; i++) {      // potencialmente quadratico *******************
			doisL = doisL.concat("0");
		}
		this.lBig = new BigInteger(doisL, 2);
	}
	
	public int size() {  // O(1)
		return this.l;
	}
	
	public double sqrtSize() {   // O(1)
		return Math.sqrt(this.l);
	}
	
	// funcao que retorna a i-esima palavra aleatoria (lembre-se, i <= 2^l - 1) ------- O(l^2)
	public int[] get(int i) { 
		BigInteger prodtemp;
		BigInteger x_j = sigma[0];
		int[] value = new int[l];
		String bits = Integer.toBinaryString(i);
		
		while(bits.length() < l) {    // precisamos completar a string binária --- quadratico O(l^2)
			bits = "0".concat(bits);  // bits vira "00 ... 00"bits
		}
		
		//percorremos a arvore geradora, onde o produto de dois bigIntegers de tamanho l é O(l^2) 
		for(int j = 0; j < l; j++) {
			if(bits.charAt(j) == '1') {
				prodtemp = sigma[2*j + 1].multiply(x_j).add(sigma[2*j+2]); // fazemos o produto com a soma mod 2^l
				prodtemp = prodtemp.mod(lBig);
				x_j = prodtemp;
			}
		}
		
		//convertemos o int em uma palavra de bits onde 1 -> 1 e 0 -> -1
		x_j = x_j.add(lBig);
		bits = byteArray2BitArray(x_j.toByteArray(), l);
		//StdOut.println("numero de bits: " + bits.length());
		

		for(int j = 0; j < l; j++) {
			if(bits.charAt(j) == '1') value[j] =  1;
			else					  value[j] = -1;
		}
		return value;
	}
	
	
	/*public static boolean[] byteArray2BitArray(byte[] bytes) {
		boolean[] bits = new boolean[bytes.length * 8];
		for (int i = 0; i < bytes.length * 8; i++) {
			if ((bytes[i / 8] & (1 << (7 - (i % 8)))) > 0)
			bits[i] = true;
		}
		return bits;
	}*/
	
	public static String byteArray2BitArray(byte[] bytes, int l) { // O(l^2)
		String bits = new String();

		for (int i = bytes.length*8 - l; i < bytes.length * 8; i++) {
			if ((bytes[i / 8] & (1 << (7 - (i % 8)))) > 0)
				bits = bits.concat("1");
			else
				bits = bits.concat("0");
		}
		return bits;
	}
	
	public static void main(String[] args) {
		NisanBig G = new NisanBig(70, 988888);
		int[] word;
		for(int i = 0; i < 10000; i++) {
			word = G.get(i);	
			for(int j = 0; j < 70; j++) {
				StdOut.print(word[j]);
			}
			StdOut.print("\n");
		}
	}
}