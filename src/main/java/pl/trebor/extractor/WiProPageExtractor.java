package pl.trebor.extractor;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Class responsible for extracting resources from @see <a href="https://wiprodigital.com">https://wiprodigital.com</a> page.
 */
public class WiProPageExtractor implements PageExtractor {
    private final Document document;

    public WiProPageExtractor(Document document) {
        this.document = document;
    }

    @Override
    public List<String> getDomainLinks() {
        Elements linksOnPage = document.select("a[href]");
        Set<String> linksWithoutDuplicates = new HashSet<>();

        for (Element link : linksOnPage) {
            String absUrl = link.absUrl("href");
            linksWithoutDuplicates.add(absUrl);
        }

        return new ArrayList<>(linksWithoutDuplicates);
    }
}
