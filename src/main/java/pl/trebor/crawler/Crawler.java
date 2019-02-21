package pl.trebor.crawler;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

public class Crawler {
    private final int maxDepth;
    private final String rootUrl;

    private Set<String> discoveredLinks = new HashSet<>();
    private Queue<String> linksToVisit = new LinkedList<>();

    public Crawler(int maxDepth, String rootUrl) {
        this.maxDepth = maxDepth;
        this.rootUrl = rootUrl;
    }

    public void bfsPrint() {

    }
}
