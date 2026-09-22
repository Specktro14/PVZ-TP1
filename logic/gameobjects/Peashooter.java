package pvz.logic.gameobjects;

import pvz.logic.Game;
import pvz.view.Messages;

public class Peashooter {
  private int col;
  private int row;
  private static final int COST = 50;
	private static final int ENDURANCE = 3;
	private static final int DAMAGE = 1;
	private static final double FREQUENCY = 1;

	private Game game;
	// private int range;

	public Peashooter(int col, int row, Game game) {
	  this.row = row;
		this.col = col;
		this.game = game;
		// Falta range
	}

	public static String getDescription() {
	  return Messages.PEASHOOTER_DESCRIPTION.formatted(COST, DAMAGE, ENDURANCE);
	}
	
	// Getters
	public int getCol() { return col; }
	public int getRow() { return row; }
	public int getCost() { return COST; }
	public int getEndurance() { return ENDURANCE; }
	public double getFrequency() { return FREQUENCY; }
	public int getDamage() { return DAMAGE; }
	//public int getRange() { return range; }

	// Setters
	public void setCol(int newCol) { col = newCol; }
	public void setRow(int newRow) { row = newRow; }
	//public void setRange(int newRange) { range = newRange; }
}