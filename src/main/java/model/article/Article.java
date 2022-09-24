package model.article;

public class Article {

    private final int articleId;
    private String articleName;
    private String description;
    private ArticleCategory category;
    private ArticleStatus status;

    public Article(int articleId, String articleName, String description, ArticleCategory category, ArticleStatus status) {
        this.articleId = articleId;
        this.articleName = articleName;
        this.description = description;
        this.category = category;
        this.status = status;
    }

    public int getArticleId() {
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
