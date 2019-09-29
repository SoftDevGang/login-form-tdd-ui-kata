import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.text.IsEqualIgnoringCase.equalToIgnoringCase;

import org.junit.jupiter.api.Test;

class Hamcrest2Test {

    @Test
    void shouldAssertWithHamcrestCore() {
        assertThat(1 + 1, is(equalTo(2)));
    }

    @Test
    void shouldAssertWithHamcrestLibrary() {
        assertThat("codecop", is(equalToIgnoringCase("CodeCop")));
    }

}
