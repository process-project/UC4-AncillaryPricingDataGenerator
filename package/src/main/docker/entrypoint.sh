#!/bin/bash
set -e

java $JOLOKIA_JAVA_AGENT $jvm_args -jar $1 $cmd_line_args

