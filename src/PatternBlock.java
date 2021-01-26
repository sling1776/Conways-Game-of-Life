public class PatternBlock extends Pattern {
    int sizeX = 2;
    int sizeY = 2;
    boolean[][] pattern = {
            {true, true},
            {true, true},
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
