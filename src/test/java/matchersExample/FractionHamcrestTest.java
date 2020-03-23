package matchersExample;

//import org.junit.jupiter.api.AfterEach;
//import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
//
//import java.security.InvalidParameterException;
//import java.util.List;
//
//import static org.assertj.core.api.Java6Assertions.assertThatThrownBy;
import static org.assertj.core.api.Java6Assertions.assertThatThrownBy;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class FractionHamcrestTest {
    @Test
    void CheckFullContanst(){
        final Fraction f = Fraction.of(1, 1);
        assertThat(f, is(Fraction.FULL));
    }
    @Test
    void CheckHalfConstant() {
        final Fraction f = Fraction.of(1, 2);
        assertThat(f, is(Fraction.HALF));
    }

    @Test
    void CheckOneThirdConstant() {
        final Fraction f = Fraction.of(1, 3);
        assertThat(f, is(Fraction.ONE_THIRD));
    }

    @Test
    void HashCodeShouldBeOfSumOfNominatorDenominator() {
        final Fraction f = Fraction.of(2, 3);
        assertThat(f.hashCode(), is(5));
    }

    @Test
    void VerifyFractionToString() {
        final Fraction f = Fraction.of(9, 11);
        assertThat(f.toString(), allOf(is(instanceOf(String.class)), is("9/11")));
    }

    @Test
    public void MultiplyByZeroShouldBeIndeterminate() {
        final Fraction f = Fraction.of(1, 1)
                .multiply(Fraction.of(0, 0));

        assertThat(f, is(Fraction.INDETERMINATE));
    }
    @Test
    void CheckEqualityOfTwoThatWillBeCutDown() {
        final Fraction f = Fraction.of(1, 3);
        final Fraction f2 = Fraction.of(2, 6);
        assertThat(f.equals(f2), is(true));
    }
    @Test
    void CheckInequality() {
        final Fraction f = Fraction.of(4, 3);
        final Fraction f2 = Fraction.of(2, 3);
        assertThat(f.equals(f2), is(false));
    }

    @Test
    public void MultiplyTwoThatResultWillBeCutDown() {
        final Fraction actual = Fraction.of(5,2).multiply(Fraction.of(2,4));
        assertThat(actual, is(Fraction.of(5,4)));
    }

    @Test
    void CheckGettingNominator() {
        final Fraction f = Fraction.of(1, 3);
        assertThat(f.getNominator(), is(1));
    }

    @Test
    void CheckGettingDenominator() {
        final Fraction f = Fraction.of(1, 3);
        assertThat(f.getDenominator(), is(3));
    }

    @Test
    public void CheckZeroInDenominator() {
        assertThatThrownBy(() -> {Fraction.of(Integer.MAX_VALUE, 0);
        }).isInstanceOf(IllegalArgumentException.class);
    }

}