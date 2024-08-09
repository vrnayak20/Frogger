public class LilyPad extends FroggerItem
{
    private boolean frog;

    public LilyPad(double x, double y)
    {
        super(x, y, 0, 0, 0);
    }

    public void setFrog(boolean frog)
    {
        this.frog = frog;
    }

    public boolean getFrog()
    {
        return frog;
    }

}
