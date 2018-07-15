package com.policy.utils;

import java.io.File;
import java.io.IOException;
import java.security.AccessController;
import java.security.PrivilegedAction;

public class FileUtil {
  public static void createFile(String name) {
    File fs = new File(name);
    try {
      fs.createNewFile();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public static void doPrivilegedAction(final String fileName) {
    // 用特权访问方式创建文件
    AccessController.doPrivileged(new PrivilegedAction<String>() {
      @Override
      public String run() {
        createFile(fileName);
        return null;
      }
    });
  }
}
