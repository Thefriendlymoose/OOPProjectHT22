package model.article;

import model.User;

import java.time.LocalDateTime;
import java.util.Calendar;

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

    public long getArticleId() {
        return articleId;
    }

    public String getArticleName() {
        return articleName;
    }

    public String getDescription() {
        return description;
    }

    public ArticleCategory getCategory() {
        return category;
    }

    public ArticleStatus getStatus() {
        return status;
    }

    public float getCost() {
        return cost;
    }

    public float getSellPrice() {
        return sellPrice;
    }

    public void setArticleName(String articleName) {
        this.articleName = articleName;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setCategory(ArticleCategory category) {
        this.category = category;
    }

    public void setStatus(ArticleStatus status) {
        this.status = status;
    }

    public void setCost(float cost) {
        this.cost = cost;
    }

    public void setSellPrice(float sellPrice) {
        this.sellPrice = sellPrice;
    }

    @Override
    public String toString() {
        return "Article{" +
                "articleId=" + articleId +
                ", articleName='" + articleName + '\'' +
                ", description='" + description + '\'' +
                ", category=" + category +
                ", status=" + status +
                ", cost=" + cost +
                ", sellPrice=" + sellPrice +
                ", createdBy=" + createdBy +
                ", createdOn=" + createdOn +
                ", lastEdited=" + lastEdited +
                '}';
    }
}
