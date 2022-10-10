package model.article;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Articles {

    private Map<Long, Article> articles;

    public Articles(Map<Long, Article> articles) {
        this.articles = articles;
    }

    public Article getArticleById(long id){
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



}
