package model.article;

import persistence.ArticlesDAO;
import persistence.IPersistence;

import java.util.List;

public class ArticlesFacade {

    private IPersistence<Article> dao;

    public ArticlesFacade(){
        this.dao = ArticlesDAO.getInstance();
    }

    public long getNextId(){
        return dao.getNextId();
    }

    public void saveArticle(Article article){
        dao.save(article);
    }

    public Article findById(long id){
        return dao.findById(id);
    }

    public List<Article> getAll(){
        return dao.getAll();
    }

    public ArticleCategory[] getCategories(){
        return ArticleCategory.values();
    }

    public ArticleStatus[] getStatuses(){
        return ArticleStatus.values();
    }

}
