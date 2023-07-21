package defensive_coding.exception.how.표준.javadoc;


//주의!
//checkedException은 javadoc에 throws로 명시하되,
//uncheckedException은 자주 바뀔 수 있기에 throws에 명시하지 말고, 코드로 보자.
public class Main {
    /**
     * This method performs a division operation.
     *
     * @param numerator the numerator of the division operation
     * @param denominator the denominator of the division operation
     * @return the result of the division operation
     * @throws ArithmeticException if the denominator is zero
     */
    public static double divide(int numerator, int denominator) throws ArithmeticException {
        if (denominator == 0) {
            throw new ArithmeticException("Denominator cannot be zero");
        }
        return (double) numerator / denominator;
    }
}
