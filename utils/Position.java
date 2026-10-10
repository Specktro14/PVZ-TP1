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

  public boolean equals(Position pos) {
    return row == pos.row && col == pos.col;
  }

  public boolean isInRow(Position pos) {
    return this.row == pos.row;
  }

  public Position myLeft() {
    Position posLeft = new Position(row, col - 1);
    return posLeft;
  }

  public void advanceLeft() {
    col -= 1;
  }

  public boolean isInsideLimits(int max_row, int max_col) {
    return (
      !outsideLeft() &&
      !outsideRight(max_col) &&
      !outsideTop() &&
      !outsideBottom(max_row)
    );
  }

  public boolean crossed() {
    return outsideLeft();
  }

  private boolean outsideLeft() {
    return col <= -1;
  }

  private boolean outsideRight(int max_col) {
    return col > max_col;
  }

  private boolean outsideTop() {
    return row <= -1;
  }

  private boolean outsideBottom(int max_row) {
    return row > max_row;
  }
}
