package com.hws.mybatisgenerator.core;


import java.util.ArrayList;
import java.util.List;




import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.api.ShellCallback;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.Context;
import org.mybatis.generator.config.GeneratedKey;
import org.mybatis.generator.config.JDBCConnectionConfiguration;
import org.mybatis.generator.config.JavaClientGeneratorConfiguration;
import org.mybatis.generator.config.JavaModelGeneratorConfiguration;
import org.mybatis.generator.config.ModelType;
import org.mybatis.generator.config.PropertyRegistry;
import org.mybatis.generator.config.SqlMapGeneratorConfiguration;
import org.mybatis.generator.config.TableConfiguration;
import org.mybatis.generator.internal.DefaultShellCallback;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 
 * @author hws
 *
 */
@Component
public class CodeGenerator {
	
	@Autowired
	JDBCPropertiesBean jdbcProperties;
	/**
	 * @param jdbcConnectionConfiguration
	 * @param parentPackagePath
	 * @param tableNames
	 * if this class can be scanned,this method can be used,if not use next method
	 */
	public void generator(String parentPackagePath,String...tableNames){
		
		System.out.println("------------------开始---------------------");
		Context context = new Context(ModelType.FLAT);
		context.setId("hws");
	    context.addProperty(PropertyRegistry.CONTEXT_BEGINNING_DELIMITER, "`");
	    context.addProperty(PropertyRegistry.CONTEXT_ENDING_DELIMITER, "`");
	
	    
	    //设置jdbc连接
	    System.out.println("开始配置连接Mysql数据库");
	    JDBCConnectionConfiguration jdbcConnectionConfiguration = new JDBCConnectionConfiguration();
	    jdbcConnectionConfiguration.setConnectionURL(jdbcProperties.getUrl());
	    jdbcConnectionConfiguration.setDriverClass(jdbcProperties.getDriverClassName());
	    jdbcConnectionConfiguration.setUserId(jdbcProperties.getUsername());
	    jdbcConnectionConfiguration.setPassword(jdbcProperties.getPassword());
	    context.setJdbcConnectionConfiguration(jdbcConnectionConfiguration);
	   
		//配置model
	    System.out.println("开始配置Model");
		JavaModelGeneratorConfiguration modeConfiguration = new JavaModelGeneratorConfiguration();
		modeConfiguration.setTargetPackage(parentPackagePath + PathProperties.MODELPACKAGE_SUFFIX);	
		modeConfiguration.setTargetProject(PathProperties.PROJECT_PATH + PathProperties.JAVA_PATH);
		context.setJavaModelGeneratorConfiguration(modeConfiguration);
		
		//配置mapper接口
		System.out.println("开始配置Mapper接口");
		JavaClientGeneratorConfiguration clientConfiguration = new JavaClientGeneratorConfiguration();
		clientConfiguration.setTargetProject(PathProperties.PROJECT_PATH + PathProperties.JAVA_PATH);
		clientConfiguration.setTargetPackage(parentPackagePath + PathProperties.MAPPERPACKAGE_SUFFIX);
		clientConfiguration.setConfigurationType("XMLMAPPER");
		context.setJavaClientGeneratorConfiguration(clientConfiguration);
		//配置mapper.xml文件
		System.out.println("开始配置Mapper.xml");
		SqlMapGeneratorConfiguration sqlMapConfiguration = new SqlMapGeneratorConfiguration();
		sqlMapConfiguration.setTargetPackage(PathProperties.MAPPER);
		sqlMapConfiguration.setTargetProject(PathProperties.PROJECT_PATH + PathProperties.RESOURCES_PATH);
		context.setSqlMapGeneratorConfiguration(sqlMapConfiguration);
		//配置表
		for(String name:tableNames){
			TableConfiguration tableConfiguration = new TableConfiguration(context);
			tableConfiguration.setTableName(name);
			tableConfiguration.setGeneratedKey(new GeneratedKey("id","Mysql",true,null));
			//去掉生成的example
			tableConfiguration.setCountByExampleStatementEnabled(false);
			tableConfiguration.setDeleteByExampleStatementEnabled(false);
			tableConfiguration.setSelectByExampleStatementEnabled(false);
			tableConfiguration.setUpdateByExampleStatementEnabled(false);
			
			context.addTableConfiguration(tableConfiguration);
		}
		MyBatisGenerator mg =  null;
		Configuration	configuration = null;
		List<String>	warnings = null;
		ShellCallback callBack = null;
		
		configuration = new Configuration();
		callBack = new DefaultShellCallback(true) ;
		warnings = new ArrayList<String>();
		configuration.addContext(context);
		try {
			configuration.validate();
			System.out.println("开始自动构建");
			mg = new MyBatisGenerator(configuration, callBack, warnings);
			mg.generate(null);
			if(mg.getGeneratedJavaFiles().isEmpty()){
				System.out.println("自动构建失败:" + warnings);
			}
			if(mg.getGeneratedXmlFiles().isEmpty()){
				System.out.println("自动构建失败:" + warnings);
			}
		} catch (Exception e) {
			System.out.println("自动构建失败:" + e);
		}
		System.out.println("自动构建成功");
		System.out.println("------------------结束---------------------");
	
	}
	/**
	 * 
	 * @param jdbcConnectionConfiguration 
	 * @param parentPackagePath
	 * @param tableNames
	 * for example:JDBCConnectionConfiguration temp = new JDBCConnectionConfiguration();
	 * 				temp.setConnectionURL(url);
	 * 				temp.setDriverClass(driverName);
	 * 				temp.setUserId(username);
	 * 				temp.setPassword(password);
	 * 
	 */
	public void generator(JDBCConnectionConfiguration jdbcConnectionConfiguration,String parentPackagePath,String...tableNames){
		
		System.out.println("------------------开始---------------------");
		Context context = new Context(ModelType.FLAT);
		context.setId("hws");
	    context.addProperty(PropertyRegistry.CONTEXT_BEGINNING_DELIMITER, "`");
	    context.addProperty(PropertyRegistry.CONTEXT_ENDING_DELIMITER, "`");
	
	    
	    //设置jdbc连接
	    System.out.println("开始配置连接Mysql数据库");
	    /*JDBCConnectionConfiguration jdbcConnectionConfiguration = new JDBCConnectionConfiguration();
	    jdbcConnectionConfiguration.setConnectionURL(url);
	    jdbcConnectionConfiguration.setDriverClass(driverName);
	    jdbcConnectionConfiguration.setUserId(username);
	    jdbcConnectionConfiguration.setPassword(password);*/
	    context.setJdbcConnectionConfiguration(jdbcConnectionConfiguration);
	   
		//配置model
	    System.out.println("开始配置Model");
		JavaModelGeneratorConfiguration modeConfiguration = new JavaModelGeneratorConfiguration();
		modeConfiguration.setTargetPackage(parentPackagePath + PathProperties.MODELPACKAGE_SUFFIX);	
		modeConfiguration.setTargetProject(PathProperties.PROJECT_PATH + PathProperties.JAVA_PATH);
		context.setJavaModelGeneratorConfiguration(modeConfiguration);
		
		//配置mapper接口
		System.out.println("开始配置Mapper接口");
		JavaClientGeneratorConfiguration clientConfiguration = new JavaClientGeneratorConfiguration();
		clientConfiguration.setTargetProject(PathProperties.PROJECT_PATH + PathProperties.JAVA_PATH);
		clientConfiguration.setTargetPackage(parentPackagePath + PathProperties.MAPPERPACKAGE_SUFFIX);
		clientConfiguration.setConfigurationType("XMLMAPPER");
		context.setJavaClientGeneratorConfiguration(clientConfiguration);
		//配置mapper.xml文件
		System.out.println("开始配置Mapper.xml");
		SqlMapGeneratorConfiguration sqlMapConfiguration = new SqlMapGeneratorConfiguration();
		sqlMapConfiguration.setTargetPackage(PathProperties.MAPPER);
		sqlMapConfiguration.setTargetProject(PathProperties.PROJECT_PATH + PathProperties.RESOURCES_PATH);
		context.setSqlMapGeneratorConfiguration(sqlMapConfiguration);
		//配置表
		for(String name:tableNames){
			TableConfiguration tableConfiguration = new TableConfiguration(context);
			tableConfiguration.setTableName(name);
			tableConfiguration.setGeneratedKey(new GeneratedKey("id","Mysql",true,null));
			//去掉生成的example
			tableConfiguration.setCountByExampleStatementEnabled(false);
			tableConfiguration.setDeleteByExampleStatementEnabled(false);
			tableConfiguration.setSelectByExampleStatementEnabled(false);
			tableConfiguration.setUpdateByExampleStatementEnabled(false);
			
			context.addTableConfiguration(tableConfiguration);
		}
		MyBatisGenerator mg =  null;
		Configuration	configuration = null;
		List<String>	warnings = null;
		ShellCallback callBack = null;
		
		configuration = new Configuration();
		callBack = new DefaultShellCallback(true) ;
		warnings = new ArrayList<String>();
		configuration.addContext(context);
		try {
			configuration.validate();
			System.out.println("开始自动构建");
			mg = new MyBatisGenerator(configuration, callBack, warnings);
			mg.generate(null);
			if(mg.getGeneratedJavaFiles().isEmpty()){
				System.out.println("自动构建失败:" + warnings);
			}
			if(mg.getGeneratedXmlFiles().isEmpty()){
				System.out.println("自动构建失败:" + warnings);
			}
		} catch (Exception e) {
			System.out.println("自动构建失败:" + e);
		}
		System.out.println("自动构建成功");
		System.out.println("------------------结束---------------------");
	
	}
}
