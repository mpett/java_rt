public class Sphere extends Hittable {
    private Vector center;
    private double radius;
    
    public Sphere() {}

    public Sphere(Vector center, double radius) {
        this.center = center;
        this.radius = radius;
    }

    public HitRecord hit(Ray r, double tMin, double tMax, HitRecord rec) {
        Vector oc = Vector.subtract(r.origin(), center);
        double a = Vector.dot(r.direction(), r.direction());
        double b = Vector.dot(oc, r.direction());
        double c = Vector.dot(oc, oc) - (radius * radius);
        double discriminant = b*b - a*c;
        //System.err.println(discriminant);

        if (discriminant > 0) {
            double temp = (-b - Math.sqrt(discriminant)) / a;

            if (temp < tMax && temp > tMin) {
                rec.setT(temp);
                rec.setP(r.pointAtParameter(rec.getT()));
                Vector pCenterDiff = Vector.subtract(rec.getP(), center);
                Vector newNormal = Vector.divideScalar(pCenterDiff, radius);
                rec.setNormal(newNormal);
                //System.err.println("Första ifsatsen: " + newNormal + " " + rec.getNormal());
                rec.setHit(true);
                return rec;
            }

            temp = (-b + Math.sqrt(discriminant)) / a;

            if (temp < tMax && temp > tMin) {
                rec.setT(temp);
                rec.setP(r.pointAtParameter(rec.getT()));
                Vector pCenterDiff = Vector.subtract(rec.getP(), center);
                Vector newNormal = Vector.divideScalar(pCenterDiff, radius);
                rec.setNormal(newNormal);
                rec.setHit(true);
                return rec;
            }
        }
        
        return rec;
    }
}
