package Game;

public class Player {

    private Location currentLocation;
    private Inventory inventory;

    public Player() {
        this.inventory = new Inventory(3); // limit 3 předměty podle designu
    }

    public Location getCurrentLocation() {
        return currentLocation;
    }

    public void setCurrentLocation(Location location) {
        this.currentLocation = location;
    }

    public Inventory getInventory() {
        return inventory;
    }
}
