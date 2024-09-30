import java.util.ArrayList;
import java.util.Date;
import java.util.List;


class Persona {
    protected String dni;
    protected String nombre;
    protected String direccion;
    protected String telefono;

    public Persona(String dni, String nombre, String direccion, String telefono) {
        this.dni = dni;
        this.nombre = nombre;
        this.direccion = direccion;
        this.telefono = telefono;
    }

    public void mostrarInformacion() {
        System.out.println("Nombre: " + nombre);
        System.out.println("DNI: " + dni);
        System.out.println("Dirección: " + direccion);
        System.out.println("Teléfono: " + telefono);
    }
}


class Cliente extends Persona {
    private Cliente aval;  // Cliente que puede avalar a otro cliente
    private String codigoUnico;
    private List<Reserva> reservas;  // Lista de reservas hechas por el cliente

    public Cliente(String dni, String nombre, String direccion, String telefono, String codigoUnico) {
        super(dni, nombre, direccion, telefono);
        this.codigoUnico = codigoUnico;
        this.reservas = new ArrayList<>(); // Inicialización de la lista de reservas
    }


    public void setAval(Cliente aval) {
        this.aval = aval;
    }


    public void agregarReserva(Reserva reserva) {
        reservas.add(reserva);
    }

    @Override
    public void mostrarInformacion() {
        super.mostrarInformacion();
        System.out.println("Código Único: " + codigoUnico);
        if (aval != null) {
            System.out.println("Avalado por: " + aval.nombre);
        }
        System.out.println("Reservas del cliente: ");
        for (Reserva reserva : reservas) {
            reserva.mostrarInformacion();
        }
    }
}


class Vehiculo {
    protected String matricula;
    protected String marca;

    public Vehiculo(String matricula, String marca) {
        this.matricula = matricula;
        this.marca = marca;
    }

    public void mostrarInformacion() {
        System.out.println("Matrícula: " + matricula);
        System.out.println("Marca: " + marca);
    }
}


class Coche extends Vehiculo {
    private String modelo;
    private String color;
    private String garajeAsignado;

    public Coche(String matricula, String marca, String modelo, String color, String garajeAsignado) {
        super(matricula, marca);  // Llamada al constructor de Vehiculo
        this.modelo = modelo;
        this.color = color;
        this.garajeAsignado = garajeAsignado;
    }

    @Override
    public void mostrarInformacion() {
        super.mostrarInformacion();
        System.out.println("Modelo: " + modelo);
        System.out.println("Color: " + color);
        System.out.println("Garaje asignado: " + garajeAsignado);
    }
}


class Reserva {
    private Cliente cliente;
    private List<Coche> cochesReservados;
    private Date fechaInicio;
    private Date fechaFin;
    private double precioTotalAlquiler;
    private double litrosGasolina;
    private boolean cochesEntregados;

    public Reserva(Cliente cliente, List<Coche> cochesReservados, Date fechaInicio, Date fechaFin, double precioTotalAlquiler, double litrosGasolina) {
        this.cliente = cliente;
        this.cochesReservados = cochesReservados;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.precioTotalAlquiler = precioTotalAlquiler;
        this.litrosGasolina = litrosGasolina;
        this.cochesEntregados = false;
    }


    public void entregarCoches() {
        cochesEntregados = true;
    }


    public void mostrarInformacion() {
        System.out.println("Reserva realizada por el cliente: ");
        cliente.mostrarInformacion();
        System.out.println("Fecha de inicio: " + fechaInicio);
        System.out.println("Fecha de fin: " + fechaFin);
        System.out.println("Precio total del alquiler: " + precioTotalAlquiler);
        System.out.println("Litros de gasolina: " + litrosGasolina);
        System.out.println("Coches reservados:");
        for (Coche coche : cochesReservados) {
            coche.mostrarInformacion();
        }
        System.out.println("¿Coches entregados?: " + (cochesEntregados ? "Sí" : "No"));
    }
}


class Agencia {
    private String nombre;
    private List<Reserva> reservas;

    public Agencia(String nombre) {
        this.nombre = nombre;
        this.reservas = new ArrayList<>();
    }


    public void agregarReserva(Reserva reserva) {
        reservas.add(reserva);
    }


    public void mostrarReservas() {
        if (reservas.isEmpty()) {
            System.out.println("No hay reservas en la agencia " + nombre);
        } else {
            System.out.println("Reservas en la agencia " + nombre + ":");
            for (Reserva reserva : reservas) {
                reserva.mostrarInformacion();
                System.out.println("-------------------------------");
            }
        }
    }
}


public class Main {
    public static void main(String[] args) {
        // Crear clientes
        Cliente cliente1 = new Cliente("12345678X", "Juan Perez", "Calle Falsa 123", "555-1234", "001");
        Cliente cliente2 = new Cliente("87654321Y", "Maria Lopez", "Avenida Siempreviva 456", "555-5678", "002");
        cliente1.setAval(cliente2);  // Maria avala a Juan


        Coche coche1 = new Coche("ABC123", "Tesla", "Model S", "Negro", "Garaje A");
        Coche coche2 = new Coche("DEF456", "BMW", "Serie 3", "Blanco", "Garaje B");


        List<Coche> cochesReservados = new ArrayList<>();
        cochesReservados.add(coche1);
        cochesReservados.add(coche2);


        Reserva reserva1 = new Reserva(cliente1, cochesReservados, new Date(), new Date(), 1000.0, 80.0);
        reserva1.entregarCoches();


        Agencia agencia = new Agencia("RentaCar");
        agencia.agregarReserva(reserva1);


        agencia.mostrarReservas();
    }
}
