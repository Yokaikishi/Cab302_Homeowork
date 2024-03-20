package obstacles;

import common.Direction;
import common.Location;

/**
 * Represents a laser obstacle on the map that obstructs all locations in a straight line from the given location in the given direction.
 */
public class Laser extends LocatableObstacle {
    private final Direction direction;

    /**
     * Constructs a new Laser object with the given location and direction.
     * @param location The location of the laser
     * @param direction The direction of the laser
     */
    public Laser(Location location, Direction direction) {
        super(location);
        this.direction = direction;
    }

    @Override
    public boolean isLocationObstructed(int x, int y) {
        // Check if the given location is in the path of the laser based on the direction

        switch (direction) {
            case NORTH:
                return x == location.getX() && y < location.getY();
            case SOUTH:
                return x == location.getX() && y > location.getY();
            case EAST:
                return y == location.getY() && x > location.getX();
            case WEST:
                return y == location.getY() && x < location.getX();
            default:
                return false;
        }
    }

    @Override
    public char getSymbol() {
        return ObstacleType.LASER.getSymbol();
    }

    /**
     * Parses a string argument into a Laser object.
     * @param arg The string argument in the format "x,y,direction" representing the location and direction of the laser
     * @return A new Laser object with the given location and direction
     */
    public static Laser parse(String arg) {
        String[] parts = arg.split(",");
        if (parts.length != 3) {
            throw new IllegalArgumentException("Laser must have 3 inputs (x, y, direction)");
        }
        int x = Integer.parseInt(parts[0]);
        int y = Integer.parseInt(parts[1]);
        Direction direction = Direction.parse(parts[2]);
        Location location = new Location(x, y);
        return new Laser(location, direction);
    }
}