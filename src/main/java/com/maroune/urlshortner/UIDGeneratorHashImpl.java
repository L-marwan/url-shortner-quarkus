package com.maroune.urlshortner;

import jakarta.enterprise.context.ApplicationScoped;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.UUID;

@ApplicationScoped
public class UIDGeneratorHashImpl implements IUIDGenerator {
    @Override
    public String generateUID(int idLength) {
        String uuid = UUID.randomUUID().toString();
        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance("SHA-1");
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
        byte[] urlBytes = uuid.getBytes(StandardCharsets.UTF_8);
        md.update(urlBytes);
        // Get the hash value
        byte[] hash = md.digest();
        // Encode the hash value using Base64
        String base64Encoded = Base64.getUrlEncoder().withoutPadding().encodeToString(hash);
        // Take the first 'length' characters as the unique ID
        return base64Encoded.substring(0, idLength);
    }
}
