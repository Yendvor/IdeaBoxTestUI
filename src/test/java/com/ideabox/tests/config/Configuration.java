package com.ideabox.tests.config;

import static com.ideabox.tests.config.Configuration.Environments.getEnvironment;

/**
 * Created by sorin on 5/11/16.
 */
public class Configuration {
  private static String envs = System.getProperty("env");

  public static String getEnvs() {
    return envs;
  }

  public static String getIdeaboxUIURL(String env) {
    return getEnvironment(env).getBaseUrlIdeabox();
  }

  enum Environments {
    DEV1("http://mb-win7.vlab.lohika.com"),
    DEV("http://mb-win7.vlab.lohika.com"),
    LOCALHOST("http://mb-win7.vlab.lohika.com");

    private String baseUrlIdeabox;

    public String getBaseUrlIdeabox() {
      return baseUrlIdeabox;
    }

    Environments(String baseUrlIdeabox) {
      this.baseUrlIdeabox = baseUrlIdeabox;
    }


    public static Environments getEnvironment(String env) {
      switch (env) {
        case "localhost":
          return LOCALHOST;
        case "dev":
          return DEV;
        case "dev1":
          return DEV1;
        default:
          return DEV;
      }
    }


  }

}
