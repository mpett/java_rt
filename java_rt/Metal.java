public class Metal extends Material {
    Vector albedo;
    double fuzz;

    public Metal(Vector a, double f) {
        this.albedo = a;
        if (f < 1.0) {
            this.fuzz = f;
        } else {
            this.fuzz = 1.0;
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

    public HitRecord scatter(Ray rIn, HitRecord rec, Vector attenuation, Ray scattered) {
        Vector reflected = Vector.reflect(Vector.unitVector(rIn.direction()), rec.getNormal());
        Vector fuzzAndRandom = Vector.multiplyScalar(fuzz, randomInUnitSphere());
        Vector reflectedAndFuzz = Vector.add(reflected, fuzzAndRandom);
        scattered = new Ray(rec.getP(), reflectedAndFuzz);
        rec.setScattered(scattered);
        rec.setAttenuation(albedo);

        if (Vector.dot(scattered.direction(), rec.getNormal()) > 0) {
            rec.setHit(true);
        } else {
            rec.setHit(false);
        }
        
        return rec;
    }

    public Vector getAlbedo() {
        return this.albedo;
    }

    public void setAlbedo(Vector albedo) {
        this.albedo = albedo;
    }
}
