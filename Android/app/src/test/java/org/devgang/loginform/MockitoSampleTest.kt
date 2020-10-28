package org.devgang.loginform

import org.junit.Assert
import org.junit.Test
import org.mockito.Mockito
import org.mockito.ArgumentCaptor
import java.util.function.Consumer

class MockitoSampleTest {
    @Test
    fun shouldAssertWithMockito() {
        // arrange
        val runner = Mockito.mock(Runnable::class.java)
        // act
        runner.run()
        // assert
        Mockito.verify(runner).run()
    }

    @Test
    fun shouldAssertWithArgumentCaptor() {
        val consumer: Consumer<String> = Mockito.mock(Consumer::class.java) as Consumer<String>
        consumer.accept("John")
        val argument = ArgumentCaptor.forClass(String::class.java)
        Mockito.verify(consumer).accept(argument.capture())
        Assert.assertEquals("John", argument.value)
    }
}