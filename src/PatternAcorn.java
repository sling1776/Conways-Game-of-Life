
public class PatternAcorn extends Pattern {
    int sizeX = 7;
    int sizeY = 3;
    boolean[][] pattern = {
            {false, true, false, false, false, false, false },
            {false, false, false, true, false, false, false},
            {true, true, false, true, true, true, true}
    };


    @Override
    public int getSizeX() {
        return this.sizeX;
    }

    @Override
    public int getSizeY() {
        return this.sizeY;
    }

    @Override
    public boolean getCell(int x, int y) {
        return this.pattern[y][x];
    }
}
