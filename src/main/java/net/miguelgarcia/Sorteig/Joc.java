/**
 *
 */
package net.miguelgarcia.Sorteig;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

/**
 * @author Mikel
 *
 */
public class Joc {
  /**
   *
   */
  Scanner lector = new Scanner(System.in);
    private int bote = 0;
  /**
   * arraylist de jugadors.
   */
  private ArrayList<Jugador> jugadors = new ArrayList<Jugador>();
  /**
   * arraylist de cartes.
   */
  private ArrayList<Carta> cartas = new ArrayList<Carta>();
  /**
   * numero de jugador de la partida.
   */
  private int nombreJugadors = 0;

  /**
   *
   * @param jugadors
   *            que jugaran aquesta partida.
   */
  public Joc(final int jugadors) {
    nombreJugadors = jugadors;
    crearJugadors();
    crearCartes();
  }

  public final void jugarMa() {
    afegirJugadors();
    apostes();
    ordenarCartes();
    mostrarCartes();
    seleccionarGuanyadors();
    repartimMonedes();
    recullirCartes();
    comprobarQueTenenMonedes();
  }

  public final void crearJugadors() {
    for (int i = 0; i < nombreJugadors; i++) {
      System.out.println("Nom del " + (i + 1) + " jugador:");
      jugadors.add(new Jugador(lector.next()));
      System.out.println("Quantes monedes té en "
          + jugadors.get(i).getNom());
      jugadors.get(i).setMonedes(lector.nextInt());
    }
  }

  public final void crearCartes() {
    String[] palCartes = { "C", "D", "P", "T" }; // Cors,Diamants,Picas,Trebols
    int[] numCartes = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13 };

    for (int i = 0; i < palCartes.length; i++) {
      for (int j = 0; j < numCartes.length; j++) {
        cartas.add(new Carta(palCartes[i], numCartes[j]));
      }
    }
  }

  public final void afegirJugadors() {
    System.out.println("Vol jugar algú més? Si(s) No(n)");
    boolean banderola = true;
    while (banderola) {
      String lletra = lector.next();
      if (lletra.equals("s")) {
        System.out.println("Quants jugadors més volen jugar?");
        int maxim = lector.nextInt();
        for (int i = 0; i < maxim; i++) {
          System.out.println("Nom del " + (i + 1)
              + " jugador a afegir:");
          jugadors.add(new Jugador(lector.next()));
          // Habiam si aixi em mostra l'ultim afegit...
          System.out.println("Quantes monedes té en "
              + jugadors.get(jugadors.size() - 1).getNom());
          jugadors.get(jugadors.size() - 1).setMonedes(
              lector.nextInt());
        }
        banderola = false;
      } else if (lletra.equals("n")) {

        banderola = false;
      } else {
        System.out.println("Si (s) o No (n).");
      }
    }
  }

  public final void apostes() {
    Random rnd = new Random();
    for (int i = 0; i < jugadors.size(); i++) {
      // He de comprobar que hi hagin suficients cartes...
      int numero = 0;
            String paraula = "";
            boolean semafor = false;
            jugadors.get(i).setestaJugan(true);
      do {
        System.out.println(jugadors.get(i).getNom()+" tens: "+jugadors.get(i).getMonedes()+" monedes.");
                System.out.println("Vols [J]ugar, [P]asar o [R]etirarte?");
                paraula = lector.next();
                if (paraula.equals("J")) { // El jugador aposta.
              System.out.println("Quantes monedes vols apostar?");
              numero = lector.nextInt();
                  if (numero <= cartas.size() && comprobarDiners(i,numero)) {
                    for (int j = 0; j < numero; j++) {
                int numRandom = rnd.nextInt(cartas.size());
                jugadors.get(i).darCarta(cartas.get(numRandom));
                cartas.remove(numRandom);
              }
                    jugadors.get(i).setMonedes(jugadors.get(i).getMonedes() - numero);
                    bote += numero;
              semafor = true;
                  } else {
              System.out.println("No hi han suficients cartes o no tens suficients diners!");
              System.out.println("Actualment hi han :" + (cartas.size())
                  + " cartes a la baralla.");
              //System.out.println(jugadors.get(i).getNom()+" tens: "+jugadors.get(i).getMonedes()+" monedes.");
              System.out.println("Sempre pots pasar o retirarte.");
                  }
                }
                if (paraula.equals("P")) { // El jugador pasa d'aquesta ma.
                    jugadors.get(i).setestaJugan(false);
                    semafor = true;
                }
                if (paraula.equals("R")) { // Eliminem el jugador.
                    System.out.println("El jugador: "
                            + jugadors.get(i).getNom()
                            + " abandona el joc amb :"
                            + jugadors.get(i).getMonedes()+" monedes.");
                    jugadors.remove(i);
                    i--;
                    semafor = true;
                }

            } while (!semafor);
    }
  }
    public final void ordenarCartes() {
        for (int i = 0; i < jugadors.size(); i++) {
            jugadors.get(i).ordenarCartes();
        }
    }
  public final boolean comprobarDiners(int i, int numero) {
    return jugadors.get(i).getMonedes() >= numero ;
  }
  public final void mostrarCartes() {
    for (int i = 0; i < jugadors.size(); i++){
      if (jugadors.get(i).getestaJugan()){
      System.out.println("El jugador :"+jugadors.get(i).getNom()+" té les cartes: "+jugadors.get(i).getCartesJugador());
      puntuacions(i);
      System.out.println("La carta més alta és :"+jugadors.get(i).getCartaAlta());
      }
    }
  }
  public final void puntuacions(int i){
    //aixi obtenim l'ultima carta, estan ordenades.
    int millorCarta = jugadors.get(i).cartesJugador.get(jugadors.get(i).cartesJugador.size()-1).getNum();
    jugadors.get(i).setCartaAlta(millorCarta);
  }
    public final void seleccionarGuanyadors() {
      int max = 0;
      //Miro el que te la carta més alta.
      for (int i = 0; i < jugadors.size(); i++) {
        int a = jugadors.get(i).getCartaAlta();
        if (a > max) {
          max = a;
        }
      }
      //Trec els que no tenen la carta més alta.
      for (int i = 0; i < jugadors.size(); i++) {
        if (jugadors.get(i).getCartaAlta() != max) {
          jugadors.get(i).setestaJugan(false);
        }
      }
    }
    public final void recullirCartes() {
        for (int i = 0; i < jugadors.size(); i++) {
            jugadors.get(i).cartesJugador.clear();
        }
        cartas.clear();
        crearCartes();
    }
    public final void repartimMonedes() {
        int j = 0;
        for (int i = 0; i < jugadors.size(); i++) {
            if (jugadors.get(i).getestaJugan()) {
                j++;
            }
        }
        for (int i = 0; i < jugadors.size(); i++) {
            if (jugadors.get(i).getestaJugan()) {
                jugadors.get(i).setMonedes(
                        jugadors.get(i).getMonedes() + bote / j);
                System.out.println("El jugador " + jugadors.get(i).getNom() + " ha guanyat " + bote / j + " monedes.");
            }
        }
        bote = 0;
    }
    public final void comprobarQueTenenMonedes() {
      for (int i = 0; i < jugadors.size(); i++) {
      if (jugadors.get(i).getMonedes() == 0) { //Si el jugador te 0 euros l'eliminem.
        System.out.println(jugadors.get(i).getNom() + " no tens monedes per jugar. Eliminat.");
        jugadors.remove(i);
      }
      }
    }
    public final boolean shaAcabat() {
        if (jugadors.size() <= 1) {
          if (jugadors.size() == 1) {
            System.out.println("////////////////////////////////");
            System.out.println("Ha guanyat el jugador " + jugadors.get(0).getNom());
            System.out.println("////////////////////////////////");
          }
          if (jugadors.size() < 1) {
            System.out.println("////////////////////////////////");
            System.out.println("Tots els jugadors s'han retirat!");
            System.out.println("////////////////////////////////");
          }
            return true;
        } else {
            return false;
        }
    }
}
