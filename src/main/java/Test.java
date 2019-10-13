import java.io.*;
import java.util.*;

public class Test {
    public static void main(String[] args) {

        ArrayList<String[]> data = new ArrayList<>();
        BufferedReader reader = null;
        BufferedWriter writer = null;

        try {
            reader = new BufferedReader(new FileReader("data.txt"));
            String currentLine = reader.readLine();

            while (currentLine != null) {
                data.add(currentLine.split("\\s+"));
                currentLine = reader.readLine();
            }

            data.sort((o1, o2) -> {
                int res = 1;
                if (o1.length > o2.length) {
                    String[] tmp = o2;
                    o2 = o1;
                    o1 = tmp;
                    res = -1;
                }

                for (var i = 0; i < o1.length; i++) {
                    if (tryParseInt(o1[i])) {
                        if(tryParseInt(o2[i])){
                            int result = Integer.compare(Integer.parseInt(o1[i]), Integer.parseInt(o2[i]));
                            if(result != 0) {
                                return res*result;
                            }
                        }
                        else return -1*res;
                    }
                    else {
                        if(tryParseInt(o2[i])) {
                            return res;
                        } else {
                            int result = o1[i].compareTo(o2[i]);
                            if(result != 0) {
                                return res*result;
                            }
                        }
                    }
                }
                return -1*res;
            });

            writer = new BufferedWriter(new FileWriter("output.txt"));

            for (String[] line : data) {
                for (var lin : line) {
                    writer.write(lin + "\t");
                }
                writer.newLine();
            }
            assert reader != null;
            reader.close();
            assert writer != null;
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static boolean tryParseInt(String value) {
        try {
            Integer.parseInt(value);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}

