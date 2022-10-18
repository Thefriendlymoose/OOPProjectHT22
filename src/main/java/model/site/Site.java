package model.site;

import model.user.User;
import model.article.Article;
import model.observer.Observable;
import model.observer.Observer;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a site/warehouse
 */
public class Site implements Observable {

    private long siteId;
    private String siteName;
    private String siteAddress;
    private final int maxCapacity;
    private List<SiteArticle> siteArticles;
    private List<User> employees;

    private List<Observer> observers;


    /**
     *
     * @param siteId Id of site
     * @param siteName name of site
     * @param siteAddress address of site
     * @param maxCapacity capacity of site
     * @param siteArticles a list of articles in stock in the site
     * @param employees users who work in the site
     */
    public Site(long siteId, String siteName, String siteAddress, int maxCapacity, List<SiteArticle> siteArticles, List<User> employees) {
        this.siteId = siteId;
        this.siteName = siteName;
        this.siteAddress = siteAddress;
        this.maxCapacity = maxCapacity;
        this.siteArticles = siteArticles;
        this.employees = employees;

        observers = new ArrayList<>();
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


    /**
     *
     * @return returns the total amount of items available in the site
     */
    public int getTotalAmountItems(){
        int sum = 0;
        for(SiteArticle sa : siteArticles){
            sum += sa.getAmount();
        }

        return sum;
    }

    /**
     * Checks if adding a new article in the site if the capacity is reached
     * @param sa takes a site article before edit
     * @param added takes the amount we are adding
     * @return returns true or false depending if we are above or below/on capacity
     */
    public boolean isOverCapacity(SiteArticle sa, int added){
        return (getTotalAmountItems() + added - sa.getAmount()) > maxCapacity;
    }

    /**
     * Checks if we add amount to total amount if we are above maxcapacity
     * @param amount amount of newly added items
     * @return returns true or false depending if we are above or below/on  maxCapacity
     */
    public boolean checkIfOverCapacity(int amount){
        return getTotalAmountItems() + amount > maxCapacity;
    }

    public void addEmployee(User user){
        employees.add(user);
        notifyObservers();
    }

    public void removeEmployee(User user){
        employees.remove(user);
        notifyObservers();
    }

    public void addSiteArticle(SiteArticle sa){
        siteArticles.add(sa);
        notifyObservers();
    }

    public void removeSiteArticles(SiteArticle sa){
        siteArticles.remove(sa);
        notifyObservers();
    }

    public void editSiteArticle(SiteArticle sa, int amount){
        sa.setAmount(amount);
        notifyObservers();
    }

    /**
     * Checks whether a user is already assigned to the site
     * @param user
     * @return returns true if user is already assigned to the site and false if they are not
     */
    public boolean checkEmployeeInSite(User user){
        return employees.contains(user);
    }


    /**
     * Checks if an article already exists in the site.
     * @param article
     * @return Returns true if we already have the article in the site and false if it is not.
     */
    public boolean checkArticleInSite(Article article){
        for (SiteArticle sa : siteArticles){
            if (sa.getArticle().equals(article)){
                return true;
            }
        }
        return false;
    }

    @Override
    public void registerObserver(Observer o) {
        observers.add(o);
    }

    @Override
    public void unregisterObserver(Observer o) {
        observers.remove(o);
    }

    @Override
    public void unregisterAll() {
        observers = new ArrayList<>();
    }

    @Override
    public void notifyObservers() {
        for (Observer o : observers){
            o.update();
        }
    }
}
