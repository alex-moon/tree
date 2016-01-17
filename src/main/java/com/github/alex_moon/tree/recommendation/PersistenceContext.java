package com.github.alex_moon.tree.recommendation;

import org.neo4j.ogm.session.Session;
import org.neo4j.ogm.session.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.core.env.Environment;
import org.springframework.data.neo4j.config.Neo4jConfiguration;
import org.springframework.data.neo4j.repository.config.EnableNeo4jRepositories;
import org.springframework.data.neo4j.server.Neo4jServer;
import org.springframework.data.neo4j.server.RemoteServer;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableNeo4jRepositories("com.github.alex_moon.tree.recommendation.repositories")
@EnableTransactionManagement
@ComponentScan("com.github.alex_moon.tree.recommendation")
public class PersistenceContext extends Neo4jConfiguration {
    @Override
    public SessionFactory getSessionFactory() {
        return new SessionFactory("com.github.alex_moon.tree.recommendation.entities");
    }

    @Bean
    public Neo4jServer neo4jServer() {
        return new RemoteServer("http://neo4j:fuck@127.0.0.1:7474");
    }

    @Override
    @Bean
    @Scope(value="session", proxyMode=ScopedProxyMode.TARGET_CLASS)
    public Session getSession() throws Exception {
        return super.getSession();
    }
}
