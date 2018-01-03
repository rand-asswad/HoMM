package model;
import java.util.*;


public class Unit extends Entity {

    Random rand = new Random();

    public Unit() {
        this.abilities = new ArrayList<Ability>();
        this.knownSpells = new ArrayList<Spell>();
      // this.defenceSkill += this.master.defenceSkill;
    }

    Hero master;
    private int Health;
    public float MinDamage;
    public float MaxDamage;

    public int getHealth() {
        return Health;
    }

    public void setHealth(int health) {
        this.Health = health;
    }

    public void Defend() {
        this.defenceSkill = this.defenceSkill + 0.2f * this.defenceSkill;
    }

    public int getAttackDamage(Unit c) {
        if (attackSkill >= defenceSkill)
            return (int) (generateRandomDamageMinMax() * (1 + 0.05 * (this.attackSkill - c.defenceSkill)));
        else
            return (int) (generateRandomDamageMinMax() / (1 + 0.05 * (this.defenceSkill - c.attackSkill)));
    }
    public float generateRandomDamageMinMax(){
        return  rand.nextFloat()*(MaxDamage-MinDamage)+MinDamage;
    }
    public void loadIcons() {
    }

    public void CastSpell(String spellname, Unit c) {
        System.out.println("Feature not implemented yet");
    }
}