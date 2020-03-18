public abstract class PlayerBase {
    String name;
    String type; //"ODD"|"EVEN" ;
    int score = 0;

    int fingers;

    public PlayerBase(String type, String name) {
        this.type = type;
        this.name = name;
    }

    public PlayerBase() {}
}
