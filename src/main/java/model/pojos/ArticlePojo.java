package model.pojos;

import org.bson.types.ObjectId;

import java.time.LocalDateTime;



public class ArticlePojo {
    private ObjectId id;

    private String articleNumber;

    private String articleDescription;

    private LocalDateTime createdDate;

    private String status;

    private LocalDateTime lastEdited;

    public ArticlePojo() {

    }

    public ArticlePojo(ObjectId id, String articleNumber, String articleDescription, LocalDateTime createdDate, String status, LocalDateTime lastEdited) {
        this.id = id;
        this.articleNumber = articleNumber;
        this.articleDescription = articleDescription;
        this.createdDate = createdDate;
        this.status = status;
        this.lastEdited = lastEdited;
    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public String getArticleNumber() {
        return articleNumber;
    }

    public void setArticleNumber(String articleNumber) {
        this.articleNumber = articleNumber;
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

    public void setLastEdited(LocalDateTime lastEdited) {
        this.lastEdited = lastEdited;
    }

    @Override
    public String toString() {
        return "ArticlePojo{" +
                "id=" + id +
                ", articleNumber='" + articleNumber + '\'' +
                ", articleDescription='" + articleDescription + '\'' +
                ", createdDate=" + createdDate +
                ", status='" + status + '\'' +
                ", lastEdited=" + lastEdited +
                '}';
    }
}
