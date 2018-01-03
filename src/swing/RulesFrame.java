package swing;

import org.jdesktop.swingx.JXLabel;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

public class RulesFrame extends JFrame {
    private final String filePath = "rules.txt";

    RulesFrame() throws IOException {
        super("Heroes of Might & Magic - Game Rules");
        JPanel main = Frame.loadDefault(this);
        main.setLayout(new BoxLayout(main, BoxLayout.Y_AXIS));

        // adds logo to frame
        JLabel logo = new JLabel(Frame.HoMM_logo);
        logo.setAlignmentX(Component.CENTER_ALIGNMENT);
        main.add(logo);

        // reads file into label
        JXLabel rules = new JXLabel();
        rules.setText(this.importRules());
        rules.setTextAlignment(JXLabel.TextAlignment.JUSTIFY);
        rules.setLineWrap(true);
        rules.setBorder(new EmptyBorder(10,10,10,10));
        JScrollPane scrollPane = new JScrollPane(rules);
        scrollPane.setMaximumSize(Frame.subPanelDimension);
        scrollPane.setAlignmentX(Component.CENTER_ALIGNMENT);
        main.add(scrollPane);

        // creates button panel
        JPanel buttons = Frame.createButtonsPanel();

        // creates buttons
        JButton newGame = new JButton(Frame.html("START GAME","h1"));
        JButton quit = new JButton(Frame.html("QUIT","h1"));

        // associates buttons with actions
        newGame.addActionListener(new NewGame(RulesFrame.this));
        quit.addActionListener(new ExitFrame());

        // adds buttons to button the button panel
        buttons.add(newGame);
        buttons.add(quit);
        Frame.addEmptySpace(buttons);

        // adds buttons to frame
        main.add(buttons);
    }

    private String importRules() throws IOException {
        return new String(Files.readAllBytes(Paths.get(this.filePath)));
    }
}
