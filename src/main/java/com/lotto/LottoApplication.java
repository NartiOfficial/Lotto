package com.lotto;

import com.lotto.domain.numbergenerator.WinningNumbersGeneratorFacadeConfigurationProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableConfigurationProperties({WinningNumbersGeneratorFacadeConfigurationProperties.class})
@EnableScheduling
@EnableMongoRepositories
public class LottoApplication {

    public static void main(String[] args) {
        SpringApplication.run(LottoApplication.class, args);
    }
}