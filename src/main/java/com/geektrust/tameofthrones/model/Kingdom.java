package com.geektrust.tameofthrones.model;

import com.geektrust.tameofthrones.model.alliance.AllianceRequest;
import com.geektrust.tameofthrones.model.encryption.Algorithm;
import com.geektrust.tameofthrones.model.encryption.Encryption;
import com.geektrust.tameofthrones.model.encryption.StringBasedEncryptionFactory;
import com.geektrust.tameofthrones.model.politicalentity.PoliticalEntity;

import java.util.*;

public enum Kingdom implements PoliticalEntity {
    SPACE("GORILLA"),
    LAND("PANDA"),
    WATER("OCTOPUS"),
    ICE("MAMMOTH"),
    AIR("OWL"),
    FIRE("DRAGON");

    private final String emblem;

    private final Encryption<String, String, Integer> encryption;

    Kingdom(String emblem) {
        this.emblem = emblem;
        this.encryption = new StringBasedEncryptionFactory().getEncryption(Algorithm.CAESAR_CIPHER);
    }

    @Override
    public boolean decide(String message) {
        Map<Character, Integer> emblemCount = getCharacterCount(emblem);
        Map<Character, Integer> messageCount = getCharacterCount(message);
        boolean decision = true;
        for (Map.Entry<Character, Integer> entry : emblemCount.entrySet()) {
            decision &= messageCount.getOrDefault(entry.getKey(), 0) >= entry.getValue();
        }
        return decision;
    }

    @Override
    public String getName() {
        return this.name();
    }

    @Override
    public Optional<List<PoliticalEntity>> attemptAlliance(List<AllianceRequest> requests) {
        List<PoliticalEntity> allies = new LinkedList<>();
        allies.add(this);
        Optional<List<PoliticalEntity>> optional = Optional.empty();
        for (AllianceRequest request : requests) {
            PoliticalEntity entity = request.getPoliticalEntity();
            String message = request.getMessage();
            if (entity.decide(this.encryption.encrypt(entity.key(), message))) {
                allies.add(entity);
            }
        }
        if (allies.size() > 3) {
            optional = Optional.of(allies);
        }
        return optional;
    }

    @Override
    public int key() {
        return this.emblem.length();
    }

    private Map<Character, Integer> getCharacterCount(String string) {
        Map<Character, Integer> count = new HashMap<>();
        for (int i = 0; i < string.length(); i++) {
            int val = count.getOrDefault(string.charAt(i), 0);
            count.put(string.charAt(i), val + 1);
        }
        return count;
    }
}