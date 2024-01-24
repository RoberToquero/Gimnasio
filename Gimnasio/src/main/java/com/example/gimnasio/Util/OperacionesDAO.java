package com.example.gimnasio.Util;

import model.ActividadHorario;
import model.Monitor;
import model.Sala;
import model.Usuario;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.SystemException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
public class OperacionesDAO implements OperacionesInterface{
    private SessionFactory factory = HibernateUtilConf.getFactory();

    @Override
    public List<Monitor> listarMonitores() {
        Transaction transaction = null;
        List<Monitor> monitores=null;
        try(Session session = factory.openSession()) {
            transaction = session.beginTransaction();
            monitores = session.createQuery("from Monitor ").list();

            transaction.commit();
        } catch (Exception e) {
            if(transaction != null)
                transaction.rollback();
        }
        return monitores;

    }

    @Override
    public void anadirMonitor(Monitor monitor) throws SystemException {
        Transaction transaction = null;
        try(Session session = factory.openSession()) {
            transaction = session.beginTransaction();
            session.save(monitor);
            transaction.commit();
        } catch (Exception e) {
            if(transaction != null)
                transaction.rollback();
        }

    }

    @Override
    public void modificarMonitor(Monitor monitor) {
        Transaction transaction = null;
        try(Session session = factory.openSession()) {
            transaction = session.beginTransaction();
            session.saveOrUpdate(monitor);
            transaction.commit();
        } catch (Exception e) {
            if(transaction != null)
                transaction.rollback();
        }

    }

    @Override
    public void borrarMonitor(int id_monitor) {
        Transaction transaction = null;
        try(Session session = factory.openSession()) {
            Monitor monitor1=session.get(Monitor.class, id_monitor);
            //borro el monitor
            session.beginTransaction();
            session.delete(monitor1);
            session.getTransaction().commit();

        } catch (Exception e) {
            if(transaction != null)
                transaction.rollback();
        }

    }

    @Override
    public Monitor obtenerMonitorPorID(int id_monitor) {
        try (Session session = factory.openSession()) {
            return session.get(Monitor.class, id_monitor);
        } catch (Exception e) {

            e.printStackTrace();
            return null;
        }

    }

    @Override
    public Sala obtenerSalaPorID(int id_sala) {
        try (Session session = factory.openSession()) {
            return session.get(Sala.class, id_sala);
        } catch (Exception e) {

            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<ActividadHorario> listarActividades() {
        Transaction transaction = null;
        List<ActividadHorario> actividades=null;
        try(Session session = factory.openSession()) {
            transaction = session.beginTransaction();
            actividades = session.createQuery("from ActividadHorario").list();

            transaction.commit();
        } catch (Exception e) {
            if(transaction != null)
                transaction.rollback();
        }
        return actividades;
    }

    @Override
    public void anadirActividad(ActividadHorario actividad) throws SystemException {
        Transaction transaction = null;
        try(Session session = factory.openSession()) {
            transaction = session.beginTransaction();
            session.save(actividad);
            transaction.commit();
        } catch (Exception e) {
            if(transaction != null)
                transaction.rollback();
        }
    }

    @Override
    public void modificarActividad(ActividadHorario actividad) {
        Transaction transaction = null;
        try(Session session = factory.openSession()) {
            transaction = session.beginTransaction();
            session.saveOrUpdate(actividad);
            transaction.commit();
        } catch (Exception e) {
            if(transaction != null)
                transaction.rollback();
        }
    }

    @Override
    public void borrarActividad(int id_actividad) {
        Transaction transaction = null;
        try(Session session = factory.openSession()) {
            ActividadHorario actividad1=session.get(ActividadHorario.class, id_actividad);
            //borro el monitor
            session.beginTransaction();
            session.delete(actividad1);
            session.getTransaction().commit();

        } catch (Exception e) {
            if(transaction != null)
                transaction.rollback();
        }
    }

    @Override
    public ActividadHorario obtenerActividadPorId(int id_actividad) {
        try (Session session = factory.openSession()) {
            return session.get(ActividadHorario.class, id_actividad);
        } catch (Exception e) {

            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<ActividadHorario> listarActividadesDia(String dia) {
        Transaction transaction = null;
        List<ActividadHorario> actividades=new ArrayList<>();
        try (Session session = factory.openSession()) {
            transaction = session.beginTransaction();

            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<ActividadHorario> query = builder.createQuery(ActividadHorario.class);
            Root<ActividadHorario> root = query.from(ActividadHorario.class);

            // restricción para filtrar por el día
            query.select(root).where(builder.equal(root.get("diaSemana"), dia));

            actividades = session.createQuery(query).getResultList();
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null)
                transaction.rollback();
        }
        return actividades;
    }

    @Override
    public List<Usuario> listarUsuarios() {
        Transaction transaction = null;
        List<Usuario> usuarios=null;
        try(Session session = factory.openSession()) {
            transaction = session.beginTransaction();
            usuarios = session.createQuery("from Usuario ").list();

            transaction.commit();
        } catch (Exception e) {
            if(transaction != null)
                transaction.rollback();
        }
        return usuarios;
    }

    @Override
    public void anadirUsuario(Usuario usuario) throws SystemException {
        Transaction transaction = null;
        try(Session session = factory.openSession()) {
            transaction = session.beginTransaction();
            session.save(usuario);
            transaction.commit();
        } catch (Exception e) {
            if(transaction != null)
                transaction.rollback();
        }

    }

    @Override
    public void modificarUsuario(Usuario usuario) {
        Transaction transaction = null;
        try(Session session = factory.openSession()) {
            transaction = session.beginTransaction();
            session.saveOrUpdate(usuario);
            transaction.commit();
        } catch (Exception e) {
            if(transaction != null)
                transaction.rollback();
        }
    }

    @Override
    public void borrarUsuario(int id_usuario) {
        Transaction transaction = null;
        try(Session session = factory.openSession()) {
            Usuario usuario1=session.get(Usuario.class, id_usuario);
            //borro el usuario
            session.beginTransaction();
            session.delete(usuario1);
            session.getTransaction().commit();

        } catch (Exception e) {
            if(transaction != null)
                transaction.rollback();
        }
    }

    @Override
    public Boolean existeUsuario(String nombre, String contrasena) {
        try (Session session = factory.openSession()) {
            // Cargar el usuario desde la base de datos usando el nombre de usuario
            Usuario usuario = (Usuario) session.createQuery("FROM Usuario WHERE nombre = :nombre")
                    .setParameter("nombre", nombre)
                    .uniqueResult();

            // Verifica si el usuario existe y la contraseña coincide (si es necesario)
            return usuario != null && (usuario.getContrasena().equals(contrasena));
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Boolean comprobarRegistro(String nombreUsuario) {
        try (Session session = factory.openSession()) {
            // Cargar el usuario desde la base de datos usando el nombre de usuario
            Usuario usuario = (Usuario) session.createQuery("FROM Usuario WHERE nombre = :nombre")
                    .setParameter("nombre", nombreUsuario)
                    .uniqueResult();

            // Verifica si el usuario existe y está registrado
            return usuario != null && usuario.getRegistrado();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Boolean existeUsuarioPorNombre(String nombre) {
        try (Session session = factory.openSession()) {
            // Cargo el usuario desde la base de datos usando el nombre de usuario
            Usuario usuario = (Usuario) session.createQuery("FROM Usuario WHERE nombre = :nombre")
                    .setParameter("nombre", nombre)
                    .uniqueResult();

            // Verifico si el usuario existe
            return usuario != null;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Boolean existeUsuarioPorNombreExcluyendoId(String nombre, int id) {
        try (Session session = factory.openSession()) {
            // Cargar el usuario desde la base de datos usando el nombre de usuario y excluyendo por ID
            Usuario usuario = (Usuario) session.createQuery("FROM Usuario WHERE nombre = :nombre AND id_Usuario != :id")
                    .setParameter("nombre", nombre)
                    .setParameter("id", id)
                    .uniqueResult();

            // Verifica si el usuario existe
            return usuario != null;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Boolean existeActividadEnHorarioConIdYDiferenteSala(String diaSemana, String horaInicio, Sala sala, Monitor monitor, int idActividad) {
        try (Session session = factory.openSession()) {
            // Consulta HQL para verificar la existencia de una actividad en el mismo horario, mismo día, mismo monitor, pero diferente sala
            Query query = session.createQuery("SELECT COUNT(*) FROM ActividadHorario " +
                            "WHERE diaSemana = :diaSemana " +
                            "AND horaInicio = :horaInicio " +
                            "AND monitor = :monitor " +
                            "AND sala <> :sala " +
                            "AND idActividad <> :idActividad") // Excluir la actividad con el mismo ID
                    .setParameter("diaSemana", diaSemana)
                    .setParameter("horaInicio", horaInicio)
                    .setParameter("monitor", monitor)
                    .setParameter("sala", sala)
                    .setParameter("idActividad", idActividad);

            // Obtenemos el resultado de la consulta (número de coincidencias)
            Long count = (Long) query.uniqueResult();

            // Si el resultado es mayor que cero, significa que ya existe una actividad en ese horario, día, monitor, pero con diferente sala (excluyendo la actividad actual)
            return count > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    @Override
    public Boolean existeActividadEnHorarioConIdYDiferenteMonitor(String diaSemana, String horaInicio, Sala sala, Monitor monitor, int idActividad) {
        try (Session session = factory.openSession()) {
            // Consulta HQL para verificar la existencia de una actividad en el mismo horario, mismo día, misma sala, pero diferente monitor
            Query query = session.createQuery("SELECT COUNT(*) FROM ActividadHorario " +
                            "WHERE diaSemana = :diaSemana " +
                            "AND horaInicio = :horaInicio " +
                            "AND sala = :sala " +
                            "AND monitor <> :monitor " +
                            "AND idActividad <> :idActividad") // Excluir la actividad con el mismo ID
                    .setParameter("diaSemana", diaSemana)
                    .setParameter("horaInicio", horaInicio)
                    .setParameter("sala", sala)
                    .setParameter("monitor", monitor)
                    .setParameter("idActividad", idActividad);

            // Obtenemos el resultado de la consulta (número de coincidencias)
            Long count = (Long) query.uniqueResult();

            // Si el resultado es mayor que cero, significa que ya existe una actividad en ese horario, día, sala, pero con diferente monitor (excluyendo la actividad actual)
            return count > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Boolean existeActividadEnHorarioConIdYMonitorYSala(String diaSemana, String horaInicio, Sala sala, Monitor monitor, int idActividad) {
        try (Session session = factory.openSession()) {
            // Consulta HQL para verificar la existencia de una actividad en el mismo horario, mismo día, misma sala y mismo monitor
            Query query = session.createQuery("SELECT COUNT(*) FROM ActividadHorario " +
                            "WHERE diaSemana = :diaSemana " +
                            "AND horaInicio = :horaInicio " +
                            "AND sala = :sala " +
                            "AND monitor = :monitor " +
                            "AND idActividad <> :idActividad") // Excluir la actividad con el mismo ID
                    .setParameter("diaSemana", diaSemana)
                    .setParameter("horaInicio", horaInicio)
                    .setParameter("sala", sala)
                    .setParameter("monitor", monitor)
                    .setParameter("idActividad", idActividad);

            // Obtenemos el resultado de la consulta (número de coincidencias)
            Long count = (Long) query.uniqueResult();

            // Si el resultado es mayor que cero, significa que ya existe una actividad en ese horario, día, sala y monitor (excluyendo la actividad actual)
            return count > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }


    @Override
    public Boolean excedeLimiteClasesEnMismoDia(Monitor monitor, String dia) {
        long clasesEnMismoDia = monitor.getActividades().stream()
                .filter(actividad -> actividad.getDiaSemana().equals(dia))
                .count();

        return clasesEnMismoDia >= 4;
    }

    @Override
    public Boolean existeMonitor(int idMonitor, String nombre, String contrasena) {
        try (Session session = factory.openSession()) {
            // Cargar el monitor desde la base de datos usando el id del monitor
            Monitor monitor = session.get(Monitor.class, idMonitor);

            // Verifica si el monitor existe y los datos coinciden
            return monitor != null && monitor.getNombre().equals(nombre) && monitor.getContrasena().equals(contrasena);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public List<ActividadHorario> listarActividadesDiaYMonitor(String dia, int monitorId) {
        try (Session session = factory.openSession()) {
            // Consulta para obtener las actividades por día y monitor
            String hql = "FROM ActividadHorario a WHERE a.diaSemana = :dia AND a.monitor.id_monitor = :monitorId";

            // Ejecutar la consulta
            List<ActividadHorario> actividades = session.createQuery(hql, ActividadHorario.class)
                    .setParameter("dia", dia)
                    .setParameter("monitorId", monitorId)
                    .list();

            return actividades;
        } catch (Exception e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }

    @Override
    public int obtenerIdUsuarioPorNombre(String nombreUsuario) {
        try (Session session = factory.openSession()) {
            // Realizar una consulta para obtener el ID del usuario por su nombre
            Usuario usuario = (Usuario) session.createQuery("FROM Usuario WHERE nombre = :nombre")
                    .setParameter("nombre", nombreUsuario)
                    .uniqueResult();

            if (usuario != null) {
                return usuario.getId_Usuario();
            } else {
                // El usuario no fue encontrado
                return -1; // Otra forma de indicar que no se encontró el usuario
            }
        } catch (Exception e) {
            e.printStackTrace();
            return -1; // Manejar la excepción según tus necesidades
        }
    }

    @Override
    public Usuario obtenerUsuarioPorId(int id_Usuario) {
        try (Session session = factory.openSession()) {
            return session.get(Usuario.class, id_Usuario);
        } catch (Exception e) {

            e.printStackTrace();
            return null;
        }
    }


}

