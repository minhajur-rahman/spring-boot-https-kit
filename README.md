Spring Boot HTTPS Kit
=====================

Getting Started
---------------

You have to provide the following properties via [these ways](http://docs.spring.io/spring-boot/docs/current-SNAPSHOT/reference/htmlsingle/#boot-features-external-config).

* `connector.attribute.keystoreFile`
* `connector.attribute.keystorePass`
* `connector.attribute.keyAlias`

Keytool
-------

You have to generates a key pair (a public key and associated private key) to
enable HTTPS. Before you begin, it might be better for you to read these
documents: [keytool - Key and Certificate Management Tool](http://docs.oracle.com/javase/6/docs/technotes/tools/solaris/keytool.html#genkeypairCmd)
and [Use keytool to Create a Server Certificate](http://docs.oracle.com/cd/E19798-01/821-1841/gjrgy/index.html).

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
