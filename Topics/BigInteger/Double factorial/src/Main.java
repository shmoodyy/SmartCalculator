import java.math.BigInteger;

class DoubleFactorial {
    public static BigInteger calcDoubleFactorial(int n) {
        BigInteger factorial = BigInteger.ONE;
        for (int i = n; i >= 0; i -= 2) {
            if (i == 0 || i == 1) {
                return factorial;
            }
            factorial = factorial.multiply(BigInteger.valueOf(i));
        }
        return factorial;
    }
}