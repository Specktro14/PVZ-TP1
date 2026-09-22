package pvz.logic;

import java.util.Random;

import pvz.control.Level;
import pvz.logic.gameobjects.PeashooterList;
import pvz.logic.gameobjects.SunflowerList;
import pvz.logic.gameobjects.ZombieList;

/**
 * Game
 */
public class Game {
  public static final int NUM_COLS = 9;
  public static final int NUM_ROWS = 4;
  
  private long seed;
  private Level level;
  
  private ZombieList zList;
  private PeashooterList pList;
  private SunflowerList sList;

  private int cycleCounter;
  private int sunCoins;
  private Random rand;

  public Game(long seed, Level level) {
    this.seed = seed;
    this.level = level;
    this.rand = new Random(seed);
    this.sunCoins = 50;
    this.cycleCounter = 0;
  }

  public int getCycleCounter() {
    return this.cycleCounter;
  }

  public int getSunCoins() {
    return this.sunCoins;
  } 
  
  public long getSeed() {
    return this.seed;
  }
  
  public Level getLevel() {
    return this.level;
  }
  
  public void update() {};
} 