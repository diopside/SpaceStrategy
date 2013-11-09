package tools;

public class Round {



	public static double round(double d, int decimalDigits){
		double multiplier = Math.pow(10, decimalDigits);
		d = (int) ((d * multiplier) + .5);
		d /= multiplier;

		return d;
	}

	public static long choose(int n, int x){
		long numElements;

		long numerator = 1, denominator = 1;
		for (int a = n; a > 0; a --){
			if (a > n - x)
				numerator *= a;
			if (a <= x)
				denominator *= a;
		}
		numElements = (long)(numerator/denominator);

		return numElements;
	}


}


