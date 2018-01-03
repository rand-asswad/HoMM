package model;

import org.w3c.dom.*;

import javax.xml.parsers.*;
import java.io.*;
import java.lang.reflect.Array;
import java.util.*;
import java.lang.*;

public class Game {

    public ArrayList<Player> Players;
    public ArrayList<Hero> HeroDB;
    public ArrayList<Unit> CreatureDB;

    public Game() throws Exception {
        Player p1 = new Player(1);
        Player p2 = new Player(2);
        this.Players = new ArrayList<Player>(2);
        this.Players.add(p1);
        this.Players.add(p2);
        HeroDB = new ArrayList<Hero>();
        CreatureDB = new ArrayList<Unit>();
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
                this.CreatureDB.add(i, e);
                this.CreatureDB.get(i).Name = aCreature.getAttribute("name");
                this.CreatureDB.get(i).AttackSkill = Integer.parseInt(aCreature.getElementsByTagName("AttackSkill").item(0).getTextContent());
                this.CreatureDB.get(i).DefenceSkill = Integer.parseInt(aCreature.getElementsByTagName("DefenceSkill").item(0).getTextContent());
                this.CreatureDB.get(i).Shots = Integer.parseInt(aCreature.getElementsByTagName("Shots").item(0).getTextContent());
                this.CreatureDB.get(i).MinDamage = Integer.parseInt(aCreature.getElementsByTagName("MinDamage").item(0).getTextContent());
                this.CreatureDB.get(i).MaxDamage = Integer.parseInt(aCreature.getElementsByTagName("MaxDamage").item(0).getTextContent());
                this.CreatureDB.get(i).Initiative = Integer.parseInt(aCreature.getElementsByTagName("Initiative").item(0).getTextContent());
                this.CreatureDB.get(i).setHealth(Integer.parseInt(aCreature.getElementsByTagName("Health").item(0).getTextContent()));
                this.CreatureDB.get(i).Text = aCreature.getElementsByTagName("Text").item(0).getTextContent();
                this.CreatureDB.get(i).Path = aCreature.getElementsByTagName("Path").item(0).getTextContent();
                NodeList SpellList = aCreature.getElementsByTagName("Spell");
                for (int j = 0; j < SpellList.getLength(); j++) {
                    Spell s = new Spell();
                    this.CreatureDB.get(i).KnownSpells.add(j, s);
                    this.CreatureDB.get(i).KnownSpells.get(j).SpellName = aCreature.getElementsByTagName("Spell").item(j).getTextContent();
                }
                NodeList AbilityList = aCreature.getElementsByTagName("Ability");
                for (int j = 0; j < AbilityList.getLength(); j++) {
                    Ability a = new Ability();
                    this.CreatureDB.get(i).Abilities.add(j, a);
                    this.CreatureDB.get(i).Abilities.get(j).name = aCreature.getElementsByTagName("Ability").item(j).getTextContent();
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
                this.HeroDB.add(i, h);
                this.HeroDB.get(i).Name = aHero.getAttribute("name");
                this.HeroDB.get(i).AttackSkill = Integer.parseInt(aHero.getElementsByTagName("AttackSkill").item(0).getTextContent());
                this.HeroDB.get(i).DefenceSkill = Integer.parseInt(aHero.getElementsByTagName("DefenceSkill").item(0).getTextContent());
                this.HeroDB.get(i).Initiative = Integer.parseInt(aHero.getElementsByTagName("Initiative").item(0).getTextContent());
                //this.HeroDB.get(i).CanFly = Boolean.parseBoolean(aHero.getElementsByTagName("Flying").item(0).getTextContent());
                this.HeroDB.get(i).Text = aHero.getElementsByTagName("Text").item(0).getTextContent();
                this.HeroDB.get(i).Path = aHero.getElementsByTagName("Path").item(0).getTextContent();
                this.HeroDB.get(i).Knowledge = Integer.parseInt(aHero.getElementsByTagName("Knowledge").item(0).getTextContent());
                this.HeroDB.get(i).SpellPower = Integer.parseInt(aHero.getElementsByTagName("SpellPower").item(0).getTextContent());
                this.HeroDB.get(i).Class = aHero.getElementsByTagName("Class").item(0).getTextContent();
                this.HeroDB.get(i).Level = Integer.parseInt(aHero.getElementsByTagName("Level").item(0).getTextContent());
                NodeList HeroSpellList = aHero.getElementsByTagName("Spell");
                Spell s1 = new FireballSpell();
                Spell s2 = new RaiseDeadSpell();
                Spell s3 = new BlessingSpell();
                this.HeroDB.get(i).KnownSpells.add(s1);
                this.HeroDB.get(i).KnownSpells.add(s2);
                this.HeroDB.get(i).KnownSpells.add(s3);
                for (int j = 0; j < HeroSpellList.getLength(); j++) {
                    this.HeroDB.get(i).KnownSpells.get(j).SpellName = aHero.getElementsByTagName("Spell").item(j).getTextContent();
                }
                NodeList HeroAbilityList = aHero.getElementsByTagName("Ability");
                for (int j = 0; j < HeroAbilityList.getLength(); j++) {
                    Ability a = new Ability();
                    this.HeroDB.get(i).Abilities.add(j, a);
                    this.HeroDB.get(i).Abilities.get(j).name = aHero.getElementsByTagName("Ability").item(j).getTextContent();
                }
            }

        }
    }

    public void loadDatabase() throws Exception {
        loadCreatureDatabase();
        loadHeroDatabase();
        //for (Hero hero : this.HeroDB) this.genHeroArmy(hero);
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

    public void genHeroArmy(Hero hero) throws Exception {
        int index = this.HeroDB.indexOf(hero);
        for (int i = (4*index), j = 0; i < 4*(index+1); j = i-(4*index), i+=4) {
            if (index==0) j = i + 1;
            hero.army.stacks.add(j, new Stack(CreatureDB.get(i), genRandom(j + 1)));
            hero.army.stacks.get(j).master = this.HeroDB.get(index);
            this.HeroDB.set(index, hero);
        }
    }


    public Hero genHeroRF(String name) throws Exception {
        if (name.equals("Nikolai")) {
            Hero h = HeroDB.get(0);
            for (int i = 0, j = 0; i < 4; j=i+1, i++) {
                h.army.stacks.add(j, new Stack(CreatureDB.get(i), genRandom(j + 1)));
                h.army.stacks.get(j).master=h;
            }
            return h;
        } else if (name.equals("Markal")) {
            Hero h = HeroDB.get(1);
            for (int i = 4, j = 0; i < 8; j=i+1-5, i++) {
                h.army.stacks.add(j, new Stack(CreatureDB.get(i), genRandom(j + 1)));
                h.army.stacks.get(j).master=h;
            }
            return h;
        } else if (name.equals("Agrael")) {
            Hero h = HeroDB.get(2);
            for (int i = 8, j = 0; i < 12; j=i+1-9, i++) {
                h.army.stacks.add(j, new Stack(CreatureDB.get(i), genRandom(j + 1)));
                h.army.stacks.get(j).master=h;
            }
            return h;
        } else if (name.equals("Havez")) {
            Hero h = HeroDB.get(3);
            for (int i = 12, j = 0; i < 16; j=i+1-13, i++) {
                h.army.stacks.add(j, new Stack(CreatureDB.get(i), genRandom(j + 1)));
                h.army.stacks.get(j).master=h;
            }
            return h;

        }
        throw new Exception();
    }

}