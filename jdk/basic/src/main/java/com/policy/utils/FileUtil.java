package com.policy.utils;

import java.io.File;
import java.io.IOException;

public class FileUtil {
  public static void createFile(String name) {
    File fs = new File(name);
    try {
      fs.createNewFile();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
