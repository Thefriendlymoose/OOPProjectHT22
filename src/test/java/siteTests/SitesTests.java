package siteTests;

import model.WMS;
import model.site.Site;
import model.site.Sites;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SitesTests {

    private WMS wms;
    private Sites sites;

    @BeforeEach
    public void init(){
        TestWMS t = new TestWMS();
        this.wms = t.getWMS();
        this.sites = wms.getSites();
    }

    @Test
    public void nextIdAfterAdd(){
        Long before = sites.getNextId();
        Site site = new Site(before, "t", "t", 500, null, null);
        sites.addSite(site);
        Long after = sites.getNextId();
        assertEquals(before + 1 , after);
    }

    @Test
    public void getById(){
        Site site = new Site(1000, "t", "t", 500, null, null);
        sites.addSite(site);
        Site site2 = sites.getById(1000L);

        assertEquals(site, site2);
    }
}
