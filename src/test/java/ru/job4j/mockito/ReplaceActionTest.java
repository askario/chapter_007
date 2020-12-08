package ru.job4j.mockito;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ReplaceActionTest {
    @Test
    public void testOutput() {
        Output out = new StubOutput();
        Tracker tracker = new Tracker();
        tracker.add(new Item("Replaced item"));
        String replacedName = "Replaced item";
        ReplaceAction rep = new ReplaceAction(out);

        Input input = mock(Input.class);
        when(input.askInt(any(String.class))).thenReturn(1);
        when(input.askStr(any(String.class))).thenReturn(replacedName);

        rep.execute(input, tracker);

        String ln = System.lineSeparator();
        assertThat(out.toString(), is("=== Edit item ===" + ln + "Edit item is done." + ln));
        assertThat(tracker.findAll().get(0).getName(), is(replacedName));
    }
}
