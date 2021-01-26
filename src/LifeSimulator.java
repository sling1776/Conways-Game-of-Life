/**
 * @Title: LifeSimulator
 * @Summary:
 *      This creates an imaginary grid of boolean values. It has the ability to add in a
 *      pattern. The getter and setter methods can tell you wheter or not the cell is
 *      alive. It can also give you the size of the grid.
 * @author Spencer Lingwall
 */

public class LifeSimulator {
    private int sizeX;
    private int sizeY;
    private Boolean[][] grid;

    public LifeSimulator(int sizeX, int sizeY) {
        /**
         * Constructor: makes an empty grid of a set size.
         */
            this.sizeX = sizeX;
            this.sizeY = sizeY;
            this.grid = new Boolean[sizeY][sizeX];
            for (int y = 0; y < sizeY; y++){
                for(int x = 0; x < sizeX ; x++){
                    this.grid[y][x]= false;
                }
            }
        }

    public int getSizeX() {
        return this.sizeX;
    }

    public int getSizeY() {
        return this.sizeY;
    }

    public boolean getCell(int x, int y) {
        return this.grid[y][x];
    }

    public void insertPattern(Pattern pattern, int startX, int startY) {
        /**
         * will insert a pattern at location startx, starty. These go from 0,0 being in the top corner and X,0
         * being in the top right corner.
         */
        for(int row = 0; row < pattern.getSizeY(); row++){
            for(int col = 0; col < pattern.getSizeX(); col++){
                if ((col + startX) < this.getSizeX() && (row + startY)< this.getSizeY()) {
                    this.grid[row+startY][col+startX] = pattern.getCell(col, row);
                }
            }
        }
    }

    public void printGrid(){
        /**
         * printGrid is a helper method that helps debug the program. It displays on the console the
         * grid.
         */
        String msg ="----------------------------------------------------------------------------------\n";
        for(int r = 0; r< this.getSizeY();r++){
            msg += "|";
            for(int c = 0; c< this.getSizeX(); c++){
                if (this.getCell(c,r)){
                    msg += "X";
                }else{
                    msg += " ";
                }
            }
            msg += "|\n";
        }
        msg += "----------------------------------------------------------------------------------\n";
        System.out.println(msg);
    }


    public void update() {
        /**
         * This updates the grid according to the rules of Conway's Game of Life:
         * 1. Any live cell with two or three live neighbours survives.
         * 2. Any dead cell with three live neighbours becomes a live cell.
         * 3. All other live cells die in the next generation. Similarly, all other dead cells stay dead.
         */
        Boolean[][] updated = new Boolean[this.getSizeY()][this.getSizeX()];
        for(int row = 0; row < this.getSizeY(); row++){
            for(int col = 0; col < this.getSizeX(); col++){
                int lifecount = 0;
                //determine the amount of life around a certain cell.
                for(int y = row-1; y <= row+1; y++){
                    for(int x = col-1; x <=col+1; x++){
                        if(y>=0 && y<this.getSizeY() && x>=0 && x<this.getSizeX()){ // make sure it is in bounds
                            if(y != row || x != col){       // don't count the cell itself!!!
                                if(this.getCell(x, y)){     //check if the neighbor is alive
                                    lifecount++;
                                }
                            }
                        }
                    }
                }
                // Implement the Rules:
                if(lifecount == 3){
                    updated[row][col] = true;
                }else if(lifecount == 2 && this.getCell(col,row)){
                    updated[row][col] = true;
                }else{
                    updated[row][col] = false;
                }
            }
        }
        // finalize and update the grid.
        this.grid = updated;

    }
}
