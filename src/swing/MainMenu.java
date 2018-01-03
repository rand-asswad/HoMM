package swing;

import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import javax.swing.*;

public class MainMenu extends JFrame {

    public MainMenu() {
        super("Heroes of Might & Magic - Main Menu");
        JPanel main = Frame.loadDefault(this);
        main.setLayout(new BoxLayout(main, BoxLayout.Y_AXIS));

        // adds logo to frame
        JLabel logo = new JLabel(Frame.HoMM_logo);
        logo.setAlignmentX(Component.CENTER_ALIGNMENT);
        main.add(logo);

        // creates button panel
        JPanel buttons = Frame.createButtonsPanel();

        // creates buttons
        JButton newGame = new JButton(Frame.html("NEW GAME","h1"));
        JButton rules = new JButton(Frame.html("GAME RULES","h1"));
        JButton quit = new JButton(Frame.html("QUIT","h1"));

        // associates buttons with actions
        newGame.addActionListener(new NewGame(MainMenu.this));
        rules.addActionListener(new DisplayRules());
        quit.addActionListener(new ExitFrame());

        // adds buttons to button the button panel
        buttons.add(newGame);
        buttons.add(rules);
        buttons.add(quit);
        Frame.addEmptySpace(buttons);

        // adds buttons to frame
        main.add(buttons);
    }

    class DisplayRules implements ActionListener {
        public void actionPerformed (ActionEvent e) {
            try {
                JFrame rulesFrame = new RulesFrame();
                rulesFrame.setVisible(true);
                MainMenu.this.setVisible(false);
            }
            catch (IOException exp) {}
        }
    }
}