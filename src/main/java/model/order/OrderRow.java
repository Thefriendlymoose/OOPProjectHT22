package model.order;

import model.article.Article;

/**
 * The part of Order which has the Article and amount of that Article.
 */

public class OrderRow {
    private Article article;
    private int amount;

    public OrderRow(Article article, int amount) {
        this.article = article;
        this.amount = amount;
    }

    public Article getArticle() {
        return article;
    }

    public void setArticle(Article article) {
        this.article = article;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public void addAmount(int amount) {
        this.amount += amount;
    }

    public void reduceAmount(int amount) {
        this.amount -= amount;
    }
}
