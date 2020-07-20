#!/bin/bash
CLASSPATH=mysql-connector-java-8.0.16.jar:mapper-3.3.0.jar:mybatis-generator-core-1.3.2.jar
mainClassName=org.mybatis.generator.api.ShellRunner
java -classpath "$CLASSPATH" "$mainClassName" -configfile generatorConfig.xml -overwrite