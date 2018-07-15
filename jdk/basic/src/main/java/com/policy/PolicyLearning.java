package com.policy;

import com.policy.utils.FileUtil;

public class PolicyLearning {
  public static void main(String[] args) {
    String policy = Thread.currentThread().getContextClassLoader().getResource("myTest.policy").toString();
    System.setProperty("java.security.policy", policy);
    System.setSecurityManager(new SecurityManager());
    FileUtil.createFile("/home/kaige/work/policy/file_a");
//    FileUtil.createFile("/home/kaige/work/policy_read_only/file_a");
    FileUtil.doPrivilegedAction("/home/kaige/work/policy_read_only/file_a");
  }
}
