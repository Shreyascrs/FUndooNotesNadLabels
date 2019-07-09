package com.bridgeit.fundoo.config;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import com.bridgeit.fundoo.responce.Responce;
import com.bridgeit.fundoo.utility.TokenUtility;

@Configuration
public class ApplicationConfig {
	@Bean
	public ModelMapper getModelMapper() {
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		return modelMapper;
	}
	
	@Bean
	public TokenUtility getTokenUtility()
	{
		return new TokenUtility();
	}
	@Bean
	public Responce get()
	{
		return new Responce();
	}
	@Bean
	public RestTemplate rest()
	{
		return new RestTemplate();
	}
}
