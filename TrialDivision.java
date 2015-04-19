import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.util.LinkedList;

public class TrialDivision {

	static PrintWriter writer;

	/**
	 * Implementation of Trial Division
	 * 
	 * @param args
	 *            File with semiprimes one for each row
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(
				new FileInputStream(args[0])));
		String fileName = args[0] + "TrialDivisionTime";
		writer = new PrintWriter(fileName, "UTF-8");
		try {
			String line;
			while ((line = br.readLine()) != null) {
				long start = System.nanoTime();
				LinkedList<BigInteger> ll = tdFactors(new BigInteger(line));
				long end = (System.nanoTime() - start);
				writer.println(end);
				System.out.println("SemiPrime: " + line + " Factors: " + ll
						+ " Time: " + end);
			}
		} finally {
			br.close();
			writer.close();
		}

	}

	public static LinkedList<BigInteger> tdFactors(BigInteger n) {
		BigInteger two = BigInteger.valueOf(2);
		LinkedList<BigInteger> fs = new LinkedList<BigInteger>();

		if (n.compareTo(two) < 0) {
			throw new IllegalArgumentException("must be greater than one");
		}

		while (n.mod(two).equals(BigInteger.ZERO)) {
			fs.add(two);
			n = n.divide(two);
		}

		if (n.compareTo(BigInteger.ONE) > 0) {
			BigInteger f = BigInteger.valueOf(3);

			while (f.multiply(f).compareTo(n) <= 0) {
				if (n.mod(f).equals(BigInteger.ZERO)) {
					fs.add(f);
					n = n.divide(f);
					fs.add(n);
					break;
				} else {
					f = f.add(two);
				}
			}
		}
		return fs;
	}
}
