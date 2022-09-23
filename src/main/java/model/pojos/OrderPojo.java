package model.pojos;

import model.article.Article;
import org.bson.types.ObjectId;

import java.time.LocalDateTime;
import java.util.List;

public class OrderPojo {
    private ObjectId id;

    private List<Article> articles; //inventory ???

    private String articleDescription;

    private LocalDateTime createdDate;

    private String status;

    private String user;

    private String site;

    private LocalDateTime lastEdited;

    public OrderPojo() {

    }

    public OrderPojo(ObjectId id, List<Article> articles, String articleDescription, LocalDateTime createdDate, String status, String site, String user, LocalDateTime lastEdited) {
        this.id = id;
        this.articles = articles;
        this.articleDescription = articleDescription;
        this.createdDate = createdDate;
        this.status = status;
        this.site = site;
        this.user = user;
        this.lastEdited = lastEdited;
    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }


    public String getArticleDescription() {
        return articleDescription;
    }

    public void setArticleDescription(String articleDescription) {
        this.articleDescription = articleDescription;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDateTime getLastEdited() {
        return lastEdited;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public void setLastEdited(LocalDateTime lastEdited) {
        this.lastEdited = lastEdited;
    }

    public String getUser() {
        return user;
    }

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }

    public List<Article> getArticles() {
        return articles;
    }

    public void setArticles(List<Article> articles) {
        this.articles = articles;
    }
}
