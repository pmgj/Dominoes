package model.rules.numberofplayers;

public class TwoToNinePlayers implements NumberOfPlayers {

    @Override
    public boolean execute(int numOfPlayers) {
        return numOfPlayers >= 2 && numOfPlayers <= 9;
    }
    
}
