package com.geektrust.tameofthrones.model.encryption;

public interface EncryptionFactory<E, D, K> {
    Encryption<E, D, K> getEncryption(Algorithm algorithm) throws AlgorithmNotFoundException;
}
