package Client.Misc;

import java.util.ArrayList;

public class TwitterClientUtils {
    public static void print2DArrayList(ArrayList<ArrayList<String>> list) {
        for (ArrayList<String> row : list) {
            for (String element : row) {
                System.out.println(element);
            }
            System.out.println("==========================");
        }
    }
}
