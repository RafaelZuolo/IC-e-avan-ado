import edu.princeton.cs.algs4.*;


// classe que existe unicamente para gerar vetores grandes aleatórios
public class StreamGen {
	
	public static String byteArray2BitArray(byte[] bytes) {
		String bits = new String();
		for (int i = 0; i < bytes.length * 8; i++) {
			if ((bytes[i / 8] & (1 << (7 - (i % 8)))) > 0)
				bits = bits.concat("1");
			else
				bits = bits.concat("0");
		}
		return bits;
	}
	
	public static void main(String[] args) {
		Out stream = new Out("stream.txt");
		int size = Integer.parseInt(args[0]);
		
		stream.println(size);
		
		for(int i = 0; i < size; i++) {
			stream.println(i + " " + StdRandom.uniform(-10, 11));
		}
	}
}