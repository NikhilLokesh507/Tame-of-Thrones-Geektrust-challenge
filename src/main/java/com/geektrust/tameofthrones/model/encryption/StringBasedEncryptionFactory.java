package com.geektrust.tameofthrones.model.encryption;

public class StringBasedEncryptionFactory implements EncryptionFactory<String, String, Integer> {

    @Override
    public Encryption<String, String, Integer> getEncryption(Algorithm algorithm) throws AlgorithmNotFoundException {
        if (algorithm == Algorithm.CAESAR_CIPHER) {
            return new CaesarCipherImpl();
        }
        throw new AlgorithmNotFoundException();
    }
}
