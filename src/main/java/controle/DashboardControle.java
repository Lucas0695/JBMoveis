package controle;

import facade.ContasPagarFacade;
import facade.ContasReceberFacade;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.ChartSeries;
import org.primefaces.model.chart.LineChartModel;
import org.primefaces.model.chart.LineChartSeries;
import util.ContasView;


@ManagedBean
@SessionScoped
public class DashboardControle implements Serializable {

    @EJB
    private ContasPagarFacade contasPagarFacade;
    @EJB
    private ContasReceberFacade contasReceberFacade;
    private LineChartModel animatedModel1;
    private BarChartModel animatedModel2;


    public void atualizar() {
        createAnimatedModels();
    }

    public LineChartModel getAnimatedModel1() {
        return animatedModel1;
    }

    public BarChartModel getAnimatedModel2() {
        return animatedModel2;
    }

    private void createAnimatedModels() {
        animatedModel1 = initLinearModel();
        animatedModel1.setTitle("Contas a Receber/Pagar");
        animatedModel1.setAnimate(true);
        animatedModel1.setLegendPosition("se");
        Axis yAxis = animatedModel1.getAxis(AxisType.Y);
//        yAxis.setMin(0);
//        yAxis.setMax(10000);

        animatedModel2 = initBarModel();
        animatedModel2.setTitle("Contas a Receber/Pagar");
        animatedModel2.setAnimate(true);
        animatedModel2.setLegendPosition("ne");
        yAxis = animatedModel2.getAxis(AxisType.Y);
//        yAxis.setMin(0);
//        yAxis.setMax(10000);
    }

    private BarChartModel initBarModel() {
        BarChartModel model = new BarChartModel();

        ChartSeries receita = new ChartSeries();
        receita.setLabel("Receita");
        for (ContasView cr : contasReceberFacade.listaCRAgrupado()) {
            receita.set(cr.getMes(), cr.getValor());
        }
        ChartSeries despesa = new ChartSeries();
        despesa.setLabel("Despesa");
        for (ContasView cp : contasPagarFacade.listaCPAgrupado()) {
            despesa.set(cp.getMes(), cp.getValor());
        }
        model.addSeries(receita);
        model.addSeries(despesa);

        return model;
    }

    private LineChartModel initLinearModel() {
        LineChartModel model = new LineChartModel();

        LineChartSeries receita = new LineChartSeries();
        receita.setLabel("Receita");
        for (ContasView cr : contasReceberFacade.listaCRAgrupado()) {
            receita.set(cr.getMes(), cr.getValor());
        }        
        
        LineChartSeries despesa = new LineChartSeries();
        despesa.setLabel("Despesa");
        for (ContasView cp : contasPagarFacade.listaCPAgrupado()) {
            despesa.set(cp.getMes(), cp.getValor());
        }
        model.addSeries(receita);
        model.addSeries(despesa);

        return model;
    }

}
