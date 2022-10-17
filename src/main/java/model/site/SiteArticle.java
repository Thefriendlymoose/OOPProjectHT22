package model.site;

import model.article.Article;

/**
 * Class represents inventory of an article in a site.
 */
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

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
