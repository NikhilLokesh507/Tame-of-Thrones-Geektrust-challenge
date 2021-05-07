package com.geektrust.tameofthrones.model.encryption;

public interface Encryption<E, D, K> {
    E encrypt(K key, D decrypt);
}
