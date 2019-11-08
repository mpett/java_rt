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

    public static Vector color(Ray r, Hittable world) {
        HitRecord rec = new HitRecord();
        rec = world.hit(r, 0.0, Double.MAX_VALUE-100.0, rec);
        boolean wasHit = rec.wasHit();

        if (wasHit) {
            Vector normalIncrement = new Vector(rec.getNormal().getX() + 1.0, 
                        rec.getNormal().getY() + 1.0, rec.getNormal().getZ() + 1.0);
            Vector halvedNormal = Vector.multiplyScalar(0.5, normalIncrement);
            //System.err.println("getnormal: " + rec.getNormal());
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

    public static void main(String[] args) {
        int nx = 2000;
        int ny = 1000;

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
                Vector col = color(r, world);

                int ir = (int) (255.99 * col.getX());
                int ig = (int) (255.99 * col.getY());
                int ib = (int) (255.99 * col.getZ());

                System.out.println(ir + " " + ig + " " + ib);
            }
            System.err.print(j + " ");
        }
    }
}
