import java.util.concurrent.ThreadLocalRandom;

public class Dielectric extends Material {
    private double refIdx;

    public Dielectric(double ri) {
        this.refIdx = ri;
    }

    public double schlick(double cosine, double refIdxParam) {
        double r0 = (1 - refIdxParam) / (1 + refIdxParam);
        r0 = r0 * r0;
        return r0 + (1 - r0) * Math.pow((1 - cosine), 5);
    }

    public double randomDouble() {
        double random = ThreadLocalRandom.current().nextDouble();
        return random;
    }

    public HitRecord scatter(Ray rIn, HitRecord rec, Vector attenuation, Ray scattered) {
        Vector outwardNormal = new Vector(0, 0, 0);
        Vector reflected = Vector.reflect(rIn.direction(), rec.getNormal());
        double niOverNt;
        attenuation = new Vector(1.0, 1.0, 1.0);
        rec.setAttenuation(attenuation);

        double reflectProb;
        double cosine;

        if (Vector.dot(rIn.direction(), rec.getNormal()) > 0) {
            outwardNormal = rec.getNormal().negate();
            niOverNt = this.refIdx;
            cosine = this.refIdx * Vector.dot(rIn.direction(), rec.getNormal()) 
                                / rIn.direction().length();
            
        } else {
            outwardNormal = rec.getNormal();
            niOverNt = 1.0 / this.refIdx;
            cosine = -Vector.dot(rIn.direction(), rec.getNormal()) 
                                / rIn.direction().length();
        }

        rec = Vector.refract(rIn.direction(), outwardNormal, niOverNt, rec);
        boolean wasRefracted = rec.wasRefracted();

        if (wasRefracted) {
            reflectProb = schlick(cosine, this.refIdx);
        } else {
            reflectProb = 1.0;
        }

        if (randomDouble() < reflectProb) {
            scattered = new Ray(rec.getP(), reflected);
            rec.setScattered(scattered);
        } else {
            Vector refracted = rec.getRefracted();
            scattered = new Ray(rec.getP(), refracted);
            rec.setScattered(scattered);
        }

        rec.setHit(true);
        return rec;
    }
}
