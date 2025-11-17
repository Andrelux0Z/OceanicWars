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
   Modo de uso: `attack <NombreJugador> <X> <Y>`  
   Descripción: Ejecuta un comando de ataque dirigido al jugador especificado en las coordenadas dadas  
   Consume turno: Sí

6. **boost**  
   Modo de uso: `boost <NombreHeroe> <HEAL/PROTECT/STRENGTHEN>`  
   Descripción: Mejora a un héroe específico con una de las opciones disponibles:

   - HEAL: Restaura la salud del héroe
   - PROTECT: Aumenta la resistencia del héroe
   - STRENGTHEN: Aumenta la fuerza del héroe  
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
