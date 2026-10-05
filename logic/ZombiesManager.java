package pvz.logic;

import java.util.Random;
import pvz.control.Level;
import pvz.logic.gameobjects.Zombie;
import pvz.logic.gameobjects.ZombieList;
import pvz.utils.Position;

/**
 * Manages the full lifecycle of zombies for a game session.
 *
 * <p>Responsibilities: deciding each cycle whether to spawn a new zombie
 * (probabilistically, subject to the remaining quota from {@link Level}),
 * delegating per-cycle updates and dead-removal to the underlying
 * {@link ZombieList}, and answering win/loss queries
 *
 */
public class ZombiesManager {

  private Game game;

  private Level level;

  private Random rand;

  private int remainingZombies;

  private ZombieList zombies;

  public ZombiesManager(Game game, Level level, Random rand) {
    this.game = game;
    this.level = level;
    this.rand = rand;
    this.remainingZombies = level.getNumberOfZombies();
    this.zombies = new ZombieList(level.getNumberOfZombies());
  }

  /**
   * Checks if the game should add (if possible) a zombie to the game.
   *
   * @return <code>true</code> if a zombie should be added to the game.
   */
  public boolean shouldAddZombie() {
    return rand.nextDouble() < level.getZombieFrequency();
  }

  /**
   * Return a random row within the board limits.
   *
   * @return a random row.
   */
  private int randomZombieRow() {
    return rand.nextInt(Game.NUM_ROWS);
  }

  public boolean addZombie() {
    int row = randomZombieRow();
    return addZombie(row);
  }

  public boolean addZombie(int row) {
    boolean canAdd =
      getRemainingZombies() > 0 &&
      shouldAddZombie() &&
      isPositionEmpty(Game.NUM_COLS - 1, row);

    if (canAdd) {
      zombies.addZombie(new Position(row, Game.NUM_COLS - 1), game);
      remainingZombies--;
    }
    return canAdd;
  }

  // ERROR: Peligro, getter suelto
  public ZombieList getZombieList() {
    return this.zombies;
  }

  public boolean isPositionEmpty(int row, int col) {
    return game.isEmpty(new Position(row, col));
  }

  // ERROR: Peligro, getter suelto
  public int getRemainingZombies() {
    return remainingZombies;
  }

  public boolean allZombiesDead() {
    return remainingZombies == 0 && zombies.getCounter() == 0;
  }

  public boolean zombiesInRow(int row) {
    return zombies.getZombieIndexByRow(row) != -1;
  }

  public Zombie getZombieByRow(int row) {
    return zombies.getZombieByRow(row);
  }

  public boolean haveZombiesCrossed() {
    return zombies.haveZombiesCrossed();
  }
}
