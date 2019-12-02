package javaschool.xml;



import javaschool.Connector;
import lombok.Getter;

import javax.sql.DataSource;


@Getter
public class XmlConnector implements Connector {
    private final String login;
    private final String password;
    private final DataSource dataSource;

    public XmlConnector(String login, String password, DataSource dataSource) {
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


