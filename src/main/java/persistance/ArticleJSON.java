package persistance;

import model.article.ArticleCategory;
import model.article.ArticleStatus;

public class ArticleJSON {

    private long articleId;
    private String articleName;
    private String description;
    private ArticleCategory category;
    private ArticleStatus status;
    private float cost;
    private float sellPrice;

    public ArticleJSON(){
    }

    public long getArticleId() {
        return articleId;
    }

    public void setArticleId(int articleId) {
        this.articleId = articleId;
    }

    public String getArticleName() {
        return articleName;
    }

    public void setArticleName(String articleName) {
        this.articleName = articleName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ArticleCategory getCategory() {
        return category;
    }

    public void setCategory(ArticleCategory category) {
        this.category = category;
    }

    public ArticleStatus getStatus() {
        return status;
    }

    public void setStatus(ArticleStatus status) {
        this.status = status;
    }

    public float getCost() {
        return cost;
    }

    public void setCost(float cost) {
        this.cost = cost;
    }

    public float getSellPrice() {
        return sellPrice;
    }

    public void setSellPrice(float sellPrice) {
        this.sellPrice = sellPrice;
    }
}
