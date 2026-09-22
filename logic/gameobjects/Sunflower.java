package pvz.logic.gameobjects;

import pvz.logic.Game;
import pvz.view.Messages;

/**
 * Sunflower
 */
public class Sunflower {
  private int col;
  private int row;
  private static final int COST = 20;
	private static final int ENDURANCE = 1;
	private static final int DAMAGE = 0;
	private static final double FREQUENCY = 0.33;

	private Game game;

	public Sunflower(int col, int row, Game game) {
	  this.col = col;
		this.row = row;
		this.game = game;
	}

	public static String getDescription() {
	  return Messages.SUNFLOWER_DESCRIPTION.formatted(COST, DAMAGE, ENDURANCE);
	}
	
	// Getters
	public int getCol() { return col; }
	public int getRow() { return row; }
	public int getCost() { return COST; }
	public int getEndurance() { return ENDURANCE; }
	public int getDamage() { return DAMAGE; }
	public double getFrequency() { return FREQUENCY; }
	//public int getRange() { return range; }

	// Setters
	public void setCol(int newCol) { col = newCol; }
	public void setRow(int newRow) { row = newRow; }
	//public void setRange(int newRange) { range = newRange; }	
}