/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Cliente;
import Hero.Hero;
import Hero.HeroPackage;
import java.util.ArrayList;

/**
 *
 * @author kokoju
 */

public class Jugador {  // Clase del Jugador: guarda su matriz, sus 3 héroes elegidos y una bitácora con información importante
    // Atributos
    private String nombre;  // Nombre del jugador
    private Matriz matriz;  // Matriz que le pertenece al jugador
    private ArrayList<Hero> heroes;  // ArrayList que almacena los héroes del Jugador (podría escalarse si se quisiera)
    private ArrayList<String> bitacora;  // ArrayList que muestra el historial del Jugador (ataques que ha hecho y que ha recibido)
    
    // Constructor

    public Jugador(String nombre, Matriz matriz, ArrayList<Hero> heroes, ArrayList<String> bitacora) {
        this.nombre = nombre;
        this.matriz = matriz;
        this.heroes = heroes;
        this.bitacora = bitacora;
    }
    
    public Hero buscarHeroe(String nombre){
        if (nombre == null) 
            return null;

        for(int i=0;i<this.heroes.size();i++)
            if(this.heroes.get(i).getNombre().equalsIgnoreCase(nombre)){
                return this.heroes.get(i);
            }
        return null;
    }

    // Construye un HeroPackage serializable a partir de un héroe local
    public HeroPackage buildHeroPackage(String nombreHero) {
        Hero heroe = this.buscarHeroe(nombreHero);

        if (heroe == null) 
            return null;

        // Verificacion de superclase para obtener el tipo correcto
        Class<?> cls = heroe.getClass();
        String heroTypeRaw = cls.getSimpleName();
        //Validacion que no deberia suceder pero por si acaso: si la superclase es hero, se usa esta misma
        if (cls.getSuperclass() != null && !cls.getSuperclass().getSimpleName().equals("Hero")) {
            heroTypeRaw = cls.getSuperclass().getSimpleName();
        }

        String heroType = heroTypeRaw.toUpperCase();
        return 
            new HeroPackage(heroType, this.nombre, heroe.getOcupacion(), heroe.getSanidad(), heroe.getFuerza(), heroe.getResistencia(), heroe.isSiguientePotenciado());
    }
    
    public void deshabilitarResistencias() {
        for(Hero heroe : this.heroes) {
            this.matriz.deshabilitarResistenciaHeroe(heroe);
        }
    }
    
    // Métodos
    // Getters
    public String getNombre() {
        return nombre;
    }

    public Matriz getMatriz() {
        return matriz;
    }

    public ArrayList<Hero> getHeroes() {
        return heroes;
    }

    public ArrayList<String> getBitacora() {
        return bitacora;
    }
    
}
