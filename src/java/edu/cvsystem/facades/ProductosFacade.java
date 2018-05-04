/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.cvsystem.facades;

import edu.cvsystem.entidades.Productos;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Jorge Amado Perdomo
 */
@Stateless
public class ProductosFacade extends AbstractFacade<Productos> {

    @PersistenceContext(unitName = "CVSystemPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ProductosFacade() {
        super(Productos.class);
    }

    public EntityManager getEm() {
        return em;
    }
    //metodo para listar en graficos
     public  List<Object[]> calcularTotal(){
//        Query query=em.createNativeQuery("SELECT producto.id_producto, producto.producto, producto.cantidad *  producto.precio AS Total FROM  producto");
       List<Object[]>  result=em.createQuery("SELECT p.idProducto,p.nombre, p.precio as Total  FROM  Productos p").getResultList();
//       List<Object[]>  result=em.createQuery("SELECT p.idProducto,p.nombre,(p.cantidad*p.precio) as Total  FROM  Producto p").getResultList();
//        List<Object[]>  result=query.getResultList();
       for(Object[] object : result) {
            System.out.println("Codigo: "+object[0]+ ", Nombre: "+object[1]+ ", Total: "+object[2]);
        }
       return result;
    
}
     public String importar(String archivo ,String tabla){
        Query query = em.createNativeQuery("LOAD DATA LOCAL INFILE '"+archivo+"' REPLACE INTO TABLE "+tabla+" FIELDS TERMINATED BY ';' ENCLOSED BY '\\\"' ESCAPED BY '\\\\' LINES TERMINATED BY '\\r\\n'");
        int resultado = query.executeUpdate();
        String frase = resultado+" filas afectadas";
        return frase;
        
    }
   
    
}
