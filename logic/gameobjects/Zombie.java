package pvz.logic.gameobjects;

import pvz.logic.Game;
import pvz.utils.Position;
import pvz.view.Messages;

/**
 * Zombie
 */
public class Zombie {
  private int row;
  private int col;
  private int hp;
  
  private static int ENDURANCE = 5;
  private static int DAMAGE = 1;
  
  private final int MOVE_EVERY_CYCLES = 2;
  private int counter;

  private Game game;

  public Zombie(Position pos, Game game) {
    this.row = pos.getRow();
    this.col = pos.getCol();
    this.game = game;
    this.hp = ENDURANCE;
    this.counter = 0;
  }

  public String getIcon() {
    return Messages.ZOMBIE_ICON.formatted(hp);
  }

  public boolean isAlive() {
    return hp > 0;
  }
  
  public boolean isInPosition(Position pos) {
    return row == pos.getRow() && col == pos.getCol();
  }

  public void receiveAttack(int damage) {
    hp -= damage;
  }

  public void update() {
    if (counter == MOVE_EVERY_CYCLES && isAlive()) {
      if (!game.isEmpty(new Position(row, col))) {
        // TODO Añadir ataque del zombie
      } else {
        col -= 1;
      }
      counter = 0;
    }
    counter++;
  }
}