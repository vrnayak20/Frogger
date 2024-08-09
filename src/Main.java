public class Main
{
    public static void main(String[] args)
    {
        FroggerFrame w = new FroggerFrame("Frogger");
        Thread t = new Thread(w.getP());
        t.start();
    }
}
