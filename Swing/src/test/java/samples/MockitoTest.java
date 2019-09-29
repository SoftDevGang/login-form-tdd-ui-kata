package samples;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.Test;

class MockitoTest {

    @Test
    void shouldAssertWithMockito() {
        // arrange
        Runnable runner = mock(Runnable.class);
        // act
        runner.run();
        // assert
        verify(runner).run();
    }

}
