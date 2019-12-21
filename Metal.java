public class Metal extends Material {
    Vector albedo;

    public Metal(Vector a) {
        this.albedo = a;
    }

    public HitRecord scatter(Ray rIn, HitRecord rec, Vector attenuation, Ray scattered) {
        Vector reflected = Vector.reflect(Vector.unitVector(rIn.direction()), rec.getNormal());
        scattered = new Ray(rec.getP(), reflected);
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
