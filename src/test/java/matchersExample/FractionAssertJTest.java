package matchersExample;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class FractionAssertJTest {
    @Test
    void CheckFullContanst(){
        final Fraction f = Fraction.of(1, 1);
        assertThat(f).isEqualTo(Fraction.FULL);
    }
    @Test
    void CheckHalfConstant() {
        final Fraction f = Fraction.of(1, 2);
        assertThat(f).isEqualTo(Fraction.HALF);
    }

    @Test
    void CheckOneThirdConstant() {
        final Fraction f = Fraction.of(1, 3);
        assertThat(f).isEqualTo(Fraction.ONE_THIRD);
    }

    @Test
    void HashCodeShouldBeOfSumOfNominatorDenominator() {
        final Fraction f = Fraction.of(2, 3);
        assertThat(f.hashCode()).isEqualTo(5);
    }

    @Test
    void VerifyFractionToString() {
        final Fraction f = Fraction.of(9, 11);
        assertThat(f.toString()).isInstanceOf(String.class).contains("9/11");
    }

    @Test
    public void MultiplyByZeroShouldBeIndeterminate() {
        final Fraction f = Fraction.of(1, 1)
                .multiply(Fraction.of(0, 0));

        assertThat(f).isEqualTo(Fraction.INDETERMINATE);
    }
    @Test
    void CheckEqualityOfTwoThatWillBeCutDown() {
        final Fraction f = Fraction.of(1, 3);
        final Fraction f2 = Fraction.of(2, 6);
        assertThat(f.equals(f2)).isTrue();
    }
    @Test
    void CheckInequality() {
        final Fraction f = Fraction.of(4, 3);
        final Fraction f2 = Fraction.of(2, 3);
        assertThat(f.equals(f2)).isFalse();
    }

    @Test
    public void MultiplyTwoThatResultWillBeCutDown() {
        final Fraction actual = Fraction.of(5,2).multiply(Fraction.of(2,4));
        assertThat(actual).isEqualTo(Fraction.of(5,4));
    }

    @Test
    void CheckGettingNominator() {
        final Fraction f = Fraction.of(1, 3);
        assertThat(f.getNominator()).isEqualTo(1);
    }

    @Test
    void CheckGettingDenominator() {
        final Fraction f = Fraction.of(1, 3);
        assertThat(f.getDenominator()).isEqualTo(3);
    }

    @Test
    public void CheckZeroInDenominator() {
        assertThatThrownBy(() -> {Fraction.of(Integer.MAX_VALUE, 0);
        }).isInstanceOf(IllegalArgumentException.class);
    }
    @Test
    public void AddingFractions(){
        assertThat(Fraction.HALF.add(Fraction.HALF)).isEqualTo(Fraction.FULL);
    }
    @Test
    public void AddingFractionsShouldBeCutDown(){
        assertThat(Fraction.of(2,3).add(Fraction.of(4,3))).isEqualTo(Fraction.of(2,1));
    }
    @Test
    public void CheckGettingDoubleValue() {
        assertThat(Fraction.of(1, 2).doubleValue()).isBetween(0.4, 0.6);
    }
    @Test
    public void CheckEqualityWithWrongType(){
        assertThat(Fraction.FULL.equals("test")).isFalse();
    }
}
