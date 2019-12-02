package ru.rosbank.javaschool.annotation;

import ru.rosbank.javaschool.Connector;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

@Getter
@Component("connector")
public class AnnotationConnector implements Connector {
  private final String login;
  private final String password;
  private final DataSource dataSource;


  public AnnotationConnector(@Value("${login}") String login, @Value("${password}") String password, DataSource dataSource) {
    this.login = login;
    this.password = password;
    this.dataSource = dataSource;
  }

  @Override
  public String toString() {
    return "Connector{"
            +
            "login='" + login + '\''
            +
            ", password='" + password + '\''
            +
            ", dataSource=" + dataSource
            +
            '}';
  }
}
