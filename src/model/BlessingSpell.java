package model;
/**
 * Created by pornoplasthecate-s on 6/20/17.
 */
public class BlessingSpell extends Spell {
    public void Cast(Unit c) {
        long tStart = System.currentTimeMillis();
        long tEnd = tStart + 60 * 1000;
        int spellDmg = (int)(c.MinDamage);
        CastingHero.Knowledge -= 30;
        System.out.println("The creature will now deal maximum damage for 1 minute regardless of the turn");
        while (System.currentTimeMillis() < tEnd) {
            c.MinDamage = c.MaxDamage;
        }
        c.MinDamage = spellDmg;
    }
}
