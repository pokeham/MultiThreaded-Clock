import javafx.concurrent.Task;

import javax.swing.*;
import java.awt.*;
import java.awt.font.FontRenderContext;
import java.awt.geom.Rectangle2D;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.TextStyle;
import java.util.Locale;
import java.util.concurrent.*;

/**
 * The clock class houses the clock and the Mypanel class to be repainted
 */
public class Clock extends JFrame{
    private ZonedDateTime realTime;
    private String ZoneID;
    private MyPanel panel;
    private boolean closed;
    private SecondHand second;
    private MinuteHand minute;
    private HourHand hour;

    /**
     * The Clock constructor intitializes the Frame and Panel and then calls the run() function
     * @param realTime
     */
    public Clock(ZonedDateTime realTime ){
        super("Clock of " + realTime.getZone().toString());
        addWindowListener(new java.awt.event.WindowAdapter(){

            public void windowClosing(java.awt.event.WindowEvent e){
                closed = true;
            }
        });
        second = new SecondHand(this);
        minute = new MinuteHand(this);
        hour = new HourHand(this);
        this.ZoneID = realTime.getZone().toString();
        this.realTime = realTime;
        setSize(400,400);
        panel = new MyPanel(400,400,realTime);
        add(panel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        setResizable(true);
        closed = false;
        run();
    }

    /**
     * returns the second member
     * @return returns the second member
     */
    public SecondHand getSecond(){return second;}

    /**
     * returns whether the clock is closed
     * @return whether the clock is closed
     */
    public boolean isClosed(){return closed;}

    /**
     * returns the ZonedDateTime member time
     * @return the ZonedDateTime member time
     */
    public ZonedDateTime getRealTime(){
        return realTime;
    }

    /**
     * returns the MyPanel class member panel
     * @return the MyPanel class member panel
     */
    public MyPanel getPanel(){return panel;}

    /**
     * the run function executes the Hand classes
     */
    public void run() {
        MyThread thread = new MyThread(this, ZoneID, realTime);
        ExecutorService es = Executors.newCachedThreadPool();
        es.execute(thread);
        es.execute(second);
        es.execute(minute);
        es.execute(hour);
        es.shutdown();
    }
}

