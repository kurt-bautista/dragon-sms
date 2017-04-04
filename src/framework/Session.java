package framework;

/**
 * Created by kurtv on 3/30/17.
 */
public class Session {

    private String name;
    private String currentRoom;
    private int gameState;

    public Session() {
        this.name = "";
        this.currentRoom = "Room1";
        this.gameState = 0;
    }

    public Session(String name, String currentRoom, int gameState) {
        this.name = name;
        this.currentRoom = currentRoom;
        this.gameState = gameState;
    }

    public void reset() {
        currentRoom = "Room1";
        gameState = 0;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCurrentRoom() {
        return currentRoom;
    }

    public void setCurrentRoom(String currentRoom) {
        this.currentRoom = currentRoom;
    }

    public int getGameState() {
        return gameState;
    }

    public void setGameState(int gameState) {
        this.gameState = gameState;
    }
}
