package GeoConsole.Figure;

public abstract class Triangle extends Figure {
    double A, B, C, height; //height on A
    String type;

    @Override
    public Circle getCircumcircle(){
        return new Circle((A*B*C)/Math.sqrt((A+B+C)*(B+C-A)*(C+A-B)*(A+B-C)), -1, -1);
    }

    @Override
    public void print(){
        throwIfZero(A, B, C, area);
        System.out.printf("[ID:%d] Triangle of type %s:\n\tA: %f\n\tB: %f\n\tC: %f\n\theight: %f\n\tarea: %f\n\tperimeter: %f\n",
                id, type, A, B, C, height, area, perimeter);
    }

}