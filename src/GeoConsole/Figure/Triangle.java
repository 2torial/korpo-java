package GeoConsole.Figure;

public abstract class Triangle extends Figure {
    double A, B, C, height; //height on A
    String type = "unspecified";

    @Override
    public Circle getCircumcircle(){
        return new Circle((A*B*C)/Math.sqrt((A+B+C)*(B+C-A)*(C+A-B)*(A+B-C)), -1, -1);
    }

    @Override
    public String getDescription(int roundTo) {
        return stringRounded("[ID:%d] Triangle of type %s: A: %f, B: %f, C: %f, height: %f, area: %f, perimeter: %f\n",
                roundTo, id, type, A, B, C, height, area, perimeter);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Triangle triangle = (Triangle) o;
        return Double.compare(A, triangle.A) == 0 && Double.compare(height, triangle.height) == 0;
    }
}