package model.site;

import model.User;

import java.util.List;

public class Site {

    private long siteId;
    private String siteName;
    private String siteAddress;
    private final int maxCapacity;
    private List<SiteArticle> siteArticles;
    private List<User> employees;

    public Site(long siteId, String siteName, String siteAddress, int maxCapacity, List<SiteArticle> siteArticles, List<User> employees) {
        this.siteId = siteId;
        this.siteName = siteName;
        this.siteAddress = siteAddress;
        this.maxCapacity = maxCapacity;
        this.siteArticles = siteArticles;
        this.employees = employees;
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

    public int getMaxCapacity() {
        return maxCapacity;
    }

    public List<SiteArticle> getSiteArticles() {
        return siteArticles;
    }

    public void setSiteArticles(List<SiteArticle> siteArticles) {
        this.siteArticles = siteArticles;
    }

    public List<User> getEmployees() {
        return employees;
    }

    public void setEmployees(List<User> employees) {
        this.employees = employees;
    }

    public int getTotalAmountItems(){
        int sum = 0;
        for(SiteArticle sa : siteArticles){
            sum += sa.getAmount();
        }

        return sum;
    }

    @Override
    public String toString() {
        return "Site{" +
                "siteId=" + siteId +
                ", siteName='" + siteName + '\'' +
                ", siteAddress='" + siteAddress + '\'' +
                ", maxCapacity=" + maxCapacity +
                ", siteArticles=" + siteArticles +
                ", employees=" + employees +
                '}';
    }
}
