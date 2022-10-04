package model.site;

import model.article.Article;

public class SiteArticle {
    private int siteArticleId;
    private Article article;
    private Site site;
    private int amount;

    public SiteArticle(int siteArticleId, Article article, Site site, int amount) {
        this.siteArticleId = siteArticleId;
        this.article = article;
        this.site = site;
        this.amount = amount;
    }

    public int getSiteArticleId() {
        return siteArticleId;
    }

    public Article getArticle() {
        return article;
    }

    public Site getSite() {
        return site;
    }

    public int getAmount() {
        return amount;
    }
}
