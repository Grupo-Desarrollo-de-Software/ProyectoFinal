package co.edu.ean.RegistradoraGastos.daos;

import co.edu.ean.RegistradoraGastos.entidades.TblRegistrosEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.math.BigDecimal;
import java.util.List;

@Transactional
@Component
@Repository
public class TblRegistrosDAOImpl implements TblRegistrosDAO{

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("EAN_PU");
    private EntityManager em = emf.createEntityManager();
    EntityTransaction trans = em.getTransaction();

    public TblRegistrosDAOImpl(){
    }

    public TblRegistrosEntity crear (TblRegistrosEntity tblRegistros){
        trans.begin();
        em.persist(tblRegistros);
        trans.commit();
        return tblRegistros;
    }

    public List<TblRegistrosEntity> buscarTodos() {
        return em.createNamedQuery("TblRegistrosEntity.findAll").getResultList();
    }

    public void actualizarRegistro(TblRegistrosEntity registro) {
        trans.begin();
        em.createNativeQuery("UPDATE TBL_REGISTROS SET TIPO_REGISTRO = ?strTipoReg, MONTO_REGISTRO = ?strMonto, DESCRIP_REGISTRO = ?strDescrip WHERE ID_REGISTRO = ?strIdReg").setParameter("strTipoReg",
                registro.getTipoRegistro()).setParameter("strDescrip",
                registro.getDescripcionRegistro()).setParameter("strMonto",
                registro.getMontoRegistro()).setParameter("strIdReg",
                registro.getIdRegistro()).executeUpdate();
        trans.commit();
    }

    public void borrarRegistro(BigDecimal registro){
        trans.begin();
        em.createNativeQuery("DELETE FROM TBL_REGISTROS WHERE ID_REGISTRO = ?strIdReg").setParameter("strIdReg", registro).executeUpdate();
        trans.commit();
    }

    public void borrarTabla(){
        trans.begin();
        em.createNativeQuery("DELETE FROM TBL_REGISTROS").executeUpdate();
        trans.commit();
    }
}
