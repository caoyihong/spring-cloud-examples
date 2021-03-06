package com.peterwanghao.spring.cloud.archaius.dynamodb.config;

import org.socialsignin.spring.data.dynamodb.repository.config.EnableDynamoDBRepositories;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder.EndpointConfiguration;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;

/**   
 * @ClassName:  DynamoDbConfiguration
 * @Description:TODO(这里用一句话描述这个类的作用)
 * @author: wanghao
 * @date:   2018年11月20日 下午4:52:46
 * @version V1.0
 * 
 */
@Configuration
@EnableDynamoDBRepositories(basePackages = "com.peterwanghao.spring.cloud.archaius.dynamodb.entity")
public class DynamoDbConfiguration {

	@Value("${amazon.dynamodb.endpoint}")
	private String amazonDynamoDBEndpoint;

	@Value("${aws.accessKeyId}")
	private String amazonDynamoDBAccessKeyId;

	@Value("${aws.secretKey}")
	private String amazonDynamoDBSecretKey;

	@Bean
	public AmazonDynamoDB amazonDynamoDB() {
		AmazonDynamoDB amazonDynamoDB = AmazonDynamoDBClientBuilder.standard().withCredentials(amazonAWSCredentials())
				.withEndpointConfiguration(setupEndpointConfiguration()).build();

		return amazonDynamoDB;
	}

	private AWSCredentialsProvider amazonAWSCredentials() {
		return new AWSStaticCredentialsProvider(
				new BasicAWSCredentials(amazonDynamoDBAccessKeyId, amazonDynamoDBSecretKey));
	}

	private EndpointConfiguration setupEndpointConfiguration() {
		return new EndpointConfiguration(amazonDynamoDBEndpoint, Regions.DEFAULT_REGION.getName());
	}
}
