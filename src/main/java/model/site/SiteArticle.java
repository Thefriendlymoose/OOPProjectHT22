package model.site;

import model.article.Article;

public class SiteArticle {
    private int siteArticleId;
    private int article;
    private int site;
    private int amount;

    public SiteArticle(int siteArticleId, int article, int site, int amount) {
        this.siteArticleId = siteArticleId;
        this.article = article;
        this.site = site;
        this.amount = amount;
    }

    public int getSiteArticleId() {
        return siteArticleId;
    }

    public void setSiteArticleId(int siteArticleId) {
        this.siteArticleId = siteArticleId;
    }

    public int getArticle() {
        return article;
    }

    public void setArticle(int article) {
        this.article = article;
    }

    public int getSite() {
        return site;
    }

    public void setSite(int site) {
        this.site = site;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
