package model.article;

import model.observer.Observable;
import model.observer.Observer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class Articles implements Observable {

    private Map<Long, Article> articles;
    private Long nextId;
    private List<Observer> observers;

    public Articles(Map<Long, Article> articles) {
        this.articles = articles;
        nextId = Collections.max(articles.keySet()) + 1;
        observers = new ArrayList<>();
    }

    public Article findById(long id){
        return articles.get(id);
    }

    public List<Article> getArticlesByStatus(ArticleStatus status){
        List<Article> arts = new ArrayList<>();

        for (Article a : articles.values()){
            if (status == a.getStatus()){
                arts.add(a);
            }
        }
        return arts;
    }
    public List<Article> getArticlesByCategory(ArticleCategory category){
        return null;
    }

    public Long getNextId(){
        return nextId;
    }

    public List<Article> getInList(){
        return new ArrayList<>(articles.values());
    }

    public Map<Long, Article> getInMap(){
        return articles;
    }

    public ArticleStatus[] getStatus(){
        return ArticleStatus.values();
    }

    public ArticleCategory[] getCategories(){
        return ArticleCategory.values();
    }

    public void addArticle(Article article){
        articles.put(article.getArticleId(), article);
        nextId++;
        notifyObservers();
    }

    public void removeArticle(Article article){
        articles.remove(article.getArticleId());
        notifyObservers();
    }

    public void updateArticle(){
        notifyObservers();
    }


    @Override
    public void registerObserver(Observer o) {
        observers.add(o);
    }

    @Override
    public void unregisterObserver(Observer o) {
        observers.remove(o);
    }

    @Override
    public void unregisterAll() {
        observers = new ArrayList<>();
    }

    @Override
    public void notifyObservers() {
        for (Observer o : observers){
            o.update();
        }
    }
}
