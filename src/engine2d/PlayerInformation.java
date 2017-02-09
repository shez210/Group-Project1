package engine2d;
import org.jsfml.system.Clock;


import java.io.Serializable;


abstract class PlayerInformation implements Serializable
{

    private int x = 0;  // Current X-coordinate
    private int y = 0;  // Current Y-coordinate
    private int health = 20;
    private int maxHealth = 20;
    private int armour = 100;

    transient Clock timer = new Clock();
    transient Clock shootingClock = new Clock();
    transient Clock killCooldown = new Clock();

    private String lastDamagedBy = "";

    private int totalKills = 0;
    private int totalDamageDealt = 0;

    /**
     * Resets the clock, for use after Deserialization
     */
    public void newClock()
    {
        timer = new Clock();
    }

    /**
     * Sets the clock to the provided one
     */
    public void setClock(Clock clock)
    {
        timer = clock;
    }

    public int getMaxHealth()
    {
        return maxHealth;
    }

    public void setMaxHealth(int maxHealth)
    {
        this.maxHealth = maxHealth;
    }

    public void setShootingClock(Clock clock) { shootingClock = clock; }

    public void setKillCooldownClock(Clock clock) { killCooldown = clock; }

    /**
     * @return the X position of the Actor instance called on
     */
    public int getX()
    {
        return x;
    }

    /**
     * @return the Y position of the Actor instance called on
     */
    public int getY()
    {
        return y;
    }

    /**
     * @return the Health of the Actor instance called on
     */
    public int getHealth()
    {
        return health;
    }

    /**
     * Set the X position of the Actor instance called on to the provided variable
     *
     * @param x the variable to set the X position to
     */
    public void setX(int x)
    {
        this.x = x;
    }

    /**
     * Set the Y position of the Actor instance called on to the provided variable
     *
     * @param y the variable to set the Y position to
     */
    public void setY(int y)
    {
        this.y = y;
    }

    /**
     * Set the Health of the Actor instance called on to the provided variable
     *
     * @param health the variable to set the Health to
     */
    public void setHealth(int health)
    {
        this.health = health;
    }

    /**
     * @return the FloatRect representing the hitbox of the Actor instance called on
     */

    public int getArmour()
    {
        return armour;
    }

    public void setArmour(int armour)
    {
        this.armour = armour;
    }

    String getLastDamagedBy()
    {
        return lastDamagedBy;
    }

    public void setLastDamagedBy(String lastDamagedBy)
    {
        this.lastDamagedBy = lastDamagedBy;
    }

    public int getTotalKills()
    {
        return totalKills;
    }

    public void setTotalKills(int totalKills)
    {
        this.totalKills = totalKills;
    }

    public int getTotalDamageDealt()
    {
        return totalDamageDealt;
    }

    public void setTotalDamageDealt(int totalDamageDealt)
    {
        this.totalDamageDealt = totalDamageDealt;
    }
}

