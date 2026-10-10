package pvz.logic.gameobjects;

import pvz.logic.Game;
import pvz.utils.Position;

/**
 * ZombieList
 */
public class ZombieList {

  private Zombie[] list;
  private int counter = 0;

  public ZombieList(int max) {
    this.list = new Zombie[max];
  }

  public boolean counterIsZero() {
    return this.counter == 0;
  }

  public void addZombie(Position pos, Game game) {
    Zombie zombie = new Zombie(pos, game);
    list[counter] = zombie;
    counter++;
  }

  // TODO Posible reestructuracion
  public String checkPosition(Position pos) {
    String ret = "";
    int i = 0;
    while (ret.equals("") && i < counter) {
      if (list[i].isInPosition(pos)) ret = list[i].getIcon();
      i++;
    }
    return ret;
  }

  public void updateZombies() {
    for (int i = 0; i < counter; i++) {
      list[i].update();
    }
  }

  public boolean areZombiesInRow(Position pos) {
    return getZombieIndexByRow(pos) != -1;
  }
  
  private int getZombieIndexByRow(Position pos) {
    int i = 0;
    while (i < counter && !list[i].isInRow(pos)) {
      i++;
    }
    if (i >= counter) i = -1;
    return i;
  }

  public void attackZombie(Position pos, int damage){
    getZombieByPos(pos).receiveAttack(damage);
  }

  private Zombie getZombieByPos(Position pos) {
    return list[getZombieIndexByRow(pos)];
  }

  public boolean haveZombiesCrossed() {
    boolean crossed = false;
    int i = 0;
    while (i < counter && !crossed) {
      if (list[i].hasCrossed()) crossed = true;
      i++;
    }
    return crossed;
  }

  public void deleteDeath() {
    int i = 0;
    while (i < counter) {
      if (!list[i].isAlive()) {
        for (int j = i; j < counter - 1; j++) {
          list[j] = list[j + 1];
        }
        counter--;
        list[counter] = null;
      } else {
        i++;
      }
    }
  }

  public void reset() {
    this.counter = 0;
  }
}
