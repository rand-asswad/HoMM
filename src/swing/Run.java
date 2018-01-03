package swing;
import com.bulenkov.darcula.DarculaLaf;
import model.Game;

import javax.swing.*;

public class Run {
    public static void main(String [] args) throws Exception {
        UIManager.setLookAndFeel(new DarculaLaf());
        JFrame menu = new MainMenu();
        menu.setVisible(true);
    }
}