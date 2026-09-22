package pvz.logic.gameobjects;

import pvz.logic.Game;

/**
 * Zombie
 */
public class Zombie {
  private int row;
  private int col;
  private static final int ENDURANCE = 5;
  private static final int DAMAGE = 1;
  private static final double SPEED = 0.5;

  private Game game;

  public Zombie(int row, int col, Game game) {
    this.row = row;
    this.col = col;
    this.game = game;
  }
}