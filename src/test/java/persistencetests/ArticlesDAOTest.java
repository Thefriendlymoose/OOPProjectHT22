package persistencetests;

import model.article.Article;
import org.junit.jupiter.api.*;
import persistence.dataaccessobjects.ArticlesDAO;

import java.io.BufferedReader;
import java.io.FileReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.junit.jupiter.api.Assertions.*;

class ArticlesDAOTest {
    private final static String jsonFile="src/main/resources/articles.json";
    private List<Long> idList;

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
    void getAllMap() {
        ArticlesDAO articlesDAO = ArticlesDAO.getInstance();
        Map<Long, Article> articles = articlesDAO.getAllMap();
        assertTrue(articles.size() == idList.size());

        for(Long id : idList) {
            assertTrue(articles.containsKey(id));
        }
    }

}