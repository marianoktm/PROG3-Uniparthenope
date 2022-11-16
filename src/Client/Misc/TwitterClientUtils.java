package Client.Misc;

import java.util.ArrayList;
import java.util.Objects;

/**
 * Utils class for Twitter Client. It contains useful static methods.
 */
public class TwitterClientUtils {
    /**
     * Prints a 2D array of strings.
     * @param list the 2D array of strings.
     */
    public static void print2DArrayList(ArrayList<ArrayList<String>> list) {
        for (ArrayList<String> row : list) {
            for (String element : row) {
                System.out.println(element);
            }
            System.out.println("==========================");
        }
    }

    /**
     * Finds the root cause of a Throwable.
     * @param throwable the throwable that was thrown.
     * @return the root cause of the throwable
     */
    public static Throwable findRootCause(Throwable throwable) {
        Objects.requireNonNull(throwable);
        Throwable rootCause = throwable;
        while (rootCause.getCause() != null && rootCause.getCause() != rootCause) {
            rootCause = rootCause.getCause();
        }
        return rootCause;
    }
}
