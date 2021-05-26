package registros.clima;

import utilerias.tiempo.Fecha;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
* InterfazPrediccionClima
*/
public class InterfazPrediccionClima extends JFrame {
	private AdministracionClima adminClima;

	public void generarPrediccion(){
		adminClima = new AdministracionClima();
	}

	public void init() {
		pnlPrincipal = new JPanel();
		FlowLayout lytPrincipal = new FlowLayout();
		pnlPrincipal.setLayout(lytPrincipal);

		BorderLayout lyt = new BorderLayout(2, 2);
		setLayout(lyt);

		GridLayout lytLeft = new GridLayout(3,1);
		pnlLeft = new JPanel();
		pnlLeft.setLayout(lytLeft);

		lblCalcularPrediccionTitulo = new JLabel("Calcular predicción", JLabel.CENTER);
		SpinnerModel limitesSpinner = new SpinnerNumberModel(1, 1, 365, 1);
		spDiaPrediccion = new JSpinner(limitesSpinner);

		btnCalcularPrediccion = new JButton("Calcular");
		btnCalcularPrediccion.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent actionEvent) {
				int dia = (Integer) spDiaPrediccion.getValue();
				double octa = adminClima.obtenerOcta(dia);
				double precipitacion = adminClima.calcularPrecipitacion(dia);
				String fecha = Fecha.obtenerFechaPorNumero(dia);
				String tipoPrecipitacion = adminClima.obtenerTipoPrecipitacion(precipitacion);

				String mensaje = "Para el día "+fecha+" del año 2021 se tiene una nubosidad de "+octa+" octas, y por \n" +
								"ende una tentativa tasa de lluvia de "+precipitacion+" mm/h de lluvia, quiere decir que\n" +
								" ese día del año el tipo de precipitación es "+tipoPrecipitacion+".";

				JOptionPane.showMessageDialog(null,mensaje);
			}
		});

		pnlLeft.add(lblCalcularPrediccionTitulo);

		GridLayout lytDia = new GridLayout(1,2);
		pnlDia = new JPanel();
		pnlDia.setLayout(lytDia);

		lblCalcularPrediccion = new JLabel("Día");

		pnlDia.add(lblCalcularPrediccion);
		pnlDia.add(spDiaPrediccion);

		pnlLeft.add(pnlDia);
		pnlLeft.add(btnCalcularPrediccion);

		pnlRight = new JPanel();
		pnlRight.setLayout(lytLeft);

		btnMostrarGrafica = new JButton("Mostrar gráfica");
		lblMostrarGrafica = new JLabel("Gráfica", JLabel.CENTER);
		btnMostrarGrafica.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent actionEvent) {
				adminClima.graficarResultados();
			}
		});

		pnlRight.add(lblMostrarGrafica);
		pnlRight.add(btnMostrarGrafica);

		pnlPrincipal.add(pnlLeft);
		pnlPrincipal.add(pnlRight);

		add(pnlPrincipal, BorderLayout.CENTER);

		setTitle("Predicción del Clima");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(300,300);
		setVisible(true);
	}
	JPanel pnlPrincipal;
	JPanel pnlLeft;
	JPanel pnlRight;
	JPanel pnlDia;

	JLabel lblMostrarGrafica;
	JLabel lblCalcularPrediccion;
	JLabel lblCalcularPrediccionTitulo;

	JButton btnMostrarGrafica;
	JButton btnCalcularPrediccion;

	JSpinner spDiaPrediccion;
}
