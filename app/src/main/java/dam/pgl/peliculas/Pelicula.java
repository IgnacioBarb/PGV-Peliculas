package dam.pgl.peliculas;

public class Pelicula {
    private int id;
    private String title;
    private String original_title;
    private String backdrop_path;
    private String poster_path;
    private String overview;
    private double popularity;
    private String release_date;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBackdrop_path() {
        return backdrop_path;
    }

    public void setBackdrop_path(String backdrop_path) {
        this.backdrop_path = backdrop_path;
    }

    public String getPoster_path() {
        return poster_path;
    }

    public void setPoster_path(String poster_path) {
        this.poster_path = poster_path;
    }

    public String getOriginal_title() {
        return original_title;
    }

    public void setOriginal_title(String original_title) {
        this.original_title = original_title;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public double getPopularity() {
        return popularity;
    }

    public void setPopularity(float popularity) {
        this.popularity = popularity;
    }

    public String getRelease_date() {
        return release_date;
    }

    public void setRelease_date(String release_date) {
        this.release_date = release_date;
    }

    public Pelicula(int id, String title, String backdrop_path, String poster_path, String original_title, String overview, double popularity, String release_date) {
        this.id = id;
        this.title = title;
        this.backdrop_path = backdrop_path;
        this.poster_path = poster_path;
        this.original_title = original_title;
        this.overview = overview;
        this.popularity = popularity;
        this.release_date = release_date;
    }
}
