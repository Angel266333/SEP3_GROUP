using System;
using System.Net;
using System.Net.Sockets;
using System.IO;
using System.Text;

namespace web
{
    public class SocketSession
    {
        Socket socket;
        NetworkStream ns;
        IPEndPoint ipe;

        public SocketSession()
        {
            IPAddress ip = Dns.GetHostEntry("localhost").AddressList[0];
            ipe = new IPEndPoint(ip, 8002);
            socket = new Socket(ipe.AddressFamily, SocketType.Stream, ProtocolType.Tcp);
        }

        public void Send(string s)
        {
            byte[] data = Encoding.UTF8.GetBytes(s);
            socket.Connect(ipe);
            socket.Send(data);
            socket.Send(new byte[]{0x00});
        }

        public string Receive()
        {
            byte[] buff = new byte[256];
            int b = 1;
            StringBuilder sb = new StringBuilder();
            string data;
            bool reading = true;
            while(reading)
            {
                Console.WriteLine("aaa");
                b = socket.Receive(buff);
                if(b > 1 && buff[b - 1] == 0x00) {
                    reading = false;
                }
                data = Encoding.UTF8.GetString(buff, 0, b);
                sb.Append(data);
            }
            socket.Close();
            return sb.ToString();
        }
    }
}