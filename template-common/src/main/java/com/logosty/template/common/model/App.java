package com.logosty.template.common.model;

import org.apache.commons.lang3.StringUtils;

/**
 * @author logosty(ganyingle)
 * @date 2019/9/27 14:31
 */
public enum App {

  SNAPTUBE("snaptube", 1),

  LARKPLAYER("larkplayer", 2),

  FOURSTATUS("fourstatus", 4);

  private String name;
  private int id;

  App(String name, int id) {
    this.name = name;
    this.id = id;
  }

  public static App getById(int id) {
    for (App app : App.values()) {
      if (app.getId() == id) {
        return app;
      }
    }

    return App.SNAPTUBE;
  }

  public static App getByName(String name) {
    if (StringUtils.isNotBlank(name)) {
      name = name.toLowerCase().trim();
      for (App app : App.values()) {
        if (app.getName().equals(name)) {
          return app;
        }
      }
    }

    return App.SNAPTUBE;
  }

  public String getName() {
    return name;
  }

  public int getId() {
    return id;
  }
}

