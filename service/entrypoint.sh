#!/bin/bash
set -e

java $JOLOKIA_JAVA_AGENT $jvm_args -jar /app/generator-jar-with-dependencies.jar $cmd_line_args

