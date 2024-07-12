package com.web.ddajait.config;
import org.jasypt.encryption.StringEncryptor;
import org.jasypt.encryption.pbe.PooledPBEStringEncryptor;
import org.jasypt.encryption.pbe.config.SimpleStringPBEConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JasyptConfig {

    @Value("${jasypt.encryptor.password}")
    private String key;
    @Value("${jasypt.encryptor.algorithm}")
    private String algorithm;
    @Value("${jasypt.encryptor.pool-size}")
    private String poolSize;
    @Value("${jasypt.encryptor.string-output-type}")
    private String outputType;
    @Value("${jasypt.encryptor.key-obtention-iterations}")
    private String hashing;

    @Bean(name = "jasyptStringEncryptor")
    public StringEncryptor stringEncryptor() {
        PooledPBEStringEncryptor encryptor = new PooledPBEStringEncryptor();
        SimpleStringPBEConfig config = new SimpleStringPBEConfig();
        config.setPassword(key); // 암호화 키
        config.setAlgorithm(algorithm); // 암호화 알고리즘
        // config.setIvGenerator(new RandomIvGenerator()); // PBE-AES 기반 알고리즘의 경우 IV 생성 필수
        config.setKeyObtentionIterations(hashing); // 반복할 해싱 회수
        config.setPoolSize(poolSize); // 인스턴스 pool
        config.setSaltGeneratorClassName("org.jasypt.salt.RandomSaltGenerator"); // salt 생성 클래스
        config.setStringOutputType(outputType); // 인코딩
        encryptor.setConfig(config);
        return encryptor;
    }
}