package dell.pressball.parser.model;


public class News {

    private String title;
    private String url;
    private String rybrika;
    private String data;
    private String news;
    private String author;
    private String img;

    public News(String title, String url, String rybrika, String data,  String img, String news, String author){
        this.title=title;
        this.url=url;
        this.rybrika=rybrika;
        this.data=data;
        this.img=img;
        this.news=news;
        this.author=author;
    }

    public String getTitle() {
        return title;
    }

    public String getUrl() {
        return url;
    }

    public String getRybrika() {
        return rybrika;
    }

    public String getData() {
        return data;
    }

    public String getImg() {
        return img;
    }

    public String getNews() {
        return news;
    }

    public String getAuthor() {
        return author;
    }

}
