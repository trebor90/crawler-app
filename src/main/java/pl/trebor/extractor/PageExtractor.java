package pl.trebor.extractor;

import java.util.List;

public interface PageExtractor {
    /**
     *
     * @return list of all pages within the domain
     */
    List<String> getDomainLinks();
}
