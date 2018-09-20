## MDCollector行情采集器
### 项目介绍:
	MDColletor采用上海期货技术提供的CTPAPI接口进行行情获取
	目前实现实时Tick,1分钟Bar,5分钟Bar,15分钟Bar,60分钟bar,1小时Bar,,1周Bar的储存.
	com.yqqtp.laoyouqian.gateway.ctp.DataCache类是该项目的所有数据缓存的管理中心,
	可以通过外部引用,如HTTP,DUBBO等将DataChche中的数据取出.
	项目启动时自动缓存MongoDB的历史数据以及上次未完成的Bar到DataCache中;
	启动后每当有Tick推送过来时,数据首先会直接缓存到DataCache中(保证DataCache数据是最新的)
	再将Tick推送给线程落地到MongoDB.

### 项目启动:
	入口:com.yqqtp.laoyouqian.starter.Starter
	行情订阅:可在Main中直接填写在数组中,也可自己写个配置文件或读取数据库来进行订阅初始化
	注:行情合约代码必须为全部小写
### 配置文件:
	RtConfig.properties 配置MongoDB
	CTPConfig.properties 配置CTP相关信息 
### 技术选型:
	Spring
	MongoDB
### 交流QQ群:798651675
