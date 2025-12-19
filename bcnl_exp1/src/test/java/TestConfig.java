import com.krowfeather.config.ConfigUtil;
import org.junit.jupiter.api.Test;

public class TestConfig {
    @Test
    public void test() {
        System.out.println(ConfigUtil.getConfig("mysql.url"));
        System.out.println(ConfigUtil.getConfig("mysql.username"));
        System.out.println(ConfigUtil.getConfig("mysql.password"));
    }
}
