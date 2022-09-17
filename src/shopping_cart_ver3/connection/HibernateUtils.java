package shopping_cart_ver3.connection;

import java.util.Properties;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;

import shopping_cart_ver3.model.Odetail;
import shopping_cart_ver3.model.Product;
import shopping_cart_ver3.model.User;



public class HibernateUtils {
    private static final SessionFactory FACTORY;

    static {
        Configuration conf = new Configuration();

        Properties props = new Properties();
        props.put(Environment.DIALECT,"org.hibernate.dialect.MySQLDialect");
        props.put(Environment.DRIVER,"com.mysql.cj.jdbc.Driver");
        props.put(Environment.URL,"jdbc:mysql://bgkw5keudgvt1ll0urj7-mysql.services.clever-cloud.com:3306/bgkw5keudgvt1ll0urj7");
        props.put(Environment.USER,"uj0ffcflipppmjov");
        props.put(Environment.PASS,"neTnQXzejM78y3j7ijct");
        props.put(Environment.SHOW_SQL,"true");

        conf.setProperties(props);
        conf.addAnnotatedClass(User.class);
        conf.addAnnotatedClass(Odetail.class);
        conf.addAnnotatedClass(Product.class);


        ServiceRegistry registry = new StandardServiceRegistryBuilder()
                .applySettings(conf.getProperties()).build();
        FACTORY = conf.buildSessionFactory(registry);
    }
    public static SessionFactory getFactory(){
        return FACTORY;
    }

}

