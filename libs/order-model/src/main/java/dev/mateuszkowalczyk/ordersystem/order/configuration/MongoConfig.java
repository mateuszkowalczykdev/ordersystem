package dev.mateuszkowalczyk.ordersystem.order.configuration;

import jakarta.validation.Validator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.EnableMongoAuditing;
import org.springframework.data.mongodb.core.mapping.event.ValidatingEntityCallback;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

@Configuration
@EnableMongoAuditing
public class MongoConfig {

    @Bean
    public LocalValidatorFactoryBean validatorFactory() {
        return new LocalValidatorFactoryBean();
    }

    @Bean
    public ValidatingEntityCallback validatingEntityCallback(Validator validator) {
        return new ValidatingEntityCallback(validator);
    }

}

