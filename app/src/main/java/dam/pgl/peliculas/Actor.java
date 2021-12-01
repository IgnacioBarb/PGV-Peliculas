package dam.pgl.peliculas;

public class Actor {
    private int id;
    private String nombre;
    private String character;
    private String profile_path;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCharacter() {
        return character;
    }

    public void setCharacter(String character) {
        this.character = character;
    }

    public String getProfile_path() {
        return profile_path;
    }

    public void setProfile_path(String profile_path) {
        this.profile_path = profile_path;
    }

    public Actor(int id, String nombre, String character, String profile_path) {
        this.id = id;
        this.nombre = nombre;
        this.character = character;
        this.profile_path = profile_path;
    }
}
