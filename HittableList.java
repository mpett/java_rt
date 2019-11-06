public class HittableList extends Hittable {
    private Hittable[] list;
    private int listSize;
    
    public HittableList() {}

    public HittableList(Hittable[] list, int listSize) {
        this.list = list;
        this.listSize = listSize;
    }

    public boolean hit(Ray r, double tMin, double tMax, HitRecord rec) {
        HitRecord tempRec = new HitRecord();
        boolean hitAnything = false;
        double closestSoFar = tMax;

        for (int i = 0; i < listSize; i++) {
            if (list[i].hit(r, tMin, closestSoFar, tempRec)) {
                hitAnything = true;
                closestSoFar = tempRec.getT();
                rec = tempRec;
            }
        }
        
        return hitAnything;
    }
}
