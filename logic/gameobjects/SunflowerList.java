package pvz.logic.gameobjects;

import pvz.utils.Position;

/**
 * SunflowerList
 */
public class SunflowerList {

  private static final int MAX = 32;
  private Sunflower[] list;
  private int counter = 0;

  public SunflowerList() {
    this.list = new Sunflower[MAX];
  }

  public void add(Sunflower plant) {
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

  public void updateSunflowers() {
    for (int i = 0; i < counter; i++) {
      list[i].update();
    }
  }

  public Sunflower getSunflowerByPosition(Position pos) {
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
