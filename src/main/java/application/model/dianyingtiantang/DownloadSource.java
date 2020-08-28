package application.model.dianyingtiantang;

import java.util.List;

public class DownloadSource {
    private String description;
    private List<String> fileNames;
    private List<String> downloadUrls;

    public DownloadSource() {
    }

    public DownloadSource(String description, List<String> fileNames, List<String> downloadUrls) {
        this.description = description;
        this.fileNames = fileNames;
        this.downloadUrls = downloadUrls;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<String> getFileNames() {
        return fileNames;
    }

    public void setFileNames(List<String> fileNames) {
        this.fileNames = fileNames;
    }

    public List<String> getDownloadUrls() {
        return downloadUrls;
    }

    public void setDownloadUrls(List<String> downloadUrls) {
        this.downloadUrls = downloadUrls;
    }
}
