package swing;
import model.*;

import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class GameFrame extends JFrame {

    ImageIcon attackIcon = new ImageIcon("./img/buttons/attack.jpg");
    ImageIcon defenseIcon = new ImageIcon("./img/buttons/defense.jpg");
    ImageIcon spellIcon = new ImageIcon("./img/buttons/spell.jpg");

    final static int ATTACK = 1;
    final static int DEFENSE = -1;
    final static int WAIT = 0;

    BattleController battleController;
    
    public GameFrame(BattleController bc) {
        super("Heroes of Might & Magic");
        JPanel main = Frame.loadDefault(this);
        main.setLayout(new BoxLayout(main, BoxLayout.Y_AXIS));

        Player player = bc.has_turn.master.player;
        this.battleController = bc;

        // Player information panel
        JPanel playerInfo = new JPanel();
        //playerInfo.setLayout(new BoxLayout(playerInfo, BoxLayout.X_AXIS));
        //playerInfo.setMaximumSize(Frame.subPanelDimension);
        JLabel playerName = new JLabel("<html><h1>"+player.name+"</h1><br><h2>"+player.selected_hero.name +"</h2></html>");
        JLabel heroIcon = new JLabel(new ImageIcon(player.selected_hero.path));
        playerInfo.add(playerName);
        playerInfo.add(heroIcon);
        main.add(playerInfo);

        // Actions panel
        JPanel actions = new JPanel();
        JButton defenseBtn = createActionButton(DEFENSE);
        JButton waitBtn = createActionButton(WAIT);
        actions.add(defenseBtn);
        actions.add(waitBtn);
        main.add(actions);

        // Queue Panel
        JPanel queuePanel = new JPanel();
        for (Stack stack : bc.queue) {
            JPanel unitPanel = createUnitPanel(stack);
            JButton attackBtn = createActionButton(ATTACK);
            attackBtn.addActionListener(new Action(ATTACK, bc, stack));
            unitPanel.add(attackBtn);
            queuePanel.add(unitPanel);
        }
        JScrollPane scrollQueue = new JScrollPane(queuePanel);
        scrollQueue.setBorder(new EmptyBorder(10,50,10,50));
        main.add(scrollQueue);
    }

    public static JPanel createUnitPanel(Stack stack) {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        ImageIcon unitImg = new ImageIcon(stack.unit.path);
        JComponent unitIcon = new JLabel(unitImg);
        JComponent unitName = new JLabel(stack.unit.name);
        String displayNbUnits = "UNITS: " + String.valueOf(stack.number_units);
        JComponent nbUnits = new JLabel(displayNbUnits);
        panel.add(unitIcon);
        panel.add(unitName);
        panel.add(nbUnits);
        return panel;
    }

    private JButton createActionButton(int actionType) {
        JButton button = new JButton();
        button.setContentAreaFilled(false);
        button.setBorderPainted(false);
        switch (actionType) {
            case ATTACK:
                button.setIcon(attackIcon);
                button.setToolTipText("ATTACK");
                break;
            case DEFENSE:
                button.setIcon(defenseIcon);
                button.addActionListener(new Action(actionType, battleController));
                button.setToolTipText("DEFENSE");
                break;
            case WAIT:
                button.setIcon(spellIcon);
                button.addActionListener(new Action(actionType, battleController));
                button.setToolTipText("WAIT");
                break;
        }
        return button;
    }

    class Action implements ActionListener {
        BattleController bc;
        Stack target;
        int action;

        Action (int actionType, BattleController battleController) {
            this.action = actionType;
            this.bc = battleController;
        }

        Action (int actionType, BattleController battleController, Stack target) {
            this.action = actionType;
            this.bc = battleController;
            this.target = target;
        }

        public void actionPerformed (ActionEvent e) {
            switch (action) {
                case ATTACK:
                    this.bc.dealDamage(this.target);
                    this.bc.updateQueue();
                    break;
                case DEFENSE:
                    this.bc.initDefense();
                    break;
                case WAIT:
                    this.bc.initWait();
                    break;
            }
            try {
                if (this.bc.gameOver()) {
                    new GameOver(this.bc).setVisible(true);
                } else {
                    GameFrame play = new GameFrame(this.bc);
                    play.setVisible(true);
                }
                GameFrame.this.setVisible(false);
            }
            catch (Exception exp) {}
        }
    }
}