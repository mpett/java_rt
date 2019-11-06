public class HitRecord {
    private double t;
    private Vector p;
    private Vector normal;

    public HitRecord() {
        this.t = 0.0;
        this.p = new Vector(0, 0, 0);
        this.normal = new Vector(0, 0, 0);
    }

    public HitRecord(double t, Vector p, Vector normal) {
        this.t = t;
        this.p = p;
        this.normal = normal;
    }

    public void setT(double t) {
        this.t = t;
    }

    public void setP(Vector p) {
        this.p = p;
    }

    public double getT() {
        return this.t;
    }

    public Vector getP() {
        return this.p;
    }

    public void setNormal(Vector normal) {
        this.normal = normal;
    }

    public Vector getNormal() {
        return this.normal;
    }
}
