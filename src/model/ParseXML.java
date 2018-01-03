package model;

import org.w3c.dom.*;
import javax.xml.parsers.*;
import java.io.*;
import java.util.*;
import java.lang.*;

public class ParseXML {
    private final static File unitDB = new File("database/unitdb.xml");
    private final static File heroDB = new File("database/heroesdb.xml");

    static ArrayList<Unit> importUnits() throws Exception {
        ArrayList<Unit> creatures = new ArrayList<Unit>();
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        Document doc = dBuilder.parse(unitDB);
        doc.getDocumentElement().normalize();
        NodeList CreatureList = doc.getElementsByTagName("Unit");
        for (int i = 0; i < CreatureList.getLength(); i++) {
            Node nCreature = CreatureList.item(i);
            if (nCreature.getNodeType() == nCreature.ELEMENT_NODE) {
                Element aCreature = (Element) nCreature;
                Unit e = new Unit();
                creatures.add(i, e);
                creatures.get(i).name = aCreature.getAttribute("name");
                creatures.get(i).attackSkill = Integer.parseInt(aCreature.getElementsByTagName("AttackSkill").item(0).getTextContent());
                creatures.get(i).defenceSkill = Integer.parseInt(aCreature.getElementsByTagName("DefenceSkill").item(0).getTextContent());
                creatures.get(i).shots = Integer.parseInt(aCreature.getElementsByTagName("Shots").item(0).getTextContent());
                creatures.get(i).MinDamage = Integer.parseInt(aCreature.getElementsByTagName("MinDamage").item(0).getTextContent());
                creatures.get(i).MaxDamage = Integer.parseInt(aCreature.getElementsByTagName("MaxDamage").item(0).getTextContent());
                creatures.get(i).initiative = Integer.parseInt(aCreature.getElementsByTagName("Initiative").item(0).getTextContent());
                creatures.get(i).setHealth(Integer.parseInt(aCreature.getElementsByTagName("Health").item(0).getTextContent()));
                creatures.get(i).text = aCreature.getElementsByTagName("Text").item(0).getTextContent();
                creatures.get(i).path = aCreature.getElementsByTagName("Path").item(0).getTextContent();
                NodeList SpellList = aCreature.getElementsByTagName("Spell");
                for (int j = 0; j < SpellList.getLength(); j++) {
                    Spell s = new Spell();
                    creatures.get(i).knownSpells.add(j, s);
                    creatures.get(i).knownSpells.get(j).SpellName = aCreature.getElementsByTagName("Spell").item(j).getTextContent();
                }
                NodeList AbilityList = aCreature.getElementsByTagName("Ability");
                for (int j = 0; j < AbilityList.getLength(); j++) {
                    Ability a = new Ability();
                    creatures.get(i).abilities.add(j, a);
                    creatures.get(i).abilities.get(j).name = aCreature.getElementsByTagName("Ability").item(j).getTextContent();
                }
            }
        }
        return creatures;
    }

    static ArrayList<Hero> importHeroes() throws Exception {
        ArrayList<Hero> heroes = new ArrayList<Hero>();
        DocumentBuilderFactory fdbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder fdBuilder = fdbFactory.newDocumentBuilder();
        Document fdoc = fdBuilder.parse(heroDB);
        fdoc.getDocumentElement().normalize();
        NodeList HeroList = fdoc.getElementsByTagName("Hero");
        for (int i = 0; i < HeroList.getLength(); i++) {
            Node nHero = HeroList.item(i);
            if (nHero.getNodeType() == nHero.ELEMENT_NODE) {
                Element aHero = (Element) nHero;
                Hero h = new Hero();
                heroes.add(i, h);
                heroes.get(i).name = aHero.getAttribute("name");
                heroes.get(i).attackSkill = Integer.parseInt(aHero.getElementsByTagName("AttackSkill").item(0).getTextContent());
                heroes.get(i).defenceSkill = Integer.parseInt(aHero.getElementsByTagName("DefenceSkill").item(0).getTextContent());
                heroes.get(i).initiative = Integer.parseInt(aHero.getElementsByTagName("Initiative").item(0).getTextContent());
                //heroes.get(i).CanFly = Boolean.parseBoolean(aHero.getElementsByTagName("Flying").item(0).getTextContent());
                heroes.get(i).text = aHero.getElementsByTagName("Text").item(0).getTextContent();
                heroes.get(i).path = aHero.getElementsByTagName("Path").item(0).getTextContent();
                heroes.get(i).Knowledge = Integer.parseInt(aHero.getElementsByTagName("Knowledge").item(0).getTextContent());
                heroes.get(i).SpellPower = Integer.parseInt(aHero.getElementsByTagName("SpellPower").item(0).getTextContent());
                heroes.get(i).Class = aHero.getElementsByTagName("Class").item(0).getTextContent();
                heroes.get(i).Level = Integer.parseInt(aHero.getElementsByTagName("Level").item(0).getTextContent());
                NodeList HeroSpellList = aHero.getElementsByTagName("Spell");
                Spell s1 = new FireballSpell();
                Spell s2 = new RaiseDeadSpell();
                Spell s3 = new BlessingSpell();
                heroes.get(i).knownSpells.add(s1);
                heroes.get(i).knownSpells.add(s2);
                heroes.get(i).knownSpells.add(s3);
                for (int j = 0; j < HeroSpellList.getLength(); j++) {
                    heroes.get(i).knownSpells.get(j).SpellName = aHero.getElementsByTagName("Spell").item(j).getTextContent();
                }
                NodeList HeroAbilityList = aHero.getElementsByTagName("Ability");
                for (int j = 0; j < HeroAbilityList.getLength(); j++) {
                    Ability a = new Ability();
                    heroes.get(i).abilities.add(j, a);
                    heroes.get(i).abilities.get(j).name = aHero.getElementsByTagName("Ability").item(j).getTextContent();
                }
            }
        }
        return heroes;
    }

}
