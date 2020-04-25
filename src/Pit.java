public class Pit {
    private final String player; // can be "A" or "B"
    private int numStones;

    public Pit(String player, int numStones) {
        this.player = player;
        this.numStones = numStones;
    }

    public String getPlayer() {
        return player;
    }

    public int getNumStones() {
        return numStones;
    }

    public void setNumStones(int numStones) {
        this.numStones = numStones;
    }
}
