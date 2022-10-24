package persistence.pojos;

/**
 * OrderRow POJO data class used temporarily when deserializing from JSON
 */
public class OrderRowJSON {
    private long article;
    private int amount;

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
