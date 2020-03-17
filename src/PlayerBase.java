public abstract class PlayerBase {
    String name;
    int id;
    int fingers;
    String type; //"ODD"|"EVEN" ;

    public PlayerBase(String type, String name) {
        this.type = type;
        this.name = name;
    }

    public PlayerBase() {}
}
