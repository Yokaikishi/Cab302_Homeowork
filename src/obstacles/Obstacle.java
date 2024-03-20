package obstacles;

public interface Obstacle {

    // Returns the symbol that represents the obstacle on the map
    char getSymbol();

    // Different obstacles will obstruct different locations
    // returns true if the given location is obstructed by the obstacle, and false otherwise
    boolean isLocationObstructed(int x, int y);


}
