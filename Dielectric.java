public class Dielectric extends Material {
    private double refIdx;

    public Dielectric(double ri) {
        this.refIdx = ri;
    }

    public HitRecord scatter(Ray rIn, HitRecord rec, Vector attenuation, Ray scattered) {
        Vector outwardNormal = new Vector(0, 0, 0);
        Vector reflected = Vector.reflect(rIn.direction(), rec.getNormal());
        double niOverNt;
        attenuation = new Vector(1.0, 1.0, 1.0);
        rec.setAttenuation(attenuation);

        if (Vector.dot(rIn.direction(), rec.getNormal()) > 0) {
            outwardNormal = rec.getNormal().negate();
            niOverNt = this.refIdx;
        } else {
            outwardNormal = rec.getNormal();
            niOverNt = 1.0 / this.refIdx;
        }

        rec = Vector.refract(rIn.direction(), outwardNormal, niOverNt, rec);
        boolean wasRefracted = rec.wasRefracted();

        if (wasRefracted) {
            scattered = new Ray(rec.getP(), rec.getRefracted());
            rec.setScattered(scattered);
        } else {
            scattered = new Ray(rec.getP(), reflected);
            rec.setScattered(scattered);
            rec.setHit(false);
            return rec;
        }

        rec.setHit(true);
        return rec;
    }
}
