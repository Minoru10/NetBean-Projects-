package Dto;

public class DVD {
    private String ID;
    private String title;
    private String releaseDate;

    public DVD(String ID) {
        this.ID = ID;
    }

    public String getID() {
        return ID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }
}
