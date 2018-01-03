package model;

import java.util.*;
import java.lang.*;

public class Game {

    public ArrayList<Player> players;
    public ArrayList<Hero> heroes;
    public ArrayList<Unit> creatures;

    public Game() throws Exception {
        this.players = new ArrayList<Player>(2);
        this.players.add(new Player(1));
        this.players.add(new Player(2));
        this.loadDatabase();
    }

    public void loadDatabase() throws Exception {
        this.creatures = ParseXML.importUnits();
        this.heroes = ParseXML.importHeroes();
    }

    public static int genRandom(int tier) {
        Random rand = new Random();
        switch (tier) {
            case 1: return rand.nextInt(30 - 20 + 1) + 20;
            case 2: return rand.nextInt(20 - 10 + 1) + 10;
            case 3: return rand.nextInt(10) + 1;
            case 4: return rand.nextInt(5) + 1;
            default: return -1;
        }
    }

    public void generateArmy(Hero hero) {
        int index = this.heroes.indexOf(hero);
        for (int i = (4*index), j = 0; i < 4*(index+1); j = (index==0) ? (i+1):(i-4*index), i++) {
            hero.army.stacks.add(j, new Stack(creatures.get(i), genRandom(j + 1)));
            hero.army.stacks.get(j).master = this.heroes.get(index);
            this.heroes.set(index, hero);
        }
    }

}