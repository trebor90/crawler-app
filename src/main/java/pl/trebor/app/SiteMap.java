package pl.trebor.app;

import pl.trebor.crawler.Crawler;

public class SiteMap {
    public static void printSiteMap() {
        new Crawler(10, "http://wiprodigital.com").bfsPrint();
    }
}
