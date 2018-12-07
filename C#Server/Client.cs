using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Net.Sockets;
using System.Text;
using System.Threading;
using System.Threading.Tasks;

namespace Client
{
    class Client
    {
        private TcpClient client;
        private NetworkStream ns;
        private byte[] data = new byte[1024];
        private bool connected = false;
        public StreamReader read;
        public Client()
        {
        }

        public void Start()
        {
            Thread t = new Thread(new ThreadStart(Run));
            t.Start();
        }

        private void Run()
        {
            while(connected == false)
            {
                Console.WriteLine( "Trying to connect" );
                try
                {
                    client = new TcpClient("localhost", 8002);
                    ns = client.GetStream();
                    connected = true;
                }
                catch ( SocketException )
                {
                }
                Thread.Sleep( 2000 );
            }

            Console.WriteLine("Connected to server");
            Console.WriteLine("Type a message to the server or exit to stop");

            while (true)
            {
                try
                {
                    read = new StreamReader(ns);
                    data = new byte[1024];
                    Console.WriteLine(read.ReadLine());
                }
                catch ( IOException )
                {
                    break;
                }
            }
            ns.Close();
            client.Close();
        }

        public void SendMsg(string msg)
        {
            if (connected)
            {
                byte[] response = new byte[1024];
                response = Encoding.ASCII.GetBytes(msg);
                ns.Write(response, 0, response.Length);
                byte [] re = new byte [1];
                re[0] = ((byte)'\n');
                ns.Write(re);
            }
        }
    }
}
