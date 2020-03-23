package matchersExample;

import com.google.common.annotations.VisibleForTesting;

import java.io.Serializable;

public final class Fraction implements Serializable {
    public static final Fraction of(final int nominator, final int denominator) {
        if(denominator == 0){
            if(nominator == 0)
                return INDETERMINATE;
            throw new IllegalArgumentException("Denominator cannot be equal to 0");
        }
        if(denominator == 1){
            switch(nominator){
                case 1:
                    return FULL;
                case 2:
                    return HALF;
                case 3:
                    return ONE_THIRD;
            }
        }
        int nd = nwd(nominator, denominator);
        return new Fraction(nominator/nd,denominator/nd);
    }
    public int getNominator(){
        return nominator;
    }
    public int getDenominator(){
        return denominator;
    }
    private static int nwd(int a, int b){
        int x = Math.abs(a);
        int y = Math.abs(b);
        if (x > y)
            return nwd(x - y, y);
        else {
            if (x == y) return x;
            else return nwd(x, y - x);
        }
    }
    public Fraction add(Fraction a) {
        return of((this.nominator * a.denominator) + (this.denominator * a.nominator), this.denominator * a.denominator);
    }


    public Fraction multiply(Fraction n) {
        return of(this.nominator * n.nominator, this.denominator * n.denominator);
    }
    public double doubleValue(){
        return (double) nominator / (double) denominator;
    }
    @Override
    public boolean equals(final Object rightSide){
        if(!(rightSide instanceof Fraction)) return false;
        final Fraction that = (Fraction) rightSide;
        return (this == that) || (nominator == that.nominator && denominator == that.denominator);
    }
    @Override
    public int hashCode(){
        return nominator + denominator;
    }
    @Override
    public String toString(){
        return new StringBuilder()
                .append(nominator)
                .append('/')
                .append(denominator)
                .toString();
    }
    private Fraction(final int nominator, final int denominator){
        this.denominator = denominator;
        this.nominator = nominator;
    }
    public static final Fraction INDETERMINATE = new Fraction(0,0);
    @VisibleForTesting static final Fraction FULL = new Fraction(1,1);
    @VisibleForTesting static final Fraction HALF = new Fraction(1,2);
    @VisibleForTesting static final Fraction ONE_THIRD = new Fraction(1,3);
    private final int nominator;
    private final int denominator;
}