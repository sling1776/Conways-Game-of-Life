public class PatternGlider extends Pattern {
    int sizeX = 3;
    int sizeY = 3;
    boolean[][] pattern = {
            {false, true, false},
            {false, false, true},
            {true, true, true}
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
