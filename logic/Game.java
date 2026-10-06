package pvz.logic;

import java.util.Random;
import pvz.control.Level;
import pvz.logic.ZombiesManager;
import pvz.logic.gameobjects.PeashooterList;
import pvz.logic.gameobjects.SunflowerList;
import pvz.logic.gameobjects.ZombieList;
import pvz.utils.Position;

/**
 * Game
 */
public class Game {

  public static final int NUM_COLS = 9;
  public static final int NUM_ROWS = 4;
  private static final int INITIAL_SUNS = 50;

  private long seed;
  private Level level;

  private ZombiesManager zombieM;

  private ZombieList zList;
  private PeashooterList pList;
  private SunflowerList sList;

  private int cycleCounter;
  private int sunCoins;
  private Random rand;
  private boolean playerDead = false;
  private boolean playerQuit = false;

  public Game(long seed, Level level) {
    this.seed = seed;
    this.level = level;
    this.rand = new Random(seed);
    this.zombieM = new ZombiesManager(this, level, rand);
    this.sunCoins = INITIAL_SUNS;
    this.cycleCounter = 0;
  }

  public int getCycleCounter() {
    return this.cycleCounter;
  }

  public int getSunCoins() {
    return this.sunCoins;
  }

  public int getRemainingZombies() {
    return this.zombieM.getRemainingZombies();
  }

  public long getSeed() {
    return this.seed;
  }

  public Level getLevel() {
    return this.level;
  }

  public boolean playerQuits() {
    return this.playerQuit;
  }

  public boolean playerDead() {
    return this.playerDead;
  }

  public boolean hasGameFinished() {
    return playerQuits() || playerDead() || zombieM.allZombiesDead();
  }

  public void addGameObject(String name, Position pos) {}

  public boolean checkGameObject(String name) {
    
  }

  public boolean isInsideBoard(Position pos) {
    return (
      pos.getRow() >= 0 &&
      pos.getRow() <= NUM_ROWS - 1 &&
      pos.getCol() >= 0 &&
      pos.getCol() <= NUM_COLS - 1
    );
  }

  public void update() {}

  public String positionToString(Position pos) {}

  // Private void removeDead()

  // TODO Añadir logica del isEmpty
  public boolean isEmpty(Position pos) {
    return true;
  }

  public void addSunCoins(int sunCoins) {
    this.sunCoins += sunCoins;
  }

  public void reset(Level level, long seed) {
    this.seed = seed;
    this.level = level;
    this.rand = new Random(seed);
  }
}
