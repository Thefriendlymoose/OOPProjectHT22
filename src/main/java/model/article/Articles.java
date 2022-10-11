package model.article;

import model.Authentication.CachedUser;
import model.Authentication.UserAuthentication;
import model.Permission;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Articles {

    private Map<Long, Article> articles;
    private CachedUser user;



    public Articles(Map<Long, Article> articles, CachedUser user) {
        this.articles = articles;
        this.user = user;
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
