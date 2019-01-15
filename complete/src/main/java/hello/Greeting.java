package hello;

public class Greeting {

    private final long id;
    private final String content;
    private final Integer year;

    public Greeting(long id, String content, Integer year) {
        this.id = id;
        this.content = content;
        this.year = year;
    }

    public long getId() {
        return id;
    }

    public String getContent() {
        return content;
    }


    public Integer getYear() {
        return year;
    }
}
