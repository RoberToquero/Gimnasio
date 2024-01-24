package com.example.gimnasio.Util;
import model.*;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;
import java.util.Properties;

public class HibernateUtilConf {
	private static SessionFactory factory;
	public static SessionFactory getFactory() {
		if (factory == null) {
			Configuration configuration = new Configuration();
			Properties settings = new Properties();
			settings.put(Environment.DRIVER, "com.mysql.cj.jdbc.Driver");
			settings.put(Environment.URL, "jdbc:mysql://localhost:3306/gimnasio"); //NOMBRE DE LA BASE DE DATOS
			settings.put(Environment.USER, "root");
			settings.put(Environment.PASS, "toor");
			settings.put(Environment.DIALECT, "org.hibernate.dialect.MySQL8Dialect");
			settings.put(Environment.SHOW_SQL, "false");
			settings.put(Environment.HBM2DDL_AUTO, "update");

			configuration.setProperties(settings);
			//TABLAS QUE ESTAN MAPEADAS QUE FORMAN PARTE DE LA BASE DE DATOS
			configuration.addAnnotatedClass(Monitor.class);
			configuration.addAnnotatedClass(Usuario.class);
			configuration.addAnnotatedClass(ActividadHorario.class);
			configuration.addAnnotatedClass(Sala.class);


			ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
					.applySettings(configuration.getProperties()).build();

			factory = configuration.buildSessionFactory(serviceRegistry);

		}
		return factory;
	}

}
