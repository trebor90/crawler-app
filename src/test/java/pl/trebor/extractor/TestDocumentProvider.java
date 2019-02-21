package pl.trebor.extractor;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import pl.trebor.provider.DocumentProvider;

import java.io.File;
import java.io.IOException;

/**
 * Static document provider for test purpose.
 */
public class TestDocumentProvider implements DocumentProvider {
    @Override
    public Document getDocument(String url, String expectedContentType) throws IOException {
        File file = new File("src/test/resources/wiprodigital.html");
        return Jsoup.parse(file, "UTF-8", "");
    }
}
