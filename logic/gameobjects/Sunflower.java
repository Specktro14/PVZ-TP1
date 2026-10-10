package pvz.logic.gameobjects;

import pvz.logic.Game;
import pvz.utils.Position;
import pvz.view.Messages;

/**
 * Sunflower
 */
public class Sunflower {

  private Position pos;
  private int hp;

  private static int COST = 20;
  private static int ENDURANCE = 1;
  private static int DAMAGE = 0;

  private static final int COOLDOWN = 3;
  private int counter;

  private Game game;

  public Sunflower(Position pos, Game game) {
    this.pos = pos;
    this.game = game;
    this.hp = ENDURANCE;
    this.counter = 0;
  }

  // Getters para Gameview
  public static String getDescription() {
    return Messages.SUNFLOWER_DESCRIPTION.formatted(COST, DAMAGE, ENDURANCE);
  }
  
  public String getIcon() {
    return Messages.SUNFLOWER_ICON.formatted(hp);
  }

  // Internal logic
  public boolean canBeAdded(int totalSuncoins) {
    if (totalSuncoins >= COST) game.substractSunCoins(COST);
    return totalSuncoins >= COST;
  }

  public boolean isAlive() {
    return hp > 0;
  }

  public void receiveDamage(int damage) {
    hp -= damage;
  }

  public void update() {
    if (this.counter == COOLDOWN) {
      game.addSunCoins(10);
      counter = 0;
    }
    counter++;
  }

  public boolean isInPosition(Position pos) {
    return this.pos.equals(pos);
  }
}
