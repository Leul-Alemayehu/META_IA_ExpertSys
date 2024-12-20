import org.jpl7.*;

public class TestJPL {
    public static void main(String[] args) {
        Query q = new Query("member(8,[1,2,3,4])");
        boolean ans = q.hasSolution();
        System.out.println("Response: " + ans);
    }
}