package pl.trebor.crawler;

import org.jsoup.nodes.Document;
import pl.trebor.extractor.PageExtractor;
import pl.trebor.extractor.WiProPageExtractor;
import pl.trebor.provider.JsoupHtmlDocumentProvider;
import pl.trebor.provider.exception.UnsupportedContentTypeException;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.*;

/**
 * Crawler responsible for web site traversing.
 */
public class Crawler {
    private final int maxDepth;
    private final String rootUrl;

    private Set<String> discoveredLinks = new HashSet<>();
    private Queue<String> linksToVisit = new LinkedList<>();

    public Crawler(int maxDepth, String rootUrl) {
        this.maxDepth = maxDepth;
        this.rootUrl = rootUrl;
    }

    /**
     * Print site links using breadth-first search approach.
     */
    public void bfsPrint() {
        int currentDepth = 0;
        linksToVisit.add(rootUrl);

        while (!linksToVisit.isEmpty()) {
            final String currentDomainLink = linksToVisit.poll();
            System.out.println(currentDomainLink);

            try {
                if (currentDepth < maxDepth) {
                    PageExtractor extractor = getExtractor(currentDomainLink);
                    discoveredLinks.add(currentDomainLink);

                    List<String> domainLinks = extractor.getDomainLinks();
                    for (String link : domainLinks) {
                        if (discoveredLinks.add(link)) {
                            linksToVisit.add(link);
                        }
                    }
                    currentDepth++;
                }
            } catch (Exception e) {
                System.out.println("Cannot access document for: " + currentDomainLink);
                e.printStackTrace();
            }
        }
    }

    private PageExtractor getExtractor(String url) throws IOException, UnsupportedContentTypeException, URISyntaxException {
        Document document = new JsoupHtmlDocumentProvider().getDocument(url, "text/html");
        return new WiProPageExtractor(document, rootUrl);
    }
}
