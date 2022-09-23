package model;

import java.util.List;

public class Site {

    private int siteId;
    private String siteName;
    private String siteAddress;
    private final int maxCapacity;
    private List<SiteArticle> siteArticles;
    private List<User> employees;

    public Site(int siteId, String siteName, String siteAddress, int maxCapacity, List<SiteArticle> siteArticles, List<User> employees) {
        this.siteId = siteId;
        this.siteName = siteName;
        this.siteAddress = siteAddress;
        this.maxCapacity = maxCapacity;
        this.siteArticles = siteArticles;
        this.employees = employees;
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
}
