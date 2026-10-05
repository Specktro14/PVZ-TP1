package pvz.logic;

import java.util.Random;
import pvz.control.Level;
import pvz.logic.gameobjects.Peashooter;
import pvz.logic.gameobjects.PeashooterList;
import pvz.logic.gameobjects.Sunflower;
import pvz.logic.gameobjects.SunflowerList;
import pvz.logic.gameobjects.ZombieList;
import pvz.utils.Position;

/**
 * Game
 */
public class Game {

  // Constants
  public static final int NUM_COLS = 8;
  public static final int NUM_ROWS = 4;
  private static final int INITIAL_SUNS = 50;

  // Game decider atributes
  private long seed;
  private Level level;

  // Managers
  private ZombiesManager zombieM;

  // Object lists
  private ZombieList zList;
  private PeashooterList pList;
  private SunflowerList sList;

  // Other atributes
  private Random rand;
  private int cycleCounter = 0;
  private int sunCoins = INITIAL_SUNS;
  private boolean playerDead = false;
  private boolean playerQuit = false;

  //Constructors
  public Game(long seed, Level level) {
    this.seed = seed;
    this.level = level;
    this.rand = new Random(seed);
    this.zombieM = new ZombiesManager(this, level, rand);
    this.pList = new PeashooterList();
    this.sList = new SunflowerList();
    this.zList = this.zombieM.getZombieList();
  }

  // Getters
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

  public ZombiesManager getZManager() {
    return zombieM;
  }

  // Game ending conditions
  public boolean getPlayerQuits() {
    return this.playerQuit;
  }

  public void setPlayerQuits(boolean newPQ) {
    this.playerQuit = newPQ;
  }

  public boolean getPlayerDead() {
    return this.playerDead;
  }

  public void setPlayerDead() {
    playerDead = zombieM.haveZombiesCrossed();
  }

  public boolean hasGameFinished() {
    return getPlayerQuits() || getPlayerDead() || zombieM.allZombiesDead();
  }

  // Game object methods
  public int addGameObject(String name, Position pos) {
    int exitCode = 0;
    if (checkIsPeashooter(name)) {
      Peashooter plant = new Peashooter(pos, this);
      // Comprobar que hay suficientes suncoins
      if (sunCoins >= plant.getCost()) {
        pList.add(plant);
        sunCoins -= plant.getCost();
      } else {
        exitCode = 4;
      }
    } else if (checkIsSunflower(name)) {
      Sunflower plant = new Sunflower(pos, this);
      // Comprobar que hay suficientes suncoins
      if (sunCoins >= plant.getCost()) {
        sList.add(plant);
        sunCoins -= plant.getCost();
      } else {
        exitCode = 4;
      }
    }
    return exitCode;
  }

  public boolean checkIsPeashooter(String name) {
    name.toLowerCase();
    return name.equals("peashooter") || name.equals("p");
  }

  public boolean checkIsSunflower(String name) {
    name.toLowerCase();
    return name.equals("sunflower") || name.equals("s");
  }

  public boolean checkGameObject(String name) {
    name.toLowerCase();
    return checkIsPeashooter(name) || checkIsSunflower(name);
  }

  public Sunflower getSunflowerByPosition(Position pos) {
    return sList.getSunflowerByPosition(pos);
  }

  public Peashooter getPeashooterByPosition(Position pos) {
    return pList.getPeashooterByPosition(pos);
  }

  // Position and board methods
  public boolean correctPosition(Position pos) {
    return isEmpty(pos) && isInsideBoard(pos);
  }
  
  public boolean isInsideBoard(Position pos) {
    return (
      pos.getRow() >= 0 &&
      pos.getRow() <= NUM_ROWS - 1 &&
      pos.getCol() >= 0 &&
      pos.getCol() <= NUM_COLS - 1
    );
  }

  public String positionToString(Position pos) {
    String ret = pList.checkPosition(pos);
    if (ret.equals("")) {
      ret = sList.checkPosition(pos);
      if (ret.equals("")) {
        ret = zList.checkPosition(pos);
      }
    }
    return ret;
  }

  public boolean isEmpty(Position pos) {
    return positionToString(pos).equals("");
  }

  // Game cycles
  public void update() {
    cycleCounter += 1;
    sList.updateSunflowers();
    pList.updatePeashooters();
    zList.updateZombies();
    removeDead();
    setPlayerDead();
  }

  private void removeDead() {
    sList.deleteDeath();
    pList.deleteDeath();
    zList.deleteDeath();
  }

  public void addSunCoins(int sunCoins) {
    this.sunCoins += sunCoins;
  }

  // Reset
  public void reset() {
    this.zombieM = new ZombiesManager(this, level, rand);
    this.pList = new PeashooterList();
    this.sList = new SunflowerList();
    this.rand = new Random(seed);
    this.zList = this.zombieM.getZombieList();
    this.cycleCounter = 0;
    this.sunCoins = INITIAL_SUNS;
    this.playerDead = false;
    this.playerQuit = false;
  }
}
