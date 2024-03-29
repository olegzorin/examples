package oz.example.springjdbc;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

@Configuration("MyConfig")
@ComponentScan("oz.example.springjdbc.database")
public class AppConfig {

    private static Properties loadProperties() {
        Properties properties = new Properties();
        try (FileReader reader = new FileReader(System.getenv().get("PPC_HOME") + "/config/properties/db.properties")) {
            properties.load(reader);
        } catch (IOException e) {
            System.err.println("Failed to load db.properties: " + e.getMessage());
            System.exit(1);
        }
        return properties;
    }

    @Bean(name="appDataSource")
    public DataSource mysqlDataSource() {
        Properties properties = loadProperties();
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(properties.getProperty("jdbc.driver"));
        dataSource.setUsername(properties.getProperty("jdbc.user"));
        dataSource.setPassword(properties.getProperty("jdbc.password"));
        dataSource.setUrl("jdbc:mysql://127.0.0.1:3306/examples");
        return dataSource;
    }

    @Bean(name = "appJdbcTemplate")
    public JdbcTemplate applicationDataConnection(){
        return new JdbcTemplate(mysqlDataSource());
    }
}
