package org.ycavatars.sboot.kit;

import org.apache.http.client.HttpClient;
import org.apache.http.client.config.CookieSpecs;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLContextBuilder;
import org.apache.http.conn.ssl.TrustSelfSignedStrategy;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.protocol.HttpContext;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.TestRestTemplate;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.client.RestTemplate;
import org.ycavatars.sboot.kit.mock.App;

import java.net.URI;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = App.class)
@ActiveProfiles("integration-test")
@IntegrationTest
@WebAppConfiguration
public class TomcatConfigTest {

  // inject the actual (local) port at runtime.
  @Value("${local.server.port}")
  private int serverPort;

  private RestTemplate restTemplate;

  @Before
  public void init() throws KeyStoreException, NoSuchAlgorithmException, KeyManagementException {
    SSLContextBuilder builder = new SSLContextBuilder();
    // trust self signed certificate
    builder.loadTrustMaterial(null, new TrustSelfSignedStrategy());
    SSLConnectionSocketFactory sslConnectionSocketFactory = new SSLConnectionSocketFactory(
        builder.build());
    final HttpClient httpClient = HttpClients.custom().setSSLSocketFactory(
        sslConnectionSocketFactory).build();

    restTemplate = new TestRestTemplate();
    restTemplate.setRequestFactory(new HttpComponentsClientHttpRequestFactory(httpClient) {
      @Override
      protected HttpContext createHttpContext(HttpMethod httpMethod, URI uri) {
        HttpClientContext context = HttpClientContext.create();
        RequestConfig.Builder builder = RequestConfig.custom()
            .setCookieSpec(CookieSpecs.IGNORE_COOKIES)
            .setAuthenticationEnabled(false).setRedirectsEnabled(false)
            .setConnectTimeout(1000).setConnectionRequestTimeout(1000).setSocketTimeout(1000);
        context.setRequestConfig(builder.build());
        return context;
      }
    });
  }

  @Test
  public void testContainerCustomizer_disableHttps() {
    ResponseEntity<String> responseEntity = restTemplate.getForEntity(
        "https://localhost:" + serverPort, String.class);
    Assert.assertEquals("hello world", responseEntity.getBody());
  }
}
