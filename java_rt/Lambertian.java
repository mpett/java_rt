package java_rt;

public class Lambertian extends Material {
    Vector albedo;

    public Lambertian(Vector a) {
        this.albedo = a;
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
        Vector pAddNormal = Vector.add(rec.getP(), rec.getNormal());
        Vector target = Vector.add(pAddNormal, randomInUnitSphere());
        Vector targetMinusP = Vector.subtract(target, rec.getP());
        Ray scatteredRay = new Ray(rec.getP(), targetMinusP);
        rec.setScattered(scatteredRay);
        rec.setAttenuation(albedo);
        rec.setHit(true);
        return rec;
    }

    public Vector getAlbedo() {
        return this.albedo;
    }

    public void setAlbedo(Vector albedo) {
        this.albedo = albedo;
    }
}
