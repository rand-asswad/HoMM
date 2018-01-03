package model;

import org.w3c.dom.*;

import javax.xml.parsers.*;
import java.io.*;
import java.util.*;
import java.lang.*;

public class Game {

    public ArrayList<Player> players;
    public ArrayList<Hero> heroDB;
    public ArrayList<Unit> creatureDB;

    public Game() throws Exception {
        Player p1 = new Player(1);
        Player p2 = new Player(2);
        this.players = new ArrayList<Player>(2);
        this.players.add(p1);
        this.players.add(p2);
        heroDB = new ArrayList<Hero>();
        creatureDB = new ArrayList<Unit>();
        this.loadDatabase();
    }

    public void loadCreatureDatabase() throws Exception {

        File fXmlFile = new File("./database/unitdb.xml");
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        Document doc = dBuilder.parse(fXmlFile);
        doc.getDocumentElement().normalize();
        NodeList CreatureList = doc.getElementsByTagName("Unit");
        for (int i = 0; i < CreatureList.getLength(); i++) {
            Node nCreature = CreatureList.item(i);
            if (nCreature.getNodeType() == nCreature.ELEMENT_NODE) {
                Element aCreature = (Element) nCreature;
                Unit e = new Unit();
                this.creatureDB.add(i, e);
                this.creatureDB.get(i).name = aCreature.getAttribute("name");
                this.creatureDB.get(i).attackSkill = Integer.parseInt(aCreature.getElementsByTagName("AttackSkill").item(0).getTextContent());
                this.creatureDB.get(i).defenceSkill = Integer.parseInt(aCreature.getElementsByTagName("DefenceSkill").item(0).getTextContent());
                this.creatureDB.get(i).shots = Integer.parseInt(aCreature.getElementsByTagName("Shots").item(0).getTextContent());
                this.creatureDB.get(i).MinDamage = Integer.parseInt(aCreature.getElementsByTagName("MinDamage").item(0).getTextContent());
                this.creatureDB.get(i).MaxDamage = Integer.parseInt(aCreature.getElementsByTagName("MaxDamage").item(0).getTextContent());
                this.creatureDB.get(i).initiative = Integer.parseInt(aCreature.getElementsByTagName("Initiative").item(0).getTextContent());
                this.creatureDB.get(i).setHealth(Integer.parseInt(aCreature.getElementsByTagName("Health").item(0).getTextContent()));
                this.creatureDB.get(i).text = aCreature.getElementsByTagName("Text").item(0).getTextContent();
                this.creatureDB.get(i).path = aCreature.getElementsByTagName("Path").item(0).getTextContent();
                NodeList SpellList = aCreature.getElementsByTagName("Spell");
                for (int j = 0; j < SpellList.getLength(); j++) {
                    Spell s = new Spell();
                    this.creatureDB.get(i).knownSpells.add(j, s);
                    this.creatureDB.get(i).knownSpells.get(j).SpellName = aCreature.getElementsByTagName("Spell").item(j).getTextContent();
                }
                NodeList AbilityList = aCreature.getElementsByTagName("Ability");
                for (int j = 0; j < AbilityList.getLength(); j++) {
                    Ability a = new Ability();
                    this.creatureDB.get(i).abilities.add(j, a);
                    this.creatureDB.get(i).abilities.get(j).name = aCreature.getElementsByTagName("Ability").item(j).getTextContent();
                }


            }

        }
    }

    public void loadHeroDatabase() throws Exception {
        File ffXmlFile = new File("./database/heroesdb.xml");
        DocumentBuilderFactory fdbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder fdBuilder = fdbFactory.newDocumentBuilder();
        Document fdoc = fdBuilder.parse(ffXmlFile);
        fdoc.getDocumentElement().normalize();
        NodeList HeroList = fdoc.getElementsByTagName("Hero");
        for (int i = 0; i < HeroList.getLength(); i++) {
            Node nHero = HeroList.item(i);
            if (nHero.getNodeType() == nHero.ELEMENT_NODE) {
                Element aHero = (Element) nHero;
                Hero h = new Hero();
                this.heroDB.add(i, h);
                this.heroDB.get(i).name = aHero.getAttribute("name");
                this.heroDB.get(i).attackSkill = Integer.parseInt(aHero.getElementsByTagName("AttackSkill").item(0).getTextContent());
                this.heroDB.get(i).defenceSkill = Integer.parseInt(aHero.getElementsByTagName("DefenceSkill").item(0).getTextContent());
                this.heroDB.get(i).initiative = Integer.parseInt(aHero.getElementsByTagName("Initiative").item(0).getTextContent());
                //this.heroDB.get(i).CanFly = Boolean.parseBoolean(aHero.getElementsByTagName("Flying").item(0).getTextContent());
                this.heroDB.get(i).text = aHero.getElementsByTagName("Text").item(0).getTextContent();
                this.heroDB.get(i).path = aHero.getElementsByTagName("Path").item(0).getTextContent();
                this.heroDB.get(i).Knowledge = Integer.parseInt(aHero.getElementsByTagName("Knowledge").item(0).getTextContent());
                this.heroDB.get(i).SpellPower = Integer.parseInt(aHero.getElementsByTagName("SpellPower").item(0).getTextContent());
                this.heroDB.get(i).Class = aHero.getElementsByTagName("Class").item(0).getTextContent();
                this.heroDB.get(i).Level = Integer.parseInt(aHero.getElementsByTagName("Level").item(0).getTextContent());
                NodeList HeroSpellList = aHero.getElementsByTagName("Spell");
                Spell s1 = new FireballSpell();
                Spell s2 = new RaiseDeadSpell();
                Spell s3 = new BlessingSpell();
                this.heroDB.get(i).knownSpells.add(s1);
                this.heroDB.get(i).knownSpells.add(s2);
                this.heroDB.get(i).knownSpells.add(s3);
                for (int j = 0; j < HeroSpellList.getLength(); j++) {
                    this.heroDB.get(i).knownSpells.get(j).SpellName = aHero.getElementsByTagName("Spell").item(j).getTextContent();
                }
                NodeList HeroAbilityList = aHero.getElementsByTagName("Ability");
                for (int j = 0; j < HeroAbilityList.getLength(); j++) {
                    Ability a = new Ability();
                    this.heroDB.get(i).abilities.add(j, a);
                    this.heroDB.get(i).abilities.get(j).name = aHero.getElementsByTagName("Ability").item(j).getTextContent();
                }
            }

        }
    }

    public void loadDatabase() throws Exception {
        loadCreatureDatabase();
        loadHeroDatabase();
    }

    public static int genRandom(int tier) {
        Random rand = new Random();
        if (tier == 1)
            return rand.nextInt(30 - 20 + 1) + 20;
        else if (tier == 2)
            return rand.nextInt(20 - 10 + 1) + 10;
        else if (tier == 3)
            return rand.nextInt(10)+1;
        else if (tier == 4)
            return rand.nextInt(5)+1;
        else
            return -1;
    }

    public void generateArmy(Hero hero) {
        int index = this.heroDB.indexOf(hero);
        for (int i = (4*index), j = 0; i < 4*(index+1); j = (index==0) ? (i+1):(i-4*index), i++) {
            hero.army.stacks.add(j, new Stack(creatureDB.get(i), genRandom(j + 1)));
            hero.army.stacks.get(j).master = this.heroDB.get(index);
            this.heroDB.set(index, hero);
        }
    }


    /*public Hero genHeroRF(String name) throws Exception {
        if (name.equals("Nikolai")) {
            Hero h = heroDB.get(0);
            for (int i = 0, j = 0; i < 4; j=i+1, i++) {
                h.army.stacks.add(j, new Stack(creatureDB.get(i), genRandom(j + 1)));
                h.army.stacks.get(j).master=h;
            }
            return h;
        } else if (name.equals("Markal")) {
            Hero h = heroDB.get(1);
            for (int i = 4, j = 0; i < 8; j=i+1-5, i++) {
                h.army.stacks.add(j, new Stack(creatureDB.get(i), genRandom(j + 1)));
                h.army.stacks.get(j).master=h;
            }
            return h;
        } else if (name.equals("Agrael")) {
            Hero h = heroDB.get(2);
            for (int i = 8, j = 0; i < 12; j=i+1-9, i++) {
                h.army.stacks.add(j, new Stack(creatureDB.get(i), genRandom(j + 1)));
                h.army.stacks.get(j).master=h;
            }
            return h;
        } else if (name.equals("Havez")) {
            Hero h = heroDB.get(3);
            for (int i = 12, j = 0; i < 16; j=i+1-13, i++) {
                h.army.stacks.add(j, new Stack(creatureDB.get(i), genRandom(j + 1)));
                h.army.stacks.get(j).master=h;
            }
            return h;

        }
        throw new Exception();
    }*/

}