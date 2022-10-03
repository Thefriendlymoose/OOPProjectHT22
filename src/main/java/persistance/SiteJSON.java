package persistance;

import model.User;

import java.util.List;

public class SiteJSON {

    private int siteId;
    private String siteName;
    private String siteAddress;
    private int maxCapacity;

    /* Tom konstruktor eftersom den ändå inte körs */
    public SiteJSON(){

    }
    public int getSiteId() {
        return siteId;
    }

    public void setSiteId(int siteId) {
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



}
