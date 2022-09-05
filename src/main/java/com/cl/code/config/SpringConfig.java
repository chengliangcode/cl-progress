package com.cl.code.config;

import org.apache.ibatis.mapping.DatabaseIdProvider;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ResourceLoader;
import tk.mybatis.mapper.autoconfigure.ConfigurationCustomizer;
import tk.mybatis.mapper.autoconfigure.MapperAutoConfiguration;
import tk.mybatis.mapper.autoconfigure.MybatisProperties;

import javax.sql.DataSource;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author chengliang
 * @date 2022/9/5 16:47
 */

@Configuration
public class SpringConfig {

    private final MapperAutoConfiguration mapperAutoConfiguration;

    public SpringConfig(MybatisProperties properties, ObjectProvider<Interceptor[]> interceptorsProvider, ResourceLoader resourceLoader, ObjectProvider<DatabaseIdProvider> databaseIdProvider, ObjectProvider<List<ConfigurationCustomizer>> configurationCustomizersProvider) {
        addMapperLocations(properties);
        this.mapperAutoConfiguration = new MapperAutoConfiguration(properties, interceptorsProvider, resourceLoader, databaseIdProvider, configurationCustomizersProvider);
    }

    @Bean
    @ConditionalOnMissingBean
    public SqlSessionFactory sqlSessionFactory(DataSource dataSource) throws Exception {
        return mapperAutoConfiguration.sqlSessionFactory(dataSource);
    }

    public void addMapperLocations(MybatisProperties properties) {
        String[] mapperLocations = properties.getMapperLocations();
        if (mapperLocations != null && mapperLocations.length != 0) {
            Set<String> set = Arrays.stream(mapperLocations).collect(Collectors.toSet());
            set.add("classpath:mapper/*.xml");
            mapperLocations = set.toArray(new String[0]);
        } else {
            mapperLocations = new String[]{"classpath:mapper/*.xml"};
        }
        properties.setMapperLocations(mapperLocations);
    }


}
