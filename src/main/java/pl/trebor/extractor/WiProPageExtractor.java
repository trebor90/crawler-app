package pl.trebor.extractor;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import pl.trebor.utils.UrlUtils;

import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Class responsible for extracting resources from @see <a href="https://wiprodigital.com">https://wiprodigital.com</a> page.
 */
public class WiProPageExtractor implements PageExtractor {

    private static final Pattern backgroundUrlPattern = Pattern.compile("(?<=\\().+?(?=\\))");

    private final Document document;
    private final String domain;

    public WiProPageExtractor(Document document, String rootUrl) throws URISyntaxException {
        this.document = document;
        this.domain = UrlUtils.getDomainName(rootUrl);
    }

    @Override
    public List<String> getDomainLinks() {
        Elements linksOnPage = document.select("a[href]");
        Set<String> linksWithoutDuplicates = new HashSet<>();

        for (Element link : linksOnPage) {
            String absUrl = link.absUrl("href");
            try {
                if (UrlUtils.getDomainName(absUrl).equals(domain)) {
                    linksWithoutDuplicates.add(absUrl);
                }
            } catch (URISyntaxException e) {
                // ignore link
            }
        }
        return new ArrayList<>(linksWithoutDuplicates);
    }

    @Override
    public List<String> getExternalLinks() {
        Set<String> linksWithoutDuplicates = new HashSet<>();
        Elements linksOnPage = document.select("a[href]");
        for (Element link : linksOnPage) {
            String absUrl = link.absUrl("href");
            try {
                if (!(UrlUtils.getDomainName(absUrl).equals(domain))) {
                    linksWithoutDuplicates.add(absUrl);
                }
            } catch (URISyntaxException e) {
                // ignore link
            }
        }
        return new ArrayList<>(linksWithoutDuplicates);
    }

    @Override
    public List<String> getStaticResourceLinks() {
        List<String> staticResourceLinks = new ArrayList<>();
        staticResourceLinks.addAll(getResourcesFromBackgroundUrl());
        staticResourceLinks.addAll(getImgResource());

        return staticResourceLinks;
    }

    private List<String> getResourcesFromBackgroundUrl() {
        Set<String> staticResourceLinks = new HashSet<>();
        Elements linksOnPage = document.select("[style^=background: url]");
        for (Element link : linksOnPage) {
            final String style = link.attr("style");
            final Matcher matcher = backgroundUrlPattern.matcher(style);

            if (matcher.find()) {
                staticResourceLinks.add(matcher.group());
            }
        }

        return new ArrayList<>(staticResourceLinks);
    }

    private List<String> getImgResource() {
        Set<String> staticResourceLinks = new HashSet<>();
        Elements linksOnPage = document.select("img[src]");
        for (Element link : linksOnPage) {
            staticResourceLinks.add(link.absUrl("src"));
        }

        return new ArrayList<>(staticResourceLinks);
    }
}
