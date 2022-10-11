package model.rules.numberofplayers;

public class FourPlayers implements NumberOfPlayers {

    @Override
    public boolean execute(int numOfPlayers) {
        return numOfPlayers == 4;        
    }
    
}
