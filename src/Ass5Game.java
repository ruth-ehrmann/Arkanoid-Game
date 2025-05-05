// 323013276 Ruth Ehrmann

import game.Game;

/**
 * The main class for running the game.
 */
public class Ass5Game {
    /**
     * The main method to start the game.
     *
     * @param args Command-line arguments (not used)
     */
    public static void main(String[] args) {
        // Create a new instance of the game
        Game exampleGame = new Game();
        // Initialize the game by creating blocks, ball, and paddle, and adding them to the game
        exampleGame.initialize();
        // Run the game loop to start the animation
        exampleGame.run();
    }
}
