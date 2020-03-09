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

    public Camera(double vFov, double aspect) {
        double theta = vFov * Math.PI / 180;
        double halfHeight = Math.tan(theta / 2);
        double halfWidth = aspect * halfHeight;
        lowerLeftCorner = new Vector(-halfWidth, -halfHeight, -1.0);
        horizontal = new Vector(2.0 * halfWidth, 0.0, 0.0);
        vertical = new Vector(0.0, 2.0 * halfHeight, 0.0);
        origin = new Vector(0.0, 0.0, 0.0);
    }

    public Camera(Vector lookFrom, Vector lookAt, Vector vUp, double vFov, double aspect) {
        double theta = vFov * Math.PI / 180;
        double halfHeight = Math.tan(theta / 2);
        double halfWidth = aspect * halfHeight;
        origin = lookFrom;
        Vector w = Vector.unitVector(Vector.subtract(lookFrom, lookAt));
        Vector u = Vector.unitVector(Vector.cross(vUp, w));
        Vector v = Vector.cross(w, u);
        Vector halfWidthU = Vector.multiplyScalar(halfWidth, u);
        Vector halfHeightV = Vector.multiplyScalar(halfHeight, v);
        Vector firstTermLowerLeft = Vector.subtract(origin, halfWidthU);
        Vector secondTermLowerLeft = Vector.subtract(halfHeightV, w);
        lowerLeftCorner = Vector.subtract(firstTermLowerLeft, secondTermLowerLeft);
        horizontal = Vector.multiplyScalar(2 * halfWidth, u);
        vertical = Vector.multiplyScalar(2 * halfHeight, v);
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
