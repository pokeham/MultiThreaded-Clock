import javax.swing.*;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class MyThread implements Runnable {
    ZonedDateTime time;
    private Clock clock;
    private String ZoneID;

    /**
     * The myThread class is used to update the String time on the clock
     * @param temp the clock object
     * @param ZoneID the zone of time
     * @param temp2 the time object
     */
    public MyThread(Clock temp,String ZoneID,ZonedDateTime temp2){
        this.ZoneID = ZoneID;
        clock = temp;
        time = temp2;
    }

    /**
     * run updates the panel to display the correct time
     */
    public void run(){
        while(!clock.isClosed()){
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy \n HH:mm:ss");
            String realTime = new String(time.format(formatter));
            clock.getPanel().setTime(realTime);
            try{
                Thread.sleep(1000);
            }catch(Exception e){
                System.out.println("youre fucked");
            }
            time = time.plusSeconds(1);
        }
    }

}
