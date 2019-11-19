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

    public static Vector randomInUnitSphere() {
        Vector p = new Vector(0, 0, 0);

        do {
            Vector randomVector = new Vector(Math.random(), Math.random(), Math.random());
            Vector firstVector = Vector.multiplyScalar(2.0, randomVector);
            Vector secondVector = new Vector(1, 1, 1);
            Vector diff = Vector.subtract(firstVector, secondVector);
            p = diff;
        } while (p.squaredLength() >= 1.0);

        return p;
    }

    public static Vector color(Ray r, Hittable world) {
        HitRecord rec = new HitRecord();
        rec = world.hit(r, 0.0, Double.MAX_VALUE, rec);
        boolean wasHit = rec.wasHit();

        if (wasHit) {
            Vector recP = rec.getP();
            Vector recNormal = rec.getNormal();
            Vector first = Vector.add(recP, recNormal);
            Vector target = Vector.add(first, randomInUnitSphere());
            Vector targetRecPDiff = Vector.subtract(target, recP);
            Ray ray = new Ray(recP, targetRecPDiff);
            return Vector.multiplyScalar(0.5, color(ray, world));
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
        int ns = 100;

        System.out.println("P3\n" + nx + " " + ny + "\n255");

        Hittable[] list = new Hittable[2];
        list[0] = new Sphere(new Vector(0.0, 0.0, -1.0), 0.5);
        list[1] = new Sphere(new Vector(0.0, -100.5, -1.0), 100.0);
        Hittable world = new HittableList(list, 2);
        Camera camera = new Camera();

        for (int j = ny-1; j >= 0; j--) {
            for (int i = 0; i < nx; i++) {
                Vector col = new Vector(0.0, 0.0, 0.0);

                for (int s = 0; s < ns; s++) {
                    double u = (double) (i + Math.random()) / (double) nx;
                    double v = (double) (j + Math.random()) / (double) ny;
                    Ray r = camera.getRay(u, v);
                    col = Vector.add(col, color(r, world));
                }

                col = Vector.divideScalar(col, (double) ns);

                int ir = (int) (255.99 * col.getX());
                int ig = (int) (255.99 * col.getY());
                int ib = (int) (255.99 * col.getZ());

                System.out.println(ir + " " + ig + " " + ib);
            }
            System.err.print(j + " ");
        }
    }
}
