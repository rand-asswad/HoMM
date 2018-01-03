package model;
import java.util.*;

public abstract class Entity {


    public Entity() {
    }

    public float AttackSkill;
    public float DefenceSkill;
    public int Initiative;
    public int Shots;
    public ArrayList<Spell> KnownSpells;
    public ArrayList<Ability> Abilities;
    public boolean CanBeKilled;
    public String Name;
    public String Text;
    public String Path;
    /*public void Defend() {
    }*/

    public void Wait() {
    }

    public abstract void CastSpell(String spellname, Unit c);


    public String toString() {
        return this.Name;
    }

}