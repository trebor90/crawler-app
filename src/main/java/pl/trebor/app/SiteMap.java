package pl.trebor.app;

import pl.trebor.crawler.Crawler;

public class SiteMap {
    public static void printSiteMap() {
        new Crawler(5, "http://wiprodigital.com").bfsPrint();
    }
}
