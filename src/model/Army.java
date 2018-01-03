package model;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Army {

    /**
     * An army is a group of stacks.
     * It can be associated to a hero
     **/

    ArrayList<Stack> stacks;
    final int N_STACKS = 4;
    int selected_stack;

    Army() {
        stacks = new ArrayList<Stack>(N_STACKS);
    }

    /**
     * Add stack to the last position of the army
     */
    public void addStack(Stack stack) {
        if (stacks.size() < N_STACKS) {
            stacks.add(stack);
        }
    }

    public void swapStackPosition(int a, int b) {
        Stack tmp = stacks.get(a);

        stacks.set(a, stacks.get(b));
        stacks.set(b, tmp);
    }

    public Stack highestInitiative() {
        return Collections.max(stacks, scp);
    }

    public void initQueue(Army a) {
        Collections.sort(a.stacks, scp);
    }

    public void updateQueue() {

    }

    /**
     * Combat methods
     */

    public void selectNextStack() {
        selected_stack++;

        if (selected_stack >= stacks.size())
            selected_stack = 0;
    }

    public void deleteStack(Stack stack) {
        stacks.remove(stack);
    }
    public ArrayList<Stack> getStacks() {
        return stacks;
    }


    /**
     * Set army side
     * Set stack side
     */

    public int getAmount() {
        int amount = 0;

        for (Stack stack : stacks)
            amount += stack.getNumber();

        return amount;
    }

    public static Comparator<Stack> scp = new Comparator<Stack>() {

        @Override
        public int compare(Stack s1, Stack s2) {
            return Double.compare(s1.unit.Initiative, s2.unit.Initiative);
        }
    };

    public int findStackIndex(Stack f) {
        int i = 0;
        int nf = -1;
        for (Stack s : stacks) {
            if (s.unit.Name.equals(f.unit.Name))
                return i;
            i++;
        }
        return nf;
    }
    public void refreshNumbers(Stack f){
        int a = findStackIndex(f);
        if (a!= -1){
            stacks.get(a).number_units=f.number_units;
        }
    }

    public ArrayList<String> unitNames() {
        ArrayList<String> names = new ArrayList<>();
        for (Stack s : stacks) {
            names.add(s.unit.Name);
        }
        return names;
    }

}
