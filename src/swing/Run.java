package swing;
import com.bulenkov.darcula.DarculaLaf;

import javax.swing.*;

public class Run {
    public static void main(String [] args) throws Exception {
        UIManager.setLookAndFeel(new DarculaLaf());
        MainMenu menu = new MainMenu();
        menu.setVisible(true);
    }
}