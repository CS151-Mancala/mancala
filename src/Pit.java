public class Pit extends Mancala {
    private final int id;

    public Pit(String player, int id, int numStones) {
        super(player, numStones);
        this.id = id;
    }

    public int getID() { return id; }

    public String toString() {
        return getPlayer() + getID() + ": " + getNumStones();
    }
}
