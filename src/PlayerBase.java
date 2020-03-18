public abstract class PlayerBase {
    String name;
    int id;
    int fingers;
    String type; //"ODD"|"EVEN" ;
    int score = 0;

    public PlayerBase(String type, String name) {
        this.type = type;
        this.name = name;
    }

    public PlayerBase() {}
}
