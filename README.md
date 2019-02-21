# crawler-app
Simple crawler app

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
* Crawler using [jsoup] for parse html document, because of that we have only static html (java script is not executed), so we loose access to dynamic page content.