import javax.swing.*;
import java.time.ZonedDateTime;
/**
 * The MinuteHand Class extends Hand
 */
public class MinuteHand extends Hand{
    /**
     * The Minute Hand constructor calls super to intizle its members
     * @param clock
     */
    public MinuteHand(Clock clock){
        super(clock);
    }
    /**
     * The run function operates until the clock is closed and calculates the current X and Y postion of the hands to be draw to the panel, it then sleeps for one second and increments the time
     */
    public void run(){
        time = clock.getRealTime();
        while(!clock.isClosed()){
            X = (int) (Math.cos(time.getMinute() * Math.PI/30 - Math.PI/2) * 100 + 200);
            Y = (int) (Math.sin(time.getMinute() * Math.PI/30 - Math.PI/2) * 100 + 200);
            clock.getPanel().setMinute(X,Y);
            try{
                Thread.sleep(1000);
            }catch(Exception e){
                System.out.println("youre fucked");
            }
            time = time.plusSeconds(1);
        }
    }
}
