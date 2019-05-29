#!/bin/bash

mvn clean install docker:build -f ../service

docker build -t ancillary-calculator ../../uc4-ancillarypricingcalculator