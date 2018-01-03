package model;
import java.text.DecimalFormat;
import java.util.*;


public class Unit extends Entity {

    Random rand = new Random();

    public Unit() {
        this.Abilities = new ArrayList<Ability>();
        this.KnownSpells = new ArrayList<Spell>();
      // this.DefenceSkill += this.master.DefenceSkill;
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
        this.DefenceSkill = this.DefenceSkill + 0.2f * this.DefenceSkill;
    }

    public int getAttackDamage(Unit c) {
        if (AttackSkill >= DefenceSkill)
            return (int) (generateRandomDamageMinMax() * (1 + 0.05 * (this.AttackSkill - c.DefenceSkill)));
        else
            return (int) (generateRandomDamageMinMax() / (1 + 0.05 * (this.DefenceSkill - c.AttackSkill)));
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