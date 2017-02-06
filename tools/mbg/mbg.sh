#!/usr/bin/env bash

mkdir -p ${HOME}/.jgyuer/libs/java
MYSQL_CONNECTOR_FILE="$HOME/.jgyuer/libs/java/mysql-connector-java-5.1.40.jar"
MYBATIS_GENERATOR_FILE="$HOME/.jgyuer/libs/java/mybatis-generator-core-1.3.3.jar"

if [ -f "$MYSQL_CONNECTOR_FILE" ]
then
	echo "$MYSQL_CONNECTOR_FILE found."
else
	echo "$MYSQL_CONNECTOR_FILE not found."
    echo "downloading missing dependency"
    curl --output ${MYSQL_CONNECTOR_FILE} http://central.maven.org/maven2/mysql/mysql-connector-java/5.1.40/mysql-connector-java-5.1.40.jar
fi

if [ -f "$MYBATIS_GENERATOR_FILE" ]
then
	echo "$MYBATIS_GENERATOR_FILE found."
else
	echo "$MYBATIS_GENERATOR_FILE not found."
    echo "downloading missing dependency"
    curl --output ${MYBATIS_GENERATOR_FILE} http://central.maven.org/maven2/org/mybatis/generator/mybatis-generator-core/1.3.3/mybatis-generator-core-1.3.3.jar
fi

java -cp ${MYSQL_CONNECTOR_FILE}:${MYBATIS_GENERATOR_FILE} org.mybatis.generator.api.ShellRunner "$@"