package pl.trebor.extractor;

import java.util.List;

public interface PageExtractor {
    /**
     * @return all pages within the domain
     */
    List<String> getDomainLinks();

    /**
     * @return all pages excluding the domain
     */
    List<String> getExternalLinks();

    /**
     * @return static resources, like img etc.
     */
    List<String> getStaticResourceLinks();
}
