package model;
import java.util.ArrayList;
import java.util.Random;

public class ATB {
    float value;
    private Random rand = new Random();

    public void init() {
        value = 0.25f * rand.nextFloat()*((1-0.001f)+0.001f);
    }

    public void reset() {
        value = 0;
    }

    public static void update(ArrayList<Stack> stacks) {

        for (Stack s : stacks) {
            s.bar.value += s.unit.initiative * 0.05f;

        }

    }

    public static boolean blasting(ArrayList<Stack> stacks) {
        for (Stack s : stacks) {
            if (s.bar.value > 1)
                return true;
        }
        return false;
    }


    public static void advanceAction(ArrayList<Stack> stacks) {
        while (!blasting(stacks)) {
            update(stacks);
        }
    }
}