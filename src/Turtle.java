public class Turtle extends FroggerItem
{

    public static final int ONE_TURTLE = 0, TWO_TURTLE = 1, THREE_TURTLE = 2, ALWAYS_UP = 4, UP = 0, HALF_UP = 1, DOWN = 2, HALF_DOWN = 3;
    private int mode;
    private double rand;
    private long timer = System.nanoTime(); // start time

    public Turtle(double x, double y, int direction, double speed, int type)
    {
        super(x, y, direction, speed, type);
        if(type == ONE_TURTLE)
            getRectangle().width = 40;
        if(type == TWO_TURTLE)
            getRectangle().width = 80;
        if(type == THREE_TURTLE)
            getRectangle().width = 120;
        rand = Math.random();
        if(rand > 0.7)
            mode = 4;
        if(rand <= 0.7)
            mode = (int)(Math.random()*4);
    }

    public int getMode()
    {
        return mode;
    }
    public void update()
    {
        super.update();
        if((System.nanoTime()-timer)/1000000000 > 2 && mode != ALWAYS_UP)
        {
            if(mode == UP)
            {
                mode = HALF_DOWN;
            }
            else if(mode == HALF_DOWN)
            {
                mode = DOWN;
            }
            else if(mode == DOWN)
            {
                mode = HALF_UP;
            }
            else if(mode == HALF_UP)
            {
                mode = UP;
            }
            timer = System.nanoTime();
        }
    }

}
