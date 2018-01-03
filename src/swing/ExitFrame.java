package swing;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class ExitFrame implements ActionListener {
    public void actionPerformed (ActionEvent e) {
        System.exit(0);
    }
}
