package swing;

import model.Game;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class NewGame implements ActionListener {

    private JFrame frame;

    NewGame (JFrame currentFrame) {
        frame = currentFrame;
    }

    public void actionPerformed (ActionEvent e) {
        try {
            InitGame initGame = new InitGame(new Game());
            initGame.setVisible(true);
            this.frame.setVisible(false);
        }
        catch (Exception exp) {}
    }
}
