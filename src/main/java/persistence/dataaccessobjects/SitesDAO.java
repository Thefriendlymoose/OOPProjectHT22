package persistence.dataaccessobjects;

import com.google.gson.Gson;
import model.user.User;
import model.article.Article;
import model.site.Site;
import model.site.SiteArticle;
import persistence.IPersistence;
import persistence.SerializeBuilder;
import persistence.WriterHelper;
import persistence.pojos.SiteArticleJSON;
import persistence.pojos.SiteJSON;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

/**
 * Data Access Object which handles loading/saving site data to/from JSON
 */
public final class SitesDAO implements IPersistence<Site> {

    private static SitesDAO instance;
    private final String sitesFile="src/main/resources/sites.json";
    private Map<Long, Site> sites;
    private Gson gson = new Gson();
    private Map<Long, User> users;
    private Map<Long, Article> articles;

    private SitesDAO(){
        this.sites  = new HashMap<>();
        this.users = UserDAO.getInstance().getAllMap();
        this.articles = ArticlesDAO.getInstance().getAllMap();

        try {
            Reader reader = Files.newBufferedReader(Path.of(sitesFile));
            List<SiteJSON> siteJSONS = Arrays.asList(gson.fromJson(reader, SiteJSON[].class));

            for (SiteJSON sj : siteJSONS){
                List<SiteArticle> siteArticles = new ArrayList<>();

                for (SiteArticleJSON saj : sj.getSiteArticles()){
                    siteArticles.add(new SiteArticle(articles.get(saj.getArticle()), saj.getAmount()));
                }

                List<User> siteUsers = new ArrayList<>();

                for (Long suid : sj.getSiteUsers()){
                    siteUsers.add(users.get(suid));
                }

                Site site = new Site(sj.getSiteId(), sj.getSiteName(), sj.getSiteAddress(), sj.getMaxCapacity(),
                                     siteArticles, siteUsers);

                sites.put(site.getSiteId(), site);


            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static SitesDAO getInstance(){
        if (instance == null){
            instance = new SitesDAO();
        }
        return instance;
    }

    /**
     * Serializes and saves a list of sites to a JSON file
     * @param list
     */
    @Override
    public void save(List<Site> list) {
        SerializeBuilder sb = new SerializeBuilder();
        sb.addArticleSerializer();
        sb.addUserSerializer();
        WriterHelper<Site> wh = new WriterHelper<>();
        wh.writeToFileSerializer(sitesFile, list, sb.getGson());
    }


    @Override
    public Map<Long, Site> getAllMap() {
        return this.sites;
    }


}
