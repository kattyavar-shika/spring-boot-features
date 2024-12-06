package com.kattyavar.shika.config_server.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "app.kattyavar.shika")
public class AppConfig {

  /*
        name: config Demo
      version: 1.0.0
      description: you can put anything no issues.
      profile: dev
   */

  private String name;
  private String version;
  private String description;
  private String profile;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getVersion() {
    return version;
  }

  public void setVersion(String version) {
    this.version = version;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getProfile() {
    return profile;
  }

  public void setProfile(String profile) {
    this.profile = profile;
  }

  @Override
  public String toString() {
    return "AppConfig{" +
      "name='" + name + '\'' +
      ", version='" + version + '\'' +
      ", description='" + description + '\'' +
      ", profile='" + profile + '\'' +
      '}';
  }
}
