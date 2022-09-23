package model.article;

public class Article {

    private final int articleId;
    private String articleName;
    private String description;
    private ArticleCategory category;
    private boolean status;

    public Article(int articleId, String articleName, String description, ArticleCategory category, boolean status) {
        this.articleId = articleId;
        this.articleName = articleName;
        this.description = description;
        this.category = category;
        this.status = status;
    }
}
