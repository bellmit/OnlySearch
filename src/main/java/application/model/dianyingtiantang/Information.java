package application.model.dianyingtiantang;

public class Information {
    private String title;
    private String downloadPageUrl;
    private String date;
    private String description;
    private int pageCount;

    public Information() {
    }

    public Information(String title, String downloadPageUrl, String date, String description, int pageCount) {
        this.title = title;
        this.downloadPageUrl = downloadPageUrl;
        this.date = date;
        this.description = description;
        this.pageCount = pageCount;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDownloadPageUrl() {
        return downloadPageUrl;
    }

    public void setDownloadPageUrl(String downloadPageUrl) {
        this.downloadPageUrl = downloadPageUrl;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPageCount() {
        return pageCount;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }
}
