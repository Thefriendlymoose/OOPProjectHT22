package persistance.pojos;

import model.User;

import java.util.List;

public class SiteJSON {

    private long siteId;
    private String siteName;
    private String siteAddress;
    private int maxCapacity;
    private List<SiteArticleJSON> siteArticles;
    private List<Long> siteUsers;


    /* Tom konstruktor eftersom den ändå inte körs */
    public SiteJSON(){

    }
    public long getSiteId() {
        return siteId;
    }

    public void setSiteId(long siteId) {
        this.siteId = siteId;
    }

    public String getSiteName() {
        return siteName;
    }

    public void setSiteName(String siteName) {
        this.siteName = siteName;
    }

    public String getSiteAddress() {
        return siteAddress;
    }

    public void setSiteAddress(String siteAddress) {
        this.siteAddress = siteAddress;
    }

    public void setMaxCapacity(int maxCapacity) {
        this.maxCapacity = maxCapacity;
    }

    public int getMaxCapacity() {
        return maxCapacity;
    }

    public List<SiteArticleJSON> getSiteArticles() {
        return siteArticles;
    }

    public void setSiteArticles(List<SiteArticleJSON> siteArticles) {
        this.siteArticles = siteArticles;
    }

    public List<Long> getSiteUsers() {
        return siteUsers;
    }

    public void setSiteUsers(List<Long> siteUsers) {
        this.siteUsers = siteUsers;
    }
}
