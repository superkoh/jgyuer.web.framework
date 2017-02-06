#!/usr/bin/env bash

DIR="$( cd "$( dirname "${BASH_SOURCE[0]}" )" && pwd )"
java -cp ${DIR}/mysql-connector-java-5.1.38.jar:${DIR}/mybatis-generator-core-1.3.3.jar org.mybatis.generator.api.ShellRunner "$@"