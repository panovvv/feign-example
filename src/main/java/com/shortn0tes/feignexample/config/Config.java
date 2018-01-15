package com.shortn0tes.feignexample.config;

import com.shortn0tes.feignexample.feign.UserClient;
import feign.Feign;
import feign.Logger;
import feign.jackson.JacksonDecoder;
import feign.jackson.JacksonEncoder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.netflix.feign.support.SpringMvcContract;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created on 1/15/2018.
 */
@Configuration
public class Config {

	@Value("${feign.userclient.url}")
	String userClientUrl;

	@Bean
	UserClient userClient() {
		return Feign.builder()
			.contract(new SpringMvcContract())
			.encoder(new JacksonEncoder())
			.decoder(new JacksonDecoder())
			.logger(new Logger.ErrorLogger())
			.logLevel(Logger.Level.FULL)
			.target(UserClient.class, userClientUrl);
	}
}
