package org.ycavatars.sboot.kit.mock;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author ycavatars
 */
@Configuration
@EnableAutoConfiguration
@ComponentScan("org.ycavatars")
public class App {

  public static void main(String[] args) {
    ApplicationContext appContext = SpringApplication.run(App.class, args);
  }
}
