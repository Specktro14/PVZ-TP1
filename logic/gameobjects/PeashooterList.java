package pvz.logic.gameobjects;

import pvz.utils.Position;

/**
 * PeashooterList
 */
public class PeashooterList {

  private static final int MAX = 32;
  private Peashooter[] list;
  private int counter = 0;

  public PeashooterList() {
    this.list = new Peashooter[MAX];
  }

  public void add(Peashooter plant) {
    list[counter] = plant;
    counter++;
  }

  public String checkPosition(Position pos) {
    String ret = "";
    int i = 0;
    while (ret.equals("") && i < counter) {
      if (list[i].isInPosition(pos)) ret = list[i].getIcon();
      i++;
    }
    return ret;
  }

  public void updatePeashooters() {
    for (int i = 0; i < counter; i++) {
      list[i].update();
    }
  }

  public Peashooter getPeashooterByPosition(Position pos) {
    int i = 0;
    while (!list[i].isInPosition(pos) && i < counter) {
      i++;
    }
    return list[i];
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
}
