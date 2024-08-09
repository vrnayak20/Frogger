import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;


public class FroggerPanel extends JPanel implements KeyListener, Runnable
{
    private BufferedImage buffer;
    private FroggerGame game;
    private int updatesPerSecond = 35;
    private long startTime;
    private int updateCount = 0;
    private BufferedImage car1_Left;
    private BufferedImage car1_Right;
    private BufferedImage car2_Left;
    private BufferedImage car2_Right;
    private BufferedImage limo_Left;
    private BufferedImage limo_Right;
    private BufferedImage semi_Left;
    private BufferedImage semi_Right;
    private BufferedImage frogUp;
    private BufferedImage frogDown;
    private BufferedImage frogLeft;
    private BufferedImage frogRight;
    private BufferedImage hsTurtle;
    private BufferedImage hmTurtle;
    private BufferedImage hlTurtle;
    private BufferedImage sTurtle;
    private BufferedImage mTurtle;
    private BufferedImage lTurtle;
    private BufferedImage sLog;
    private BufferedImage mLog;
    private BufferedImage lLog;
    private BufferedImage lilyPad;
    private BufferedImage frogLife;
    Font newGame = new Font(Font.SERIF, Font.BOLD, 76);
    Font livesAndTime = new Font(Font.SERIF, Font.BOLD, 37);

    public void reset()
    {
        game = new FroggerGame();
        for(int b=0; b<1000; b++)
            game.update();
    }

    public FroggerPanel()
    {
        super();
        setSize(1000,750);
        try
        {
            reset();
            buffer = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_4BYTE_ABGR);
            frogUp = ImageIO.read(new File("Frogger Images\\Frog Up.png"));
            frogDown = ImageIO.read(new File("Frogger Images\\Frog Down.png"));
            frogLeft = ImageIO.read(new File("Frogger Images\\Frog Left.png"));
            frogRight = ImageIO.read(new File("Frogger Images\\Frog Right.png"));
            lilyPad = ImageIO.read(new File("Frogger Images\\lilyPad.png"));
            car1_Left = ImageIO.read(new File("Frogger Images\\Car 1 - Left.png"));
            car1_Right = ImageIO.read(new File("Frogger Images\\Car 1 - Right.png"));
            car2_Left = ImageIO.read(new File("Frogger Images\\Car 2 - Left.png"));
            car2_Right = ImageIO.read(new File("Frogger Images\\Car 2 - Right.png"));
            limo_Left = ImageIO.read(new File("Frogger Images\\Limo - Left.png"));
            limo_Right = ImageIO.read(new File("Frogger Images\\Limo - Right.png"));
            semi_Left = ImageIO.read(new File("Frogger Images\\Semi - Left.png"));
            semi_Right = ImageIO.read(new File("Frogger Images\\Semi - Right.png"));
            frogLife = ImageIO.read(new File("Frogger Images\\Frog Life.png"));
            hsTurtle = ImageIO.read(new File("Frogger Images\\HS-Turtle.png"));
            hmTurtle = ImageIO.read(new File("Frogger Images\\HM-Turtle.png"));
            hlTurtle = ImageIO.read(new File("Frogger Images\\HL-Turtle.png"));
            sTurtle = ImageIO.read(new File("Frogger Images\\S-Turtle.png"));
            mTurtle = ImageIO.read(new File("Frogger Images\\M-Turtle.png"));
            lTurtle = ImageIO.read(new File("Frogger Images\\L-Turtle.png"));
            sLog = ImageIO.read(new File("Frogger Images\\S-Log.png"));
            mLog = ImageIO.read(new File("Frogger Images\\M-Log.png"));
            lLog = ImageIO.read(new File("Frogger Images\\L-Log.png"));
            addKeyListener(this);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    public void paint(Graphics g)
    {
        Graphics b = buffer.getGraphics();
        game.lilyCheck();
        if(game.getPlayer().getY() < 60)
            game.getPlayer().setY(game.getPlayer().getY()+50);

        long started  = game.getStartLifeTime();
        int timeUsed = (int)((System.nanoTime()-started)/1000000000);
        int timerWidth = (int) (230*((double) FroggerGame.MAX_LIFE_TIME - timeUsed)/FroggerGame.MAX_LIFE_TIME);

        game.gameOver();

        b.setColor(Color.GREEN);
        // green background
        b.fillRect(0, 0, getWidth(), getHeight());
        // water
        // main water
        b.setColor(Color.BLUE);
        b.fillRect(0, 100, getWidth(), 250);
        // blocks of water on top
        for (int x = 140; x < getWidth(); x += 215)
        {
            b.fillRect(x, 50, 75, 50);
        }
        // road
        // white outline on side of road
        b.setColor(Color.WHITE);
        b.fillRect(0, 400, getWidth(), 250);
        // gray part of road
        b.setColor(Color.GRAY);
        b.fillRect(0, 402, getWidth(), 246);
        // yellow lines on road
        for(int y=447; y<647; y+=50)
        {
            for (int x = 11; x < getWidth(); x += 100)
            {
                b.setColor(Color.YELLOW);
                b.fillRect(x, y, 75, 5);
            }
        }
        // draws bottom black thing with its text
        b.setColor(Color.BLACK);
        b.fillRect(0, 700, getWidth(), 50);
        b.setFont(livesAndTime);
        b.setColor(Color.RED);
        b.drawString("Lives: ", 50, 740);
        b.drawString("Time Left: ", 560, 740);

        // draws logs
        for(LogLane l: game.getLogLanes())
        {
            for (int i=0; i < l.getItems().size(); i++)
            {
                if (l.getItems().get(i).getType() == Log.SHORT)
                    b.drawImage(sLog, (int) l.getItems().get(i).getX(), (int) l.getY(), null);
                else if (l.getItems().get(i).getType() == Log.MEDIUM)
                    b.drawImage(mLog, (int) l.getItems().get(i).getX(), (int) l.getY(), null);
                else if (l.getItems().get(i).getType() == Log.LONG)
                    b.drawImage(lLog, (int) l.getItems().get(i).getX(), (int) l.getY(), null);
            }
        }

        // draws turtles
        for(TurtleLane t: game.getTurtleLanes())
        {
            for (int i=0; i < t.getItems().size(); i++)
            {
                if(((Turtle) t.getItems().get(i)).getMode() == Turtle.ALWAYS_UP || ((Turtle) t.getItems().get(i)).getMode() == Turtle.UP)
                {
                    if (t.getItems().get(i).getType() == Turtle.ONE_TURTLE)
                        b.drawImage(sTurtle, (int) t.getItems().get(i).getX(), (int) t.getY(), null);
                    else if (t.getItems().get(i).getType() == Turtle.TWO_TURTLE)
                        b.drawImage(mTurtle, (int) t.getItems().get(i).getX(), (int) t.getY(), null);
                    else if (t.getItems().get(i).getType() == Turtle.THREE_TURTLE)
                        b.drawImage(lTurtle, (int) t.getItems().get(i).getX(), (int) t.getY(), null);
                }
                else if(((Turtle) t.getItems().get(i)).getMode() == Turtle.HALF_DOWN || ((Turtle) t.getItems().get(i)).getMode() == Turtle.HALF_UP)
                {
                    if (t.getItems().get(i).getType() == Turtle.ONE_TURTLE)
                        b.drawImage(hsTurtle, (int) t.getItems().get(i).getX(), (int) t.getY(), null);
                    else if (t.getItems().get(i).getType() == Turtle.TWO_TURTLE)
                        b.drawImage(hmTurtle, (int) t.getItems().get(i).getX(), (int) t.getY(), null);
                    else if (t.getItems().get(i).getType() == Turtle.THREE_TURTLE)
                        b.drawImage(hlTurtle, (int) t.getItems().get(i).getX(), (int) t.getY(), null);
                }
                else if(((Turtle) t.getItems().get(i)).getMode() == Turtle.DOWN)
                {
                    // literally do nothing (and yes I know I don't need this else if at all)
                }
            }
        }

        // draws cars
        for(CarLane c: game.getCarLane())
        {
            for (int i=0; i < c.getItems().size(); i++)
            {
                if (c.getDirection() == FroggerItem.LEFT)
                {
                    if (c.getItems().get(i).getType() == Car.CAR_1)
                        b.drawImage(car1_Left, (int) c.getItems().get(i).getX(), (int) c.getY(), null);
                    else if (c.getItems().get(i).getType() == Car.CAR_2)
                        b.drawImage(car2_Left, (int) c.getItems().get(i).getX(), (int) c.getY(), null);
                    else if (c.getItems().get(i).getType() == Car.LIMO)
                        b.drawImage(limo_Left, (int) c.getItems().get(i).getX(), (int) c.getY(), null);
                    else if (c.getItems().get(i).getType() == Car.SEMI)
                        b.drawImage(semi_Left, (int) c.getItems().get(i).getX(), (int) c.getY(), null);
                }
                else if (c.getDirection() == FroggerItem.RIGHT)
                {
                    if (c.getItems().get(i).getType() == Car.CAR_1)
                        b.drawImage(car1_Right, (int) c.getItems().get(i).getX(), (int) c.getY(), null);
                    else if (c.getItems().get(i).getType() == Car.CAR_2)
                        b.drawImage(car2_Right, (int) c.getItems().get(i).getX(), (int) c.getY(), null);
                    else if (c.getItems().get(i).getType() == Car.LIMO)
                        b.drawImage(limo_Right, (int) c.getItems().get(i).getX(), (int) c.getY(), null);
                    else if (c.getItems().get(i).getType() == Car.SEMI)
                        b.drawImage(semi_Right, (int) c.getItems().get(i).getX(), (int) c.getY(), null);
                }
            }
        }
        // draws lily pads
        for(int i=0; i<4; i++)
        {
            b.drawImage(lilyPad, (int)game.getLilyPads()[i].getX(), (int)game.getLilyPads()[i].getY(), 50, 50, null);
        }
        if(game.status() == FroggerGame.PLAYING)
        {
            if (game.getPlayer().getDirection() == Frog.UP)
                b.drawImage(frogUp, (int) game.getPlayer().getX(), (int) game.getPlayer().getY(), null);
            else if (game.getPlayer().getDirection() == Frog.DOWN)
                b.drawImage(frogDown, (int) game.getPlayer().getX(), (int) game.getPlayer().getY(), null);
            else if (game.getPlayer().getDirection() == FroggerItem.LEFT)
                b.drawImage(frogLeft, (int) game.getPlayer().getX(), (int) game.getPlayer().getY(), null);
            else if (game.getPlayer().getDirection() == FroggerItem.RIGHT)
                b.drawImage(frogRight, (int) game.getPlayer().getX(), (int) game.getPlayer().getY(), null);
        }

        for(int i=0; i<4; i++)
        {
            if(game.getLilyPads()[i].getFrog())
            {
                b.drawImage(frogDown, (int)game.getLilyPads()[i].getX(), (int)game.getLilyPads()[i].getY(), 50, 50, null);
                repaint();
            }
        }
        // draws lives
        for(int i=0, x=150; i<game.getLives(); i++, x+=35)
            b.drawImage(frogLife, x, 715, 25, 24, null);
        // draws life timer
        if(game.status() == FroggerGame.PLAYING)
        {
            if(timeUsed > FroggerGame.MAX_LIFE_TIME/3*2)
            {
                b.setColor(Color.RED);
            }
            else if(timeUsed > FroggerGame.MAX_LIFE_TIME / 3)
            {
                b.setColor(Color.ORANGE);
            }
            else {
                b.setColor(Color.GREEN);
            }
            b.fillRect(750, 710, timerWidth, 30);
            if(timeUsed >= FroggerGame.MAX_LIFE_TIME)
                game.playerDeath();
        }

        b.setFont(newGame);
        b.setColor(Color.BLACK);
        if(game.status() == FroggerGame.PLAYER_WINS)
            b.drawString("You win!! ('n' for new game)", 20, 300);

        if(game.status() == FroggerGame.DEAD)
            b.drawString("You lost. ('n' for new game)", 20, 300);


        g.drawImage(buffer, 0, 0, null);
    }


    @Override
    public void keyTyped(KeyEvent e) {
        char dir = e.getKeyChar();
        if((dir == 'd' || dir == 'D') && game.status() == FroggerGame.PLAYING)
        {
            game.getPlayer().setDirection(FroggerItem.RIGHT);
            game.getPlayer().setX(game.getPlayer().getX()+50);
            if(game.getPlayer().getX()>getWidth()-40)
                game.getPlayer().setX(getWidth()-40);
        }
        else if((dir == 'a' || dir == 'A') && game.status() == FroggerGame.PLAYING)
        {
            game.getPlayer().setDirection(FroggerItem.LEFT);
            game.getPlayer().setX(game.getPlayer().getX()-50);
            if(game.getPlayer().getX()<0)
                game.getPlayer().setX(0);
        }
        else if((dir == 'w' || dir == 'W') && game.status() == FroggerGame.PLAYING)
        {
            game.getPlayer().setDirection(Frog.UP);
            game.getPlayer().setY(game.getPlayer().getY()-50);
        }
        else if((dir == 's' || dir == 'S') && game.status() == FroggerGame.PLAYING)
        {
            game.getPlayer().setDirection(Frog.DOWN);
            game.getPlayer().setY(game.getPlayer().getY()+50);
            if(game.getPlayer().getY()>660)
                game.getPlayer().setY(game.getPlayer().getY()-50);
        }
        else if((dir == 'n' || dir == 'N') && (game.status() != FroggerGame.PLAYING))
            reset();

        repaint();
    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    public void addNotify()
    {
        super.addNotify();
        requestFocus();
    }

    public void update()
    {
        game.update();
    }

    @Override
    public void run()
    {
        int waitToUpdate = 1000/updatesPerSecond;
        startTime = System.nanoTime();

        while(true)
        {
            boolean shouldRepaint = false;
            long currentTime = System.nanoTime();
            long updatesNeeded  = ((currentTime-startTime)/1000000)/waitToUpdate;
            for(long x=updateCount; x<updatesNeeded; x++)
            {
                update();
                shouldRepaint=true;
                updateCount++;
            }

            if(shouldRepaint)
                repaint();

            try
            {
                Thread.sleep(35);
            }
            catch(Exception e)
            {
                System.out.println("Error sleeping in run method: " + e.getMessage());
                e.printStackTrace();
            }
        }


    }
}
