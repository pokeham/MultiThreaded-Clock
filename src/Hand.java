import javax.swing.*;
import java.time.ZonedDateTime;

/**
 * The hand class is the abstract parent class to the SecondHand, HourHand, and MinuteHand and implements runnable
 */
public abstract class Hand implements Runnable {
    protected ZonedDateTime time;
    protected int X;
    protected int Y;
    protected Clock clock;

    /**
     * The construtor assigns the clock member variabel and the time member to the clocks time
     * @param clock
     */
    public Hand(Clock clock){
        this.clock = clock;
        time = clock.getRealTime();
    }
    public void run(){

    }

}
