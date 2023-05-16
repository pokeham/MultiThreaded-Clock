import javax.swing.*;
import java.awt.*;
import java.time.ZonedDateTime;
/**
 * The HourHand Class extends Hand
 */
public class SecondHand extends Hand{
    /**
     * The Hour Hand constructor calls super to intizle its members
     * @param clock
     */
    public SecondHand(Clock clock){
        super(clock);
    }
    /**
     * The run function operates until the clock is closed and calculates the current X and Y postion of the hands to be draw to the panel, it then sleeps for one second and increments the time
     */
    public void run(){
        time = clock.getRealTime();
        while(!clock.isClosed()){
            X = (int) (Math.cos(time.getSecond() * Math.PI/30 - Math.PI/2) * 120 + 200);
            Y = (int) (Math.sin(time.getSecond() * Math.PI/30 - Math.PI/2) * 120 + 200);

            clock.getPanel().setSecond(X,Y);
            SwingUtilities.invokeLater(new Runnable() {
                @Override
                public void run() {
                    clock.getPanel().repaint();
                }
            });
            try{
                Thread.sleep(1000);
            }catch(Exception e){
                System.out.println("youre fucked");
            }
            time = time.plusSeconds(1);
        }
    }
    public ZonedDateTime getTime(){return time;}
}
