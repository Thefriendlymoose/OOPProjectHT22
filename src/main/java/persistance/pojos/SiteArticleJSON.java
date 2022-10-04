package persistance.pojos;

public class SiteArticleJSON {
    private int articleId;
    private int amount;

    public SiteArticleJSON() {

    }

    public int getArticleId() {
        return articleId;
    }

    public void setArticle(int articleId) {
        this.articleId = articleId;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
