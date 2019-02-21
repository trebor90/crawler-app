package pl.trebor;

import pl.trebor.app.SiteMap;

import java.net.URISyntaxException;
import java.util.Objects;

public class Main {

    private static final int REQUIRED_PARAMS_NUMBER = 2;

    public static void main(String[] args) throws URISyntaxException {
        final int pageLimit;
        final String rootUrl;
        if (Objects.isNull(args) || args.length != REQUIRED_PARAMS_NUMBER) {
            pageLimit = 10;
            rootUrl = "http://wiprodigital.com";
        } else {
            pageLimit = Integer.parseInt(args[0]);
            rootUrl = args[1];
        }
        SiteMap.printSiteMap(pageLimit, rootUrl);
    }
}
