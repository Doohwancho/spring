package _2_oop._1_상태_데이터의_캡슐화.state.immutable_object.example.복소수;

public final class Complex {
    
    private final double re;
    private final double im;
    
    
    public Complex(double re, double im) {
        this.re = re;
        this.im = im;
    }
    
    public static Complex valueOf(double re, double im){
        return new Complex(re, im);
    }
    
    public double realPart() {
        return re;
    }
    
    public double imaginaryPart() {
        return im;
    }
    
    public Complex plus(Complex c) {
        return new Complex(re + c.re, im + c.im);
    }
    
    public Complex minus(Complex c) {
        return new Complex(re - c.re, im - c.im);
    }
    
    public Complex times(Complex c) {
        return new Complex(re * c.re, im * c.im);
    }
    
    public Complex dividedBy(Complex c) {
        double tmp = c.re * c.re + c.im * c.im;
        return new Complex((re * c.re + im * c.im) / tmp,
            (im * c.re - re * c.im) / tmp);
    }
    
    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof Complex)) {
            return false;
        }
        Complex c = (Complex) o;
        
        return Double.compare(c.re, re) == 0 && Double.compare(c.im, im) == 0;
    }
    
    @Override
    public int hashCode() {
        return 31 * Double.hashCode(re) + Double.hashCode(im);
    }
    
    @Override
    public String toString() {
        return "(" + re + " + " + im + "i)";
    }
    
}

