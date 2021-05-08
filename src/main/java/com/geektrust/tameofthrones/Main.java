package com.geektrust.tameofthrones;

import com.geektrust.tameofthrones.model.Kingdom;
import com.geektrust.tameofthrones.model.alliance.AllianceRequest;
import com.geektrust.tameofthrones.model.politicalentity.PoliticalEntity;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

import static com.geektrust.tameofthrones.model.Kingdom.SPACE;

public class Main {
    public static void main(String[] args) {
        try {
            Optional<List<PoliticalEntity>> optional = SPACE.attemptAlliance(extractFromFile(args[0]));
            String answer = "NONE";

            if (optional.isPresent()) {
                StringBuilder sb = new StringBuilder();
                List<PoliticalEntity> entities = optional.get();
                entities.forEach(politicalEntity -> sb.append(politicalEntity.getName()).append(" "));
                answer = sb.toString();
            }
            System.out.println(answer);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private static List<AllianceRequest> extractFromFile(String file) throws IOException {
        List<AllianceRequest> requests = new LinkedList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                int index = line.indexOf(' ');
                Kingdom kingdom = Kingdom.valueOf(line.substring(0, index));
                String message = line.substring(index + 1);
                AllianceRequest request = new AllianceRequest(kingdom, message);
                if (!requests.contains(request)) {
                    requests.add(request);
                }
            }
        }
        return requests;
    }
}
