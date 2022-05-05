import java.time.ZonedDateTime;

/**
 * @author Edgar
 * @create 2022-05-05 15:11
 * @faction:
 */
public class Test {

    public static void main(String[] args) {

        /*使用这个类，可以具体的算出系统的时间，以及
        * 那个地区的*/
        ZonedDateTime now = ZonedDateTime.now();
        System.out.println(now);
    }
}
