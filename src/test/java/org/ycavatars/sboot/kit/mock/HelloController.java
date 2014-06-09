package org.ycavatars.sboot.kit.mock;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ycavatars
 */
@RestController
@EnableAutoConfiguration
public class HelloController {

  @RequestMapping(method = RequestMethod.GET)
  public String hello() {
    return "hello world";
  }
}
