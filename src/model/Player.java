package model;
public class Player {

    public String name;
    public int id;
    public boolean hasTurn;

    public Hero selected_hero;

    public Player(int id) {
        this.id = id;
        this.name = "Player " + String.valueOf(id);
    }

    public void selectHero(Hero hero) {
        this.selected_hero = hero;
        this.selected_hero.player = this;
    }
}
