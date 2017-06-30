package de.chaostreffflensburg.nwclient;

/**
 * @author Stefan Erichsen <stefan@balticfinance.com>
 *         Date:   30.06.17
 */
public class NewtonShot {
    private String token;
    private Integer angle;
    private Integer velocity;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Integer getAngle() {
        return angle;
    }

    public void setAngle(Integer angle) {
        this.angle = angle;
    }

    public Integer getVelocity() {
        return velocity;
    }

    public void setVelocity(Integer velocity) {
        this.velocity = velocity;
    }
}
