package model;

import model.pojos.ArticlePojo;

public class Article {

//    private ArticlePojo pojo;
    private int articleId;
    private String articleName;
    private String description;
    private Category category;

    public Article(int articleId, String articleName, String description, Category category) {

        this.articleId = articleId;
        this.articleName = articleName;
        this.description = description;
        this.category = category;
    }

    public int getArticleId() {
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

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}
