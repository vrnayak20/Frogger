import java.util.ArrayList;

public class Lane
{
    private double y;
    private int direction;
    private double speed;
    private ArrayList<FroggerItem> items = new ArrayList<>();

    public Lane(double y, int direction, double speed)
    {
        this.speed = speed;
        this.direction = direction;
        this.y = y;
    }

    public double getY() {
        return y;
    }

    public int getDirection() {
        return direction;
    }

    public double getSpeed() {
        return speed;
    }

    public ArrayList<FroggerItem> getItems() {
        return items;
    }
    public void update()
    {
        for(FroggerItem i: items)
        {
            i.update();
        }
    }
}
