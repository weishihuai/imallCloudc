package com.imall.iportal.shiro.service;

import org.apache.shiro.crypto.RandomNumberGenerator;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;
import org.springframework.stereotype.Service;

/**
 * Created by yang on 2015-10-28.
 */
@Service
public class PasswordHelper {

    private RandomNumberGenerator randomNumberGenerator = new SecureRandomNumberGenerator();

/*    @Value("${password.algorithmName}")*/
    private String algorithmName = "md5";

/*    @Value("${password.hashIterations}")*/
    private int hashIterations = 2;

    public void setRandomNumberGenerator(RandomNumberGenerator randomNumberGenerator) {
        this.randomNumberGenerator = randomNumberGenerator;
    }

    public void setAlgorithmName(String algorithmName) {
        this.algorithmName = algorithmName;
    }

    public void setHashIterations(int hashIterations) {
        this.hashIterations = hashIterations;
    }

    public String genSalt(){
        return randomNumberGenerator.nextBytes().toHex();
    }

    public String encryptPassword(String sourcePassword, String salt) {
        return new SimpleHash(algorithmName, sourcePassword, ByteSource.Util.bytes(salt), hashIterations).toHex();
    }


}
