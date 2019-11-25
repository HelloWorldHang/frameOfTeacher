package wang.doug.frame.model;

public class ResWithBLOBs extends Res {
    private String resPreviewUrl;

    private String resDownloadUrl;

    public String getResPreviewUrl() {
        return resPreviewUrl;
    }

    public void setResPreviewUrl(String resPreviewUrl) {
        this.resPreviewUrl = resPreviewUrl == null ? null : resPreviewUrl.trim();
    }

    public String getResDownloadUrl() {
        return resDownloadUrl;
    }

    public void setResDownloadUrl(String resDownloadUrl) {
        this.resDownloadUrl = resDownloadUrl == null ? null : resDownloadUrl.trim();
    }
}