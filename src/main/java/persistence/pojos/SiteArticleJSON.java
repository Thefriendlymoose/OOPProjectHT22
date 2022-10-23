package persistence.pojos;

public class SiteArticleJSON {
    private long articleId;
    private int amount;

    public SiteArticleJSON(long articleId, int amount) {
        this.articleId = articleId;
        this.amount = amount;
    }

    public long getArticleId() {
        return articleId;
    }

    public void setArticleId(long articleId) {
        this.articleId = articleId;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
