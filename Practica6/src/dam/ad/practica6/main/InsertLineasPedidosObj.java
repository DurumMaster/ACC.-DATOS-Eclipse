package dam.ad.practica6.main;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import dam.ad.practica6.javabeans.LineaPedido;
import dam.ad.practica6.javabeans.Producto;

public class InsertLineasPedidosObj {

	public static void main(String[] args) {

		// Inicializar objetos Producto

		Producto[] productos = { new Producto(1, "Destornillador", 12.3), new Producto(2, "Martillo", 15.5),
				new Producto(3, "Taladro", 99.9), new Producto(4, "Sierra", 45.0),
				new Producto(5, "Llave Inglesa", 20.0) };

		// Inicializar un array de 5 objetos LinePedido
		LineaPedido[] lineasPedido = { new LineaPedido(1, productos[0], 3, productos[0].getPrecio() * 3, "2024-10-01"),
				new LineaPedido(2, productos[1], 2, productos[1].getPrecio() * 2, "2024-10-02"),
				new LineaPedido(3, productos[2], 1, productos[2].getPrecio() * 1, "2024-10-03"),
				new LineaPedido(4, productos[3], 4, productos[3].getPrecio() * 4, "2024-10-04"),
				new LineaPedido(5, productos[4], 5, productos[4].getPrecio() * 5, "2024-10-05") };
		
		// Escribir objetos en el archivo binario
		
		try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("Ficheros/lineasPedidos.dat"))) {
			for (LineaPedido lineaPedido : lineasPedido) {
				oos.writeObject(lineaPedido);
			}
			System.out.println("Se han escrito los pedidos con éxito.");
		} catch (FileNotFoundException e) {
			System.out.println("No se ha encontrado el archivo.");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("Ha ocurrido un error al escribir los pedidos.");
			e.printStackTrace();
		}
		
		// Leer los objetos del archivo binario
		
		try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream("Ficheros/lineasPedidos.dat"))) {
			
			boolean finalArch = false;
			LineaPedido linea;
			
			System.out.println("\n* * * PEDIDOS * * *");
			while ( !finalArch) {
				try {
					linea = (LineaPedido) ois.readObject();
					System.out.println(linea);
				} catch (Exception e) {
					finalArch = true;
				}
				
			}
		
		} catch (FileNotFoundException e1) {
			System.out.println("No se ha encontrado el archivo.");
			e1.printStackTrace();
		} catch (IOException e1) {
			System.out.println("Ha ocurrido un error al leer los pedidos.");
			e1.printStackTrace();
		}
	}

}
