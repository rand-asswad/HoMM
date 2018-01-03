package swing;

import model.BattleController;

import javax.swing.*;
import java.awt.*;

public class GameOver extends JFrame {
    public GameOver(BattleController bc) {
        super("Heroes of Might & Magic - Game Over");
        JPanel main = Frame.loadDefault(this);
        main.setLayout(new BoxLayout(main, BoxLayout.Y_AXIS));

        // adds logo to frame
        JLabel logo = new JLabel(Frame.HoMM_logo);
        logo.setAlignmentX(Component.CENTER_ALIGNMENT);
        main.add(logo);

        JLabel winner = new JLabel(Frame.html(bc.winner().name + " has won!", "h1"));
        winner.setAlignmentX(Component.CENTER_ALIGNMENT);
        main.add(winner);

        // creates button panel
        JPanel buttons = Frame.createButtonsPanel();

        // creates buttons
        JButton newGame = new JButton(Frame.html("PLAY AGAIN","h1"));
        JButton quit = new JButton(Frame.html("QUIT","h1"));

        // associates buttons with actions
        newGame.addActionListener(new NewGame(GameOver.this));
        quit.addActionListener(new ExitFrame());

        // adds buttons to button the button panel
        buttons.add(newGame);
        buttons.add(quit);
        Frame.addEmptySpace(buttons);

        // adds buttons to frame
        main.add(buttons);
    }
}
