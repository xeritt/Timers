import java.io.*;
import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;

public class CalendarTimer {
    public static final int PERIOD = 1000;
    public static String format = "%d h. %d min. %d sec.";
    private int min;
    private int sec;
    private int h;

    public void start(String fileName, String hour, String minute){
        Calendar cal = Calendar.getInstance();
        h = Integer.parseInt(hour);
        min = Integer.parseInt(minute);
        cal.add(Calendar.HOUR, h);
        cal.add(Calendar.MINUTE, min);

        File file = new File(fileName);
        Timer timer = new Timer();
        TimerTask tsk = new TimerTask() {
            @Override
            public void run() {
                Calendar cur = Calendar.getInstance();
                h = cal.get(Calendar.HOUR) - cur.get(Calendar.HOUR);
                min = cal.get(Calendar.MINUTE) - cur.get(Calendar.MINUTE) - 1;
                sec = 59 - Math.abs(cur.get(Calendar.SECOND) - cal.get(Calendar.SECOND));

                String str = String.format(format, h, min , sec);
                writeData(file, str);
                System.out.println(str);
                if (h <= 0 && min <= 0 && sec <= 0){
                    timer.cancel();
                }
            }
        };
        timer.schedule(tsk, 0, PERIOD);
    }


    public static void main(String[] args) {
        CalendarTimer tm = new CalendarTimer();
        tm.start(args[0], args[1], args[2]);
    }

    private void writeData(File file, String data) {
        try(BufferedWriter writer = new BufferedWriter(new FileWriter(file))){
            writer.write(data);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
