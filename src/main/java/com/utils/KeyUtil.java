package com.utils;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

@Component
@Getter
@Setter
@Slf4j
public class KeyUtil {

    @Value("${mix.auth.privateKey}")
    private String mixPrivateKeyString;

    @Value("${mix.auth.publicKey}")
    private String mixPublicKeyString;

    private PrivateKey mixPrivateKey;
    private PublicKey mixPublicKey;


    @PostConstruct
    public void init() throws NoSuchAlgorithmException, InvalidKeySpecException {

        log.info("loading Keys");

        this.mixPrivateKey = loadMixPrivateKey();
        this.mixPublicKey = loadMixPublicKey();

        log.info("loaded Keys");
    }

    private PrivateKey loadMixPrivateKey() throws NoSuchAlgorithmException, InvalidKeySpecException {
        log.info("loading MixPrivateKey");
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        PKCS8EncodedKeySpec keySpecPKCS8 = new PKCS8EncodedKeySpec(Base64.getDecoder().decode(mixPrivateKeyString));
        return keyFactory.generatePrivate(keySpecPKCS8);
    }

    private PublicKey loadMixPublicKey() throws NoSuchAlgorithmException, InvalidKeySpecException {
        log.info("loading MixPublicKey");
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        X509EncodedKeySpec keySpecX509 = new X509EncodedKeySpec(Base64.getDecoder().decode(mixPublicKeyString));
        return keyFactory.generatePublic(keySpecX509);
    }

}
