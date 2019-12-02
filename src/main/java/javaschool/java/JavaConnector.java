package javaschool.java;


import javaschool.Connector;
import lombok.Getter;

import javax.sql.DataSource;

@Getter
public class JavaConnector implements Connector {
  private final String login;
  private final String password;
  private final DataSource dataSource;

  public JavaConnector(String login, String password, DataSource dataSource) {
    this.login = login;
    this.password = password;
    this.dataSource = dataSource;
  }

  @Override
  public String toString() {
    return "Connector{" +
            "login='" + login + '\'' +
            ", password='" + password + '\'' +
            ", dataSource=" + dataSource +
            '}';
  }
}
