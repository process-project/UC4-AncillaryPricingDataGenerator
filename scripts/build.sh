#!/bin/bash

cd ../service

mvn clean install -DskipTests docker:build

docker build -t ancillary-calculator ../../uc4-ancillarypricingcalculator