package delivery_api.v1.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

@Configuration
public class DataSourceConfig {

    @Bean
    @ConfigurationProperties("spring.datasource.primary")
    public DataSource primaryDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean
    @ConfigurationProperties("spring.datasource.replica-1")
    public DataSource replica1DataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean
    @ConfigurationProperties("spring.datasource.replica-2")
    public DataSource replica2DataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean
    @Primary
    public DataSource routingDataSource(
            @Qualifier("primaryDataSource")  DataSource primary,
            @Qualifier("replica1DataSource") DataSource replica1,
            @Qualifier("replica2DataSource") DataSource replica2
    ) {
        List<DataSource> replicas = List.of(replica1, replica2);
        AtomicInteger counter = new AtomicInteger(0);

        return new AbstractRoutingDataSource() {
            @Override
            protected Object determineCurrentLookupKey() {
                if (TransactionSynchronizationManager.isCurrentTransactionReadOnly()) {
                    int idx = counter.getAndIncrement() % replicas.size();
                    return "replica-" + idx;
                }
                return "primary";
            }

            {
                Map<Object, Object> map = new HashMap<>();
                map.put("primary",   primary);
                map.put("replica-0", replica1);
                map.put("replica-1", replica2);
                setTargetDataSources(map);
                setDefaultTargetDataSource(primary);
            }
        };
    }
}
