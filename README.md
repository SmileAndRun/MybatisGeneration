# MybatisGeneration
## 代码自动构建源码包，自动生成model、mapper接口、xml文件  
### 一：将本源码包clone到本地，通过mvn clean install装载到本地maven仓库，此时只需要通过maven依赖即可。

    <dependency>
	   	<groupId>com.hws</groupId>
		  <artifactId>MybatisGenerator</artifactId>
		  <version>0.0.1-SNAPSHOT</version>
	  </dependency>
 ### 二：依赖我的个人maven库，详情请点击 [这里](https://github.com/ForeverLTP/PersonalDependencyLibrary)
 ### 三：项目中核心类为CodeGenerator
 #### &nbsp;&nbsp;&nbsp;第一个方法是在核心类能被Spring扫描到的时候才能使用，才疏学浅，无法做到自动扫描核心类，渴望赐教，QQ：912176173
 #### &nbsp;&nbsp;&nbsp;手动添加扫描，如springboot项目，在启动类上添加@SpringBootApplication(scanBasePackages="com.hws.mybatisgenerator.core")就可以扫描到核心类了。
 #### &nbsp;&nbsp;&nbsp;第二个方法直接调用，参数详情看源码。
