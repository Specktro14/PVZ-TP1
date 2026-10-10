package pvz.logic;

import java.util.Random;
import pvz.control.Level;
import pvz.logic.gameobjects.Peashooter;
import pvz.logic.gameobjects.PeashooterList;
import pvz.logic.gameobjects.Sunflower;
import pvz.logic.gameobjects.SunflowerList;
import pvz.utils.Position;
import pvz.view.Messages;

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
  private ZombiesManager zManager;

  // Object lists
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
    this.zManager = new ZombiesManager(this, level, rand);
    this.pList = new PeashooterList();
    this.sList = new SunflowerList();
  }

  // Getters para Gameview
  public String showCycleCounter() {
    return Messages.NUMBER_OF_CYCLES.formatted(cycleCounter);
  }

  public String showSunCoins() {
    return Messages.NUMBER_OF_COINS.formatted(sunCoins);
  }

  public String showRemainingZombies() {
    return zManager.showRemainingZombies();
  }

  public String showEndMessage() {
    String message;
    if (playerQuit) {
      message = Messages.PLAYER_QUITS;
    } else if (playerDead) {
      message = Messages.ZOMBIES_WIN;
    } else {
      message = Messages.PLAYER_WINS;
    }
    return message;
  }

  // Game ending conditions
  public void setPlayerQuits(boolean newPQ) {
    this.playerQuit = newPQ;
  }

  public void setPlayerDead() {
    playerDead = zManager.haveZombiesCrossed();
  }

  public boolean hasGameFinished() {
    return playerQuit || playerDead || zManager.allZombiesDead();
  }

  // Game object methods
  public void addZombie() {
    zManager.addZombie();
  }

  public boolean areZombiesInRow(Position pos) {
    return zManager.areZombiesInRow(pos);
  }

  public void attackZombie(Position pos, int damage) {
    zManager.attackZombie(pos, damage);
  }

  public int addGameObject(String name, Position pos) {
    int exitCode = 0;
    if (checkIsPeashooter(name)) {
      Peashooter plant = new Peashooter(pos, this);
      // Comprobar que hay suficientes suncoins
      if (plant.canBeAdded(sunCoins)) {
        pList.add(plant);
      } else {
        exitCode = 4;
      }
    } else if (checkIsSunflower(name)) {
      Sunflower plant = new Sunflower(pos, this);
      // Comprobar que hay suficientes suncoins
      if (plant.canBeAdded(sunCoins)) {
        sList.add(plant);
      } else {
        exitCode = 4;
      }
    }
    return exitCode;
  }

  private boolean checkIsPeashooter(String name) {
    name.toLowerCase();
    return (
      name.equals(Messages.PEASHOOTER_NAME) ||
      name.equals(Messages.PEASHOOTER_NAME.substring(0, 1))
    );
  }

  private boolean checkIsSunflower(String name) {
    name.toLowerCase();
    return (
      name.equals(Messages.SUNFLOWER_NAME) ||
      name.equals(Messages.SUNFLOWER_NAME.substring(0, 1))
    );
  }

  public boolean checkGameObject(String name) {
    name.toLowerCase();
    return checkIsPeashooter(name) || checkIsSunflower(name);
  }

  public void attackSunflower(Position pos, int dmg) {
    sList.attackedSunflower(pos, dmg);
  }

  public void attackPeashooter(Position pos, int dmg) {
    pList.attackedPeashooter(pos, dmg);
  }

  // Position and board methods
  public boolean correctPosition(Position pos) {
    return isEmpty(pos) && isInsideBoard(pos);
  }

  public boolean isInsideBoard(Position pos) {
    return pos.isInsideLimits(NUM_ROWS - 1, NUM_COLS - 1);
  }

  public boolean isEmpty(Position pos) {
    return positionToString(pos).equals("");
  }

  public String positionToString(Position pos) {
    String ret = pList.checkPosition(pos);
    if (ret.equals("")) {
      ret = sList.checkPosition(pos);
      if (ret.equals("")) {
        ret = zManager.checkPosition(pos);
      }
    }
    return ret;
  }

  // Game cycles
  public void update() {
    cycleCounter += 1;
    sList.updateSunflowers();
    pList.updatePeashooters();
    zManager.updateZombies();
    removeDead();
    setPlayerDead();
  }

  private void removeDead() {
    sList.deleteDeath();
    pList.deleteDeath();
    zManager.deleteDeath();
  }

  public void addSunCoins(int sunCoins) {
    this.sunCoins += sunCoins;
  }

  public void substractSunCoins(int sunCoins) {
    this.sunCoins -= sunCoins;
  }

  // Reset
  public void reset() {
    this.rand = new Random(seed);
    this.zManager.reset(rand);
    this.pList.reset();
    this.sList.reset();
    this.cycleCounter = 0;
    this.sunCoins = INITIAL_SUNS;
    this.playerDead = false;
    this.playerQuit = false;
  }
}
