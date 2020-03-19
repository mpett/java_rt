package java_rt;

public abstract class Material {
    abstract HitRecord scatter(Ray rIn, HitRecord rec, Vector attenuation, Ray scattered);
}
