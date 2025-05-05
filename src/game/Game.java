// 323013276 Ruth Ehrmann
package game;
import biuoop.GUI;
import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import collisionDetection.Collidable;
import collisionDetection.GameEnvironment;
import geometryPrimitive.Point;
import geometryPrimitive.Rectangle;
import hitListener.BallRemover;
import hitListener.BlockRemover;
import hitListener.ScoreTrackingListener;
import sprites.Ball;
import sprites.Paddle;
import sprites.Sprite;
import sprites.SpriteCollection;
import sprites.Block;
import sprites.Velocity;
import sprites.ScoreIndicator;


import java.awt.Color;
import java.util.Arrays;
import java.util.List;
/**
 * The game.Game class represents the main game logic, including initialization and the game loop.
 */
public class Game {
    // Define constants for window title, width, height, and number of balls
    private static final String WINDOW_TITLE = "Arkanoid game.Game";
    private static final int WINDOW_WIDTH = 800;
    private static final int WINDOW_HEIGHT = 600;
    private static final int NUM_BALLS = 3;  // Number of balls in the game

    private final SpriteCollection sprites; // Collection of all sprites in the game
    private final GameEnvironment environment; // game.Game environment containing all collidable objects
    private final GUI gui; // Graphical user interface
    private final biuoop.Sleeper sleeper; // Sleeper to control the game loop timing
    private final KeyboardSensor keyboardSensor; // Keyboard sensor to detect user inputs
    private Paddle paddle; // The paddle controlled by the player
    private final Ball[] balls; // Array to hold the balls in the game
    private final Counter remainingBlocks; // Counter for remaining blocks
    private final Counter remainingBalls; // Counter for remaining balls
    private final Counter score; // Counter for the score

    /**
     * Constructor to initialize the game.
     */
    public Game() {
        this.sprites = new SpriteCollection();
        this.environment = new GameEnvironment();
        this.gui = new GUI(WINDOW_TITLE, WINDOW_WIDTH, WINDOW_HEIGHT);
        this.sleeper = new biuoop.Sleeper();
        this.keyboardSensor = gui.getKeyboardSensor();
        this.balls = new Ball[NUM_BALLS];
        this.remainingBlocks = new Counter();
        this.remainingBalls = new Counter();
        this.score = new Counter();
    }
    /**
     * Adds a collidable object to the game environment.
     *
     * @param c the collidable object to add
     */
    public void addCollidable(Collidable c) {
        this.environment.addCollidable(c);
    }
    /**
     * Removes a given collisionDetection.Collidable from the game environment.
     * If the given collisionDetection.Collidable is null, it prints a message and does nothing.
     *
     * @param c the collisionDetection.Collidable to be removed
     */
    public void removeCollidable(Collidable c) {
        if (c == null) {
            // If the collidable is null, print a message and do nothing.
            System.out.println("The given collidabe is NULL");
            return;
        }
        // Removes the given collidable from the environment.
        this.environment.removeCollidable(c);
    }
    /**
     * Adds a sprite object to the sprite collection.
     *
     * @param s the sprite object to add
     */
    public void addSprite(Sprite s) {
        // Adds the given sprites.Sprite to the sprites' collection.
        this.sprites.addSprite(s);
    }
    /**
     * Removes a sprites.Sprite from the collection of sprites.
     * If the given sprites.Sprite is null, it prints a message and does nothing.
     *
     * @param s the sprites.Sprite to remove
     */
    public void removeSprite(Sprite s) {
        if (s == null) {
            // If the sprite is null, print a message and do nothing.
            System.out.println("The given sprite is NULL");
            return;
        }
        // Removes the given sprites.Sprite from the sprites' collection.
        this.sprites.removeSprite(s);
    }
    /**
     * Gets the score counter.
     *
     * @return the score counter
     */
    public Counter getScore() {
        return this.score;
    }
    /**
     * Initializes the game by creating the blocks, ball, paddle, and adding them to the game.
     */
    public void initialize() {
        // Create and add external frame blocks
        Rectangle[] externalFrame = {
            new Rectangle(new Point(0, 25), 800, 25),
            new Rectangle(new Point(775, 50), 25, 550),
            new Rectangle(new Point(0, 50), 25, 550),
        };
        for (Rectangle rectangle : externalFrame) {
            rectangle.setColorRectangle(Color.GRAY);
            Block block = new Block(rectangle);
            block.addToGame(this);
        }
        // Create and add death region block
        Color darkBlue = new Color(0, 0, 139);
        Rectangle deathRegion = new Rectangle(new Point(25, 600), 750, 0);
        deathRegion.setColorRectangle(darkBlue);
        Block deathBlock = new Block(deathRegion);
        deathBlock.addToGame(this);

        // Create and add frame
        Rectangle frame = new Rectangle(new Point(25, 50), 750, 550);
        frame.setColorRectangle(darkBlue);
        this.addSprite(new Block(frame));
        // Create and add the first ball
        Ball fstBall = new Ball(new Point(150, 150), 5, Color.WHITE);
        fstBall.setGameEnvironment(environment);
        Velocity v1 = Velocity.fromAngleAndSpeed(30, 2);
        fstBall.setVelocity(v1);
        fstBall.addToGame(this);
        this.balls[0] = fstBall;
        this.remainingBalls.increase(1);
        // Create and add the second ball
        Ball secBall = new Ball(new Point(600, 300), 5, Color.ORANGE);
        secBall.setGameEnvironment(environment);
        Velocity v2 = Velocity.fromAngleAndSpeed(40, 2);
        secBall.setVelocity(v2);
        secBall.addToGame(this);
        this.balls[1] = secBall;
        this.remainingBalls.increase(1);
        // Create and add the third ball
        Ball thdBall = new Ball(new Point(100, 200), 5, Color.CYAN);
        thdBall.setGameEnvironment(environment);
        Velocity v3 = Velocity.fromAngleAndSpeed(50, 2);
        thdBall.setVelocity(v3);
        thdBall.addToGame(this);
        this.balls[2] = thdBall;
        this.remainingBalls.increase(1);
        // Add ball remover listener to the death block
        BallRemover ballRemover = new BallRemover(this, this.remainingBalls);
        deathBlock.addHitListener(ballRemover);
        // Add score indicator to the game
        ScoreIndicator scoreIndicator = new ScoreIndicator(this);
        this.addSprite(scoreIndicator);
        // Add block remover and score tracking listeners
        BlockRemover blockRemover = new BlockRemover(this, this.remainingBlocks);
        ScoreTrackingListener scoreTrackingListener = new ScoreTrackingListener(this.score);
        // Create and add blocks
        for (int i = 0; i < 6; i++) { // 6 rows
            List<Color> colors = Arrays.asList(Color.GRAY, Color.RED, Color.YELLOW, Color.BLUE, Color.PINK,
                    Color.GREEN);
            for (int j = i; j < 12; j++) { // 12 blocks max in line
                Rectangle rectangle = new Rectangle(new Point(151 + (52 * j), 120 + (20 * i)), 52, 20);
                Color color = colors.get(i % colors.size());
                rectangle.setColorRectangle(color);
                Block block = new Block(rectangle);
                block.addToGame(this);
                this.remainingBlocks.increase(1);
                block.addHitListener(blockRemover);
                block.addHitListener(scoreTrackingListener);
            }
        }
        // Create and add paddle
        Rectangle rect = new Rectangle(new Point(100, 554), 75, 20);
        rect.setColorRectangle(Color.YELLOW);
        Paddle paddle = new Paddle(rect, this.keyboardSensor, 5);
        paddle.addToGame(this);
        this.paddle = paddle;
    }
    /**
     * Runs the game, starting the animation loop.
     */
    public void run() {
        int framesPerSecond = 60;
        int millisecondsPerFrame = 1000 / framesPerSecond;
        // Game loop: runs as long as there are remaining blocks and balls
        while (this.remainingBlocks.getValue() != 0 && this.remainingBalls.getValue() != 0) {
            long startTime = System.currentTimeMillis(); // timing
            DrawSurface d = this.gui.getDrawSurface();
            // Notify all sprites that time has passed
            this.sprites.notifyAllTimePassed();
            // Draw all sprites on the draw surface
            this.sprites.drawAllOn(d);
            // Display final score if no remaining blocks
            if (this.remainingBlocks.getValue() == 0) {
                d.drawText(350, 20, "Score: " + (100 + this.score.getValue()), 15);
            }
            // Show the current draw surface
            this.gui.show(d);
            // Handle ball-paddle collisions; Checks if during the movement a ball enters the paddle
            for (Ball ball : this.balls) {
                double leftXPaddle = this.paddle.getCollisionRectangle().getX();
                double rightXPaddle = this.paddle.getCollisionRectangle().getX()
                        + paddle.getCollisionRectangle().getWidth();
                if ((ball.getY()) > this.paddle.getCollisionRectangle().getY()
                        && (ball.getX()) >= leftXPaddle
                        && (ball.getX()) <= rightXPaddle) {
                    ball.setCenter(ball.getX(), paddle.getCollisionRectangle().getUpperLeft().getY()
                            - (ball.getSize() + 2));
                    ball.setVelocity(Velocity.fromAngleAndSpeed(210, 5));
                }
            }
            // timing
            long usedTime = System.currentTimeMillis() - startTime;
            long milliSecondLeftToSleep = millisecondsPerFrame - usedTime;
            if (milliSecondLeftToSleep > 0) {
                this.sleeper.sleepFor(milliSecondLeftToSleep);
            }
        }
        gui.close();
    }
}
