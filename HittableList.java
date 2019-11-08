public class HittableList extends Hittable {
    private Hittable[] list;
    private int listSize;
    
    public HittableList() {}

    public HittableList(Hittable[] list, int listSize) {
        this.list = list;
        this.listSize = listSize;
    }

    public HitRecord hit(Ray r, double tMin, double tMax, HitRecord rec) {
        HitRecord tempRec = new HitRecord();
        double closestSoFar = tMax;

        for (int i = 0; i < listSize; i++) {
            tempRec = list[i].hit(r, tMin, closestSoFar, tempRec);
            boolean wasHit = tempRec.wasHit();
            if (wasHit) {
                tempRec.setHit(true);
                closestSoFar = tempRec.getT();
                rec = tempRec;
            }
        }
        
        return rec;
    }
}
