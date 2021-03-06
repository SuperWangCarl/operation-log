## 基础日志框架

### 功能:

```
1.记录用户的访问日志
2.每次启动工程自动维护字典表(对字典表新增或更新)
3.对响应的访问日志分层次记录(1:数据操作日志 2:异常日志 3:用户登录日志 4:用户访问日志)  
4.对响应的访问路径可配置是否需要记录
5.对响应的访问路径可配置记录的页面位置
6.对响应访问日志的请求参数和响应参数记录
7.对响应访问日志记录操作的用户
```

### 使用

```
1.该工程使用的mysql及mybatis-plus进行日志的操作
2.引入该工程,在mysql中执行sql文件,新增两个日志相关的表
3.配置mybatis-plus对应的链接
4.在需要记录日志的Controller方法上添加@BaseLog注解及可进行日志记录
```

### 扩展

```
1.对请求路径的中文描述,默认使用@ApiOperation注解中的value值,也可以自己进行配置说明(配置vlaue=".."即可)
2.对不需要记录日志的,在@BaseLog中配置isRecord=false即可
3.使用type属性可以对日志的类型进行记录,如果不配置,默认按照请求方法的类型,进行存储
4.使用webLocal属性,可以配置刚路径对应的前台位置,默认为空
5.该@BaseLog注解可以注解到类上,当注解到类上上会和方法上的注解进行配合使用(如UserController类上注解了为`用户行为`,方法上注解了为`用户添加`,则存入数据库的中文描述为 `用户行为-用户添加`) 
6.在ParamLoggerConfig类中也记录了相应的文本记录
```

### 待续

....