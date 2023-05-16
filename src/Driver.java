import java.time.*;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

/**
 * The Driver class is used to demonstate the clock object
 */

public class Driver {
    /**
     * the function used to demonstrate the clock object
     * @param args the arguement to run main
     */
    public static void main(String[] args){
        ZonedDateTime iowa = ZonedDateTime.of(LocalDate.of(2022,3,13), LocalTime.of(1,59,55), ZoneId.of("America/Chicago"));
        Clock iowa_clock = new Clock(iowa);
        boolean menu = true;
        int choice = 0;
        while(menu){
            try{
                Scanner input = new Scanner(System.in);
                System.out.println("Would you like to see 10 more clocks(yes - 1/no -0)");
                choice = input.nextInt();
                if(choice == 0 || choice == 1){
                    menu = false;
                }else{
                    System.out.println("Please Enter a Valid Input");
                }
            }catch (Exception e){
                System.out.println("Please Enter a Valid Input");
            }
        }
        if(choice == 0){
            System.exit(0);
        }else{
            LocalDate date = LocalDate.from(iowa_clock.getSecond().getTime());
            LocalTime time = LocalTime.from(iowa_clock.getSecond().getTime());
            LocalDateTime temp =  LocalDateTime.of(date,time);
            ZonedDateTime temp2 = temp.atZone(ZoneId.of("America/Chicago"));
            Set<String> set = new HashSet<String>(){{
                add("America/Denver");
                add("Asia/Tokyo");
                add("Europe/Paris");
                add("Pacific/Fiji");
                add("America/New_York");
                add("America/Los_Angeles");
                add("Pacific/Honolulu");
                add("Africa/Cairo");
                add("Africa/Nairobi");
                add("Canada/Yukon");
                add("America/Mexico_City");
            }};
            for(String zone : set){
                ZonedDateTime timeTemp = temp2.withZoneSameInstant(ZoneId.of(zone));
                Clock clock = new Clock(timeTemp);
            }
        }
    }
}
