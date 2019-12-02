package ru.rosbank.javaschool;

import ru.rosbank.javaschool.java.JavaConfiguration;
import ru.rosbank.javaschool.kotlin.BeansKt;
import ru.rosbank.javaschool.programmatic.ProgrammaticConnector;
import lombok.val;
import org.springframework.beans.factory.annotation.QualifierAnnotationAutowireCandidateResolver;
import org.springframework.beans.factory.groovy.GroovyBeanDefinitionReader;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.ClassPathResource;
import org.sqlite.SQLiteDataSource;

public class Service {


    public GenericApplicationContext xmlConnector(){
        val context = new GenericApplicationContext();
        new XmlBeanDefinitionReader(context).loadBeanDefinitions("context.xml");
        context.refresh();
        return context;
    }

    public GenericApplicationContext programmaticConnector(){
        val db = new GenericApplicationContext();
        db.registerBean("datasource", SQLiteDataSource.class, () -> {
            val dataSource = new SQLiteDataSource();
            dataSource.setUrl("jdbc:sqlite:db.sqlite");
            return dataSource;
        });
        db.refresh();
        val context = new GenericApplicationContext();
        context.registerBean(PropertySourcesPlaceholderConfigurer.class, () -> {
            val configurer = new PropertySourcesPlaceholderConfigurer();
            configurer.setLocation(new ClassPathResource("db.properties"));
            return configurer;
        });
        context.registerBean("connector",
                ProgrammaticConnector.class,
                "${login}", "${password}", db.getBean("datasource"));
        context.refresh();
        return context;
    }

    public GenericApplicationContext kotlinConnector(){
        val factory = new DefaultListableBeanFactory();
        factory.setAutowireCandidateResolver(new QualifierAnnotationAutowireCandidateResolver());
        val context = new GenericApplicationContext(factory);
        BeansKt.getBeans().initialize(context);
        context.refresh();
        return context;
    }
    public AnnotationConfigApplicationContext javaConnector(){
        val context = new AnnotationConfigApplicationContext(JavaConfiguration.class);
        return context;
    }
    public GenericApplicationContext groovyConnector(){
        val context = new GenericApplicationContext();
        val reader = new GroovyBeanDefinitionReader(context);
        reader.loadBeanDefinitions("context.groovy");
        context.refresh();
        return context;
    }
    public AnnotationConfigApplicationContext annotationConnector(){
        val context = new AnnotationConfigApplicationContext("ru.rosbank.javaschool.annotation");
        return context;
    }
}
