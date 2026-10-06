package pvz.logic.gameobjects;

import pvz.logic.Game;
import pvz.utils.Position;
import pvz.view.Messages;

public class Peashooter {

  private Position pos;
  private int hp;

  private static final String NAME = "peashooter";
  private static final int COST = 50;
  private static final int ENDURANCE = 3;
  private static final int DAMAGE = 1;

  private Game game;

  public Peashooter(Position pos, Game game) {
    this.pos = pos;
    this.game = game;
    this.hp = ENDURANCE;
  }

  // Getters for Gameview
  public static String getDescription() {
    return Messages.PEASHOOTER_DESCRIPTION.formatted(COST, DAMAGE, ENDURANCE);
  }

  public String getIcon() {
    return Messages.PEASHOOTER_ICON.formatted(hp);
  }

  // Internal logic
  public boolean isAlive() {
    return hp > 0;
  }

  public void receiveDamage(int damage) {
    hp -= damage;
  }

  public boolean canBeAdded(int totalSuncoins) {
    if (totalSuncoins > COST) game.substractSunCoins(COST);
    return totalSuncoins > COST;
  }

  public void update() {
    if (isAlive() && zombiesInRange()) {
      shoot();
    }
  }

  private boolean zombiesInRange() {
    return game.getZManager().zombiesInRow(row);
  }

  private void shoot() {
    game.getZManager().getZombieByRow(row).receiveAttack(DAMAGE);
  }

  public boolean isInPosition(Position pos) {
    return this.pos.equals(pos);
  }
}
