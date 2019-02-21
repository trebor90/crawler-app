package pl.trebor.provider;

import org.jsoup.nodes.Document;
import pl.trebor.provider.exception.UnsupportedContentTypeException;

import java.io.IOException;

public interface DocumentProvider {
    /**
     * Get jsoup Document object.
     */
    Document getDocument(String pageUrl, String expectedContentType) throws IOException, UnsupportedContentTypeException;
}
