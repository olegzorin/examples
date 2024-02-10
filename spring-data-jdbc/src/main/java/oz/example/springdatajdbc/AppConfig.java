package oz.example.springdatajdbc;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jdbc.repository.config.AbstractJdbcConfiguration;
import org.springframework.data.jdbc.repository.config.DialectResolver;
import org.springframework.data.jdbc.repository.config.EnableJdbcRepositories;
import org.springframework.data.relational.core.dialect.Dialect;
import org.springframework.data.relational.core.dialect.MySqlDialect;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.transaction.TransactionManager;

import javax.sql.DataSource;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

@Configuration("MyConfig")
//@EntityScan(basePackages = {"oz.example.springdatajdbc.entity"})
@ComponentScan(basePackages = {"oz.example.springdatajdbc.database","oz.example.springdatajdbc.database.crud"})
@EnableJdbcRepositories(basePackages = {"oz.example.springdatajdbc.database"})
public class AppConfig extends AbstractJdbcConfiguration {

    @Bean
    @Override
    public Dialect jdbcDialect(NamedParameterJdbcOperations operations) {
        return MySqlDialect.INSTANCE;
    }


    private static Properties loadProperties() {
        Properties properties = new Properties();
        try (FileReader reader = new FileReader(System.getenv().get("PPC_HOME") + "/config/properties/db.properties.local")) {
            properties.load(reader);
        } catch (IOException e) {
            System.err.println("Failed to load db.properties: " + e.getMessage());
            System.exit(1);
        }
        return properties;
    }

    @Bean(name = "appDataSource")
    public DataSource mysqlDataSource() {
        Properties properties = loadProperties();
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        System.out.println("jdbc.driver="+properties.getProperty("jdbc.driver"));
        dataSource.setDriverClassName(properties.getProperty("jdbc.driver"));
        dataSource.setUsername(properties.getProperty("jdbc.user"));
        dataSource.setPassword(properties.getProperty("jdbc.password"));
        dataSource.setUrl("jdbc:mysql://127.0.0.1:3306/examples");
        return dataSource;
    }

    @Bean
    NamedParameterJdbcOperations namedParameterJdbcOperations(DataSource dataSource) {
        return new NamedParameterJdbcTemplate(dataSource);
    }

    @Bean
    TransactionManager transactionManager(DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

//    @Bean
//    public JdbcMappingContext mappingContext() {
//        return new JdbcMappingContext();
//    }
//    @Bean
//    public Dialect dialect() {
//        return MySqlDialect.INSTANCE;
//    }

//    @Bean
//    public JdbcConverter jdbcConverter() {
//return new BasicJdbcConverter(mappingContext());
//    }
//    @Bean
//    @Override
//    public JdbcMappingContext jdbcMappingContext(Optional<NamingStrategy> namingStrategy, JdbcCustomConversions customConversions) {
//
//        JdbcMappingContext mappingContext = super.jdbcMappingContext(namingStrategy, customConversions);
//        mappingContext.setForceQuote(false);
//
//        return mappingContext;
//    }

}