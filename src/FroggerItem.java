import java.awt.*;

public class FroggerItem
{
    public static final int LEFT = 2;
    public static final int RIGHT = 3;

    private double x;
    private double y;
    private int direction;
    private int type;
    private double speed;
    private Rectangle rect;

    public FroggerItem(double x, double y, int direction, double speed, int type)
    {
        this.x = x;
        this.y = y;
        this.direction = direction;
        this.speed = speed;
        this.type = type;
        rect = new Rectangle((int)getX(), (int)getY(), 40, 40);
        updateRectangle();
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
        updateRectangle();
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
        updateRectangle();
    }

    public int getDirection() {
        return direction;
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public Rectangle getRectangle() {
        return rect;
    }

    public void setRectangle(Rectangle rect) {
        this.rect = rect;
    }

    public void updateRectangle(){
        rect.x = (int)x;
        rect.y = (int)y;
    }

    public void update(){
        if(direction==LEFT)
            x -= speed;
        if(direction==RIGHT)
            x += speed;
        updateRectangle();
    }
}
