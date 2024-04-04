package GeoConsole.Figure;

public abstract class Triangle extends Figure {
    double A, B, C, height; //height on A
    String type;
    @Override
    public void print(){
        throwIfZero(A, B, C, height, area);
        System.out.printf("Triangle of type %s:\n\tA: %f\n\tB: %f\n\tC: %f\n\theight: %f\n\tarea: %f\n", type, A, B, C, height, area);
    }
}
