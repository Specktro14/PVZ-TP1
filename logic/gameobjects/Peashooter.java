package pvz.logic.gameobjects;

import pvz.view.Messages;

public class Peashooter {
  private int col;
  private int row;
  private int cost;
	private int resistance;
	private int frecuency;
	private int damage;
	// private int range;

	public Peashooter(int col, int row) {
	  this.row = row;
		this.col = col;
		this.cost = 50;
		this.resistance = 3;
		this.damage = 1;
		this.frecuency = 1;
		// Falta range
	}

	public String getDescripion() {
	  return Messages.PEASHOOTER_DESCRIPTION.formatted(this.cost, this.damage, this.resistance);
	}
	
	// Getters
	public int getCol() { return col; }
	public int getRow() { return row; }
	public int getCost() { return cost; }
	public int getResistance() { return resistance; }
	public int getFrecuency() { return frecuency; }
	public int getDamage() { return damage; }
	//public int getRange() { return range; }

	// Setters
	public void setCol(int newCol) { col = newCol; }
	public void setRow(int newRow) { row = newRow; }
	public void setCost(int newCost) { cost = newCost; }
	public void setResistance(int newResistance) { resistance = newResistance; }
	public void setFrecuency(int newFrecuency) { frecuency = newFrecuency; }
	public void setDamage(int newDamage) { damage = newDamage; }
	//public void setRange(int newRange) { range = newRange; }
}