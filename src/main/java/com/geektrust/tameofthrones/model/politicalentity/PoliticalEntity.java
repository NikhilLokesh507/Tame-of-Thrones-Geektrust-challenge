package com.geektrust.tameofthrones.model.politicalentity;

import com.geektrust.tameofthrones.model.reciever.Receiver;
import com.geektrust.tameofthrones.model.alliance.AllianceRequest;
import com.geektrust.tameofthrones.model.decider.Decider;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface PoliticalEntity extends Decider, Receiver {
    String getName();

    Optional<List<PoliticalEntity>> attemptAlliance(Set<AllianceRequest> requests);
}
