package pvz.logic.gameobjects;

/**
 * Sunflower
 */
public class Sunflower {
  private int col;
  private int row;
  private int cost;
	private int resistance;
	private int frecuency;
	private int damage;

	public Sunflower(int col, int row) {
	  this.col = col;
		this.row = row;
	  this.cost = 20;
		this.resistance = 1;
		this.frecuency = 3;
	}

	// Getters
	public int getX() { return x; }
	public int getY() { return y; }
	public int getCost() { return cost; }
	public int getResistance() { return resistance; }
	public int getFrecuency() { return frecuency; }
	public int getDamage() { return damage; }

	// Setters
	public void setX(int newX) { x = newX; }
	public void setY(int newY) { y = newY; }
	public void setCost(int newCost) { cost = newCost; }
	public void setResistance(int newResistance) { resistance = newResistance; }
	public void setFrecuency(int newFrecuency) { frecuency = newFrecuency; }
	public void setDamage(int newDamage) { damage = newDamage; }

	
}