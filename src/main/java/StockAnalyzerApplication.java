import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

//import stockanalyzer.api.models.User;
@SpringBootApplication
public class StockAnalyzerApplication extends SpringBootServletInitializer{

    public static void main(String[] args) {
        SpringApplication.run(StockAnalyzerApplication.class, args);
    }
}

