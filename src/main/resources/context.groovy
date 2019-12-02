import javaschool.groovy.GroovyConnector
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer
import org.sqlite.SQLiteDataSource

beans {
    propertyPlaceholderConfigurer PropertySourcesPlaceholderConfigurer, {
        location = 'classpath:db.properties'
    }

    sqLiteDataSource SQLiteDataSource, {
        url = 'jdbc:sqlite:db.sqlite'
    }

    connector GroovyConnector, '${login}', '${password}', ref(sqLiteDataSource)
}
