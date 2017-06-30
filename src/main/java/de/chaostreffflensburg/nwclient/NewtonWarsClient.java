package de.chaostreffflensburg.nwclient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

/**
 * @author Stefan Erichsen <stefan@balticfinance.com>
 *         Date:   30.06.17
 */
public class NewtonWarsClient {

    private Socket socket;
    private BufferedReader r;
    private OutputStream w;

    private String username;
    private int velocity = 10;
    private int lastAngle = 0;
    private long currentEnery = 20L;
    private LocalDateTime energyTime;

    public String connect(String name) throws IOException, InterruptedException {
        this.username = name;
        socket = new Socket("172.16.0.189", 3490);
        socket.setKeepAlive(true);
        r = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        w = socket.getOutputStream();
        energyTime = LocalDateTime.now().minusSeconds(this.currentEnery);
        return sendCmd("n " + name);
    }

    public String getUsername() {
        return username;
    }

    public void close() throws IOException {
        socket.close();
    }

    public String shoot() throws IOException {
        return shoot(this.lastAngle);
    }

    public String shoot(int angle) throws IOException {
        this.lastAngle = angle;
        if (getCurrentEnergy() > this.velocity) {
            this.currentEnery -= this.velocity;
            return sendCmd("" + angle);
        }
        return null;
    }

    public int getLastAngle() {
        return lastAngle;
    }

    public String setVelocity(int velocity) throws IOException {
        this.velocity = velocity;
        return sendCmd("" + velocity);
    }

    public int getVelocity() {
        return velocity;
    }

    private String sendCmd(String command) throws IOException {
        w.write((command + "\n\r").getBytes()); // also tried simply \n or \r
        w.flush();
        return read(r);
    }

    private String read(BufferedReader r) throws IOException {
        StringBuilder answer = new StringBuilder();
        int c;
        while ((c = r.read()) != '>') {
            answer.append((char) c);
        }
        return answer.toString();
    }

    public long getCurrentEnergy() {
        System.out.println("Energy before: " + this.currentEnery);
        this.currentEnery = this.energyTime.until(LocalDateTime.now(), ChronoUnit.SECONDS);
        System.out.println("Energy after: " + this.currentEnery);
        return this.currentEnery;
    }
}
