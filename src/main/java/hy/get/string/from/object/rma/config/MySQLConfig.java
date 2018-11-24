package hy.get.string.from.object.rma.config;

import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class MySQLConfig {

	@Value("${mysql.host}")
	private String host;

	@Value("${mysql.port}")
	private Integer port;

	@Value("${mysql.database}")
	private String database;

	@Value("${mysql.username}")
	private String username;

	@Value("${mysql.password}")
	private String password;

	@Bean
	@Primary
	public DataSource mySqlDriver() {
		return DataSourceBuilder.create()
			.url("jdbc:mysql://" + host + ":" + port + "/" + database)
			.username(username)
			.password(password)
			.build();
	}

}
