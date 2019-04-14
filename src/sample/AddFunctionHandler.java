package sample;

import javafx.event.Event;
import javafx.event.EventHandler;

public class AddFunctionHandler implements EventHandler<Event> {

    int index;
    AddFunctionHandler(int i)
    {
        this.index=i;
    }
    @Override
    public void handle(Event event) {
        Main.data.index = index;
        new SelectGraphTypeWindow();
    }
}