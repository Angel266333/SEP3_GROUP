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
            byte[] buff = new byte[32];
            int b = 1;
            MemoryStream stream = new MemoryStream();
            bool reading = true;
            while(reading)
            {
                b = socket.Receive(buff);
                if(b > 1 && buff[b - 1] == 0x00) {
                    reading = false;
                }
                stream.Write(buff, 0, b);
            }
            socket.Close();
            int l = (int) stream.Length;
            return Encoding.UTF8.GetString(stream.ToArray(), 0, l);
        }
    }
}