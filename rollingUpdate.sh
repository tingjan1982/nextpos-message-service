#!/bin/zsh

kubectl set image deployment/message-service nextpos-message-service=docker.io/joelin/nextpos-message-service:latest