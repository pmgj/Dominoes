package model.rules.usestock;

public class YesStock implements UseStock {

    @Override
    public boolean execute() {
        return true;
    }

}
