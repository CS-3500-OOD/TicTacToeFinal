package Model;

    public class Coord {

        private final int x;
        private  final int y;

        public Coord(int x, int y){
            this.x = x;
            this.y = y;
        }

        int x(){
            return x;
        }

        int y(){
            return y;
        }
        @Override
        public boolean equals(Object other) {
            if (!(other instanceof Coord)) {
                return false;
            } else {
                return this.x == ((Coord) other).x() && this.y == ((Coord) other).y();
            }
        }

        @Override
        public int hashCode() {
            return Integer.hashCode(this.x) + Integer.hashCode(this.y);
        }


        @Override
        public String toString() {
            return "(" + this.x + ", " + this.y + ")";
        }


    }

