/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.cvsystem.beans;

import edu.cvsystem.entidades.Productos;
import edu.cvsystem.facades.ProductosFacade;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.enterprise.context.Dependent;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.ChartSeries;
import org.primefaces.model.chart.PieChartModel;

/**
 *
 * @author Jorge Amado Perdomo
 */
@Named(value = "graficosBean")
@ViewScoped
@ManagedBean
public class GraficosBean implements Serializable{
 

 
    private PieChartModel pieModel1;
    private PieChartModel pieModel2;
 
    @PostConstruct
    public void init() {
        createPieModels();
    }
 
    public PieChartModel getPieModel1() {
        return pieModel1;
    }
     
    public PieChartModel getPieModel2() {
        return pieModel2;
    }
     
    private void createPieModels() {
        createPieModel1();
        createPieModel2();
    }
 
    private void createPieModel1() {
        pieModel1 = new PieChartModel();
         
        pieModel1.set("Brand 1", 540);
        pieModel1.set("Brand 2", 325);
        pieModel1.set("Brand 3", 702);
        pieModel1.set("Brand 4", 421);
         
        pieModel1.setTitle("Simple Pie");
        pieModel1.setLegendPosition("w");
    }
     
    private void createPieModel2() {
        pieModel2 = new PieChartModel();
         
        pieModel2.set("Brand 1", 540);
        pieModel2.set("Brand 2", 325);
        pieModel2.set("Brand 3", 702);
        pieModel2.set("Brand 4", 421);
         
        pieModel2.setTitle("Custom Pie");
        pieModel2.setLegendPosition("e");
        pieModel2.setFill(false);
        pieModel2.setShowDataLabels(true);
        pieModel2.setDiameter(150);
    }   
    
    
    
    
    
    
    
    
    
    
    
    
    
}
//
//    /**
//     * Creates a new instance of GraficosBean
//     */
//    public GraficosBean() {
//    }
//    ProductosFacade productoFacade;
//    private PieChartModel pieModel;
//    private BarChartModel  barModel;
//
//    public BarChartModel getBarModel() {
//        return barModel;
//    }
//
//    public void setBarModel(BarChartModel barModel) {
//        this.barModel = barModel;
//    }
//
//    public PieChartModel getPieModel() {
//        return pieModel;
//    }
//
//    public void setPieModel(PieChartModel pieModel) {
//        this.pieModel = pieModel;
//    }        
//@PostConstruct
//    public void init(){
//listar();
//}
//    public void listar() {
//        
//        List<Productos> lista;
//        lista=productoFacade.findAll();
//         List<Object[]> listaTotal=productoFacade.calcularTotal();      
//         graficarBarra(lista); 
//         //graficarTotales(listaTotal);
//         graficar(lista);
//    }
//private void graficarTotales(List<Object[]> lista){
// 
//        barModel = new BarChartModel();
//ChartSeries producto=new ChartSeries();
////ChartSeries total=new ChartSeries();
//producto.setLabel("Producto");
////total.setLabel("Total");
//        for (Object[] pro : lista) {
//            producto.set(pro[1],Integer.parseInt(pro[2].toString()));
//            //precio.set(pro.getProducto(), pro.getPrecio());
//        }
//        barModel.addSeries(producto);
//        //barModel.addSeries(precio);
//        barModel.setTitle("Totales");
//        barModel.setLegendPosition("e");
//      
//}
//
//
//    private void graficar(List<Productos> lista) {
//
//        pieModel = new PieChartModel();
//
//        for (Productos pro : lista) {
//            pieModel.set(pro.getNombre(), pro.getPrecio());
//        }
//        pieModel.setTitle("Cantidades");
//        pieModel.setLegendPosition("e");
//        pieModel.setFill(false);
//        pieModel.setShowDataLabels(true);
//        pieModel.setDiameter(150);
//    }
//    private void graficarBarra(List<Productos> lista) {
//
//        barModel = new BarChartModel();
//ChartSeries cantidad=new ChartSeries();
//ChartSeries precio=new ChartSeries();
//
//precio.setLabel("Precio");
//        for (Productos pro : lista) {
//            cantidad.set(pro.getNombre(), pro.getPrecio());
//           
//        }
//        barModel.addSeries(cantidad);
//        barModel.addSeries(precio);
//        barModel.setTitle("Cantidades/Precio");
//        barModel.setLegendPosition("e");
//      
//    }
//}
    
