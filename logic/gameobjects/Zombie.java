package pvz.logic.gameobjects;

import pvz.logic.Game;
import pvz.utils.Position;
import pvz.view.Messages;

/**
 * Zombie
 */
public class Zombie {

  private Position pos;
  private int hp;

  private static int ENDURANCE = 5;
  private static int DAMAGE = 1;

  private final int MOVE_EVERY_CYCLES = 2;
  private int counter;

  private Game game;

  public Zombie(Position pos, Game game) {
    this.pos = pos;
    this.game = game;
    this.hp = ENDURANCE;
    this.counter = 0;
  }

  // Getters para Gameview
  public String getIcon() {
    return Messages.ZOMBIE_ICON.formatted(hp);
  }

  // Internal logic
  public boolean isAlive() {
    return hp > 0;
  }

  public void receiveAttack(int damage) {
    hp -= damage;
  }

  public void update() {
    // TODO Preguntar si el zombie siempre ataca, sin tener en cuenta el com
    if (counter == MOVE_EVERY_CYCLES && isAlive()) {
      Position posLeft = pos.myLeft();
      if (!game.isEmpty(posLeft)) {
        attack(posLeft);
      } else {
        pos.advanceLeft();
      }
      counter = 0;
    }
    counter++;
  }

  private void attack(Position pos) {
    String icon = game.positionToString(pos);
    if (icon.length() >= 2) {
      char plantType = icon.charAt(1);
      if (plantType == 'P') {
        game.getPeashooterByPosition(pos).receiveDamage(DAMAGE);
      } else if (plantType == 'S') {
        game.getSunflowerByPosition(pos).receiveDamage(DAMAGE);
      }
    }
  }

  public boolean isInPosition(Position pos) {
    return this.pos.equals(pos);
  }

  public boolean isInRow(int row) {
    return pos.isInRow(row);
  }

  public boolean hasCrossed() {
    return pos.crossed();
  }
}
