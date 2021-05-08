package com.geektrust.tameofthrones.model.politicalentity;

import com.geektrust.tameofthrones.model.alliance.AllianceRequest;
import com.geektrust.tameofthrones.model.decider.Decider;
import com.geektrust.tameofthrones.model.reciever.Receiver;

import java.util.List;
import java.util.Optional;

public interface PoliticalEntity extends Decider, Receiver {
    String getName();

    Optional<List<PoliticalEntity>> attemptAlliance(List<AllianceRequest> requests);
}
