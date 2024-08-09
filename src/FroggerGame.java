import java.util.ArrayList;
public class FroggerGame
{
    public static final int PLAYING = 0;
    public static final int DEAD = 1;
    public static final int PLAYER_WINS = 2;
    public static final int MAX_LIFE_TIME = 30;

    private int status;
    private long startLifeTime;
    private int lives;
    private Frog player;
    private LilyPad[] lilyPads;
    private CarLane[] carLanes;
    private LogLane[] logLanes;
    private TurtleLane[] turtleLanes;
    private int deathX;
    private int deathY;

    private int won;

    public FroggerGame()
    {
        status = PLAYING;
        deathX = 480;
        deathY = 655;
        player = new Frog(deathX, deathY);
        lilyPads = new LilyPad[]{new LilyPad(157, 55), new LilyPad(372, 55), new LilyPad(587, 55), new LilyPad(802, 55)};
        carLanes = new CarLane[]
                {new CarLane(607, FroggerItem.LEFT, 4.4),
                new CarLane(557, FroggerItem.RIGHT, 3),
                new CarLane(507, FroggerItem.LEFT, 4.2),
                new CarLane(457, FroggerItem.RIGHT, 5.5),
                new CarLane(407, FroggerItem.LEFT, 4)};
        logLanes = new LogLane[]
                {new LogLane(252, FroggerItem.RIGHT, 2),
                new LogLane(203, FroggerItem.RIGHT, 2),
                new LogLane(105, FroggerItem.RIGHT, 2)};
        turtleLanes = new TurtleLane[]
                {new TurtleLane(301, FroggerItem.LEFT, 2),
                new TurtleLane(154, FroggerItem.LEFT, 2)};
        lives = 3;
        startLifeTime = System.nanoTime();
        won = 0;
    }

    public int status()
    {
        return status;
    }
    public Frog getPlayer()
    {
        return player;
    }
    public LilyPad[] getLilyPads()
    {
        return lilyPads;
    }
    public void lilyCheck()
    {
        if(player.getY()<90) {
            if (player.getX() >= 110 && player.getX() < 215 && !lilyPads[0].getFrog()) {
                lilyPads[0].setFrog(true);
                player = new Frog(480, 655);
                deathY = 655;
                won++;
                startLifeTime = System.nanoTime();
            }
            if (player.getX() >= 325 && player.getX() < 430 && !lilyPads[1].getFrog()) {
                lilyPads[1].setFrog(true);
                player = new Frog(480, 655);
                deathY = 655;
                won++;
                startLifeTime = System.nanoTime();
            }
            if (player.getX() >= 540 && player.getX() < 655 && !lilyPads[2].getFrog()) {
                lilyPads[2].setFrog(true);
                player = new Frog(480, 655);
                deathY = 655;
                won++;
                startLifeTime = System.nanoTime();
            }
            if (player.getX() >= 755 && player.getX() < 860 && !lilyPads[3].getFrog()) {
                lilyPads[3].setFrog(true);
                player = new Frog(480, 655);
                deathY = 655;
                won++;
                startLifeTime = System.nanoTime();
            }
            if (won == 4) {
                status = PLAYER_WINS;
            }
        }
    }

    public long getStartLifeTime()
    {
        return startLifeTime;
    }

    public int getLives()
    {
        return lives;
    }

    public void playerDeath()
    {
        lives--;
        player = new Frog(deathX, deathY);
        startLifeTime = System.nanoTime();
    }

    public void gameOver()
    {
        if(lives == 0)
        {
            status = DEAD;
        }
    }

    public LogLane[] getLogLanes()
    {
        return logLanes;
    }

    public TurtleLane[] getTurtleLanes()
    {
        return turtleLanes;
    }

    public CarLane[] getCarLane()
    {
        return carLanes;
    }

    public void savePoint()
    {
        deathX = 480;
        deathY = 355;
    }

    public void carCheck()
    {
        for (CarLane carLane : carLanes) {
            for (int j = 0; j < carLane.getItems().size(); j++) {
                if (player.getRectangle().intersects(carLane.getItems().get(j).getRectangle())) {
                    playerDeath();
                }
            }
        }
    }

    public void logCheck()
    {
        for (LogLane carLane : logLanes) {
            for (int j = 0; j < carLane.getItems().size(); j++) {
                if (player.getRectangle().intersects(carLane.getItems().get(j).getRectangle())) {
                    player.setX(player.getX()+carLane.getSpeed());
                    return;
                }
            }
        }
        playerDeath();

    }
    public void turtleCheck()
    {
        for (TurtleLane carLane : turtleLanes) {
            for (int j = 0; j < carLane.getItems().size(); j++) {
                if (player.getRectangle().intersects(carLane.getItems().get(j).getRectangle()) && ((Turtle)carLane.getItems().get(j)).getMode() != Turtle.DOWN) {
                    player.setX(player.getX()-carLane.getSpeed());
                    return;
                }
            }
        }
        playerDeath();
    }

    public void runChecks()
    {
        if(player.getY() < 400)
            savePoint();
        if(player.getY() > 350 && player.getY() < 700)
            carCheck();
        if(player.getY()<90)
            lilyCheck();
        if((player.getY() < 300 && player.getY() > 202) || (player.getY() < 153 && player.getY() > 104))
            logCheck();
        if((player.getY() < 349 && player.getY() > 300) || (player.getY() < 202 && player.getY() > 153))
            turtleCheck();
    }

    public void update()
    {
        for (CarLane carLane : carLanes)
            carLane.update();
        for (LogLane logLane : logLanes)
            logLane.update();
        for (TurtleLane turtleLane : turtleLanes)
            turtleLane.update();
        runChecks();
    }
}
