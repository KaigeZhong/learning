package com;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.internal.DefaultShellCallback;

public class MybatisGenerator {

    public static void main(String[] args) throws Exception {
        File configFile = new File(Thread.currentThread().getContextClassLoader().getResource("generatorConfiguration.xml").getPath());

        if(!configFile.exists()) {
            System.out.println("配置文件不存在");
            return;
        }

        List<String> warnings = new ArrayList<String>();
        Configuration config = new ConfigurationParser(warnings).parseConfiguration(configFile);
        DefaultShellCallback callback = new DefaultShellCallback(true);

        MyBatisGenerator myBatisGenerator = new MyBatisGenerator(config, callback, warnings);
        myBatisGenerator.generate(null);
    }
}