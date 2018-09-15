package com.yqqtp.laoyouqian.engine;

import java.net.MalformedURLException;
import java.net.URL;

import org.apache.commons.configuration2.CompositeConfiguration;
import org.apache.commons.configuration2.PropertiesConfiguration;
import org.apache.commons.configuration2.builder.FileBasedConfigurationBuilder;
import org.apache.commons.configuration2.builder.fluent.Parameters;
import org.apache.commons.configuration2.builder.fluent.PropertiesBuilderParameters;
import org.apache.commons.configuration2.convert.DefaultListDelimiterHandler;
import org.apache.commons.configuration2.ex.ConfigurationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class BaseConfig {
	private static Logger log = LoggerFactory.getLogger(BaseConfig.class);
	public static CompositeConfiguration rtConfig;
	static {
		rtConfig = new CompositeConfiguration();

		try {
			FileBasedConfigurationBuilder<PropertiesConfiguration> builder = new FileBasedConfigurationBuilder<PropertiesConfiguration>(
					PropertiesConfiguration.class)
							.configure(new Parameters().properties().setFileName("RtConfig.properties")
									.setThrowExceptionOnMissing(true)
									.setListDelimiterHandler(new DefaultListDelimiterHandler(';'))
									.setIncludesAllowed(false));
			PropertiesConfiguration propertiesConfiguration = builder.getConfiguration();
			rtConfig.addConfiguration(propertiesConfiguration);
			log.info("默认配置文件加载成功");
		} catch (ConfigurationException e) {
			log.error("配置文件RtConfig.properties加载失败", e);
			throw new Error("配置文件RtConfig.properties加载失败");
		}
		
	}

	static boolean tryConfig(PropertiesBuilderParameters params, String configUrlStr) {
		try {
			URL configFileUrl = new URL(configUrlStr);
			FileBasedConfigurationBuilder<PropertiesConfiguration> builder = new FileBasedConfigurationBuilder<PropertiesConfiguration>(
					PropertiesConfiguration.class).configure(params.setURL(configFileUrl));
			PropertiesConfiguration propertiesConfiguration = builder.getConfiguration();
			rtConfig.addConfiguration(propertiesConfiguration);
			return true;
		} catch (ConfigurationException e) {
			log.debug("配置文件RtConfig.properties加载失败", e);
			return false;
		} catch (MalformedURLException e) {
			log.debug("配置文件RtConfig.properties加载失败", e);
			return false;
		}
	}
}
