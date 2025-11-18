# OceanicWars

## Comandos Disponibles

El juego soporta los siguientes comandos de consola:

### Comandos de Jugador

1. **name**  
   Modo de uso: `name <NombreJugador>`  
   Descripción: Registra tu nombre en el juego y notifica a todos los demás jugadores que te has conectado

2. **ready**  
   Modo de uso: `ready`  
   Descripción: Indica que estás listo para comenzar el juego

### Comandos de Comunicación

3. **message**  
   Modo de uso: `message <TextoMensaje>`  
   Descripción: Transmite un mensaje a todos los jugadores conectados

4. **private_message**  
   Modo de uso: `private_message <NombreJugador> <TextoMensaje>`  
   Descripción: Envía un mensaje privado que solo el jugador especificado puede ver

### Comandos de Juego

5. **attack**  
   Modo de uso: `attack <NombreJugadorObjetivo> <NombreHeroeApariencia> <TipoAtaque> [Parámetros]`  
   Descripción: Ejecuta un ataque específico del héroe indicado contra el jugador objetivo  
   Consume turno: Sí

   **Importante**: Debes usar el nombre de apariencia del héroe (el nombre que elegiste al crearlo), no el arquetipo.

   #### Ataques por Héroe:

   **Thunders Under The Sea**

   - `ATTACK <jugador> <heroe> THUNDERRAIN`  
     Ataque de lluvia de rayos que afecta múltiples casillas aleatorias

   - `ATTACK <jugador> <heroe> POSEIDONTHUNDERS`  
     Rayos de Poseidón con mayor poder destructivo

   - `ATTACK <jugador> <heroe> EELATTACK`  
     Ataque de anguilas eléctricas

   **Fish Telepathy**

   - `ATTACK <jugador> <heroe> CARDUMEN <x1> <y1> <x2> <y2> ... <xN> <yN>`  
     Ataque de cardumen en múltiples coordenadas (mínimo 1 par de coordenadas)

   - `ATTACK <jugador> <heroe> SHARKATTACK`  
     Ataque de tiburones de alto impacto

   - `ATTACK <jugador> <heroe> PULP <x1> <y1> <x2> <y2> ... <xN> <yN>`  
     Ataque de pulpos en múltiples coordenadas (mínimo 1 par de coordenadas)

   **Release The Kraken**

   - `ATTACK <jugador> <heroe> TENTACULOS <x1> <y1> <x2> <y2> ... <xN> <yN>`  
     Ataque con tentáculos del Kraken (mínimo 1 par de coordenadas)

   - `ATTACK <jugador> <heroe> KRAKENBREATH <x> <y> <DIRECCION>`  
     Aliento del Kraken en línea (DIRECCION: ARRIBA, ABAJO, IZQUIERDA o DERECHA)

   - `ATTACK <jugador> <heroe> RELEASETHEKRAKEN <x> <y>`  
     Libera al Kraken en las coordenadas especificadas

   **Waves Control**

   - `ATTACK <jugador> <heroe> SWIRLRAISING <x> <y>`  
     Levanta un remolino en las coordenadas dadas

   - `ATTACK <jugador> <heroe> SENDHUMANGARBAGE <x> <y>`  
     Envía basura humana a las coordenadas especificadas

   - `ATTACK <jugador> <heroe> RADIOACTIVERUSH`  
     Ataque radioactivo masivo sin coordenadas específicas

   **The Trident (Poseidon Trident)**

   - `ATTACK <jugador> <heroe> THREELINES <x1> <y1> <x2> <y2> <x3> <y3> ...`  
     Ataque en tres líneas (mínimo 3 pares de coordenadas)

   - `ATTACK <jugador> <heroe> THREENUMBERS <num1> <num2> <num3>`  
     Ataque basado en tres números

   - `ATTACK <jugador> <heroe> CONTROLTHEKRAKEN <x> <y>`  
     Controla al Kraken en las coordenadas especificadas

   **Undersea Volcanoes (Undersea Fire)**

   - `ATTACK <jugador> <heroe> VOLCANORAISING <x> <y>`  
     Levanta un volcán en las coordenadas dadas

   - `ATTACK <jugador> <heroe> VOLCANOEXPLOSION <x> <y>`  
     Hace explotar un volcán en las coordenadas especificadas

   - `ATTACK <jugador> <heroe> TERMALRUSH <x> <y>`  
     Ataque de corriente termal en las coordenadas dadas

   **Ejemplos de uso**:

   ```
   ATTACK sol Omniman KRAKENBREATH 6 7 ARRIBA
   ATTACK luna Goku THUNDERRAIN
   ATTACK andre Zanka VOLCANOEXPLOSION 5 5
   ATTACK player1 Luffy TENTACULOS 3 4 5 6 7 8
   ```

6. **boost**  
   Modo de uso: `boost <NombreHeroe> <HEAL/PROTECT/STRENGHTEN>`  
   Descripción: Mejora a un héroe específico con una de las opciones disponibles:

   - HEAL: Restaura la salud del héroe
   - PROTECT: Aumenta la resistencia del héroe
   - STRENGHTEN: Aumenta la fuerza del héroe  
     Consume turno: Sí

7. **next**  
   Modo de uso: `next`  
   Descripción: Pasa al siguiente turno

8. **giveup**  
   Modo de uso: `giveup`  
   Descripción: Termina tu participación en el juego actual y notifica a todos los jugadores

### Notas

- Los comandos no distinguen entre mayúsculas y minúsculas
- Los comandos que consumen turno solo pueden usarse durante tu turno activo
