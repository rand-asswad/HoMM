package model;
public class Player {

    public String name;
    public int Id;
    public boolean hasTurn;

    public Hero selected_hero;

    public Player(int Id) {
        this.Id = Id;
        this.name = "Player " + String.valueOf(Id);
    }

    public void selectHero(Hero hero) {
        this.selected_hero = hero;
        this.selected_hero.player = this;
    }
}
