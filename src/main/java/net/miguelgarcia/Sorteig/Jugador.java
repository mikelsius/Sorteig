/**
 * 
 */
package net.miguelgarcia.Sorteig;

import java.util.ArrayList;
import java.util.Collections;

/**
 * @author Mikel
 *
 */
public class Jugador {
	/**
	 * nom del jugador
	 */
	private String nom;
	/**
	 * monedes que te
	 */
	private int monedes = 0;
	/**
	 * variable que ens diu si el jugador esta jugan
	 * aquesta ronda o no.
	 */
	private boolean estaJugan = true;
	/**
	 * punts del jugador
	 */
	private int cartaAlta = 0;
	/**
	 * creem un Arraylist de cartes per cada jugador.
	 */
	ArrayList<Carta> cartesJugador = new ArrayList<Carta>();
	/**
	 *
	 * @return ens dona les cartes que te el jugador.
	 */
	public final ArrayList<Carta> getCartesJugador() {
		return cartesJugador;
	}
	/**
	 *
	 * @param elnom contructor, li pasem el nom del jugador.
	 */
	public Jugador(String elnom) {
		nom = elnom;
	}
	/**
	 *
	 * @param juga li passem si continua jugan o no.
	 * @return retorna l'estat del jugador.
	 */
	public final boolean setestaJugan(boolean juga) {
		estaJugan = juga;
		return estaJugan;
	}
	/**
	 *
	 * @return ens diu si el jugador esta jugan actualment.
	 */
	public final boolean getestaJugan() {
		return estaJugan;
	}
	/**
	 *
	 * @param miCarta li sumem una carta a l'arraylist de cartas.
	 */
	public final void darCarta(Carta miCarta) {
	     cartesJugador.add(miCarta);
	}
	/**
	 *
	 * @return ens diu el nom del jugador.
	 */
    public final String getNom() {
		return nom;
	}
    /**
     *
     * @param nom li passem el nou nom
     */
	public final void setNom(String nom) {
		this.nom = nom;
	}
	/**
	 *
	 * @param puntss nous punts del jugador
	 */
	public final void setCartaAlta(int CartaAlta) {
		this.cartaAlta = CartaAlta;
	}
	/**
	 *
	 * @return retorna els punts del jugador
	 */
	public final int getCartaAlta() {
		return cartaAlta;
	}
	/**
	 * ordena les cartes del jugador.
	 */
	public final void ordenarCartes() {
		Collections.sort(cartesJugador);
	}
	public final void setMonedes(int Monedes) {
		this.monedes = Monedes;
	}
	public final int getMonedes() {
		return monedes;
	}
	/**
	 *@return nom del jugador.
	 */
	public final String toString() {
        return nom;
    }
}
