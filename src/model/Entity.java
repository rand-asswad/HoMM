package model;
import java.util.*;

public abstract class Entity {


    public Entity() {
    }

    public float attackSkill;
    public float defenceSkill;
    public int initiative;
    public int shots;
    public ArrayList<Spell> knownSpells;
    public ArrayList<Ability> abilities;
    public boolean canBeKilled;
    public String name;
    public String text;
    public String path;
    /*public void Defend() {
    }*/

    public void Wait() {
    }

    public abstract void CastSpell(String spellname, Unit c);


    @Override
    public String toString() {
        return this.name;
    }

}