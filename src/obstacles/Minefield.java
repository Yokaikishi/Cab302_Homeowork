package obstacles;

import common.Location;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a minefield obstacle on the map that obstructs all locations in the given mine locations.
 */
public class Minefield implements Obstacle {
    private final List<Location> mineLocations;

    /**
     * Constructs a new Minefield object with the given mine locations.
     * @param mineLocations The locations of the mines
     */
    public Minefield(List<Location> mineLocations) {
        this.mineLocations = mineLocations;
    }

    @Override
    public boolean isLocationObstructed(int x, int y) {
        // Check if the given location is one of the mine locations
        return mineLocations.contains(new Location(x, y));
    }

    @Override
    public char getSymbol() {
        return ObstacleType.MINEFIELD.getSymbol();
    }

    /**
     * Parses a string argument into a Minefield object.
     * @param arg The string argument in the format "x1,y1,x2,y2,x3,y3,..." representing the locations of the mines
     * @return A new Minefield object with the given mine locations
     */
    public static Minefield parse(String arg) {
        String[] parts = arg.split(",");
        if (parts.length % 2 != 0) {
            throw new IllegalArgumentException("Minefield must have an even number of inputs representing x and y coordinates");
        }

        List<Location> mineLocations = new ArrayList<>();
        for (int i = 0; i < parts.length; i += 2) {
            int x = Integer.parseInt(parts[i]);
            int y = Integer.parseInt(parts[i + 1]);
            mineLocations.add(new Location(x, y));
        }

        return new Minefield(mineLocations);
    }
}