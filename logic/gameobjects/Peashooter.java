package pvz.logic.gameobjects;

import pvz.logic.Game;
import pvz.utils.Position;
import pvz.view.Messages;

public class Peashooter {

  private int col;
  private int row;
  private int hp;

  private static final String NAME = "peashooter";
  private static final int COST = 50;
  private static final int ENDURANCE = 3;
  private static final int DAMAGE = 1;

  private Game game;

  public Peashooter(Position pos, Game game) {
    this.row = pos.getRow();
    this.col = pos.getCol();
    this.game = game;
    this.hp = ENDURANCE;
  }

  public static String getDescription() {
    return Messages.PEASHOOTER_DESCRIPTION.formatted(COST, DAMAGE, ENDURANCE);
  }

  public String getIcon() {
    return Messages.PEASHOOTER_ICON.formatted(hp);
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
    if (isAlive() && zombiesInRange()) {
      shoot();
    }
  }

  public boolean canBeAdded(int totalSuncoins) {
    return totalSuncoins > COST;
  }

  private boolean zombiesInRange() {
    return game.getZManager().zombiesInRow(row);
  }

  private void shoot() {
    game.getZManager().getZombieByRow(row).receiveAttack(DAMAGE);
  }
}
