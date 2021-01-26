// Reference for Lanterna 3: https://github.com/mabe02/lanterna/blob/master/docs/contents.md
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;

public class ConwaysLife {
    public static void main(String[] args) {
        try {
            Terminal terminal = new DefaultTerminalFactory().createTerminal();
            Screen screen = new TerminalScreen(terminal);
            TextGraphics graphics = screen.newTextGraphics();

            TerminalSize size = screen.getTerminalSize();
            LifeSimulator simulation = new LifeSimulator(size.getColumns(), size.getRows());

            screen.startScreen();
            screen.setCursorPosition(null);

            simulation.insertPattern(new PatternGlider(), 30,0);
            simulation.insertPattern(new PatternAcorn(), 15,10);
            simulation.insertPattern(new PatternBlinker(), 40, 4);
            simulation.insertPattern(new PatternBlinker(), 45, 4);
            simulation.insertPattern(new PatternBlinker(), 50, 4);
            simulation.insertPattern(new PatternBlinker(), 55, 4);
            simulation.insertPattern(new PatternBlinker(), 60, 4);
            simulation.insertPattern(new PatternBlock(), 54, 22);



            /*****************************************************
            This is my animation Loop.
             @author Spencer Lingwall
            ******************************************************* */
            for (int i = 0; i < 150; i++) {
                render(simulation, screen, graphics);   // Render the current state of the simulation
//                sampleRender(screen, graphics, i);
                Thread.yield();                         // Let the JVM have some time to update other things
                Thread.sleep(100);                // Sleep for a bit to make for a nicer paced animation
                if (i == 70){
                    simulation.insertPattern(new PatternAcorn(), 40,15);
                }
                simulation.update();                    // Tell the simulation to update
            }

            screen.stopScreen();
        } catch (Exception ex) {
            System.out.println("Something bad happened: " + ex.getMessage());
        }
    }

    public static void sampleRender(Screen screen, TextGraphics graphics, int xPos) {
        screen.clear();

        // Not very interesting, but showing how to set characters
        graphics.setCharacter(xPos, 10, 'X');

        // This is what causes the console to render the new state, it is required
        try {
            screen.refresh();
        } catch (Exception ex) {
        }
    }

    public static void render(LifeSimulator simulation, Screen screen, TextGraphics graphics) {
        /**
         * Render refreshes the screen and puts an X in each needed location
         * @author Spencer Lingwall
         */
        screen.clear();
        int sizeY = simulation.getSizeY();
        int sizeX = simulation.getSizeX();

        for(int yPos = 0; yPos < sizeY; yPos++){
            for(int xPos = 0; xPos < sizeX; xPos++){
                if(simulation.getCell(xPos, yPos)){
                    graphics.setCharacter(xPos, yPos, 'X');
                }
            }
        }

        // This is what causes the console to render the new state, it is required
        try {
            screen.refresh();
        } catch (Exception ex) {
        }
    }
}
