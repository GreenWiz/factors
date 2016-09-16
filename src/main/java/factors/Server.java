package factors;

import java.io.DataInputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Server extends Thread {
    private Socket s;

    public Server(Socket s) {
        this.s = s;
        start();
    }

    public static void main(String args[]) {
        try {
            ServerSocket server = new ServerSocket(4001);
            System.out.println("server is started");

            // слушаем порт
            while (true) {
                // ожидание нового подключения, после чего запуск обработки клиента
                // в отдельном потоке
                new Server(server.accept());
            }
        } catch (Exception e) {
            System.out.println("init error: " + e);
        }
    }

    public void run() {
        try {
            //чтение данных из сокета
            DataInputStream dataInputStream = new DataInputStream(s.getInputStream());
            long l = dataInputStream.readLong();

            //получение и форматировние результата
            List<Long> result = factor(l);
            String strRes = Arrays.toString(result.toArray());
            String response = String.format("Множители для числа %d : %s\n",l,strRes);
            //отправка результата клиенту
            OutputStream os = s.getOutputStream();
            os.write(response.getBytes());
            os.flush();
            //закрытие соединения
            s.close();
        } catch (Exception e) {
            System.out.println("init error: " + e);
        }
    }

    //функция разложения числа на простые множители
    public static List<Long> factor(long number){
        List<Long> result = new ArrayList<>();

        Long d = 2L;

        while (d * d <= number){
            if (number % d == 0){
                result.add(d);
                number /= d;
            }else{
                d += 1;
            }
        }
        if (number > 1){
            result.add(number);
        }
        return result;
    }


}
