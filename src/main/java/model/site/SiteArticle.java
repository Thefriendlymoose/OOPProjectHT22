package model.site;

import model.article.Article;

public class SiteArticle {
    private Article article;
    private int amount;

    public SiteArticle(Article article, int amount) {
        this.article = article;
        this.amount = amount;
    }


    public Article getArticle() {
        return article;
    }


    public int getAmount() {
        return amount;
    }
}
