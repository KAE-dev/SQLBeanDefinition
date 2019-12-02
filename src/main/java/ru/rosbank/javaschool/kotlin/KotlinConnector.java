package ru.rosbank.javaschool.kotlin;

import ru.rosbank.javaschool.Connector;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;

import javax.sql.DataSource;

@Getter
public class KotlinConnector implements Connector {
  private final String login;
  private final String password;
  private final DataSource dataSource;

  public KotlinConnector(@Value("${login}") String login, @Value("${password}") String password, DataSource dataSource) {
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
