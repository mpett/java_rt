public abstract class Hittable {
    abstract HitRecord hit(Ray r, double tMin, double tMax, HitRecord rec);
}
