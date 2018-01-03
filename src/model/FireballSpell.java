package model;
import java.text.DecimalFormat;

/**
 * Created by pornoplasthecate-s on 6/20/17.
 */
public class FireballSpell extends Spell {

/*    public void Cast(Unit c) {
        c.StackHealth = c.Health * c.CombatSize;
        if (c.StackHealth > 0) {
            double damage = 20 * this.CastingHero.SpellPower;
            c.StackHealth = c.StackHealth - damage;
            CastingHero.Knowledge -= 20;
            c.CombatSize = c.StackHealth / c.Health;
            System.out.println();
            double temp = Math.ceil(c.StackHealth / c.Health);
            c.RoundedCombatSize = (int) temp;
            System.out.println(this.CastingHero.Name + " cast Fireball on " + c.Name + "." + " Damage done " + new DecimalFormat("##.##").format(damage));
            if (c.RoundedCombatSize > 0) {
                System.out.println(c.Name + "(s) left " + c.RoundedCombatSize);
            } else {
                System.out.println("Only one " + c.Name + " left.");
            }
        } else {
            System.out.println("All creatures in the stack are dead");
            this.CastingHero.Army.remove(c);

        }
    }*/
}
