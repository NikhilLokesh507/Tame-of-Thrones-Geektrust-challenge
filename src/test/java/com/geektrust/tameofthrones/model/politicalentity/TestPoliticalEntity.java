package com.geektrust.tameofthrones.model.politicalentity;

import com.geektrust.tameofthrones.model.alliance.AllianceRequest;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.*;
import java.util.stream.Collectors;

public class TestPoliticalEntity {

    private static final String ACCEPT_MESSAGE = "accept";
    private static final String UNACCEPT_MESSAGE = "unaccept";

    private PoliticalEntity entity;

    @Before
    public void setup() {
        entity = new PoliticalEntity() {
            @Override
            public String getName() {
                return "AllianceMaker";
            }

            @Override
            public Optional<List<PoliticalEntity>> attemptAlliance(List<AllianceRequest> requests) {
                Optional<List<PoliticalEntity>> optional = Optional.empty();
                List<PoliticalEntity> list = requests.stream()
                        .filter(request -> request.getPoliticalEntity().decide(request.getMessage()))
                        .map(AllianceRequest::getPoliticalEntity).collect(Collectors.toList());
                if (!list.isEmpty()) {
                    optional = Optional.of(list);
                }
                return optional;
            }

            @Override
            public int key() {
                return 0;
            }

            @Override
            public boolean decide(String message) {
                return false;
            }
        };
    }

    @Test
    public void testAllianceMakingSuccessful() {
        List<AllianceRequest> requests = new LinkedList<>();
        for (int i = 0; i < 100; i++) {
            requests.add(new AllianceRequest(entity(), i % 2 == 0 ? ACCEPT_MESSAGE : UNACCEPT_MESSAGE));
        }
        Optional<List<PoliticalEntity>> optional = entity.attemptAlliance(requests);
        if (!optional.isPresent()) {
            Assert.fail();
        }
        Assert.assertEquals(50, optional.get().size());
    }

    @Test
    public void testAllianceMakingUnsuccessful() {
        List<AllianceRequest> requests = new LinkedList<>();
        for (int i = 0; i < 100; i++) {
            requests.add(new AllianceRequest(entity(), UNACCEPT_MESSAGE));
        }
        Optional<List<PoliticalEntity>> optional = entity.attemptAlliance(requests);
        Assert.assertEquals(Optional.empty(), optional);
    }

    private PoliticalEntity entity() {
        PoliticalEntity entity1 = Mockito.mock(PoliticalEntity.class);
        Mockito.when(entity1.decide(Mockito.anyString())).thenAnswer(invocationOnMock -> invocationOnMock.getArgumentAt(0, String.class).equals(ACCEPT_MESSAGE));
        return entity1;
    }
}
