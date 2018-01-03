package model;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Random;

public class Stack {

    // UnitIcon icon;
    public ATB bar = new ATB();
    public Hero master;
    public Unit unit;
    public int number_units;
    public int number_units_dead;
    public int first_health;
    public boolean can_retaliate = true;


    public Stack(Unit unit, int number) {
        this.unit = unit;
        this.number_units = number;


        // actions_queue = new ArrayList<CustomAnimation>();
        first_health = unit.getHealth();
        // view = new StackView( unit.getMapWidth(), unit.getMapHeight(), number );
    }

    public void receiveDamage(int damage) {
        if (damage >= first_health)
            killUnits(damage);
        else
            first_health -= damage;
    }

    private void killUnits(int damage) {
        int deaths = 1;    // dead first unit
        damage -= (first_health);

        /**
         *  Dead units while @param damage > @param unit life
         */
        while (damage > unit.getHealth()) {
            damage -= unit.getHealth();
            deaths++;
        }

        updateLastUnitWoundLife(damage);
        updateNumberWithDeaths(deaths);

    }

    private void updateLastUnitWoundLife(int damage) {
        if (damage < unit.getHealth())
            first_health = unit.getHealth() - damage;
        else
            first_health = unit.getHealth();
    }

    private void updateNumberWithDeaths(int deaths) {
        if (deaths >= number_units) {
            number_units = 0;
        } else {
            number_units -= deaths;
        }
    }

    public int getAttackDamage(Stack c) {
        if (unit.AttackSkill > c.unit.DefenceSkill)
            return (number_units * c.unit.getAttackDamage(c.unit));
        else
            return number_units * unit.getAttackDamage(c.unit);
    }

    public int getNumber() {
        return number_units;
    }

    public void setNumber(int number) {
        this.number_units = number;
    }

    public Unit getUnit() {
        return unit;
    }

    public void addUnits(int amount) {
        number_units += amount;
    }

    public boolean isDead() {
        if (number_units > 0)
            return false;
        else
            return true;
    }
}
