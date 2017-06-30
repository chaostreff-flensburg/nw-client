package de.chaostreffflensburg.nwclient;

import org.springframework.web.bind.annotation.*;

import java.io.IOException;

/**
 * @author Stefan Erichsen <stefan@balticfinance.com>
 *         Date:   30.06.17
 */
@RestController
@RequestMapping("/api")
@CrossOrigin
public class NewtonController {

    private final ClientCache clientCache;

    public NewtonController(ClientCache clientCache) {
        this.clientCache = clientCache;
    }

    @RequestMapping(method = RequestMethod.POST, path = "/connect")
    @ResponseBody
    public ApiAnswer getUniqueToken(@RequestBody RegisterToken token) throws IOException, InterruptedException {
        String uuid = clientCache.registerClient(token.getUsername());
        NewtonWarsClient client = clientCache.getClient(uuid);
        System.out.println("Registered new user: " + token.getUsername() + " / " + uuid);
        return new ApiAnswer(uuid, client);
    }

    @RequestMapping(method = RequestMethod.POST, path = "/shoot", consumes = "application/json")
    @ResponseBody
    public ApiAnswer postAction(@RequestBody NewtonShot shot) throws IOException {

        System.out.println("Shooting: " + shot.getToken() + " / " + shot.getAngle() + "° at " + shot.getVelocity() + "p/s");
        NewtonWarsClient client = clientCache.getClient(shot.getToken());
        if (shot.getVelocity() != null && client.getVelocity() != shot.getVelocity()) {
            client.setVelocity(shot.getVelocity());
        }

        if (shot.getAngle() != null) {
            client.shoot(shot.getAngle());
        } else {
            client.shoot();
        }
        return new ApiAnswer(client);
    }

    @RequestMapping(method = RequestMethod.POST, path = "/disconnect")
    public void postDisconnect(@RequestBody Disconnect token) throws IOException {
        System.out.println("Trying to disconnect: " + token.getToken());
        clientCache.getClient(token.getToken()).close();
        clientCache.removeClient(token.getToken());
    }
}
