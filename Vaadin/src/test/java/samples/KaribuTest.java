package samples;

import static com.github.mvysny.kaributesting.v8.LocatorJ._click;
import static com.github.mvysny.kaributesting.v8.LocatorJ._get;
import static com.github.mvysny.kaributesting.v8.LocatorJ._setValue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.github.mvysny.kaributesting.v8.MockVaadin;
import com.vaadin.ui.Button;
import com.vaadin.ui.Label;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

/**
 * @see "https://github.com/mvysny/karibu-testing/tree/master/karibu-testing-v8"
 */
class KaribuTest {

    @BeforeEach
    void beforeEach() {
        MockVaadin.setup(HelloUI::new);
    }

    @AfterEach
    void afterEach() {
        MockVaadin.tearDown();
    }

    @Test
    void shouldHaveTwoComponentsInLayout() {
        final VerticalLayout layout = (VerticalLayout) UI.getCurrent().getContent();
        assertEquals(2, layout.getComponentCount());
    }

    @Test
    void shouldDisplayHello() {
        // simulate a text entry as if entered by the user
        _setValue(_get(TextField.class, spec -> spec.withCaption("Type your name here:")), "Baron Vladimir Harkonnen");

        // simulate a button click as if clicked by the user
        _click(_get(Button.class, spec -> spec.withCaption("Click Me")));

        // verify that there is a single Label and assert on its value
        assertEquals("Thanks Baron Vladimir Harkonnen, it works!", _get(Label.class).getValue());
    }
}
