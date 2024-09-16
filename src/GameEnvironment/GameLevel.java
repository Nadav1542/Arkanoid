package GameEnvironment;

import Animations.AnimationRunner;
import Animations.Animation;
import biuoop.DrawSurface;
import Animations.KeyPressStoppableAnimation;
import Animations.CountdownAnimation;
import Animations.PauseScreen;
import java.awt.Color;

import Geometry.Ball;
import Geometry.Block;
import Geometry.Paddle;
import Geometry.Rectangle;
import Geometry.Point;
import Geometry.Collidable;
import biuoop.KeyboardSensor;

/**
 * The Game level Class.
 */
public class GameLevel implements Animation {

    /**
     * The constant WINDOW_WIDTH.
     */
    public static final int WINDOW_WIDTH = 800;


    /**
     * The constant WINDOW_HEIGHT.
     */
    public static final int WINDOW_HEIGHT = 600;


    /**
     * Sprites collection in the game.
     */
    private SpriteCollection sprites;
    /**
     * Game environment field which includes collidable list.
     */
    private GameEnvironment environment;
    /**
     * Paddle controlled by the user.
     */
    private Paddle userPaddle;


    /**
     * Counter of blocks in the game.
     */
    private Counter counterBlocks;
    /**
     * Counter of balls in the game.
     */
    private Counter counterBalls;
    /**
     * Counter score of player.
     */
    private Counter score;

    private AnimationRunner runner;
    private boolean running;

    private LevelInformation levelInformation;

    private KeyboardSensor keyboardSensor;

    private ScoreTrackingListener scoreTrackingListener;

    @Override
    public boolean shouldStop() {
        return !this.running;
    }

    /**
     * Constructor instantiates of a new Game level.
     *
     * @param levelInformation      level information(balls,blocks,featurs).
     * @param ks                    Keyboardsensor.
     * @param ar                    runner for running animations.
     * @param scoreTrackingListener score tracking listener, for knowing
     *                              score in previous level.
     */
    public GameLevel(LevelInformation levelInformation, KeyboardSensor ks,
                     AnimationRunner ar, ScoreTrackingListener scoreTrackingListener) {
        this.levelInformation = levelInformation;
        this.runner = ar;
        this.keyboardSensor = ks;
        this.scoreTrackingListener = scoreTrackingListener;
    }

    @Override
    public void doOneFrame(DrawSurface d) {

        this.sprites.drawAllOn(d);
        this.sprites.notifyAllTimePassed();
        if (this.runner.getGui().getKeyboardSensor().isPressed("p")) {
            this.runner.run(new KeyPressStoppableAnimation(this.runner.getGui().getKeyboardSensor(), "space",
                    new PauseScreen(this.runner.getGui().getKeyboardSensor())));
        }
//just borders left
        if (this.levelInformation.numberOfBlocksToRemove()
                == (this.levelInformation.blocks().size() - this.counterBlocks.getValue())) {
            this.running = false;
        }
        if (this.counterBalls.getValue() == 0) {
            this.running = false;
        }
    }

    /**
     * Method creates balls on top of paddle.
     */
    public void createBallsOnTopOfPaddle() {
        for (int i = 0; i < this.levelInformation.numberOfBalls(); i++) {
            Ball b = new Ball(new Point(400, 500), 5, Color.GRAY);
            b.setVelocity(this.levelInformation.initialBallVelocities().get(i));
            this.addBallToGame(b);
        }
    }

    /**
     * Method add ball to game.
     *
     * @param ball the ball for adding.
     */
    public void addBallToGame(Ball ball) {

        ball.setGameEnvironment(this.getEnvironment());
        this.addSprite(ball);
        this.addnumBalls(1);

    }

    /**
     * Sets paddle speed.
     *
     * @param paddleSpeed the paddle speed
     */
    public void setPaddleSpeed(int paddleSpeed) {
        this.userPaddle.setSpeed(paddleSpeed);
    }

    /**
     * Gets paddle speed.
     *
     * @return the paddle speed
     */
    public int getPaddleSpeed() {
        return this.userPaddle.getSpeed();
    }



    /**
     * Method initialize the game setting. Creating paddle,borders,blocks and
     * balls.
     */
    public void initialize() {
        this.sprites = new SpriteCollection();
        this.environment = new GameEnvironment();
        this.userPaddle = new Paddle(keyboardSensor,
                new Block(new Point((800-this.levelInformation.paddleWidth())/2,
                570),
                this.levelInformation.paddleWidth(), 15, Color.magenta), this.levelInformation.paddleSpeed());
//setting counters for blocks,balls and score.
        this.counterBlocks = new Counter(0);
        this.counterBalls = new Counter(0);
        this.score = new Counter(this.scoreTrackingListener.getCurrentScore().getValue());
        //creating listeners which remove balls and blocks.
        BlockRemover blockRemover = this.setBlockRemover(this.counterBlocks);
       // BallRemover ballRemover = this.setBallRemover(this.counterBalls);
        //creating top line for game details.
        GameIndicator gameIndicator =
                new GameIndicator(scoreTrackingListener.getCurrentScore(),
                        this.levelInformation.levelName());
        this.addSprite(this.levelInformation.getBackground());
        this.addSprite(gameIndicator);
        this.userPaddle.addToGame(this);
        createBallsOnTopOfPaddle();
        createBorders();

        for (int i = 0; i < this.levelInformation.blocks().size(); i++) {

            Block b = this.levelInformation.blocks().get(i);
            this.addToGame(b);
            b.addHitListener(blockRemover);
            b.addHitListener(this.scoreTrackingListener);
            if (this.levelInformation.levelName().equals("Level Two") && (i % 2 == 0)) {
                //duplicates the hitter ball.
                b.addHitListener(new BallDuplicator(this));
            }
            if (this.levelInformation.levelName().equals("Level Three") && (i % 3 == 0)) {
                //makes the paddle faster,more hard for controlling.
                b.addHitListener(new PaddleAccelarator(this));
            }
            blockRemover.addnumBlocks(1);
        }


    }


    /**
     * Method creates borders for the game.
     *
     */
    public void createBorders() {
        Block rightborder =
                new Block(new Rectangle(
                        new Point(WINDOW_WIDTH - 10, 17), 10,
                        WINDOW_HEIGHT - 17), Color.darkGray);
        Block leftborder = new Block(new Rectangle(new Point(0, 17),
                10, WINDOW_HEIGHT - 17), Color.darkGray);
        Block upperborder = new Block(new Rectangle(new Point(0, 17),
                WINDOW_WIDTH, 15), Color.darkGray);
        Block bottomborder = new Block(new Rectangle(new Point(10,
                WINDOW_HEIGHT - 15),
                WINDOW_WIDTH - 15, 15), Color.white);
        BallRemover ballRemover = this.setBallRemover(this.counterBalls);
        this.addToGame(rightborder);
        this.addToGame(leftborder);
        this.addToGame(upperborder);
        this.addCollidable(bottomborder);
        bottomborder.addHitListener(ballRemover);
    }

    /**
     * Method Sets boolean running field of the game.
     *
     * @param statement setting true or false.
     */
    public void setRunning(boolean statement) {
        this.running = statement;
    }


    /**
     * Method runs animation on screen.
     */
    public void run() {
        this.runner.run(new CountdownAnimation(2, 3, this.sprites));
        this.running = true;
        this.runner.run(this);

        if (this.counterBlocks.getValue() == 0) {
            this.score.increase(100);
        }

    }

    /**
     * Gets counter balls.
     *
     * @return the counter balls
     */
    public Counter getCounterBalls() {
        return this.counterBalls;
    }


    /**
     * Method adding number of balls to counters of balls.
     *
     * @param num number of balls for adding.
     */
    public void addnumBalls(int num) {
        this.counterBalls.increase(num);
    }

    /**
     * Method removing number of balls from counter.
     *
     * @param num number of balls to removing from counter.
     */
    public void removenumBalls(int num) {
        this.counterBalls.decrease(num);
    }


    /**
     * Method creates block remover for removing the blocks.
     *
     * @param counterBlocks counter of blocks in the game.
     * @return (BlockRemover) listener which remove blocks
     */
    public BlockRemover setBlockRemover(Counter counterBlocks) {
        return new BlockRemover(this, counterBlocks);
    }

    /**
     * Method creates ball remover for removing the balls.
     *
     * @param counterBalls counter of balls in the game.
     * @return (BlockRemover) listener which remove ball.
     */
    public BallRemover setBallRemover(Counter counterBalls) {
        return new BallRemover(this, counterBalls);
    }

    /**
     * Add to game.
     *
     * @param b the b
     */
    public void addToGame(Block b) {
        this.environment.addCollidable(b);
        this.sprites.addSprite(b);
    }

    /**
     * Remove from game.
     *
     * @param b the b
     */
    public void removeFromGame(Block b) {
        this.environment.removeCollidable(b);
        this.sprites.removeSprite(b);
        this.counterBlocks.decrease(1);
    }


    /**
     * Method return game environment field of game object.
     *
     * @return (GameEnvironment) game environment field of game object.
     */
    public GameEnvironment getEnvironment() {
        return this.environment;
    }


    /**
     * Method adding collidable to collidable list of objects in the game.
     *
     * @param c collidable for adding.
     */
    public void addCollidable(Collidable c) {
        this.environment.addCollidable(c);
    }


    /**
     * Method adding sprite to sprite-collection in the game.
     *
     * @param s sprite to be added.
     */
    public void addSprite(Sprite s) {
        this.sprites.addSprite(s);
    }

    /**
     * Method removes sprite.
     *
     * @param s sprite for removing.
     */
    public void removeSprite(Sprite s) {
        this.sprites.removeSprite(s);
    }
}
