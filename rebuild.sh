#!/bin/bash
PGPASSWORD=tree psql --user=tree --host=127.0.0.1 tree < tree.sql
mvn tomcat7:redeploy
rm -rf target/*
