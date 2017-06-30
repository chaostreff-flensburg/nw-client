package de.chaostreffflensburg.nwclient;

import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * @author Stefan Erichsen <stefan@balticfinance.com>
 *         Date:   30.06.17
 */
@Repository
public class ClientCache {

    private Map<String, NewtonWarsClient> clients = new HashMap<>();

    public NewtonWarsClient getClient(String uuid) {
        return clients.get(uuid);
    }

    public String registerClient(String username) throws IOException, InterruptedException {
        UUID uuid = UUID.randomUUID();
        NewtonWarsClient client = new NewtonWarsClient();
        client.connect(username);
        clients.put(uuid.toString(), client);
        return uuid.toString();
    }

    public void removeClient(String token) {
        clients.remove(token);
    }
}
