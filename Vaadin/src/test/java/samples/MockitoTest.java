package samples;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import java.util.function.Consumer;

import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

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

    @Test
    void shouldAssertWithArgumentCaptor() {
        @SuppressWarnings("unchecked")
        Consumer<String> consumer = mock(Consumer.class);

        consumer.accept("John");

        ArgumentCaptor<String> argument = ArgumentCaptor.forClass(String.class);
        verify(consumer).accept(argument.capture());
        assertEquals("John", argument.getValue());
    }

}
