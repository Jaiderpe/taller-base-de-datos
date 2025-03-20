package com.hexagonaljava;


import java.util.Scanner;
import com.hexagonaljava.infrastructure.persistence.Equipo;
import com.hexagonaljava.infrastructure.database.EquipoDAO;
import com.hexagonaljava.infrastructure.persistence.Entrenador;
import com.hexagonaljava.infrastructure.database.EntrenadorDAO;
import com.hexagonaljava.infrastructure.persistence.Jugador;
import com.hexagonaljava.infrastructure.database.JugadorDAO;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper; // Importar la clase ObjectMapper
import java.io.InputStream;    


public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        EquipoDAO equipoDAO = new EquipoDAO();
        JugadorDAO jugadorDAO = new JugadorDAO();
        EntrenadorDAO entrenadorDAO = new EntrenadorDAO();

        while (true) {
            mostrarMenu();
            try {
                System.out.print("Selecciona una opción: ");
                int opcion = scanner.nextInt();
                scanner.nextLine(); // Limpiar buffer

                switch (opcion) {
                    case 1:
                        registrarEquipo(scanner, equipoDAO);
                        break;
                    case 2:
                        actualizarEquipo(scanner, equipoDAO);
                        break;
                    case 3:
                        eliminarEquipo(scanner, equipoDAO);
                        break;
                    case 4:
                        listarEquipos(equipoDAO);
                        break;
                    case 5:
                        registrarJugador(scanner, jugadorDAO);
                        break;
                    case 6:
                        actualizarJugador(scanner, jugadorDAO);
                        break;
                    case 7:
                        eliminarJugador(scanner, jugadorDAO);
                        break;
                    case 8:
                        listarJugadores(jugadorDAO);
                        break;
                    case 9:
                        registrarEntrenador(scanner, entrenadorDAO);
                        break;
                    case 10:
                        actualizarEntrenador(scanner, entrenadorDAO);
                        break;
                    case 11:
                        eliminarEntrenador(scanner, entrenadorDAO);
                        break;
                    case 12:
                        listarEntrenadores(entrenadorDAO);
                        break;
                    case 13:
                        System.out.println("Saliendo...");
                        scanner.close();
                        return;
                    case 14:
                        insertDataFromJSON(equipoDAO, jugadorDAO, entrenadorDAO);
                        break;
                    default:
                        System.out.println("Opción inválida.");
                }
            } catch (Exception e) {
                System.out.println("Error: Ingresa un número válido.");
                scanner.nextLine(); // Limpiar buffer
            }
        }
    }

    private static void mostrarMenu() {
        System.out.println("Menú:");
        System.out.println("1. Registrar equipo");
        System.out.println("2. Actualizar equipo");
        System.out.println("3. Eliminar equipo");
        System.out.println("4. Listar equipos");
        System.out.println("5. Registrar jugador");
        System.out.println("6. Actualizar jugador");
        System.out.println("7. Eliminar jugador");
        System.out.println("8. Listar jugadores");
        System.out.println("9. Registrar entrenador");
        System.out.println("10. Actualizar entrenador");
        System.out.println("11. Eliminar entrenador");
        System.out.println("12. Listar entrenadores");
        System.out.println("13. Salir");
        System.out.println("14. Insertar datos desde JSON");
    }

    private static void registrarEquipo(Scanner scanner, EquipoDAO equipoDAO) {
        try {
            System.out.print("Nombre del equipo: ");
            String name = scanner.nextLine();
            System.out.print("Año de fundación: ");
            int yearfoundation = Integer.parseInt(scanner.nextLine());
            System.out.print("ID del entrenador: ");
            int coachId = Integer.parseInt(scanner.nextLine());

            Equipo equipo = new Equipo();
            equipo.setName(name);
            equipo.setYearfoundation(yearfoundation);
            equipo.setCoachId(coachId);
            equipoDAO.addEquipo(equipo);

            System.out.println("Equipo registrado exitosamente.");
        } catch (NumberFormatException e) {
            System.out.println("Error: Ingresa valores numéricos válidos para el año de fundación y el ID del entrenador.");
        }
    }

    private static void actualizarEquipo(Scanner scanner, EquipoDAO equipoDAO) {
        try {
            System.out.print("ID del equipo a actualizar: ");
            int id = Integer.parseInt(scanner.nextLine());
            System.out.print("Nuevo nombre del equipo: ");
            String name = scanner.nextLine();
            System.out.print("Nuevo año de fundación: ");
            int yearfoundation = Integer.parseInt(scanner.nextLine());
            System.out.print("Nuevo ID del entrenador: ");
            int coachId = Integer.parseInt(scanner.nextLine());

            Equipo equipo = new Equipo();
            equipo.setId(id);
            equipo.setName(name);
            equipo.setYearfoundation(yearfoundation);
            equipo.setCoachId(coachId);
            equipoDAO.updateEquipo(equipo);

            System.out.println("Equipo actualizado exitosamente.");
        } catch (NumberFormatException e) {
            System.out.println("Error: Ingresa valores numéricos válidos para el ID, año de fundación y el ID del entrenador.");
        }
    }

    private static void eliminarEquipo(Scanner scanner, EquipoDAO equipoDAO) {
        try {
            System.out.print("ID del equipo a eliminar: ");
            int id = Integer.parseInt(scanner.nextLine());
            equipoDAO.deleteEquipo(id);
            System.out.println("Equipo eliminado exitosamente.");
        } catch (NumberFormatException e) {
            System.out.println("Error: Ingresa un valor numérico válido para el ID del equipo.");
        }
    }

    private static void listarEquipos(EquipoDAO equipoDAO) {
        System.out.println("Lista de equipos:");
        for (Equipo equipo : equipoDAO.getAllEquipos()) {
            System.out.println(equipo);
        }
    }

    private static void registrarJugador(Scanner scanner, JugadorDAO jugadorDAO) {
        try {
            System.out.print("ID del equipo: ");
            int equipoId = Integer.parseInt(scanner.nextLine());
            System.out.print("Dorsal: ");
            int dorsal = Integer.parseInt(scanner.nextLine());
            System.out.print("Nombre del jugador: ");
            String name = scanner.nextLine();
            System.out.print("Nacionalidad: ");
            String nationality = scanner.nextLine();
            System.out.print("Edad: ");
            int age = Integer.parseInt(scanner.nextLine());
            double height = leerAltura(scanner); // Método para validar altura
            System.out.print("Peso (kg): ");
            int weight = Integer.parseInt(scanner.nextLine());
            System.out.print("Posición: ");
            String position = scanner.nextLine();

            Jugador jugador = new Jugador();
            jugador.setEquipoId(equipoId);
            jugador.setDorsal(dorsal);
            jugador.setName(name);
            jugador.setNationality(nationality);
            jugador.setAge(age);
            jugador.setHeight((int) height); // Convertir altura a entero si es necesario
            jugador.setWeight(weight);
            jugador.setPosition(position);

            jugadorDAO.addJugador(jugador);
            System.out.println("Jugador registrado exitosamente.");
        } catch (NumberFormatException e) {
            System.out.println("Error: Ingresa valores numéricos válidos para el dorsal, edad, altura y peso.");
        }
    }

    private static double leerAltura(Scanner scanner) {
        double altura = 0;
        boolean entradaValida = false;

        while (!entradaValida) {
            try {
                System.out.print("Altura (cm): ");
                altura = Double.parseDouble(scanner.nextLine().replace(',', '.')); // Reemplazar coma por punto
                entradaValida = true;
            } catch (NumberFormatException e) {
                System.out.println("Error: Ingresa un número válido usando '.' como separador decimal.");
            }
        }

        return altura;
    }

    private static void actualizarJugador(Scanner scanner, JugadorDAO jugadorDAO) {
        try {
            System.out.print("ID del jugador a actualizar: ");
            int id = Integer.parseInt(scanner.nextLine());
            System.out.print("Nuevo ID del equipo: ");
            int equipoId = Integer.parseInt(scanner.nextLine());
            System.out.print("Nuevo dorsal: ");
            int dorsal = Integer.parseInt(scanner.nextLine());
            System.out.print("Nuevo nombre del jugador: ");
            String name = scanner.nextLine();
            System.out.print("Nueva nacionalidad: ");
            String nationality = scanner.nextLine();
            System.out.print("Nueva edad: ");
            int age = Integer.parseInt(scanner.nextLine());
            double height = leerAltura(scanner); // Método para validar altura
            System.out.print("Nuevo peso (kg): ");
            int weight = Integer.parseInt(scanner.nextLine());
            System.out.print("Nueva posición: ");
            String position = scanner.nextLine();

            Jugador jugador = new Jugador();
            jugador.setId(id);
            jugador.setEquipoId(equipoId);
            jugador.setDorsal(dorsal);
            jugador.setName(name);
            jugador.setNationality(nationality);
            jugador.setAge(age);
            jugador.setHeight((int) height); // Convertir altura a entero si es necesario
            jugador.setWeight(weight);
            jugador.setPosition(position);

            jugadorDAO.updateJugador(jugador);
            System.out.println("Jugador actualizado exitosamente.");
        } catch (NumberFormatException e) {
            System.out.println("Error: Ingresa valores numéricos válidos para el ID, dorsal, edad, altura y peso.");
        }
    }

    private static void eliminarJugador(Scanner scanner, JugadorDAO jugadorDAO) {
        try {
            System.out.print("ID del jugador a eliminar: ");
            int id = Integer.parseInt(scanner.nextLine());
            jugadorDAO.deleteJugador(id);
            System.out.println("Jugador eliminado exitosamente.");
        } catch (NumberFormatException e) {
            System.out.println("Error: Ingresa un valor numérico válido para el ID del jugador.");
        }
    }

    private static void listarJugadores(JugadorDAO jugadorDAO) {
        System.out.println("Lista de jugadores:");
        for (Jugador jugador : jugadorDAO.getAllJugadores()) {
            System.out.println(jugador);
        }
    }

    private static void registrarEntrenador(Scanner scanner, EntrenadorDAO entrenadorDAO) {
        try {
            System.out.print("Nombre del entrenador: ");
            String name = scanner.nextLine();
            System.out.print("Nacionalidad: ");
            String nationality = scanner.nextLine();
            System.out.print("Edad: ");
            int age = Integer.parseInt(scanner.nextLine());

            Entrenador entrenador = new Entrenador();
            entrenador.setName(name);
            entrenador.setNationality(nationality);
            entrenador.setAge(age);
            entrenadorDAO.addEntrenador(entrenador);

            System.out.println("Entrenador registrado exitosamente.");
        } catch (NumberFormatException e) {
            System.out.println("Error: Ingresa un valor numérico válido para la edad.");
        }
    }

    private static void actualizarEntrenador(Scanner scanner, EntrenadorDAO entrenadorDAO) {
        try {
            System.out.print("ID del entrenador a actualizar: ");
            int id = Integer.parseInt(scanner.nextLine());
            System.out.print("Nuevo nombre del entrenador: ");
            String name = scanner.nextLine();
            System.out.print("Nueva nacionalidad: ");
            String nationality = scanner.nextLine();
            System.out.print("Nueva edad: ");
            int age = Integer.parseInt(scanner.nextLine());

            Entrenador entrenador = new Entrenador();
            entrenador.setId(id);
            entrenador.setName(name);
            entrenador.setNationality(nationality);
            entrenador.setAge(age);
            entrenadorDAO.updateEntrenador(entrenador);

            System.out.println("Entrenador actualizado exitosamente.");
        } catch (NumberFormatException e) {
            System.out.println("Error: Ingresa un valor numérico válido para el ID y la edad.");
        }
    }

    private static void eliminarEntrenador(Scanner scanner, EntrenadorDAO entrenadorDAO) {
        try {
            System.out.print("ID del entrenador a eliminar: ");
            int id = Integer.parseInt(scanner.nextLine());
            entrenadorDAO.deleteEntrenador(id);
            System.out.println("Entrenador eliminado exitosamente.");
        } catch (NumberFormatException e) {
            System.out.println("Error: Ingresa un valor numérico válido para el ID del entrenador.");
        }
    }

    private static void listarEntrenadores(EntrenadorDAO entrenadorDAO) {
        System.out.println("Lista de entrenadores:");
        for (Entrenador entrenador : entrenadorDAO.getAllEntrenadores()) {
            System.out.println(entrenador);
        }
    }

    private static void insertDataFromJSON(EquipoDAO equipoDAO, JugadorDAO jugadorDAO, EntrenadorDAO entrenadorDAO) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            InputStream inputStream = Main.class.getClassLoader().getResourceAsStream("Equipos.json");

            if (inputStream == null) {
                System.err.println("El archivo Equipos.json no se encontró.");
                return;
            }

            JsonNode rootNode = objectMapper.readTree(inputStream);
            JsonNode equiposNode = rootNode.get("equipos");

            if (equiposNode == null || !equiposNode.isArray()) {
                System.err.println("El nodo 'equipos' no existe o no es un array.");
                return;
            }

            for (JsonNode equipoNode : equiposNode) {
                // Insertar entrenador
                String coachName = equipoNode.get("coach").asText();
                Entrenador entrenador = new Entrenador();
                entrenador.setName(coachName);
                entrenador.setNationality("Desconocida");
                entrenador.setAge(0);
                entrenadorDAO.addEntrenador(entrenador);

                // Crear y guardar el equipo
                Equipo equipo = new Equipo();
                equipo.setName(equipoNode.get("name").asText());
                equipo.setYearfoundation(Integer.parseInt(equipoNode.get("yearfoundation").asText()));
                equipo.setCoachId(entrenador.getId());
                equipoDAO.addEquipo(equipo);

                // Insertar jugadores
                JsonNode playersNode = equipoNode.get("players");
                if (playersNode == null || !playersNode.isArray()) {
                    System.err.println("El nodo 'players' no existe o no es un array para el equipo: " + equipo.getName());
                    continue;
                }

                for (JsonNode jugadorNode : playersNode) {
                    Jugador jugador = new Jugador();
                    jugador.setEquipoId(equipo.getId());
                    jugador.setDorsal(jugadorNode.get("dorsal").asInt());
                    jugador.setName(jugadorNode.get("name").asText());
                    jugador.setNationality(jugadorNode.get("nationality").asText());
                    jugador.setAge(jugadorNode.get("age").asInt());
                    jugador.setHeight(jugadorNode.get("height").asInt());
                    jugador.setWeight(jugadorNode.get("weight").asInt());
                    jugador.setPosition(jugadorNode.get("position").asText());
                    jugadorDAO.addJugador(jugador);
                }
            }

            System.out.println("Datos insertados correctamente desde el archivo JSON.");
        } catch (Exception e) {
            System.err.println("Error al insertar datos desde el archivo JSON: " + e.getMessage());
            e.printStackTrace();
        }
    }
}