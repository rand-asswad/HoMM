package swing;
import model.*;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import org.jdesktop.swingx.JXLabel;
import java.util.ArrayList;

public class HeroesFrame extends JFrame {

    public HeroesFrame(Game game, int playerID) throws Exception {
        super("Heroes of Might & Magic - Heroes");
        JPanel main = Frame.loadDefault(this);
        main.setLayout(new GridLayout(1, game.heroDB.size()));

        for (Hero hero : game.heroDB) {
            //hero = game.genHeroRF(hero.name);   // initializes hero's army
            game.generateArmy(hero);
            JPanel heroPanel = new HeroPanel(hero, game, playerID);    // hero description panel
            main.add(heroPanel);
        }
    }

    // hero panel subclass
    private class HeroPanel extends JPanel {

        private HeroPanel(Hero hero, Game game, int playerID) {
            super();
            this.setBorder(new EmptyBorder(20,20,20,20));

            this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

            JComponent heroIcon = new JLabel(new ImageIcon(hero.path));
            heroIcon.setAlignmentX(Component.CENTER_ALIGNMENT);
            this.add(heroIcon);

            JLabel heroName = new JLabel(Frame.html(hero.name, "h1"));
            heroName.setAlignmentX(Component.CENTER_ALIGNMENT);
            this.add(heroName);

            JXLabel heroText = new JXLabel();
            heroText.setLineWrap(true);
            heroText.setText(hero.text);
            heroText.setTextAlignment(JXLabel.TextAlignment.JUSTIFY);
            heroText.setBorder(new EmptyBorder(10,10,10,10));
            JScrollPane scrollText = new JScrollPane(heroText);
            scrollText.setAlignmentX(Component.CENTER_ALIGNMENT);
            this.add(scrollText);

            ArrayList<Attribute> attributes = initAttributes(hero);
            JPanel attributesPanel = formatAttributes(attributes);
            //JScrollPane scrollAttributes = new JScrollPane(attributesPanel);
            attributesPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
            this.add(attributesPanel);

            JButton selectHero = new JButton("Select " + hero.name);
            selectHero.setAlignmentX(Component.CENTER_ALIGNMENT);
            selectHero.addActionListener(new HeroInitializer(game, playerID, hero));
            this.add(selectHero);
        }

        // creates and formats attributes panel
        private JPanel formatAttributes(ArrayList<Attribute> attributes) {
            JPanel attributesPanel = new JPanel();
            GroupLayout layout = new GroupLayout(attributesPanel);
            attributesPanel.setLayout(layout);
            layout.setAutoCreateGaps(true);
            layout.setAutoCreateContainerGaps(true);

            GroupLayout.SequentialGroup hGroup = layout.createSequentialGroup();
            GroupLayout.ParallelGroup labels = layout.createParallelGroup();
            GroupLayout.ParallelGroup attributesCol = layout.createParallelGroup();
            GroupLayout.SequentialGroup vGroup = layout.createSequentialGroup();
            for (Attribute a : attributes) {
                labels.addComponent(a.label);
                attributesCol.addComponent(a.attribute);

                GroupLayout.ParallelGroup attributeGroup = layout.createParallelGroup(GroupLayout.Alignment.BASELINE);
                attributeGroup.addComponent(a.label);
                attributeGroup.addComponent(a.attribute);
                vGroup.addGroup(attributeGroup);
            }
            hGroup.addGroup(labels);
            hGroup.addGroup(attributesCol);
            layout.setHorizontalGroup(hGroup);
            layout.setVerticalGroup(vGroup);

            return attributesPanel;
        }

        // initializes attributes ArrayList
        private ArrayList<Attribute> initAttributes(Hero hero) {
            ArrayList<Attribute> attributes = new ArrayList<Attribute>();
            attributes.add(new Attribute("Attack skill: ", hero.attackSkill));
            attributes.add(new Attribute("Defence skill: ", hero.defenceSkill));
            attributes.add(new Attribute("Knowledge: ", hero.Knowledge));
            attributes.add(new Attribute("initiative: ", hero.initiative));
            attributes.add(new Attribute("Level: ", hero.Level));
            attributes.add(new Attribute("Spell power: ", hero.SpellPower));
            attributes.add(new Attribute("Class: ", hero.Class));
            attributes.add(new Attribute("Abilities: ", hero.abilities.toString()));
            attributes.add(new Attribute("Creatures: ", hero.army.unitNames().toString()));
            return attributes;
        }

        // subclass that contains Swing elements to store hero attribute
        private class Attribute {
            JLabel label;
            JLabel attribute;

            Attribute(String label, String attribute) {
                this.label = new JLabel(label);
                this.attribute = new JLabel(attribute);
            }

            Attribute(String label, Number value) {
                this.label = new JLabel(label);
                this.attribute = new JLabel(String.valueOf(value));
            }
        }

        // action listener for hero selector button
        private class HeroInitializer implements ActionListener {
            Game game;
            int playerID;
            Hero hero;

            private HeroInitializer (Game game, int playerID, Hero hero) {
                this.game = game;
                this.playerID = playerID;
                this.hero = hero;
            }

            public void actionPerformed (ActionEvent e) {
                try {
                    for (Player player : game.players) {
                        if (player.Id==playerID) {
                            player.selectHero(hero);
                            System.out.printf("%s selected %s\n", player.name, hero.name);
                        }
                    }
                    InitGame initGame = new InitGame(this.game);
                    initGame.setVisible(true);
                    HeroesFrame.this.setVisible(false);
                }
                catch (Exception exp) {}
            }
        }
    }

}