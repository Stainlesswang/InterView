package allen.bigdata.bitmap;

import org.roaringbitmap.IntIterator;
import org.roaringbitmap.RoaringBitmap;
/**
 * @author wangjianqiang
 */
public class RoaringBitmapTest {
    public static void main(String[] args) {
        RoaringBitmap rr = RoaringBitmap.bitmapOf(1,2,3,1000);
        RoaringBitmap rr2 = new RoaringBitmap();
//        rr2.add(4000L,4255L);
        rr2.add(444);
        // new bitmap
        RoaringBitmap rror = RoaringBitmap.or(rr, rr2);
        //in-place computation
        rr.or(rr2);
        // true
        boolean equals = rror.equals(rr);
        if(!equals) {
            throw new RuntimeException("bug");
        }
        // number of values stored?
        long cardinality = rr.getLongCardinality();
        IntIterator intIterator= rr.getIntIterator();
        System.out.println("遍历rr");
        while (intIterator.hasNext()){
            System.out.println(intIterator.next());
        }
        System.out.println("cardinality");
        System.out.println(cardinality);
        System.out.println("rr遍历");

        // a "forEach" is faster than this loop, but a loop is possible:
        for(int i : rr) {
            System.out.println(i);
        }
    }
}
