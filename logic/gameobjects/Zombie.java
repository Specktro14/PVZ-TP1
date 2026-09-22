package pvz.logic.gameobjects;

/**
 * Zombie
 */
public class Zombie {
  private int row;
  private int col;
  private int resistance;
  private int damage;
  private double speed;

  public Zombie(int row, int col) {
    this.row = row;
    this.col = col;
    this.resistance = 5;
    this.damage = 1;
    this.speed = 0.5;
  }
}