import java.util.ArrayList;
import java.util.HashMap;

class Main {
    public static void main(String[] args) {
        HashMap<String, ArrayList<String>> parsedArgs = parseArgs(args);
        System.out.println(parsedArgs);
    }

    // Creates a HashMap which allows arguments to be read from config
    private static HashMap<String, ArrayList<String>> parseArgs(String[] args) {
        HashMap<String, ArrayList<String>> parsedArgs = new HashMap<>();
        ArrayList<String> argValues = null;

        // Iterates every argument given, separating each by identifying "-"
        // Which puts the individual args into an arraylist to be used later
        for (String arg : args) {
            if (arg.startsWith("-")) {
                argValues = new ArrayList<>();
                parsedArgs.put(arg, argValues);
                continue;
            }

            // Ensures no args given are null/empty
            if (argValues != null) {
                argValues.add(arg);
            }
        }
        return parsedArgs;
    }
}