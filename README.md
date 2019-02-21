# crawler-app
Simple crawler app

### How it works

Crawler start from rootUrl (by default http://wiprodigital.com) and looking for links from the same domain.
Web pages is like graph, but because this crawler shouldn't visit the same page twice, we have more like tree structure.
I decided to use breadth-first search approach for traversing pages.
Crawler print result to standard output. Prints domain link, then external links for this page, then static resources for this page.

### Assumption for static resource retrieval
Crawler looking for img tag, as well as for element with style attr with background url property. 

### How to build
This is a simple maven project.
Build project:

```sh
mvn clean package
```

Run test:
```sh
mvn test
```

### How to run

Run executable jar:
```sh
java -cp target/crawler-app-1.0-SNAPSHOT-jar-with-dependencies.jar pl.trebor.Main
```

By default crawler visiting "http://wiprodigital.com" and has a limit of traversing pages set to 5.
If you want to change this add this params while execute jar.
Example:
```sh
java -cp target/crawler-app-1.0-SNAPSHOT-jar-with-dependencies.jar pl.trebor.Main 10 http://wiprodigital.com
```

### Reasoning and describe any trade offs
* Because website is like a graph I decided to limit traversed pages.
Crawler shouldn't visit the same page twice, but still amount of unique link could be huge.
The limit is for time reasons.
* Crawler using [jsoup](https://jsoup.org) for parse html document, because of that we have only static html (java script is not executed), so we loose access to dynamic page content.
* to keep it simple, crawler extracting only two types of static resources: img and element with style attr with background url property

### Explanation of what could be done with more time
* run page in headless browser to get access to dynamic content
* add prettier display structure (full tree with resources)
* build tree structure instead of print flat structure to standard output
* present result as web page
* check more precisely which resources we could retrieve from page