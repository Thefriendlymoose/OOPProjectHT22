package persistance;

public class SiteArticleJSON {
    private int siteArticleId;
    private int articleId;
    private int siteId;
    private int amount;

    public SiteArticleJSON() {

    }

    public int getSiteArticleId() {
        return siteArticleId;
    }

    public void setSiteArticleId(int siteArticleId) {
        this.siteArticleId = siteArticleId;
    }

    public int getArticleId() {
        return articleId;
    }

    public void setArticle(int articleId) {
        this.articleId = articleId;
    }

    public int getSite() {
        return siteId;
    }

    public void setSite(int site) {
        this.siteId = site;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
