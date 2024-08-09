public class TurtleLane extends Lane
{
    public TurtleLane(double y, int direction, double speed)
    {
        super(y, direction, speed);
        getItems().add(new Turtle(500, getY(), getDirection(), getSpeed(), (int)(Math.random()*3)));
    }

    public void update()
    {
        int rand = (int)(Math.random()*3);
        int length = getItems().get(getItems().size()-1).getRectangle().width;
        int gap = 100 + (int)(Math.random()*30);

        if (1000-length > getItems().get(getItems().size()-1).getX())
            getItems().add(new Turtle(1000+gap, getY(), getDirection(), getSpeed(), rand));

        if (-length > getItems().get(0).getX())
            getItems().remove(0);

        super.update();
    }
}
