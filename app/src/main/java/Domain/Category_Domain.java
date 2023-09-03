package Domain;

public class Category_Domain {
    private String title;
    private String pic;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public Category_Domain(String title, String pic) {
        this.title = title;
        this.pic = pic;

    }
}
