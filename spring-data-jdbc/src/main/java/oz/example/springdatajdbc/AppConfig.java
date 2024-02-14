package oz.example.springdatajdbc;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jdbc.repository.config.AbstractJdbcConfiguration;
import org.springframework.data.jdbc.repository.config.EnableJdbcRepositories;
import org.springframework.data.relational.core.dialect.Dialect;
import org.springframework.data.relational.core.dialect.MySqlDialect;
import org.springframework.data.relational.core.mapping.NamingStrategy;
import org.springframework.data.relational.core.mapping.RelationalPersistentEntity;
import org.springframework.data.relational.core.mapping.RelationalPersistentProperty;
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
@ComponentScan(basePackages = {"oz.example.springdatajdbc.database", "oz.example.springdatajdbc.database.crud"})
@EnableJdbcRepositories(basePackages = {"oz.example.springdatajdbc.database"})
public class AppConfig extends AbstractJdbcConfiguration implements ApplicationContextAware {

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        super.setApplicationContext(applicationContext);
    }


    @Bean
    public NamingStrategy namingStrategy() {

        return new NamingStrategy() {

            @Override
            public String getColumnName(RelationalPersistentProperty property) {
                System.out.println(">getColumnName(RelationalPersistentProperty) " + property.getName() + " " + property.getActualType().getName() + " " + property.getActualType().isPrimitive());
                Class<?> type = property.getActualType();
                String prefix = type.isPrimitive() && "boolean".equals(type.getName()) ? "is_" : "";
                return prefix + NamingStrategy.super.getColumnName(property);
            }

            @Override
            public String getReverseColumnName(RelationalPersistentProperty property) {
                System.out.println("getReverseColumnName(RelationalPersistentProperty " + property);
                return NamingStrategy.super.getReverseColumnName(property);
            }

            /*
             * The table of the referenced entity is expected to have an additional column named the same
             * as the table of the referencing entity. You can change this name by implementing
             * NamingStrategy.getReverseColumnName(PersistentPropertyPathExtension path).
             */
            @Override
            public String getReverseColumnName(RelationalPersistentEntity<?> owner) {
                System.out.println("getReverseColumnName(RelationalPersistentEntity owner: name=" + owner.getName() + ", idColumn= " + owner.getIdColumn() + ", tableName=" + owner.getTableName());
                // use column 'dept_no' instead of 'dept' in the 'emp' table
                return NamingStrategy.super.getReverseColumnName(owner) + "_no";
            }

            @Override
            public String getKeyColumn(RelationalPersistentProperty property) {
                return NamingStrategy.super.getKeyColumn(property);
            }
        };
    }

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

}