package model.article;

import model.user.User;

import java.time.LocalDateTime;


/**
 * Article Class
 *
 * @author David Al Amiri
 */
public class Article {

    private final long articleId;
    private String articleName;
    private String description;
    private ArticleCategory category;
    private ArticleStatus status;
    private float cost;
    private float sellPrice;
    private User createdBy;
    private LocalDateTime createdOn;
    private LocalDateTime lastEdited;


    /**
     * Article Constructor
     * @param articleId
     * @param articleName
     * @param description
     * @param category
     * @param status
     * @param cost
     * @param sellPrice
     * @param createdBy
     * @param createdOn
     * @param lastEdited
     */
    public Article(long articleId, String articleName, String description, ArticleCategory category, ArticleStatus status, float cost, float sellPrice, User createdBy, LocalDateTime createdOn, LocalDateTime lastEdited) {
        this.articleId = articleId;
        this.articleName = articleName;
        this.description = description;
        this.category = category;
        this.status = status;
        this.cost = cost;
        this.sellPrice = sellPrice;
        this.createdBy = createdBy;
        this.createdOn = createdOn;
        this.lastEdited = lastEdited;
    }

    /**
     *
     * @return returns the article ID
     */
    public long getArticleId() {
        return articleId;
    }

    /**
     *
     * @return returns the article name
     */
    public String getArticleName() {
        return articleName;
    }

    /**
     *
     * @return returns the article description
     */
    public String getDescription() {
        return description;
    }

    /**
     *
     * @return returns the article category
     */
    public ArticleCategory getCategory() {
        return category;
    }

    /**
     *
     * @return returns the article status
     */
    public ArticleStatus getStatus() {
        return status;
    }

    /**
     *
     * @return returns the cost of the article
     */
    public float getCost() {
        return cost;
    }

    /**
     *
     * @return returns the sell price of the article
     */
    public float getSellPrice() {
        return sellPrice;
    }

    /**
     *
     * @param articleName sets the article name
     */
    public void setArticleName(String articleName) {
        this.articleName = articleName;
    }

    /**
     *
     * @param description sets the article description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     *
     * @param category sets the article category
     */
    public void setCategory(ArticleCategory category) {
        this.category = category;
    }

    /**
     *
     * @param status sets the article status
     */
    public void setStatus(ArticleStatus status) {
        this.status = status;
    }

    /**
     *
     * @param cost sets the article cost
     */
    public void setCost(float cost) {
        this.cost = cost;
    }

    /**
     *
     * @param sellPrice sets the article sellprice
     */
    public void setSellPrice(float sellPrice) {
        this.sellPrice = sellPrice;
    }

    public User getCreatedBy() {
        return createdBy;
    }

    public LocalDateTime getCreatedOn() {
        return createdOn;
    }

    public LocalDateTime getLastEdited() {
        return lastEdited;
    }

    public void setLastEdited(LocalDateTime lastEdited) {
        this.lastEdited = lastEdited;
    }


}
