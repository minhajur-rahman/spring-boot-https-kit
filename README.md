Spring Boot HTTPS Kit
=====================

Import this library to get HTTPS capability for your spring-boot application.

Build
-----

If you don't want to test with your keystore, run `./gradlew clean build -x test` to exclude tests. Otherwise, [configure keystore proeprties](https://github.com/ycavatars/spring-boot-https-kit#getting-started) in `src/test/resources/application.yml` and execute `./gradlew clean build`. 

The output folder is `build/libs`.

Getting Started
---------------

Import the library and enable component scan `@ComponentScan("org.ycavatars.sboot.kit")`, provide your keystore and configure these properties via [these ways](http://docs.spring.io/spring-boot/docs/current-SNAPSHOT/reference/htmlsingle/#boot-features-external-config).

* `connector.https.enabled`
* `connector.https.keystoreFile`
* `connector.https.keystorePass`
* `connector.https.keyAlias`

Keytool
-------

If you don't know how to generate a keystore, read this section.

You have to generates a key pair (a public key and associated private key) to
enable HTTPS. Before you begin, it might be better for you to read these
documents: [keytool - Key and Certificate Management Tool](http://docs.oracle.com/javase/8/docs/technotes/tools/unix/keytool.html) 
,[Use keytool to Create a Server Certificate](http://docs.oracle.com/cd/E19798-01/821-1841/gjrgy/index.html) and [SSLConnectionSocketFactory java doc](https://hc.apache.org/httpcomponents-client-ga/httpclient/apidocs/org/apache/http/conn/ssl/SSLConnectionSocketFactory.html).

### Example

`
keytool -genkeypair -alias <keyAlias> -keyalg RSA -keystore <keystoreFile>.p12
-storepass <keystorePass> -validity 3650 -keysize 2048 -dname "CN=<hostname>"
`

Specify your own <keyAlias>, <keystoreFile> and <keystorePass>, and set them to
`connector.attribute.keyAlias`, `connector.attribute.keystoreFile` and
`connector.attribute.keystorePass`. <hostname> means the host which runs your
server. If you forget to set <hostname>, your client will have issues like
`SSLHandshakeException`.

Note that each certificate is valid for 10 years in this example
(`-validity 3650`). Remember to change them depends on your needs.
