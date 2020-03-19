package java_rt;

public class HitRecord {
    private Vector p;
    private Vector normal;
    private Vector attenuation;
    private Vector refracted;
    
    private double t;
    private Material material;

    private boolean wasHitByRay;
    private boolean wasRefracted;
    
    private Ray scattered;

    public HitRecord() {
        this.t = 0.0;
        this.p = new Vector(0, 0, 0);
        this.normal = new Vector(0, 0, 0);
        this.wasHitByRay = false;
    }

    public HitRecord(double t, Vector p, Vector normal) {
        this.t = t;
        this.p = p;
        this.normal = normal;
        this.wasHitByRay = false;
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

    public void setNormal(Vector norm) {
        normal = norm;
    }

    public Vector getNormal() {
        return normal;
    }

    public boolean wasHit() {
        return this.wasHitByRay;
    }

    public void setHit(boolean condition) {
        this.wasHitByRay = condition;
    }

    public void setMaterial(Material material) {
        this.material = material;
    }

    public Material getMaterial() {
        return this.material;
    }

    public void setAttenuation(Vector attenuation) {
        this.attenuation = attenuation;
    }

    public Vector getAttenuation() {
        return this.attenuation;
    }

    public void setScattered(Ray scattered) {
        this.scattered = scattered;
    }

    public Ray getScattered() {
        return this.scattered;
    }

    public void setWasRefracted(boolean wasRefracted) {
        this.wasRefracted = wasRefracted;
    }

    public boolean wasRefracted() {
        return this.wasRefracted;
    }

    public void setRefracted(Vector refracted) {
        this.refracted = refracted;
    }

    public Vector getRefracted() {
        return this.refracted;
    }
}
