package application.model.app;

import java.util.List;

public class App {
    private String apkMd5;
    private long apkPublishTime;
    private String apkUrl;
    private long appDownCount;
    private int appId;
    private String appName;
    private int authorId;
    private String authorName;
    private double averageRating;
    private int categoryId;
    private String categoryName;
    private String description;
    private String editorIntro;
    private double fileSize;
    private String iconUrl;
    private List<String> images;
    private String newFeature;
    private String pkgName;
    private String snapshotsUrl;
    private String versionName;

    public App(){}

    public App(String apkMd5, long apkPublishTime, String apkUrl, long appDownCount, int appId, String appName, int authorId, String authorName, double averageRating, int categoryId, String categoryName, String description, String editorIntro, double fileSize, String iconUrl, List<String> images, String newFeature, String pkgName, String snapshotsUrl, String versionName) {
        this.apkMd5 = apkMd5;
        this.apkPublishTime = apkPublishTime;
        this.apkUrl = apkUrl;
        this.appDownCount = appDownCount;
        this.appId = appId;
        this.appName = appName;
        this.authorId = authorId;
        this.authorName = authorName;
        this.averageRating = averageRating;
        this.categoryId = categoryId;
        this.categoryName = categoryName;
        this.description = description;
        this.editorIntro = editorIntro;
        this.fileSize = fileSize;
        this.iconUrl = iconUrl;
        this.images = images;
        this.newFeature = newFeature;
        this.pkgName = pkgName;
        this.snapshotsUrl = snapshotsUrl;
        this.versionName = versionName;
    }

    public String getApkMd5() {
        return apkMd5;
    }

    public void setApkMd5(String apkMd5) {
        this.apkMd5 = apkMd5;
    }

    public long getApkPublishTime() {
        return apkPublishTime;
    }

    public void setApkPublishTime(long apkPublishTime) {
        this.apkPublishTime = apkPublishTime;
    }

    public String getApkUrl() {
        return apkUrl;
    }

    public void setApkUrl(String apkUrl) {
        this.apkUrl = apkUrl;
    }

    public long getAppDownCount() {
        return appDownCount;
    }

    public void setAppDownCount(long appDownCount) {
        this.appDownCount = appDownCount;
    }

    public int getAppId() {
        return appId;
    }

    public void setAppId(int appId) {
        this.appId = appId;
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public int getAuthorId() {
        return authorId;
    }

    public void setAuthorId(int authorId) {
        this.authorId = authorId;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public double getAverageRating() {
        return averageRating;
    }

    public void setAverageRating(double averageRating) {
        this.averageRating = averageRating;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getEditorIntro() {
        return editorIntro;
    }

    public void setEditorIntro(String editorIntro) {
        this.editorIntro = editorIntro;
    }

    public double getFileSize() {
        return fileSize;
    }

    public void setFileSize(double fileSize) {
        this.fileSize = fileSize;
    }

    public String getIconUrl() {
        return iconUrl;
    }

    public void setIconUrl(String iconUrl) {
        this.iconUrl = iconUrl;
    }

    public List<String> getImages() {
        return images;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }

    public String getNewFeature() {
        return newFeature;
    }

    public void setNewFeature(String newFeature) {
        this.newFeature = newFeature;
    }

    public String getPkgName() {
        return pkgName;
    }

    public void setPkgName(String pkgName) {
        this.pkgName = pkgName;
    }

    public String getSnapshotsUrl() {
        return snapshotsUrl;
    }

    public void setSnapshotsUrl(String snapshotsUrl) {
        this.snapshotsUrl = snapshotsUrl;
    }

    public String getVersionName() {
        return versionName;
    }

    public void setVersionName(String versionName) {
        this.versionName = versionName;
    }


    @Override
    public String toString() {
        return "App{" +
                "apkMd5='" + apkMd5 + '\'' +
                ", apkPublishTime=" + apkPublishTime +
                ", apkUrl='" + apkUrl + '\'' +
                ", appDownCount=" + appDownCount +
                ", appId=" + appId +
                ", appName='" + appName + '\'' +
                ", authorId=" + authorId +
                ", authorName='" + authorName + '\'' +
                ", averageRating=" + averageRating +
                ", categoryId=" + categoryId +
                ", categoryName='" + categoryName + '\'' +
                ", description='" + description + '\'' +
                ", editorIntro='" + editorIntro + '\'' +
                ", fileSize=" + fileSize +
                ", iconUrl='" + iconUrl + '\'' +
                ", images=" + images +
                ", newFeature='" + newFeature + '\'' +
                ", pkgName='" + pkgName + '\'' +
                ", snapshotsUrl='" + snapshotsUrl + '\'' +
                ", versionName='" + versionName + '\'' +
                '}';
    }
}