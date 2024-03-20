import java.util.ArrayList;
import java.util.HashMap;

import common.Location;
import common.Map;
import obstacles.*;

class Main {
    public static void main(String[] args) {
        // Parse the command line arguments into obstacles
        // and create a map with those obstacles
        HashMap<String, ArrayList<String>> parsedArgs = parseArgs(args);
        ArrayList<Obstacle> obstacles = parseObstacles(parsedArgs);
        Map map = new Map(obstacles);

        // Parse the start and target locations
        String startArg = stripParentheses(parsedArgs.get("-start").get(0));
        String targetArg = stripParentheses(parsedArgs.get("-target").get(0));
        Location start = Location.parse(startArg);
        Location target = Location.parse(targetArg);

        // Show the map
        System.out.println(map.getSolvedMap(start, target));
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
        for (ObstacleType type : ObstacleType.values()) {
            String key = "-" + type.getArgumentName();
            ArrayList<String> args = parsedArgs.get(key);
            if (args == null) {
                continue;
            }
            for (String arg : args) {
                // Remove the parentheses from the argument
                String cleanedArg = stripParentheses(arg);
                Obstacle obstacle = switch (type) {
                    case GUARD -> Guard.parse(cleanedArg);
                    case FENCE -> Fence.parse(cleanedArg);
                    case SENSOR -> Sensor.parse(cleanedArg);
                    case CAMERA -> Camera.parse(cleanedArg);
                    case WALL -> Wall.parse(cleanedArg);
                    case LASER -> Laser.parse(cleanedArg);
                    case MINEFIELD -> Minefield.parse(cleanedArg);
                };
                obstacles.add(obstacle);
            }
        }
        return obstacles;
    }
}