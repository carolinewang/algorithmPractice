/**
 * Created by haylin2002 on 2/6/16.
 */
public class Cell {
    int column;
    int row;
    int value;

    public Cell(int c, int r, int v) {
        column = c;
        row = r;
        value = v;
    }

    @Override
    public boolean equals(Object c) {
        if (this == c) {
            return true;
        }
        if (!(c instanceof Cell)) {
            return false;
        }
        Cell another = (Cell) c;
        return this.column == another.column && this.row == another.row;
    }

    @Override
    public int hashCode() {
        return column * 31 * 31 + row * 31 + value;
    }

}
