package dam.ad.practica2.main;

import java.io.BufferedInputStream;
import java.io.FileNotFoundException;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

import dam.ad.practica2.javabeans.Cliente;

public class MostrarClientes {

public static void main(String[] args) {
		
        
        try ( ObjectInputStream ois = new ObjectInputStream(new BufferedInputStream(new FileInputStream("clientes.dat")))) {
        	
        	while(true) {
        		try {
        			Cliente cliente = (Cliente) ois.readObject();
        			System.out.println(cliente);
        		} catch (IOException | ClassNotFoundException e) {
					break;
				}
        	}
        	
        	System.out.println("\n * * Todos los clientes han sido leídos del archivo 'clientes.dat' * *");
        	
        } catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
