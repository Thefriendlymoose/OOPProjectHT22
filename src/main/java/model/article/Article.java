package model.article;

public class Article {

    private final long articleId;
    private String articleName;
    private String description;
    private ArticleCategory category;
    private ArticleStatus status;
    private float cost;
    private float sellPrice;

    public Article(long articleId, String articleName, String description, ArticleCategory category, ArticleStatus status, float cost, float sellPrice) {
        this.articleId = articleId;
        this.articleName = articleName;
        this.description = description;
        this.category = category;
        this.status = status;
        this.cost = cost;
        this.sellPrice = sellPrice;
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

    @Override
    public String toString() {
        return "Article{" +
                "articleId=" + articleId +
                ", articleName='" + articleName + '\'' +
                ", description='" + description + '\'' +
                ", category=" + category +
                ", status=" + status +
                '}';
    }
}
