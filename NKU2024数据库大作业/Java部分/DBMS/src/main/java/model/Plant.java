package model;
public class Plant {
    private Long id;
    private String name;
    private int toughness;
    private int power;
    private String range;
    private String ammo;
    private String features;
    private int cost;
    private int cooldown;
    private String description;
    private String imageUrl;

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public int getToughness() { return toughness; }
    public void setToughness(int toughness) { this.toughness = toughness; }
    public int getPower() { return power; }
    public void setPower(int power) { this.power = power; }
    public String getRange() { return range; }
    public void setRange(String range) { this.range = range; }
    public String getAmmo() { return ammo; }
    public void setAmmo(String ammo) { this.ammo = ammo; }
    public String getFeatures() { return features; }
    public void setFeatures(String features) { this.features = features; }
    public int getCost() { return cost; }
    public void setCost(int cost) { this.cost = cost; }
    public int getCooldown() { return cooldown; }
    public void setCooldown(int cooldown) { this.cooldown = cooldown; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public String getImageUrl() { return imageUrl; }
    public void setImageUrl(String imageUrl) { this.imageUrl = imageUrl; }
}
