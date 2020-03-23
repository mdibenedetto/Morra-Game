//
public abstract class PlayerBase {
    String name;
    String type; //"ODD"|"EVEN" ;
    int fingers;

    int points = 0;
    int extraPoints = 0;

    public int getScore() {
        return points + extraPoints;
    }

    public PlayerBase(String type, String name) {
        this.type = type;
        this.name = name;
    }

    public PlayerBase() {}
}
