package de.chaostreffflensburg.nwclient;

/**
 * @author Stefan Erichsen <stefan@balticfinance.com>
 *         Date:   30.06.17
 */
public class ApiAnswer {
    private final String token;
    private final String username;
    private final int velocity;
    private final long energy;

    public ApiAnswer(String uuid, NewtonWarsClient client) {
        this.token = uuid;
        this.username = client.getUsername();
        this.velocity = client.getVelocity();
        this.energy = client.getCurrentEnergy();
    }

    public ApiAnswer(NewtonWarsClient client) {
        this.token = "geheim";
        this.username = client.getUsername();
        this.velocity = client.getVelocity();
        this.energy = client.getCurrentEnergy();
    }

    public String getToken() {
        return token;
    }

    public String getUsername() {
        return username;
    }

    public int getVelocity() {
        return velocity;
    }

    public long getEnergy() {
        return energy;
    }
}
