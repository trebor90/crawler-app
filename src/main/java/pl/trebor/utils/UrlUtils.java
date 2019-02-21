package pl.trebor.utils;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Objects;

public final class UrlUtils {
    private UrlUtils() {
        // util class
    }

    public static String getDomainName(String url) throws URISyntaxException {
        URI uri = new URI(url);
        String domain = uri.getHost();
        if (Objects.isNull(domain)) {
            throw new URISyntaxException(url, "Domain is null");
        }
        return domain.startsWith("www.") ? domain.substring(4) : domain;
    }
}
