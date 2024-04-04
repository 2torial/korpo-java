package GeoConsole.Figure;

public class Rectangle extends Figure {
    private int propertiesUntilDefined = 2;
    double sideA, sideB, diagonal, area;

    public final void setSide(double value){
        if (propertiesUntilDefined <= 0)
            throw new IllegalStateException("Rectangle is already defined");
        if (value <= 0)
            throw new IllegalArgumentException("The side has to be greater than 0");
        if (sideA == 0)
            sideA = value;
        else
            sideB = value;
        propertiesUntilDefined--;
        if (propertiesUntilDefined == 0)
            fillData();
    }

    public final void setDiagonal(double value){
        if (propertiesUntilDefined <= 0)
            throw new IllegalStateException("Rectangle is already defined");
        if (value <= 0)
            throw new IllegalArgumentException("The diagonal has to be greater than 0");
        if (diagonal > 0)
            throw new IllegalStateException("Diagonal is already defined");
        diagonal = value;
        propertiesUntilDefined--;
        if (propertiesUntilDefined == 0)
            fillData();
    }

    public final void setArea(double value){
        if (propertiesUntilDefined <= 0)
            throw new IllegalStateException("Rectangle is already defined");
        if (value <= 0)
            throw new IllegalArgumentException("The area has to be greater than 0");
        if (area > 0)
            throw new IllegalStateException("Area is already defined");
        area = value;
        propertiesUntilDefined--;
        if (propertiesUntilDefined == 0)
            fillData();
    }

    public void print(){
        throwIfZero(sideB, sideA, area, diagonal);
        System.out.printf("Rectangle:\n\tside: %f x %f\n\tdiagonal: %f\n\tarea: %f\n", sideA, sideB, diagonal, area);
    }

    private void fillData() {
        if (sideA > 0 && sideB > 0)
            diagonal = Math.sqrt(sideA * sideA + sideB * sideB);
        else if (sideA > 0 && diagonal > 0)
            sideB = Math.sqrt(diagonal * diagonal - sideA * sideA);
        else if (sideA > 0 && area > 0) {
            sideB = area / sideA;
            diagonal = Math.sqrt(sideA * sideA + sideB * sideB);
        } else {
            sideA = Math.sqrt((Math.pow(diagonal, 2) + Math.sqrt(Math.pow(diagonal, 4) - 4 * Math.pow(area, 2))) / 2);
            sideB = area / sideA;
        }
        area = (area > 0) ? area : sideA * sideB;
        throwIfNaN(sideA, sideB, diagonal, area);
    }
}