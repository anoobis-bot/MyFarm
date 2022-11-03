public class Time
{
    private int systemDay;

    Time()
    {
        systemDay = 0;
    }

    public int advanceTime()
    {
        this.systemDay++;

        return this.systemDay;
    }

    public int getCurrentDay()
    {
        return this.systemDay;
    }
}
