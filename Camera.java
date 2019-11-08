public class Camera {
    private Vector origin;
    private Vector lowerLeftCorner;
    private Vector horizontal;
    private Vector vertical;

    public Camera() {
        lowerLeftCorner = new Vector(-2.0, -1.0, -1.0);
        horizontal = new Vector(4.0, 0.0, 0.0);
        vertical = new Vector(0.0, 2.0, 0.0);
        origin = new Vector(0.0, 0.0, 0.0);
    }

    public Ray getRay(double u, double v) {
        Vector uHorizontal = Vector.multiplyScalar(u, horizontal);
        Vector vVertical = Vector.multiplyScalar(v, vertical);
        Vector first = Vector.add(lowerLeftCorner, uHorizontal);
        Vector second = Vector.subtract(vVertical, origin);
        Vector firstSecondSum = Vector.add(first, second);
        
        Ray result = new Ray(origin, firstSecondSum);
        
        return result;
    }
}
