import org.springframework.context.annotation.Configuration
import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.context.annotation.ComponentScan
import org.springframework.boot.SpringApplication

@Configuration
@EnableAutoConfiguration
@ComponentScan(basePackages = Array("controllers"))
class Application


object Main extends App {
  SpringApplication.run(classOf[Application]);
}