package pl.trebor.extractor;

import org.jsoup.nodes.Document;

import java.util.List;

public class WiProPageExtractor implements PageExtractor {
    private final Document document;

    public WiProPageExtractor(Document document) {
        this.document = document;
    }

    @Override
    public List<String> getDomainLinks() {
        return null;
    }
}
