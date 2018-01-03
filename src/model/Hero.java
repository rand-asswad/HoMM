package model;
import java.util.*;

/**
 *
 */
public class Hero extends Entity {

    public Hero() {
        this.abilities = new ArrayList<Ability>();
        this.knownSpells = new ArrayList<Spell>();
        Random generator = new Random();
        this.initiative = generator.nextInt();
    }

    public int Knowledge;
    public Spell currentSpell;
    public int SpellPower;
    public Army army = new Army();
    public String Class;
    public int Level;
    public Player player;


    public void CastSpell(String spellname, Unit c) {
        if (Knowledge > 0) {
            try {
                this.currentSpell = this.identifySpell(spellname);
                this.currentSpell.CastingHero = this;
                currentSpell.Cast(c);
            } catch (Exception e) {
                e.getMessage();
            }
        } else {
            System.out.println("Not enough mana to cast the spell !");
        }
    }

    public Spell identifySpell(String spellname) throws Exception {
        for (Spell spell :
                this.knownSpells
                ) {
            if (spell.SpellName.equals(spellname)) {
                return spell;
            }

        }
        throw new Exception("Spell not found exception");
    }

    public Stack identifyCreature(String name) throws Exception {
        for (Stack c : this.army.stacks
                ) {
            if (c.unit.equals(name)) {
                return c;
            }
        }
        throw new Exception("Unit non existent");
    }
}