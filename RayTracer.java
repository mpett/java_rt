public class RayTracer {
    public static double hitSphere(Vector center, double radius, Ray r) {
        Vector rayOrigin = r.origin();
        Vector rayDirection = r.direction();
        Vector oc = Vector.subtract(rayOrigin, center);
        double a = Vector.dot(rayDirection, rayDirection);
        double b = 2.0 * Vector.dot(oc, rayDirection);
        double c = Vector.dot(oc, oc) - radius * radius;
        double discriminant = b*b - 4*a*c;

        if (discriminant < 0) {
            return -1.0;
        } else {
            return (-b - Math.sqrt(discriminant) ) / (2.0 * a);
        }
    }

    public static Vector color2(Ray r, Hittable world) {
        HitRecord rec = new HitRecord();

        if (world.hit(r, 0.0, Double.MAX_VALUE-100.0, rec)) {
            Vector normalIncrement = new Vector(rec.getNormal().getX() + 1.0, rec.getNormal().getY() + 1.0, rec.getNormal().getZ() + 1.0);
            //System.err.println("lol " + normalIncrement);
            Vector halvedNormal = Vector.multiplyScalar(0.5, normalIncrement);
            return halvedNormal;
        } else {
            Vector unitDirection = Vector.unitVector(r.direction());
            double t = 0.5 * (unitDirection.getY() + 1.0);
            Vector a = new Vector(1.0, 1.0, 1.0);
            Vector b = new Vector(0.5, 0.7, 1.0);
            Vector first = Vector.multiplyScalar(1.0 - t, a);
            Vector second = Vector.multiplyScalar(t, b);
            Vector result = Vector.add(first, second);
            return result;
        }
    }
    
    public static Vector color(Ray r) {
        Vector center = new Vector(0.0, 0.0, -1.0);
        double t = hitSphere(center, 0.5, r);

        if (t > 0.0) {
            Vector d = new Vector(0.0, 0.0, -1.0);
            Vector pointAtParameter = r.pointAtParameter(t);
            Vector nDiff = Vector.subtract(pointAtParameter, d);
            Vector N = Vector.unitVector(nDiff);
            Vector incrementN = new Vector(N.getX() + 1.0, N.getY() + 1.0, N.getZ() + 1.0);
            Vector resultingVector = Vector.multiplyScalar(0.5, incrementN);
            return resultingVector;
        }

        Vector unitDirection = Vector.unitVector(r.direction());
        t = 0.5 * (unitDirection.getY() + 1.0);
        Vector firstVector = new Vector(1.0, 1.0, 1.0);
        Vector secondVector = new Vector(0.5, 0.7, 1.0);
        Vector multFirst = Vector.multiplyScalar(1.0 - t, firstVector);
        Vector multSecond = Vector.multiplyScalar(t, secondVector);
        Vector resultingVector = Vector.add(multFirst, multSecond);
        return resultingVector;
    }

    public static void main(String[] args) {
        int nx = 200;
        int ny = 100;

        System.out.println("P3\n" + nx + " " + ny + "\n255");

        Vector lowerLeftCorner = new Vector(-2.0, -1.0, -1.0);
        Vector horizontal = new Vector(4.0, 0.0, 0.0);
        Vector vertical = new Vector(0.0, 2.0, 0.0);
        Vector origin = new Vector(0.0, 0.0, 0.0);

        Hittable[] list = new Hittable[2];
        list[0] = new Sphere(new Vector(0.0, 0.0, -1.0), 0.5);
        list[1] = new Sphere(new Vector(0.0, -100.5, -1.0), 100.0);
        Hittable world = new HittableList(list, 2);

        for (int j = ny-1; j >= 0; j--) {
            for (int i = 0; i < nx; i++) {
                double u = (double) i / (double) nx;
                double v = (double) j / (double) ny;
                Vector uHorizontal = Vector.multiplyScalar(u, horizontal);
                Vector vVertical = Vector.multiplyScalar(v, vertical);
                Vector horVert = Vector.add(uHorizontal, vVertical);
                Vector rayDirection = Vector.add(lowerLeftCorner, horVert);
                Ray r = new Ray(origin, rayDirection);

                Vector p = r.pointAtParameter(2.0);
                //Vector col = color2(r, world);   
                Vector col = color(r);

                int ir = (int) (255.99 * col.getX());
                int ig = (int) (255.99 * col.getY());
                int ib = (int) (255.99 * col.getZ());

                System.out.println(ir + " " + ig + " " + ib);
            }
        }
    }
}
