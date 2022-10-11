package model.rules.usestock;

public class NoStock implements UseStock {

    @Override
    public boolean execute() {
        return false;
    }

}
