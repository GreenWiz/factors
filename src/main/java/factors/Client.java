package factors;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.Scanner;

public class Client extends Thread {

    private Long input;

    Client(Long input){
        this.input = input;
    }

    @Override
    public void run() {

        try {
            //Открытие сокета и передача числа для разложеия
            Socket s = new Socket("localhost", 4001);
            DataOutputStream stream = new DataOutputStream(s.getOutputStream());
            stream.writeLong(input);
            stream.flush();

            //Ожидание ответа от сервера и вывод результата в консоль
            BufferedReader br = new BufferedReader(new InputStreamReader(s.getInputStream()));
            String response = br.readLine();
            System.out.println(response);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        //ожиание пользовательскго ввода
        while (scanner.hasNext()) {
            if (scanner.hasNextLong()){
                Long in = scanner.nextLong();
                new Client(in).start();            //запуск отдельного потока для работы с сервером
            } else {
                System.out.println("Слишком большое число");
                scanner.next();
            }
        }
    }

}
