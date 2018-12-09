/**  
 * ---------------------------------------------------------------------------
 * Copyright (c) 2017, xsjt- All Rights Reserved.
 * Project Name:spring-boot-learn  
 * File Name:DynamicDataSourceRegister.java  
 * Package Name:com.xsjt.dynamicDataSource
 * 
 * Date:2017年11月13日下午7:40:42
 * ---------------------------------------------------------------------------  
*/  
  
package com.xsjt.dynamicDataSource;  
import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.PropertyValues;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.boot.context.properties.bind.Binder;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.support.DefaultConversionService;
import org.springframework.core.env.Environment;
import org.springframework.core.type.AnnotationMetadata;
/**  
 * ClassName:DynamicDataSourceRegister 
 * Date:     2017年11月13日 下午7:40:42
 * @author   
 * @version    
 * @since    JDK 1.8
 */
public class DynamicDataSourceRegister implements ImportBeanDefinitionRegistrar, EnvironmentAware {
    private static final Logger logger = LoggerFactory.getLogger(DynamicDataSourceRegister.class);
    
    private ConversionService conversionService = new DefaultConversionService();
    private PropertyValues dataSourcePropertyValues;
    
    // 如配置文件中未指定数据源类型，使用该默认值
    private static final Object DATASOURCE_TYPE_DEFAULT = "org.apache.tomcat.jdbc.pool.DataSource";
    
//     private static final Object DATASOURCE_TYPE_DEFAULT = "com.zaxxer.hikari.HikariDataSource";
    
    // 数据源
    private DataSource defaultDataSource;
    
    private Map<String, DataSource> customDataSources = new HashMap<String, DataSource>();
    
    public void registerBeanDefinitions( AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {
        Map<Object, Object> targetDataSources = new HashMap<Object, Object>();
        // 将主数据源添加到更多数据源中
        targetDataSources.put("dataSource", defaultDataSource);
        DynamicDataSourceContextHolder.dataSourceIds.add("dataSource");
        // 添加更多数据源
        targetDataSources.putAll(customDataSources);
        for (String key : customDataSources.keySet()) {
            DynamicDataSourceContextHolder.dataSourceIds.add(key);
        }
        
        // 创建DynamicDataSource
        GenericBeanDefinition beanDefinition = new GenericBeanDefinition();
        beanDefinition.setBeanClass(DynamicDataSource.class);
        beanDefinition.setSynthetic(true);
        MutablePropertyValues mpv = beanDefinition.getPropertyValues();
        mpv.addPropertyValue("defaultTargetDataSource", defaultDataSource);
        mpv.addPropertyValue("targetDataSources", targetDataSources);
        registry.registerBeanDefinition("dataSource", beanDefinition); // 注册到Spring容器中
        
        logger.info("Dynamic DataSource Registry");
    }
    
    /**
     * 创建DataSource
     * @param type
     * @param driverClassName
     * @param url
     * @param username
     * @param password
     * @return
     */
    @SuppressWarnings("unchecked")
    public DataSource buildDataSource(Map<String, Object> dsMap) {
        try {
            Object type = dsMap.get("type");
            if (type == null)
                type = DATASOURCE_TYPE_DEFAULT;// 默认DataSource
                
            Class<? extends DataSource> dataSourceType;
            dataSourceType = (Class<? extends DataSource>)Class.forName((String)type);
            
            String driverClassName = dsMap.get("driver-class-name").toString();
            String url = dsMap.get("url").toString();
            String username = dsMap.get("username").toString();
            String password = dsMap.get("password").toString();
            
            DataSourceBuilder factory = DataSourceBuilder.create()
                    .driverClassName(driverClassName)
                    .url(url)
                    .username(username)
                    .password(password)
                    .type(dataSourceType);
            return factory.build();
        }
        catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    /**
     * 加载多数据源配置
     */
    public void setEnvironment(Environment env) {
        initDefaultDataSource(env);
        initCustomDataSources(env);
    }
    
    /**
     * 初始化主数据源
     */
    private void initDefaultDataSource(Environment env) {
        // 读取主数据源
        Map<String, Object> dsMap = new HashMap<String, Object>();
        dsMap.put("type", env.getProperty("spring.datasource.type"));
        dsMap.put("driver-class-name",env.getProperty("spring.datasource.driver-class-name"));
        dsMap.put("url", env.getProperty("spring.datasource.url"));
        dsMap.put("username", env.getProperty("spring.datasource.username"));
        dsMap.put("password", env.getProperty("spring.datasource.password"));
        defaultDataSource = buildDataSource(dsMap); 
        dataBinder(defaultDataSource, env,1);
    }
    
    /**
     * 为DataSource绑定更多数据
     * @param dataSource
     * @param env
     */
    private void dataBinder(DataSource dataSource, Environment env,int type) {
    	
    	Binder binder = Binder.get(env);
    	if(type == 1) {
    		binder.bind("spring.datasource", dataSource.getClass());
    	}else {
    		binder.bind("custom.datasource", dataSource.getClass());
    	}
       /* RelaxedDataBinder dataBinder = new RelaxedDataBinder(dataSource);
        //dataBinder.setValidator(new LocalValidatorFactory().run(this.applicationContext));
        dataBinder.setConversionService(conversionService);
        dataBinder.setIgnoreNestedProperties(false);//false
        dataBinder.setIgnoreInvalidFields(false);//false
        dataBinder.setIgnoreUnknownFields(true);//true
        if (dataSourcePropertyValues == null) {
            Map<String, Object> rpr = new RelaxedPropertyResolver(env, "spring.datasource").getSubProperties(".");
            Map<String, Object> values = new HashMap<String, Object>(rpr);
            // 排除已经设置的属性
            values.remove("type");
            values.remove("driver-class-name");
            values.remove("url");
            values.remove("username");
            values.remove("password");
            dataSourcePropertyValues = new MutablePropertyValues(values);
        }
        dataBinder.bind(dataSourcePropertyValues);*/
    }
    
    /**
     * 初始化更多数据源
     *
     */
    private void initCustomDataSources(Environment env) {
        // 读取配置文件获取更多数据源，也可以通过defaultDataSource读取数据库获取更多数据源
//        RelaxedPropertyResolver propertyResolver = new RelaxedPropertyResolver( env, "custom.datasource.");
        String dsPrefixs = env.getProperty("custom.datasource.names");
        for (String dsPrefix : dsPrefixs.split(",")) {// 多个数据源
        	Map<String, Object> dsMap = new HashMap<String, Object>();
        	dsMap.put("type", env.getProperty("custom.datasource."+dsPrefix + ".type"));
            dsMap.put("driver-class-name",env.getProperty("custom.datasource."+dsPrefix + ".driver-class-name"));
            dsMap.put("url", env.getProperty("custom.datasource."+dsPrefix + ".url"));
            dsMap.put("username", env.getProperty("custom.datasource."+dsPrefix + ".username"));
            dsMap.put("password", env.getProperty("custom.datasource."+dsPrefix + ".password"));
            DataSource ds = buildDataSource(dsMap);
            customDataSources.put(dsPrefix, ds);
            dataBinder(ds, env,0);
        }
    }
}