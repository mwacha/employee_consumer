package tk.mwacha.config;

import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.sns.AmazonSNS;
import com.amazonaws.services.sns.AmazonSNSClient;
import com.amazonaws.services.sns.model.ListTopicsResult;
import com.amazonaws.services.sns.model.Topic;
import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.AmazonSQSClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Slf4j
@Configuration
@RequiredArgsConstructor
public class AwsConfig {

    private final ConfigurableListableBeanFactory beanFactory;

    @Bean
    public AWSCredentialsProvider amazonAWSCredentialsProvider(
            @Value("${aws.secret-key}") String secretKey,
            @Value("${aws.access-key}") String accessKeyId) {
        return new AWSStaticCredentialsProvider(new BasicAWSCredentials(accessKeyId, secretKey));
    }

    @Bean
    @Profile("!development")
    public AmazonSQS sqsClient(AWSCredentialsProvider credentialsProvider, @Value("${aws.region}") String region) {
        return AmazonSQSClient.builder().withCredentials(credentialsProvider).withRegion(region).build();
    }
}