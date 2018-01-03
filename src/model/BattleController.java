package model;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * Controls the battle turns, etc.
 */
public class BattleController {


    private ArrayList<Army> armies;
    public ArrayList<Stack> queue;
    public Stack has_turn;
    public Game game;


    public BattleController(Game game) {
        this.game = game;
        this.initArmies(game);
        this.initBattle();
    }

    private void initArmies(Game game) {
        this.armies = new ArrayList<Army>(game.Players.size());
        for (Player player : this.game.Players) {
            this.armies.add(player.selected_hero.army);
        }
    }

    private void initQueue() {
        this.queue = new ArrayList<Stack>();
        for (Army army : this.armies) {
            this.queue.addAll(army.stacks);
        }
        Collections.sort(this.queue, scp);
        this.has_turn = this.queue.get(0);
    }

    /*public ArrayList<Stack> initQueue(Army a1, Army a2) {
        ArrayList<Stack> queue = new ArrayList<Stack>(a1.stacks.size() + a2.stacks.size());
        queue.addAll(a1.stacks);
        queue.addAll(a2.stacks);
        Collections.sort(queue, scp);
        has_turn = queue.get(0);
        return queue;
    }*/

    public void updateQueue() {
        queue.get(0).bar.reset();
        ATB.advanceAction(queue);
        Collections.sort(queue, scatb);
        fillRGauge();
        giveTurn(queue.get(0));
    }

    public void initAtbBar() {
        for (Army a : this.armies) {
            for (Stack s : a.stacks) {
                s.bar.init();
            }
        }
    }

    public void initBattle() {
        initAtbBar();
        //queue = initQueue(armies.get(0), armies.get(1));
        initQueue();
    }

    /**
     * Attack enemy with selected stack of unit
     */
    public void dealDamage(Stack target) {
        target.receiveDamage(has_turn.getAttackDamage(target));
        System.out.println(has_turn.unit.Name+" dealt damage to "+target.unit.Name);
        if (target.isDead()) {
            int a = armies.get(0).findStackIndex(target);
            if (a != -1) {
                armies.get(0).stacks.remove(a);
            } else {
                armies.get(1).stacks.remove(armies.get(1).findStackIndex(target));
            }
            queue.remove(target);
        } else {
            int b = armies.get(0).findStackIndex(target);
            if (b != -1) {
                armies.get(0).refreshNumbers(target);
            } else {
                armies.get(1).refreshNumbers(target);
            }
            has_turn=target;
            retaliate(target);
        }
    }

    public void initDefense() {
        queue.get(0).unit.Defend();
        updateQueue();
    }

    public void initWait() {
        queue.get(0).bar.value = 0.5f;
        ATB.advanceAction(queue);
        Collections.sort(queue, scatb);
        fillRGauge();
        giveTurn(queue.get(0));
    }

    public void retaliate(Stack s) {
        if (s.can_retaliate) {
            queue.get(0).can_retaliate = false;
            //giveTurn(s);
            //queue.get(0).receiveDamage(s.getAttackDamage(queue.get(0)));
            dealDamage(queue.get(0));
        }
    }

    public void fillRGauge() {
        for (Stack s : queue) s.can_retaliate = true;
    }

    public void giveTurn(Stack s) {
        has_turn = s;
    }

    public static Comparator<Stack> scp = new Comparator<Stack>() {

        @Override
        public int compare(Stack s1, Stack s2) {
            return Integer.compare(s2.unit.Initiative, s1.unit.Initiative);
        }
    };

    public static Comparator<Stack> scatb = new Comparator<Stack>() {

        @Override
        public int compare(Stack s1, Stack s2) {
            return Float.compare(s2.bar.value, s1.bar.value);
        }
    };

    public boolean gameOver() {
        Hero master = this.queue.get(0).master;
        for (Stack stack : this.queue) {
            if (!stack.master.equals(master)) {
                return false;
            }
        }
        return true;
    }

    public Player winner() {
        if (this.gameOver()) {
            return this.queue.get(0).master.player;
        }
        else {
            return null;
        }
    }
}