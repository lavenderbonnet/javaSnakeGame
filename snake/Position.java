public class Position {

    public Position()
    {
        X = 0;
        Y = 0;
    }
    public Position(int x, int y) {
        X = x;
        Y = y;
    }
    public int X;
    public int Y;

    public boolean Equals(Position p){
        return this.X == p.X && this.Y == p.Y;
    }

    public static Position Clone(Position p) {
        return new Position(p.X, p.Y);
    }

    public void Copy(Position p) {
        this.X = p.X;
        this.Y = p.Y;
    }
}
