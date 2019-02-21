package pl.trebor.app;

import pl.trebor.crawler.Crawler;

import java.net.URISyntaxException;

public class SiteMap {
    public static void printSiteMap(int pageLimit, String rootUrl) throws URISyntaxException {
        new Crawler(pageLimit, rootUrl).bfsPrint();
    }
}
