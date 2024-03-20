package obstacles;

import common.Location;

    public class Wall extends LocatableObstacle {
        private final Location end;


        public Wall(Location start, Location end) {
            super(start);
            this.end = end;
            if (!isRectangular()) {
                throw new IllegalArgumentException("Wall must be vertical or horizontal");
            }
        }
        /**
         * Checks if the wall forms a rectangular area.
         * @return True if the wall forms a rectangular area, false otherwise
         */
        private boolean isRectangular() {
            return location.getX() != end.getX() && location.getY() != end.getY();
        }

        @Override
        public boolean isLocationObstructed(int x, int y) {
            // Check if the location lies within the rectangular
            // area defined by start and end locations
            int minX = Math.min(location.getX(), end.getX());
            int maxX = Math.max(location.getX(), end.getX());
            int minY = Math.min(location.getY(), end.getY());
            int maxY = Math.max(location.getY(), end.getY());
            return x >= minX && x <= maxX && y >= minY && y <= maxY;
        }

        @Override
        public char getSymbol() {
            return ObstacleType.WALL.getSymbol();
        }

        public static obstacles.Wall parse(String arg) {
            String[] parts = arg.split(",");
            if (parts.length != 4) {
                throw new IllegalArgumentException("Wall must have 4 coordinates: startX,startY,endX,endY");
            }
            int startX = Integer.parseInt(parts[0]);
            int startY = Integer.parseInt(parts[1]);
            int endX = Integer.parseInt(parts[2]);
            int endY = Integer.parseInt(parts[3]);
            Location start = new Location(startX, startY);
            Location end = new Location(endX, endY);
            return new obstacles.Wall(start, end);
        }
}
