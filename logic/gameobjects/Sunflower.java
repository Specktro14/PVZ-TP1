package pvz.logic.gameobjects;

import pvz.logic.Game;
import pvz.utils.Position;
import pvz.view.Messages;

/**
 * Sunflower
 */
public class Sunflower {

  private int col;
  private int row;
  private int hp;

  private static final String NAME = "sunflower";
  private static int COST = 20;
  private static int ENDURANCE = 1;
  private static int DAMAGE = 0;

  private static final int COOLDOWN = 3;
  private int counter;

  private Game game;

  public Sunflower(Position pos, Game game) {
    this.col = pos.getCol();
    this.row = pos.getRow();
    this.game = game;
    this.hp = ENDURANCE;
    this.counter = 0;
  }

  public String getName() {
    return NAME;
  }

  public static String getDescription() {
    return Messages.SUNFLOWER_DESCRIPTION.formatted(COST, DAMAGE, ENDURANCE);
  }

  public String getIcon() {
    return Messages.SUNFLOWER_ICON.formatted(hp);
  }

  public boolean isAlive() {
    return hp > 0;
  }

  public boolean isInPosition(Position pos) {
    return row == pos.getRow() && col == pos.getCol();
  }

  public void receiveDamage(int damage) {
    hp -= damage;
  }

  public void update() {
    if (this.counter == COOLDOWN && isAlive()) {
      game.addSunCoins(10);
      counter = 0;
    }
    counter++;
  }
}
