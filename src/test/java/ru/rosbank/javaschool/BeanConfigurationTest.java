package ru.rosbank.javaschool;

import ru.rosbank.javaschool.annotation.AnnotationConnector;
import ru.rosbank.javaschool.annotation.AnnotationDataSource;
import ru.rosbank.javaschool.groovy.GroovyConnector;
import ru.rosbank.javaschool.java.JavaConnector;
import ru.rosbank.javaschool.kotlin.KotlinConnector;
import ru.rosbank.javaschool.kotlin.SQLKotlinConnector;
import ru.rosbank.javaschool.programmatic.ProgrammaticConnector;
import ru.rosbank.javaschool.xml.XmlConnector;
import lombok.val;
import org.junit.jupiter.api.Test;
import org.sqlite.SQLiteDataSource;

import static org.junit.jupiter.api.Assertions.assertEquals;


class BeanConfigurationTest {

    @Test
    void xmlConnectorTest() {
        val service = new BeanConfiguration();
        val context = service.xmlConnector();
        assertEquals("Connector{login='admin', password='admin', dataSource=" + context.getBean("datasource") + "}",
                context.getBean("connector").toString());
        XmlConnector connector = (XmlConnector) context.getBean("connector");
        assertEquals("admin", connector.getLogin());
        assertEquals("admin", connector.getPassword());
        assertEquals("jdbc:sqlite:db.sqlite", ((SQLiteDataSource) connector.getDataSource()).getUrl());
    }

    @Test
    void programmaticConnector() {
        val service = new BeanConfiguration();
        val context = service.programmaticConnector();
        ProgrammaticConnector connector = (ProgrammaticConnector) context.getBean("connector");

        assertEquals("admin", connector.getLogin());
        assertEquals("admin", connector.getPassword());
        assertEquals("jdbc:sqlite:db.sqlite", ((SQLiteDataSource) connector.getDataSource()).getUrl());
    }

    @Test
    void kotlinConnector() {
        val service = new BeanConfiguration();
        val context = service.kotlinConnector();
        KotlinConnector connector = (KotlinConnector) context.getBean("connector");
        SQLKotlinConnector sqlConnector = (SQLKotlinConnector) context.getBean("datasource");
        assertEquals("admin", connector.getLogin());
        assertEquals("admin", connector.getPassword());
        assertEquals("jdbc:sqlite:db.sqlite", sqlConnector.getUrl());
    }

    @Test
    void javaConnector() {
        val service = new BeanConfiguration();
        val context = service.javaConnector();
        JavaConnector connector = (JavaConnector) context.getBean("connector");
        assertEquals("admin", connector.getLogin());
        assertEquals("admin", connector.getPassword());
        assertEquals("jdbc:sqlite:db.sqlite", ((SQLiteDataSource) connector.getDataSource()).getUrl());
    }

    @Test
    void groovyConnector() {
        val service = new BeanConfiguration();
        val context = service.groovyConnector();
        GroovyConnector connector = (GroovyConnector) context.getBean("connector");
        assertEquals("admin", connector.getLogin());
        assertEquals("admin", connector.getPassword());
        assertEquals("jdbc:sqlite:db.sqlite", ((SQLiteDataSource) connector.getDataSource()).getUrl());
    }

    @Test
    void annotationConnector() {
        val service = new BeanConfiguration();
        val context = service.annotationConnector();
        AnnotationConnector connector = (AnnotationConnector) context.getBean("connector");
        AnnotationDataSource sqliteDataSource = (AnnotationDataSource) context.getBean("datasource");
        assertEquals("admin", connector.getLogin());
        assertEquals("admin", connector.getPassword());
        assertEquals("jdbc:sqlite:db.sqlite", sqliteDataSource.getUrl());
    }
}