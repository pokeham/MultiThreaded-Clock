import javax.swing.*;
import java.time.ZonedDateTime;

/**
 * The HourHand Class extends Hand
 */
public class HourHand extends Hand{
    /**
     * The Hour Hand constructor calls super to intizle its members
     * @param clock
     */
    public HourHand(Clock clock){
        super(clock);
    }

    /**
     * The run function operates until the clock is closed and calculates the current X and Y postion of the hands to be draw to the panel, it then sleeps for one second and increments the time
     */
    public void run(){
        time = clock.getRealTime();
        while(!clock.isClosed()){
            X = (int) (Math.cos((time.getHour()*30 + time.getMinute()/2) * Math.PI/180 - Math.PI/2) * 80 + 200);
            Y = (int) (Math.sin((time.getHour()*30 + time.getMinute()/2) * Math.PI/180 - Math.PI/2) * 80 + 200);
            clock.getPanel().setHour(X,Y);
            try{
                Thread.sleep(1000);

            }catch(Exception e){
                System.out.println("youre fucked");
            }
            time = time.plusSeconds(1);
        }
    }
}
