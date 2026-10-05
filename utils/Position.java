package pvz.utils;

/**
 * Position
 */
public class Position {
  private int row;
  private int col;

  public Position(int row, int col) {
    this.row = row;
    this.col = col;
  }

  public Position(String row, String col) {
    this.row = Integer.parseInt(row);
    this.col = Integer.parseInt(col);
  }

  public int getRow() { 
    return this.row;
  }

  public int getCol() {
    return this.col;
  }

  public boolean equals(Position pos) {
    return row == pos.row && col == pos.col;
  }
}