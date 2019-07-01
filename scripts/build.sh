#!/bin/bash

cd ../service

mvn clean install docker:build

docker build -t ancillary-calculator ../../uc4-ancillarypricingcalculator