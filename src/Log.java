public class Log extends FroggerItem
{
    public static final int SHORT = 0;
    public static final int MEDIUM = 1;
    public static final int LONG = 2;

    public Log(double x, double y, int direction, double speed, int type)
    {
        super(x, y, direction, speed, type);
        if(type == SHORT)
            getRectangle().width = 80;
        if(type == MEDIUM)
            getRectangle().width = 120;
        if(type == LONG)
            getRectangle().width = 200;
    }
}
