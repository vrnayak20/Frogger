public class LogLane extends Lane
{
    public LogLane(double y, int direction, double speed)
    {
        super(y, direction, speed);
        getItems().add(new Log(650-(500 + (int)(Math.random()*70)), y, direction, speed, (int)(Math.random()*3)));
    }

    public void update()
    {
        int rand = (int)(Math.random()*3);
        int length = (int) getItems().get(getItems().size()-1).getRectangle().getWidth();
        int gap = 130 + (int)(Math.random()*30);

        if (getItems().get(getItems().size()-1).getX() > 0)
            getItems().add(new Log(-length-gap, getY(), getDirection(), getSpeed(), rand));

        if (1000 < getItems().get(0).getX())
            getItems().remove(0);

        super.update();
    }
}
