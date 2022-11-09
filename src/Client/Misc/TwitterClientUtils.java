package Client.Misc;

import java.util.ArrayList;
import java.util.Objects;

public class TwitterClientUtils {
    public static void print2DArrayList(ArrayList<ArrayList<String>> list) {
        for (ArrayList<String> row : list) {
            for (String element : row) {
                System.out.println(element);
            }
            System.out.println("==========================");
        }
    }

    public static Throwable findRootCause(Throwable throwable) {
        Objects.requireNonNull(throwable);
        Throwable rootCause = throwable;
        while (rootCause.getCause() != null && rootCause.getCause() != rootCause) {
            rootCause = rootCause.getCause();
        }
        return rootCause;
    }
}
