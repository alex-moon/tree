#!/bin/bash
PGPASSWORD=tree psql --user=tree --host=127.0.0.1 tree < tree.sql
/opt/neo4j/bin/neo4j-shell < tree.cql
mvn tomcat7:redeploy
rm -rf target/*
