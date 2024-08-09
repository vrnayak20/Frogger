public class Car extends FroggerItem
{
    public static final int SEMI = 0;
    public static final int LIMO = 1;
    public static final int CAR_1 = 2;
    public static final int CAR_2 = 3;

    public Car(double x, double y, int direction, double speed, int type)
    {
        super(x, y, direction, speed, type);
        if(type == CAR_1 || type == CAR_2)
            getRectangle().width = 40;
        if(type == LIMO)
            getRectangle().width = 80;
        if(type == SEMI)
            getRectangle().width = 120;
    }
}
