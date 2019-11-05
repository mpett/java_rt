public abstract class Hittable {
    abstract boolean hit(Ray r, double tMin, double tMax, HitRecord rec);
}
