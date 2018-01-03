package model;
/**
 * Created by pornoplasthecate-s on 6/20/17.
 */
public class RaiseDeadSpell extends Spell {

   /* public void Cast(Unit c) {
        c.StackHealth = c.Health * c.CombatSize;
        double LifePower = 30*this.CastingHero.SpellPower;
        double RaisedCreatures = Math.ceil(LifePower/c.Health);
        int RoundedRaisedCreatures = (int) RaisedCreatures;

        c.StackHealth = c.StackHealth + LifePower;
        c.CombatSize = c.StackHealth / c.Health;
        double temp = Math.ceil(c.StackHealth / c.Health);
        c.RoundedCombatSize = (int) temp;
        System.out.println(this.CastingHero.name + " cast Raise Dead on " + c.name + "." + "\n Creatures revived " + RoundedRaisedCreatures+ ". \n Population is now  "+ c.RoundedCombatSize);
        CastingHero.Knowledge -= 30;
    }*/
}
