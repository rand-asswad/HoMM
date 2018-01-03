package swing;
import model.*;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.border.TitledBorder;

public class InitGame extends JFrame {
    
    public InitGame(Game game) {
        super("Heroes of Might & Magic - New Game");
        JPanel main = Frame.loadDefault(this);
        main.setLayout(new BoxLayout(main, BoxLayout.Y_AXIS));

        // logo
        JLabel logo = new JLabel(Frame.HoMM_logo);
        logo.setAlignmentX(Component.CENTER_ALIGNMENT);
        main.add(logo);

        // players
        JPanel playersPanel = new JPanel();
        playersPanel.setMaximumSize(Frame.subPanelDimension);
        initPlayers(playersPanel, game);
        main.add(playersPanel);

        // buttons
        JPanel buttons = Frame.createButtonsPanel();
        JButton startGame = new JButton(Frame.html("START GAME","h1"));
        JButton backToMenu = new JButton(Frame.html("MAIN MENU","h1"));
        JButton quit = new JButton(Frame.html("QUIT","h1"));

        startGame.addActionListener(new StartGame(game));
        backToMenu.addActionListener(new GoToMainMenu());
        quit.addActionListener(new ExitFrame());

        buttons.add(startGame);
        buttons.add(backToMenu);
        buttons.add(quit);
        Frame.addEmptySpace(buttons);
        main.add(buttons);
    }

    private void initPlayers(JPanel playersPanel, Game game) {
        // Sets border for players panel
        TitledBorder title = BorderFactory.createTitledBorder("players");
        playersPanel.setBorder(title);

        // Initialize panel parameters
        GroupLayout layout = new GroupLayout(playersPanel);
        playersPanel.setLayout(layout);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);

        // Declare layout groups
        GroupLayout.SequentialGroup hGroup = layout.createSequentialGroup();
        GroupLayout.ParallelGroup labels = layout.createParallelGroup();
        GroupLayout.ParallelGroup boxes = layout.createParallelGroup();
        GroupLayout.ParallelGroup buttons = layout.createParallelGroup();
        GroupLayout.ParallelGroup selected = layout.createParallelGroup();
        GroupLayout.SequentialGroup vGroup = layout.createSequentialGroup();

        for (Player player : game.players) {
            // Declare panel components
            JLabel playerLabel = new JLabel("Player "+player.id+":");
            JTextField playerName = new JTextField();
            playerName.addFocusListener(new FocusListener() {
                @Override
                public void focusGained(FocusEvent e) {
                }

                @Override
                public void focusLost(FocusEvent e) {
                    player.name = playerName.getText();
                }
            });
            playerName.setText(player.name);
            JButton selectHero = new JButton("Select Hero");
            selectHero.addActionListener(new SelectHero(game, player.id));

            // adds component to horizontal parallel groups
            labels.addComponent(playerLabel);
            boxes.addComponent(playerName);
            buttons.addComponent(selectHero);

            // initializes vertical parallel groups
            GroupLayout.ParallelGroup playerGroup = layout.createParallelGroup(GroupLayout.Alignment.BASELINE);
            playerGroup.addComponent(playerLabel);
            playerGroup.addComponent(playerName);
            playerGroup.addComponent(selectHero);

            try {
                JLabel selectedHero = new JLabel(player.selected_hero.name);
                selected.addComponent(selectedHero);
                playerGroup.addComponent(selectedHero);
            }
            catch (NullPointerException nException) {
            }

            vGroup.addGroup(playerGroup);
        }
        hGroup.addGroup(labels);
        hGroup.addGroup(boxes);
        hGroup.addGroup(buttons);
        hGroup.addGroup(selected);
        layout.setHorizontalGroup(hGroup);

        layout.setVerticalGroup(vGroup);
    }

    class SelectHero implements ActionListener {
        Game game;
        int playerID;

        SelectHero (Game game, int playerID) {
            this.game = game;
            this.playerID = playerID;
        }

        public void actionPerformed (ActionEvent e) {
            try {
                HeroesFrame hf = new HeroesFrame(this.game, this.playerID);
                hf.setVisible(true);
                InitGame.this.setVisible(false);
            } catch (Exception exp) {}
        }
    }

    class StartGame implements ActionListener {
        Game game;

        StartGame (Game game) {
            this.game = game;
        }

        public void actionPerformed (ActionEvent e) {
            try {
                GameFrame play = new GameFrame(new BattleController(this.game));
                play.setVisible(true);
                InitGame.this.setVisible(false);
            }
            catch (Exception exp) {}
        }
    }

    class GoToMainMenu implements ActionListener {
        public void actionPerformed (ActionEvent e) {
            try {
                MainMenu menu = new MainMenu();
                menu.setVisible(true);
                InitGame.this.setVisible(false);
            }
            catch (Exception exp) {}
        }
    }
}