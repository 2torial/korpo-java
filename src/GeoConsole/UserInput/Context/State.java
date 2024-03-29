package GeoConsole.UserInput.Context;

public class State {
    private int state = 0;

    public State(int initialState) {
        state = initialState;
    }

    public int read() {
        return state;
    }
    public void update(int value) {
        state = value;
    }
    public int readAndIncrement() {
        int value = state;
        update(value+1);
        return value;
    }
    public void reset() {
        state = 0;
    }
}
