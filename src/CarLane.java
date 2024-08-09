public class CarLane extends Lane
{
    private int x=0;
    public CarLane(double y, int direction, double speed)
    {
        super(y, direction, speed);
    }

    public void update()
    {
        super.update();
        int rand = (int)(Math.random()*4);
        int pixelCount;
        if(getItems().size() == 0)
        {
            if (getDirection() == FroggerItem.LEFT)
            {
                getItems().add(new Car(1000, getY(), getDirection(), getSpeed(), rand));
            }
            else if (getDirection() == FroggerItem.RIGHT)
            {
                    if(rand == Car.CAR_1 || rand == Car.CAR_2)
                getItems().add(new Car(110, getY(), getDirection(), getSpeed(), rand));
                else if(rand == Car.LIMO)
                    getItems().add(new Car(70, getY(), getDirection(), getSpeed(), rand));
                else if(rand == Car.SEMI)
                    getItems().add(new Car(30, getY(), getDirection(), getSpeed(), rand));
            }
        }

        if (getDirection() == FroggerItem.LEFT)
        {
            rand = (int)(Math.random()*4);
            if(getItems().get(x).getX() < 1000 && (getItems().get(x).getType() == Car.CAR_1 || getItems().get(x).getType() == Car.CAR_2))
            {
                getItems().add(new Car(1220, getY(), getDirection(), getSpeed(), rand));
                x++;
            }
            else if(getItems().get(x).getX() < 1000 && getItems().get(x).getType() == Car.LIMO)
            {
                getItems().add(new Car(1260, getY(), getDirection(), getSpeed(), rand));
                x++;
            }
            else if(getItems().get(x).getX() < 1000 && getItems().get(x).getType() == Car.SEMI)
            {
                getItems().add(new Car(1300, getY(), getDirection(), getSpeed(), rand));
                x++;
            }
            if (-150 > getItems().get(0).getX()) {
                getItems().remove(0);
                x--;
            }
        }
        else if (getDirection() == FroggerItem.RIGHT)
        {
            rand = (int)(Math.random()*4);
            if(rand == 0)
                pixelCount = 120;
            else if(rand == 1)
                pixelCount = 80;
            else
                pixelCount = 40;

            if(getItems().get(x).getX() > -40 && (getItems().get(x).getType() == Car.CAR_1 || getItems().get(x).getType() == Car.CAR_2))
            {
                getItems().add(new Car(-220-pixelCount, getY(), getDirection(), getSpeed(), rand));
                x++;
            }
            else if(getItems().get(x).getX() > -80 && getItems().get(x).getType() == Car.LIMO)
            {
                getItems().add(new Car(-260-pixelCount, getY(), getDirection(), getSpeed(), rand));
                x++;
            }
            else if(getItems().get(x).getX() > -120 && getItems().get(x).getType() == Car.SEMI)
            {
                getItems().add(new Car(-300-pixelCount, getY(), getDirection(), getSpeed(), rand));
                x++;
            }
            if (1000 < getItems().get(0).getX()) {
                getItems().remove(0);
                x--;
            }
        }
    }
}
