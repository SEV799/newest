package model;


public class Zombie {
    private Long id;
    private String name;
    private int toughness;
    private String equipment;
    private int biteDamage;
    private int throwDamage;
    private int crushDamage;
    private String speed;
    private String features;
    private String description;
    private String imageUrl;

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public int getToughness() { return toughness; }
    public void setToughness(int toughness) { this.toughness = toughness; }
    public String getEquipment() { return equipment; }
    public void setEquipment(String equipment) { this.equipment = equipment; }
    public int getBiteDamage() { return biteDamage; }
    public void setBiteDamage(int biteDamage) { this.biteDamage = biteDamage; }
    public int getThrowDamage() { return throwDamage; }
    public void setThrowDamage(int throwDamage) { this.throwDamage = throwDamage; }
    public int getCrushDamage() { return crushDamage; }
    public void setCrushDamage(int crushDamage) { this.crushDamage = crushDamage; }
    public String getSpeed() { return speed; }
    public void setSpeed(String speed) { this.speed = speed; }
    public String getFeatures() { return features; }
    public void setFeatures(String features) { this.features = features; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public String getImageUrl() { return imageUrl; }
    public void setImageUrl(String imageUrl) { this.imageUrl = imageUrl; }
}



