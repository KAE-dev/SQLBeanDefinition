package ru.rosbank.javaschool.kotlin
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer
import org.springframework.context.support.beans
import org.springframework.core.io.ClassPathResource
import org.sqlite.SQLiteDataSource


val beans = beans {
    bean {
        PropertySourcesPlaceholderConfigurer().apply {
            setLocation(ClassPathResource("db.properties"))
        }
    }
    bean<KotlinConnector>("connector")
    bean("datasource") {
        SQLiteDataSource().apply {
            url = "jdbc:sqlite:db.sqlite"
        }
    }
}