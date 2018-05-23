import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan({"controllers", "config"})
public class Main {

    //region Defaults

    //endregion

    public static void main(String[] args) {

        ApplicationContext applicationContext = SpringApplication.run(Main.class, args);
    }
}