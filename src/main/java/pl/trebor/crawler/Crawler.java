package pl.trebor.crawler;

import org.jsoup.nodes.Document;
import pl.trebor.extractor.PageExtractor;
import pl.trebor.extractor.WiProPageExtractor;
import pl.trebor.provider.JsoupHtmlDocumentProvider;
import pl.trebor.provider.exception.UnsupportedContentTypeException;
import pl.trebor.utils.UrlUtils;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.*;

/**
 * Crawler responsible for web site traversing.
 */
public class Crawler {
    private static final String CONTENT_TYPE = "text/html";
    private final int pageLimit;
    private final String rootUrl;

    private Set<String> discoveredLinks = new HashSet<>();
    private Queue<String> linksToVisit = new LinkedList<>();

    public Crawler(int pageLimit, String rootUrl) throws URISyntaxException {
        UrlUtils.getDomainName(rootUrl); // for validation purpose
        this.pageLimit = pageLimit;
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
            System.out.println("domain: " + currentDomainLink);

            try {
                if (currentDepth < pageLimit) {
                    PageExtractor extractor = getExtractor(currentDomainLink);
                    discoveredLinks.add(currentDomainLink);

                    addUnvisitedToQueue(extractor.getDomainLinks());
                    printExternalLinks(extractor.getExternalLinks());
                    printStaticResourcesLinks(extractor.getStaticResourceLinks());

                    currentDepth++;
                }
            } catch (Exception e) {
                System.out.println("Cannot access document for: " + currentDomainLink);
                e.printStackTrace();
            }
        }
    }

    private PageExtractor getExtractor(String url) throws IOException, UnsupportedContentTypeException, URISyntaxException {
        Document document = new JsoupHtmlDocumentProvider().getDocument(url, CONTENT_TYPE);
        return new WiProPageExtractor(document, rootUrl);
    }

    private void addUnvisitedToQueue(List<String> domainLinks) {
        for (String link : domainLinks) {
            if (discoveredLinks.add(link)) {
                linksToVisit.add(link);
            }
        }
    }

    private void printExternalLinks(List<String> externalLinks) {
        for (String link : externalLinks) {
            System.out.println(" ext link: " + link);
        }
    }

    private void printStaticResourcesLinks(List<String> staticResourcesLinks) {
        for (String link : staticResourcesLinks) {
            System.out.println(" resource: " + link);
        }
    }
}
