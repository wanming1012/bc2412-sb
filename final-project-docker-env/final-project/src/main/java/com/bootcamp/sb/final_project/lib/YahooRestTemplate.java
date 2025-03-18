package com.bootcamp.sb.final_project.lib;

import java.io.IOException;
import org.apache.hc.client5.http.config.RequestConfig;
import org.apache.hc.client5.http.cookie.BasicCookieStore;
import org.apache.hc.client5.http.cookie.StandardCookieSpec;
import org.apache.hc.client5.http.impl.LaxRedirectStrategy;
import org.apache.hc.client5.http.impl.classic.HttpClientBuilder;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.lang.NonNull;
import org.springframework.web.client.RestTemplate;

public class YahooRestTemplate extends RestTemplate {
  public YahooRestTemplate() {
    super(new HttpComponentsClientHttpRequestFactory(
        HttpClientBuilder.create().setDefaultCookieStore(new BasicCookieStore())
            .setRedirectStrategy(new LaxRedirectStrategy()) // Handle redirects
            .setDefaultRequestConfig(
                RequestConfig.custom().setCookieSpec(StandardCookieSpec.RELAXED) // Use "best-match"
                    .build())
            .build()));
    this.getInterceptors().add(getUserAgentInterceptor());
  }

  private ClientHttpRequestInterceptor getUserAgentInterceptor() {
    return new ClientHttpRequestInterceptor() {
      @Override
      public @NonNull ClientHttpResponse intercept(@NonNull HttpRequest request, @NonNull byte[] body,
          @NonNull ClientHttpRequestExecution execution) throws IOException {
        // Mimic browser headers
        request.getHeaders().set("User-Agent", "Mozilla/5.0");
        request.getHeaders().set("Accept-Language", "en-US,en;q=0.9");
        request.getHeaders().set("Referer", "https://www.google.com/"); // Often required
        request.getHeaders().set("Accept",
            "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");

        try {
          ClientHttpResponse response = execution.execute(request, body);
          return response;
        } catch (IOException e) {
          throw new RuntimeException("Failed to execute request", e);
        }
      }
    };
  }
}
