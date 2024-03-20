import java.util.ArrayList;
import java.util.HashMap;
import obstacles.*;

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


    /**
     * Strips the parentheses from the argument
     * @param arg The argument to strip
     * @return The argument without parentheses
     */
    private static String stripParentheses(String arg) {
        return arg.substring(1, arg.length() - 1);
    }

    /**
     * Parses the obstacles from the command line arguments
     * @param parsedArgs The parsed arguments
     */
    public static ArrayList<Obstacle> parseObstacles(HashMap<String, ArrayList<String>> parsedArgs) {
        ArrayList<Obstacle> obstacles = new ArrayList<>();
        ObstacleType type = ObstacleType.GUARD;
        String key = "-" + type.getArgumentName();
        ArrayList<String> args = parsedArgs.get(key);
        if (args == null) {
            return obstacles;
        }
        for (String arg : args) {
            // Remove the parentheses from the argument
            String cleanedArg = stripParentheses(arg);
            Obstacle obstacle = Guard.parse(cleanedArg);
            obstacles.add(obstacle);
        }
        return obstacles;
    }
}