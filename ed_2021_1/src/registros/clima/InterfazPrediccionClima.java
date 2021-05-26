package registros.clima;

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

		lblTitulo = new JLabel("Prediccion del clima", JLabel.CENTER);
		add(lblTitulo, BorderLayout.PAGE_START);

		GridLayout lytLeft = new GridLayout(3,1);
		pnlLeft = new JPanel();
		pnlLeft.setLayout(lytLeft);

		lblCalcularPrediccionTitulo = new JLabel("Calcular prediccion", JLabel.CENTER);
		spDiaPrediccion = new JSpinner();
		btnCalcularPrediccion = new JButton("Calcular");
		btnCalcularPrediccion.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent actionEvent) {
				double precipitacion = adminClima.calcularPrediccion((Integer) spDiaPrediccion.getValue());
				String mensaje = "La precipitación para ese día es de "+precipitacion;
				JOptionPane.showMessageDialog(null,mensaje);
			}
		});

		pnlLeft.add(lblCalcularPrediccionTitulo);

		GridLayout lytDia = new GridLayout(1,2);
		pnlDia = new JPanel();
		pnlDia.setLayout(lytDia);

		lblCalcularPrediccion = new JLabel("Dia");

		pnlDia.add(lblCalcularPrediccion);
		pnlDia.add(spDiaPrediccion);

		pnlLeft.add(pnlDia);
		pnlLeft.add(btnCalcularPrediccion);

		pnlRight = new JPanel();
		pnlRight.setLayout(lytLeft);

		btnMostrarGrafica = new JButton("Mostrar grafica");
		lblMostrarGrafica = new JLabel("Grafica", JLabel.CENTER);
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

	JLabel lblTitulo;

	JPanel pnlPrincipal;
	JPanel pnlLeft;
	JPanel pnlRight;
	JPanel pnlDia;

	JButton btnMostrarGrafica;
	JLabel lblMostrarGrafica;

	JButton btnCalcularPrediccion;
	JSpinner spDiaPrediccion;
	JLabel lblCalcularPrediccion;
	JLabel lblCalcularPrediccionTitulo;
}
