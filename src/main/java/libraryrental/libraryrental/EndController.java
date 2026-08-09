package libraryrental.libraryrental;

import javafx.event.Event;
import java.io.IOException;

public class EndController {
    public void logout(Event event) throws IOException {
        MenuController m = new MenuController();
        m.logout(event);
    }
}
