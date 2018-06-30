package pe.edu.upc.RsAuth.configs;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

/**
 * Created by Paolo Ortega on 25/06/2018.
 */
@Configuration
@MapperScan("pe.edu.upc.RsAuth.mappers")
public class ConfigurationDb {

    @Bean
    @ConfigurationProperties(prefix = "datasource")
    public DataSource authDataSource() {
        return DataSourceBuilder.create().build();
    }
}
