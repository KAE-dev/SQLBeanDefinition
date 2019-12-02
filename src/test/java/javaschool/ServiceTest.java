package javaschool;

import javaschool.annotation.AnnotationConnector;
import javaschool.annotation.AnnotationDataSource;
import javaschool.groovy.GroovyConnector;
import javaschool.java.JavaConnector;
import javaschool.kotlin.KotlinConnector;
import javaschool.programmatic.ProgrammaticConnector;
import javaschool.xml.XmlConnector;
import lombok.val;
import org.junit.jupiter.api.Test;
import org.sqlite.SQLiteDataSource;

import static org.junit.Assert.assertEquals;

class ServiceTest {

    @Test
    void xmlConnector() {
        val service = new Service();
        val context = service.xmlConnector();
        assertEquals("Connector{login='admin', password='admin', dataSource=" + context.getBean("datasource") + "}",
                context.getBean("connector").toString());
        XmlConnector connector = (XmlConnector) context.getBean("connector");
        assertEquals("jdbc:sqlite:db.sqlite", ((SQLiteDataSource) connector.getDataSource()).getUrl());
    }

    @Test
    void programmaticConnector() {
        val service = new Service();
        val context = service.programmaticConnector();
        ProgrammaticConnector connector = (ProgrammaticConnector) context.getBean("connector");

        assertEquals("admin", connector.getLogin());
        assertEquals("admin", connector.getPassword());
        assertEquals("jdbc:sqlite:db.sqlite", ((SQLiteDataSource) connector.getDataSource()).getUrl());
    }

    @Test
    void kotlinConnector() {
        val service = new Service();
        val context = service.kotlinConnector();
        KotlinConnector connector = (KotlinConnector) context.getBean("connector");
        assertEquals("admin", connector.getLogin());
        assertEquals("admin", connector.getPassword());
        assertEquals("jdbc:sqlite:db.sqlite", ((SQLiteDataSource) connector.getDataSource()).getUrl());
    }

    @Test
    void javaConnector() {
        val service = new Service();
        val context = service.javaConnector();
        JavaConnector connector = (JavaConnector) context.getBean("connector");
        assertEquals("admin", connector.getLogin());
        assertEquals("admin", connector.getPassword());
        assertEquals("jdbc:sqlite:db.sqlite", ((SQLiteDataSource) connector.getDataSource()).getUrl());
    }

    @Test
    void groovyConnector() {
        val service = new Service();
        val context = service.groovyConnector();
        GroovyConnector connector = (GroovyConnector) context.getBean("connector");
        assertEquals("admin", connector.getLogin());
        assertEquals("admin", connector.getPassword());
        assertEquals("jdbc:sqlite:db.sqlite", ((SQLiteDataSource) connector.getDataSource()).getUrl());
    }

    @Test
    void annotationConnector() {
        val service = new Service();
        val context = service.annotationConnector();
        AnnotationConnector connector = (AnnotationConnector) context.getBean("connector");
        AnnotationDataSource sqliteDataSource = (AnnotationDataSource) context.getBean("datasource");
        assertEquals("admin", connector.getLogin());
        assertEquals("admin", connector.getPassword());
        assertEquals("jdbc:sqlite:db.sqlite", sqliteDataSource.getUrl());
    }
}