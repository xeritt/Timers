import java.io.*;
public class SchoolTimer {

    public static void main(String[] args) {
        int h = Integer.parseInt(args[1]);
        int m = Integer.parseInt(args[2]);
        File file = new File(args[0]);
        do{
            for (int min = m; min >= 0; min--) {
                for (int sec = 59; sec >= 0; sec--) {
                    String str = h + " h. " + min + " min. " + sec + " sec.";
                    writeData(file, str);
                    System.out.println(str);
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
            m = 59;
        } while (h-- > 0);

    }

    private static void writeData(File file, String data) {
        try(BufferedWriter writer = new BufferedWriter(new FileWriter(file))){
            writer.write(data);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
