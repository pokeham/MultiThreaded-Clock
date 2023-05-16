import javax.swing.*;
import java.awt.*;
import java.awt.font.FontRenderContext;
import java.awt.geom.Rectangle2D;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.TextStyle;
import java.util.Locale;


public class MyPanel extends JPanel {
    private static final int centerX = 200;
    private static final int centerY = 200;
    private static final int width = 400;
    private static final int height = 400;
    private ZonedDateTime realTime;
    private String time;
    private int Xsecond;
    private int Ysecond;
    private int Xhour;
    private int Yhour;
    private int Xminute;
    private int Yminute;

    /**
     * The MyPanel holds the clock components
     * @param x the width
     * @param y the height
     * @param realTime the time object
     */
    public MyPanel(int x, int y,ZonedDateTime realTime){
        setSize(x,y);
        this.realTime = realTime;
        time = "XXXX";
        Xsecond =(int) (Math.cos(realTime.getSecond() * Math.PI/30 - Math.PI/2) * 120 + 200);
        Ysecond = (int) (Math.sin(realTime.getSecond() * Math.PI/30 - Math.PI/2) * 120 + 200);
        Xminute = (int) (Math.cos(realTime.getMinute() * Math.PI/30 - Math.PI/2) * 100 + 200);
        Yminute = (int) (Math.sin(realTime.getMinute() * Math.PI/30 - Math.PI/2) * 100 + 200);
        Xhour = (int) (Math.cos(realTime.getHour() * Math.PI/30 - Math.PI/2) * 80 + 200);
        Yhour = (int) (Math.sin(realTime.getHour() * Math.PI/30 - Math.PI/2) * 80 + 200);
        setVisible(true);
    }

    /**
     * paint component draws the grpahics of a clock to the panel
     * @param g
     */
    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D)g;
        super.paintComponents(g2d);
        g2d.setColor(Color.GRAY);
        g2d.fillRect(0,0,width+100,height+100);
        g2d.setColor(Color.WHITE);
        g2d.fillOval(centerX-(300/2),centerY-(300/2)+15,300,300);
        g2d.setColor(Color.RED);
        g2d.setFont(new Font("TimesRoman",Font.BOLD,15));
        g2d.drawString(time,centerX-90,centerY);
        g2d.drawString(realTime.getZone().getDisplayName(TextStyle.FULL, Locale.ENGLISH), centerX-50,centerY+20);
        g2d.setColor(Color.BLACK);
        Font font = new Font("TimesRoman",Font.BOLD,15);
        FontRenderContext frc = new FontRenderContext(null,true,true);
        for(int i = 0; i<12;i++){
            double t= 2*Math.PI * i/12;
            int x = (int) Math.round(centerX + 135*Math.cos(t));
            int y = (int) Math.round(centerY + 135*Math.sin(t));
            String num = String.valueOf(((i+2)%12)+1);
            Rectangle2D r2D = font.getStringBounds(num,frc);
            int rWidth = (int) Math.round(r2D.getWidth());
            int rHeight = (int) Math.round(r2D.getHeight());
            int rX = (int) Math.round(r2D.getX());
            int rY = (int) Math.round(r2D.getY());
            int a = (rWidth/2) -rX;
            int b = (rHeight/2) - rY;
            g2d.drawString(num,x-10+a,y+b);
        }
        for(int i = 0; i <60;i++){
            double t= 2*Math.PI * i/60;
            int x = (int) Math.round(centerX + 150*Math.cos(t));
            int y = (int) Math.round(centerY + 150*Math.sin(t));
            g2d.fillRect(x,y+13,3,3);
        }
        g2d.setColor(Color.BLUE);
        g2d.drawLine(centerX,centerY+15,Xsecond,Ysecond);
        g2d.setColor(Color.ORANGE);
        g2d.drawLine(centerX,centerY+15,Xminute,Yminute);
        g2d.setColor(Color.GREEN);
        g2d.drawLine(centerX,centerY+15,Xhour,Yhour);
    }

    /**
     * sets the hour hand x and y positions
     * @param X x position
     * @param Y y postion
     */
    public void setHour(int X,int Y){
        Xhour = X;
        Yhour = Y;
    }
    /**
     * sets the minute hand x and y positions
     * @param X x position
     * @param Y y postion
     */
    public void setMinute(int X, int Y){
        Xminute = X;
        Yminute = Y;
    }
    /**
     * sets the second hand x and y positions
     * @param X x position
     * @param Y y postion
     */
    public void setSecond(int X,int Y){
        Xsecond = X;
        Ysecond = Y;
    }
    public void setTime(String s){
        time = s;
    }
}
