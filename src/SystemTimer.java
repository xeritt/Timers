import java.io.*;
import java.util.Timer;
import java.util.TimerTask;

public class SystemTimer {
    public static final int PERIOD = 1000;
    public static String format = "%d h. %d min. %d sec.";

    public void start(String fileName, String hour, String minute){
        int h = Integer.parseInt(hour);
        int m = Integer.parseInt(minute);

        File file = new File(fileName);
        Timer timer = new Timer();

        long curTime = System.currentTimeMillis() / 1000;
        long deltaTime = (h * 60 + m) * 60;
        long futureTime = curTime + deltaTime;

        TimerTask tsk = new TimerTask() {
            @Override
            public void run() {
                long curTime = System.currentTimeMillis() / 1000;
                long deltaTime = futureTime - curTime;
                int hour = (int) (deltaTime / 3600);
                int min = (int) (deltaTime / 60 -  hour * 60);
                int sec = (int) ((deltaTime -  hour * 3600 - min * 60));

                String str = String.format(format, hour, min , sec);
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
        SystemTimer tm = new SystemTimer();
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
