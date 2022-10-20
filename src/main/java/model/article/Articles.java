package model.article;

import model.observer.Observable;
import model.observer.Observer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;


/**
 * Is a collection of articles, implements Observable so that observers will be notified of changes.
 */
public class Articles {

    private Map<Long, Article> articles;
    private Long nextId;



    /**
     * Constructor of articles, the constructor also calculates what the next available id is and sets the attribute
     * @param articles Takes a Map of Ids and articles
     */
    public Articles(Map<Long, Article> articles) {
        this.articles = articles;
        nextId = Collections.max(articles.keySet()) + 1;
    }


    /**
     * Used to find a specific article.
     * @param id Client send an id as parameter
     * @return Returns an article
     */
    public Article findById(long id){
        return articles.get(id);
    }

    /**
     * Used to get a list of article based on the status
     * @param status Takes an ArticleStatus
     * @return returns a list of Articles with the same status
     */
    public List<Article> getArticlesByStatus(ArticleStatus status){
        List<Article> arts = new ArrayList<>();

        for (Article a : articles.values()){
            if (status == a.getStatus()){
                arts.add(a);
            }
        }
        return arts;
    }

    /**
     * Used to get a list of articles with the same category
     * @param category Takes an ArticleCategory
     * @return returns a list of articles with the same category
     */
    public List<Article> getArticlesByCategory(ArticleCategory category){
        List<Article> arts = new ArrayList<>();

        for (Article a : articles.values()){
            if (category == a.getCategory()){
                arts.add(a);
            }
        }
        return arts;
    }

    /**
     *
     * @return returns the next available ID
     */
    public Long getNextId(){
        return nextId;
    }

    /**
     *
     * @return returns a list of all the articles in the system
     */
    public List<Article> getInList(){
        return new ArrayList<>(articles.values());
    }

    /**
     *
     * @return returns a map of all the articles in the system
     */
    public Map<Long, Article> getInMap(){
        return articles;
    }

    /**
     *
     * @return returns a list of all the available statuses
     */
    public ArticleStatus[] getStatus(){
        return ArticleStatus.values();
    }

    /**
     *
     * @return returns a list of all the available categories
     */
    public ArticleCategory[] getCategories(){
        return ArticleCategory.values();
    }

    /**
     * Adds a new article to the system
     * @param article Takes an article
     */
    public void addArticle(Article article){
        articles.put(article.getArticleId(), article);
        nextId++;
    }

    /**
     * Removed an article from the system
     * @param article Takes the article you want to remove
     */
    public void removeArticle(Article article){
        articles.remove(article.getArticleId());
    }


    /**
     * Used to send an update to observers when an article is edited.
     */
    public void updateArticle(){

    }

}
