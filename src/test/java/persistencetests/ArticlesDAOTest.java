package persistencetests;

import model.article.Article;
import model.article.ArticleCategory;
import model.article.ArticleStatus;
import model.user.Permission;
import model.user.User;
import org.junit.jupiter.api.*;
import persistence.ArticlesDAO;

import java.io.BufferedReader;
import java.io.FileReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.time.LocalDateTime;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.lang.Long.valueOf;

import static org.junit.jupiter.api.Assertions.*;

class ArticlesDAOTest {
    private final static String jsonFile="src/main/resources/articles.json";
    private ArrayList<Long> idList;

    /**
     * Create backup copy of json file before tests commence to preserve business data
     */
    @BeforeAll
    static void backup() {
        try {
            Path backupFilePath = Path.of(jsonFile + ".bak");
            Path jsonFilePath = Path.of(jsonFile);
            Files.copy(jsonFilePath, backupFilePath, StandardCopyOption.REPLACE_EXISTING);
        }
        catch (java.io.IOException ioe){
            System.out.println(ioe);
        }
    }

    /**
     * Restore pre-testing file data
     */
    @AfterAll
    static void restore() {
        try {
            Path backupFilePath = Path.of(jsonFile + ".bak");
            Path restoreFilePath = Path.of(jsonFile);
            Files.copy(backupFilePath, restoreFilePath, StandardCopyOption.REPLACE_EXISTING);
            Files.deleteIfExists(backupFilePath);
        }
        catch (java.io.IOException ioe){
            System.out.println(ioe);
        }
    }

    /**
     * Read and parse json-file with regex. Generate list of id values to compare with the DAO version
     */
    @BeforeEach
    void setUp() {
        idList = new ArrayList<>();
        List<String> lines;

        try {
            Pattern rxPat = Pattern.compile("articleId.\\: (?<articleId>\\d+)");
            BufferedReader br = new BufferedReader(new FileReader(jsonFile));
            lines = br.lines().toList();

            for(String line : lines ) {
                Matcher rxMatcher = rxPat.matcher(line);
                if(rxMatcher.find()) {
                    idList.add(Long.parseLong(rxMatcher.group("articleId")));
                }

            }
            br.close();
            Collections.sort(idList);
        }
        catch (java.io.IOException ioe){
            System.out.println(ioe);
        }
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void getInstance() {
        ArticlesDAO articlesDAO1=ArticlesDAO.getInstance();
        ArticlesDAO articlesDAO2=ArticlesDAO.getInstance();
        assertTrue(articlesDAO1==articlesDAO2,"DAO instances identical");
    }

    @Test
    void save() {
        ArticlesDAO articlesDAO = ArticlesDAO.getInstance();
        List<Article> testArticlesBefore = articlesDAO.getAll();

        Long nextFreeId = articlesDAO.getNextId();

        List<Permission> testPermissions = new ArrayList<>();
        testPermissions.add(Permission.USER);

        User testUser = new User(999, "Test User", "TestTest", "Test Name",
                true, testPermissions);

        Article testArticle = new Article(nextFreeId, "Test article", "Test description", ArticleCategory.Electronics,
                ArticleStatus.Active, Float.parseFloat("100"), Float.parseFloat("110"), testUser,
                LocalDateTime.now(), LocalDateTime.now());

        articlesDAO.save(testArticle);

        List<Article> testArticlesAfter = articlesDAO.getAll();
        assertTrue(testArticlesAfter.size()> testArticlesBefore.size());
        assertTrue(articlesDAO.findById(nextFreeId) == testArticle);

    }

    /**
     * Basic checks of size etc.
     */
    @Test
    void getAll() {

        ArticlesDAO articlesDAO = ArticlesDAO.getInstance();
        List<Article> articles = articlesDAO.getAll();
        // Sort the list
        articles.sort(Comparator.comparing((Article a) -> (valueOf(a.getArticleId()))));

        assertTrue(articles.size() == idList.size());
        for(Article article : articles){
            assertTrue(idList.contains(article.getArticleId()));
        }
    }

    @Test
    void getAllMap() {
        ArticlesDAO articlesDAO = ArticlesDAO.getInstance();
        Map<Long, Article> articles = articlesDAO.getAllMap();
        assertTrue(articles.size() == idList.size());

        for(Long id : idList) {
            assertTrue(articles.containsKey(id));
        }
    }

    @Test
    void getNextId() {
        ArticlesDAO articlesDAO = ArticlesDAO.getInstance();
        Long nextId = articlesDAO.getNextId();
        assertFalse(idList.contains(nextId));
        assertTrue(Collections.max(idList)<nextId);

    }

    @Test
    void findById() {
    }
}