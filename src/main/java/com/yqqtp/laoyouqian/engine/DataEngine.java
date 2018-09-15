package com.yqqtp.laoyouqian.engine;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.yqqtp.laoyouqian.entity.RtConstant;
import com.yqqtp.laoyouqian.utils.MongoDBClient;

@Service
public class DataEngine {
	public final static String DEFAULT_DB_NAME = RtConstant.RED_TORCH_DB_NAME+"_"+BaseConfig.rtConfig.getString("rt.client.id");

	private static final Logger log = LoggerFactory.getLogger(DataEngine.class);

	private MongoDBClient mdDBClient;//行情数据库客户端
	private MongoDBClient defaultDBClient;//默认数据库客户端

	public DataEngine() throws Exception {

		String mdMongoHost = BaseConfig.rtConfig.getString("mongodb.instance.md.host");
		int mdMongoPort = BaseConfig.rtConfig.getInt("mongodb.instance.md.port");
		String mdMongoUsername = BaseConfig.rtConfig.getString("mongodb.instance.md.username");
		String mdMongoPassword = BaseConfig.rtConfig.getString("mongodb.instance.md.password");
		String mdMongoAuthDB = BaseConfig.rtConfig.getString("mongodb.instance.md.authdb");
		try {
			mdDBClient = new MongoDBClient(mdMongoHost, mdMongoPort, mdMongoUsername, mdMongoPassword, mdMongoAuthDB);
			log.info("行情数据库连接成功");
		} catch (Exception e) {
			throw new Exception("行情MongoDB数据库连接失败", e);
		}

	}

	public MongoDBClient getMdDBClient() {
		return mdDBClient;
	}

	public MongoDBClient getDefaultDBClient() {
		return defaultDBClient;
	}

}
