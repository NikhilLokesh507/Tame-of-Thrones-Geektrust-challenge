package com.geektrust.tameofthrones.model.alliance;

import com.geektrust.tameofthrones.model.politicalentity.PoliticalEntity;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@AllArgsConstructor
@EqualsAndHashCode(of = {"politicalEntity"})
public class AllianceRequest {

    private final PoliticalEntity politicalEntity;

    private final String message;

}
