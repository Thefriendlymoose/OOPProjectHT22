package persistence;

// @todo justera importer när klasserna flyttas till ett paket per
// funktionellt paket i applikationen
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import model.user.User;
import model.article.Article;
import model.site.Site;
import model.site.SiteArticle;
import persistence.pojos.SiteArticleJSON;
import persistence.pojos.SiteJSON;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

public final class SitesDAO implements IPersistence<Site> {

    private static SitesDAO instance;
    private final String sitesFile="src/main/resources/sites.json";
    private Map<Long, Site> sites;
    private long nextFreeId =0;
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
                    siteArticles.add(new SiteArticle(articles.get(saj.getArticleId()), saj.getAmount()));
                }

                List<User> siteUsers = new ArrayList<>();

                for (Long suid : sj.getSiteUsers()){
                    siteUsers.add(users.get(suid));
                }

                Site site = new Site(sj.getSiteId(), sj.getSiteName(), sj.getSiteAddress(), sj.getMaxCapacity(),
                                     siteArticles, siteUsers);

                sites.put(site.getSiteId(), site);

                if (sites.size() > 0 ){
                    nextFreeId = Collections.max(sites.keySet()) + 1;
                }
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

    @Override
    public void save(List<Site> list) {
        SerializeBuilder sb = new SerializeBuilder();
        sb.addArticleSerializer();
        sb.addUserSerializer();
        Gson g = sb.getGson();

        try{
            FileWriter fw = new FileWriter(sitesFile);
            BufferedWriter writer = new BufferedWriter(fw);

            g.toJson(list, writer);
            writer.flush();
            writer.close();
            fw.close();
        } catch (IOException e) {
            System.out.println(e);
        }

    }

    //@todo borde gå att ha koden i interfacet?
    @Override
    public List<Site> getAll() {
        //@todo sortera före retur
        return new ArrayList<>(this.sites.values());
    }

    @Override
    public Map<Long, Site> getAllMap() {
        return this.sites;
    }

    @Override
    public long getNextId() {
        return this.nextFreeId;
    }

    @Override
    public Site findById(long id) {
        return this.sites.get(id);
    }

}
