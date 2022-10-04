package model.order;

import model.article.Article;

public class OrderRow {
    private Article article;
    private int amount;

    public OrderRow(Article article, int amount) {
        this.article = article;
        this.amount = amount;
    }
}
