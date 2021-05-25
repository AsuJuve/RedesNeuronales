package registros.clima;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import java.awt.*;

/**
* InterfazPrediccionClima
*/
public class InterfazPrediccionClima extends JFrame {

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
		tfCalcularPrediccion = new JSpinner();
		btnCalcularPrediccion = new JButton("Calcular");

		pnlLeft.add(lblCalcularPrediccionTitulo);

		GridLayout lytDia = new GridLayout(1,2);
		pnlDia = new JPanel();
		pnlDia.setLayout(lytDia);

		lblCalcularPrediccion = new JLabel("Dia");

		pnlDia.add(lblCalcularPrediccion);
		pnlDia.add(tfCalcularPrediccion);

		pnlLeft.add(pnlDia);
		pnlLeft.add(btnCalcularPrediccion);

		pnlRight = new JPanel();
		pnlRight.setLayout(lytLeft);

		btnMostrarGrafica = new JButton("Mostrar grafica");
		lblMostrarGrafica = new JLabel("Grafica", JLabel.CENTER);

		pnlRight.add(lblMostrarGrafica);
		pnlRight.add(btnMostrarGrafica);

		pnlPrincipal.add(pnlLeft);
		pnlPrincipal.add(pnlRight);

		add(pnlPrincipal, BorderLayout.CENTER);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
	JSpinner tfCalcularPrediccion;
	JLabel lblCalcularPrediccion;
	JLabel lblCalcularPrediccionTitulo;
}
