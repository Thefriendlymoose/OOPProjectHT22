package model.article;

import persistance.ArticlesDAO;
import persistance.IPersistenceNew;

import java.util.List;

public class ArticlesFacade {

    private IPersistenceNew<Article> dao;

    private void ArticleFacade(){
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
