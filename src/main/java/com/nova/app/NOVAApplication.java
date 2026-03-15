package com.nova.app;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.boot.autoconfigure.data.jpa.JpaRepositoriesAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication(exclude = {
    DataSourceAutoConfiguration.class,
    HibernateJpaAutoConfiguration.class,
    JpaRepositoriesAutoConfiguration.class
})
@ComponentScan(basePackages = "com.nova")
public class NOVAApplication {
    public static void main(String[] args) {
        SpringApplication.run(NOVAApplication.class, args);
        System.out.println("\n");
        System.out.println("╔══════════════════════════════════════════╗");
        System.out.println("║        NOVA AI is Running!           ║");
        System.out.println("║   AI-Powered Coffee Recommendation       ║");
        System.out.println("║   Visit: http://localhost:8080           ║");
        System.out.println("╚══════════════════════════════════════════╝");
    }
}
