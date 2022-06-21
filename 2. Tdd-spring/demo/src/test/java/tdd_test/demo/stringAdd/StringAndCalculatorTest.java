package tdd_test.demo.stringAdd;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import tdd_test.demo.stringaddsum.StringAndCalculator;

import static org.assertj.core.api.Assertions.*;

public class StringAndCalculatorTest {

    @Test
    public void StringAddSum_null또는_빈문자(){
        int result = StringAndCalculator.splitAndSum(null);
        assertThat(result).isEqualTo(0);

        result = StringAndCalculator.splitAndSum("     ");
        assertThat(result).isEqualTo(0);
    }

    @Test
    public void splitAndSum_숫자하나() throws Exception{
        int result = StringAndCalculator.splitAndSum("1");
        assertThat(result).isEqualTo(1);
    }

    @Test
    public void splitAndSum_쉼표구문자() throws Exception{
        int result = StringAndCalculator.splitAndSum("1,2");
        assertThat(result).isEqualTo(3);
    }

    @Test
    public void splitAndSum_쉼표_또는_콜론_구분자() throws Exception{
        int result = StringAndCalculator.splitAndSum("1,2:3");
        assertThat(result).isEqualTo(6);
    }

    @Test
    public void splitAndSum_custom_구분자() throws Exception{
        int result = StringAndCalculator.splitAndSum("//;\n1;2;3");
        assertThat(result).isEqualTo(6);
    }

    @Test
    public void splitAndSum_negative() throws Exception {
        assertThatThrownBy( ()-> StringAndCalculator.splitAndSum("-1,2,3"))
                .isInstanceOf(RuntimeException.class);
    }
}
