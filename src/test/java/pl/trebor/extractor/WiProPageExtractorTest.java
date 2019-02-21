package pl.trebor.extractor;

import org.hamcrest.MatcherAssert;
import org.jsoup.nodes.Document;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.hamcrest.Matchers.hasSize;

public class WiProPageExtractorTest {

    private WiProPageExtractor wiProPageExtractor;

    @Before
    public void setUp() throws Exception {
        Document document = new TestDocumentProvider().getDocument(null, null);
        wiProPageExtractor = new WiProPageExtractor(document, "http://wiprodigital.com");
    }

    @Test
    public void shouldFindDomainLinks() {
        List<String> domainLinks = wiProPageExtractor.getDomainLinks();
        MatcherAssert.assertThat(domainLinks, hasSize(5));
    }

    @Test
    public void shouldFindExternalLinks() {
        List<String> externalLinks = wiProPageExtractor.getExternalLinks();
        MatcherAssert.assertThat(externalLinks, hasSize(2));
    }

    @Test
    public void shouldFindImgResources() {
        List<String> staticResourceLinks = wiProPageExtractor.getStaticResourceLinks();
        MatcherAssert.assertThat(staticResourceLinks, hasSize(3));
    }
}