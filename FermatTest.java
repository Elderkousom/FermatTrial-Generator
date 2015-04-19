import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.util.LinkedList;

public class FermatTest {

	static PrintWriter writer;

	/**
	 * Implementation of Fermat's factorization method.
	 * 
	 * @param args
	 *            File with semiprimes, one for each new row.
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(
				new FileInputStream(args[0])));
		String fileName = args[0] + "FermatFactorTime";
		writer = new PrintWriter(fileName, "UTF-8");
		try {
			String line;

			while ((line = br.readLine()) != null) {
				long start = System.nanoTime();
				LinkedList<BigInteger> ll = FermatFactor(new BigInteger(line));
				long end = (System.nanoTime() - start);
				writer.println((double) end / 1000000000);
				System.out.println("Factors: " + ll + " Time: " + end);
			}
		} finally {
			br.close();
			writer.close();
		}

	}

	public static LinkedList<BigInteger> FermatFactor(BigInteger N) {

		BigInteger a = sqrt(N);
		BigInteger b2 = a.pow(2).subtract(N);
		System.out.println(N + " " + a + " " + b2);
		while (!isSquare(b2)) {

			a = a.add(BigInteger.ONE);
			b2 = a.multiply(a).subtract(N);

			if (N.mod(a.subtract(sqrt(b2))) == BigInteger.ZERO) {
				break;
			}

		}

		LinkedList<BigInteger> ll = new LinkedList<BigInteger>();
		BigInteger r1 = a.subtract(sqrt(b2));
		BigInteger r2 = N.divide(r1);
		ll.add(r1);
		ll.add(r2);
		return ll;
	}

	public static boolean isSquare(BigInteger N) {
		BigInteger sqr = sqrt(N);
		if (sqr.multiply(sqr).equals(N)
				|| (sqr.add(BigInteger.ONE)).multiply(sqr.add(BigInteger.ONE))
						.equals(N))
			return true;
		return false;
	}

	public static BigInteger sqrt(BigInteger n) {
		BigInteger r = BigInteger.ZERO;
		BigInteger start = n;
		BigInteger m = r.setBit(2 * n.bitLength());
		BigInteger nr;

		do {
			nr = r.add(m);
			if (nr.compareTo(n) != 1) {
				n = n.subtract(nr);
				r = nr.add(m);
			}
			r = r.shiftRight(1);
			m = m.shiftRight(2);
		} while (m.bitCount() != 0);
		BigInteger p = r;

		if (p.pow(2).equals(start)) {
			return r;
		} else {
			return r.add(BigInteger.ONE);
		}
	}
}
