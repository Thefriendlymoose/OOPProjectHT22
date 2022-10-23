package persistence.pojos;

public class SiteArticleJSON {
    private long article;
    private int amount;

    public SiteArticleJSON(long articleId, int amount) {
        this.article = articleId;
        this.amount = amount;
    }

    public long getArticle() {
        return article;
    }

    public void setArticle(long article) {
        this.article = article;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
