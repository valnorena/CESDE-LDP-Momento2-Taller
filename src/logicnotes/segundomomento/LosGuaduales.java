package logicnotes.segundomomento;
import java.util.Scanner;

public class LosGuaduales {

    //Valentina Noreña Gomez y Mariana Torres Mercado

    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        double[] consumos= new double[6];

        int opcion = 0;

        do {

            mostrarMenu();

            opcion = sc.nextInt();

            switch (opcion) {
                case 1:
                    registrarConsumo(consumos);
                    break;
                case 2:
                    consultarFactura(consumos);
                    break;
                case 3:
                    generarReporte(consumos);
                    break;
                case 4:
                    System.out.println("Gracias ¡Hasta luego!");
                    break;
                default:
                    System.out.println("Opción inválida");
            }
        } while (opcion != 4);
    }


    static void mostrarMenu(){
        System.out.println("============================================");
        System.out.println("===== LOS GUADUALES - CONTROL DE AGUA =====");
        System.out.println("1. Registrar consumo de un apartamento");
        System.out.println("2. Consultar factura de un apartamento");
        System.out.println("3. Ver reporte general del conjunto");
        System.out.println("4. Salir");
        System.out.println("============================================");
        System.out.println("Ingrese su opcion: ");
    }

    static void registrarConsumo(double[] consumos){

        System.out.println("Ingrese el numero del apartamento: ");
        int apartamento = sc.nextInt();

        while (apartamento < 1 || apartamento > 6){
            System.out.println("Apartamento inválido, debe ser de 1 a 6.");
            apartamento = sc.nextInt();
        }

        System.out.println("Ingrese el cantidad de agua en metros cubicos (m³): ");
        double consumo = sc.nextDouble();

        while  (consumo <= 0){
            System.out.println("El consumo debe ser mayor que 0.");
            consumo = sc.nextDouble();
        }

        consumos[apartamento-1] = consumo;
    }

    static void consultarFactura(double[] consumos){
        System.out.println("Ingrese el numero del apartamento: ");
        int apartamento = sc.nextInt();

        while (apartamento < 1 || apartamento > 6){
            System.out.println("Apartamento inválido, debe ser de 1 a 6.");
            apartamento = sc.nextInt();
        }

        double consumo = consumos[apartamento-1];

        if (consumo == 0){
            System.out.println("El apartamento " + apartamento + " no tiene consumo registrado.");
        } else {
            imprimirFactura(apartamento, consumo);
        }
    }

    static void imprimirFactura(int apartamento, double consumo){

        double tarifa = 0;
        double recargo = 0;

        if (consumo <= 10){
            tarifa = 3000;
        } else if (consumo <= 20){
            tarifa = 4500;
        } else {
            tarifa = 6000;
        }

        double valorConsumo = consumo *  tarifa;
        double subtotal = valorConsumo + 12000;

        if (consumo > 25){
            recargo = subtotal * 0.1;
            System.out.println("ALERTA: consumo excesivo");
        }

        double total = subtotal + recargo;

        System.out.println("Apartamento: " + apartamento);
        System.out.println("Consumo en m³: " + consumo);
        System.out.println("Tarifa: " + tarifa);
        System.out.println("Valor del consumo: " + valorConsumo);
        System.out.println("Cargo fijo: 12000");
        System.out.println("Recargo: " + recargo);
        System.out.println("Total: " + total);
    }

    static void generarReporte(double[] consumos){
        int i = 0;
        double total = 0;
        int registrados = 0;
        int sinRegistrados = 0;
        int excesivos = 0;
        double mayor = 0;
        int aptoMayor = 0;

        while (i < consumos.length){
            double actual = consumos[i];

            if (actual == 0){
                sinRegistrados++;
            } else {
                total = total + actual;
                registrados++;

                if (actual > 25){
                    excesivos++;
                }

                if (actual > mayor){
                    mayor = actual;
                    aptoMayor = i + 1;
                }
            }

            i++;
        }
        if (registrados == 0) {
            System.out.println("Aún no hay consumos registrados.");
        } else {
            double promedio = total / registrados;
            System.out.println("===== REPORTE GENERAL =====");
            System.out.println("Consumo total del conjunto: " + total + " m³");
            System.out.println("Consumo promedio: " + promedio + " m³");
            System.out.println("Mayor consumo: apartamento " + aptoMayor + " → " + mayor + " m³");
            System.out.println("Apartamentos sin registro: " + sinRegistrados);
            System.out.println("Apartamentos con consumo excesivo: " + excesivos);
        }
    }
}
