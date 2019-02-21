package pl.trebor.provider;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import pl.trebor.provider.exception.UnsupportedContentTypeException;

import java.io.IOException;

/**
 * Provider for jsoup Document object.
 */
public class JsoupDocumentProvider implements DocumentProvider {
    // our crawler should seen as browser
    private static final String USER_AGENT = "Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/72.0.3626.96 Safari/537.36";

    @Override
    public Document getDocument(String pageUrl, String expectedContentType) throws IOException, UnsupportedContentTypeException {
        Connection connection = Jsoup.connect(pageUrl).userAgent(USER_AGENT);
        Document document = connection.get();
        if (!connection.response().contentType().contains(expectedContentType)) {
            throw new UnsupportedContentTypeException("Document is not: " + expectedContentType);
        }

        return document;
    }
}