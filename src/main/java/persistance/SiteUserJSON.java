package persistance;

public class SiteUserJSON {
    private int siteUserId;
    private int userId;
    private int siteId;

    public SiteUserJSON(){

    }

    public int getSiteUserId() {
        return siteUserId;
    }

    public void setSiteUserId(int siteUserId) {
        this.siteUserId = siteUserId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getSiteId() {
        return siteId;
    }

    public void setSiteId(int siteId) {
        this.siteId = siteId;
    }
}
